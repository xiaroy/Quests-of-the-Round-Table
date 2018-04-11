'use strict';

var usernamePage = document.querySelector('#username-page');
var gamePage = document.querySelector('#game-page');

var usernameForm = document.querySelector('#usernameForm');

var divSelfFieldArea = document.querySelector('.DivPlayArea');
var divOppArea = document.querySelector('.DivOppArea');

var txtSelfName = document.querySelector('.txtName');
var txtSelfHand = document.querySelector('.txtHand');
var txtSelfRank = document.querySelector('.txtRank');
var txtSelfShield = document.querySelector('.txtShield');
var divSelfBoard = document.querySelector('.DivFieldArea');
var handArea = document.querySelector('.DivHandArea');

var txtOppName = null;
var txtOppHand = null;
var txtOppRank = null;
var txtOppShield = null;
var divOppBoard = null;

var divCurStoryArea = document.querySelector('.DivCurStoryCard');
var divStageArea = document.querySelector('.DivStage');
var btnNextStage = document.querySelector('.buttonNext');
var btnPrevStage = document.querySelector('.buttonPrev');
var txtCurStage = document.querySelector('.stageDisplay');
var curQuestStageCards = null;
var curDisplayStage = 0;

var txtInputArea = document.querySelector('.txtInputMsg');
var divInputBtns = document.querySelector('.DivInputButtons');

var messageForm = document.querySelector('#messageForm');
var messageInput = document.querySelector('#message');
var messageArea = document.querySelector('#messageArea');
var connectingElement = document.querySelector('.connecting');

var stompClient = null;
var username = null;

var colors = [
    '#2196F3', '#32c787', '#00BCD4', '#ff5652',
    '#ffc107', '#ff85af', '#FF9800', '#39bbb0'
];

function connect(event) {
    username = document.querySelector('#name').value.trim();

    if(username) {
        usernamePage.classList.add('hidden');
        gamePage.classList.remove('hidden');

        var socket = new SockJS('/ws');
        stompClient = Stomp.over(socket);

        stompClient.connect({}, onConnected, onError);
    }
    event.preventDefault();
}


function onConnected() {
    // Subscribe to the Public Topic
    stompClient.subscribe('/topic/public', onMessageReceived);
	stompClient.subscribe('/topic/' + username, onMessageReceived);
	stompClient.subscribe('/board/' + username, onBoardRecieved);
	stompClient.subscribe('/input/' + username, onInputRecieved);

    // Tell your username to the server
    stompClient.send("/app/chat.addUser",
        {},
        JSON.stringify({sender: username, type: 'JOIN'})
    )

    connectingElement.classList.add('hidden');
}


function onError(error) {
    connectingElement.textContent = 'Could not connect to WebSocket server. Please refresh this page to try again!';
    connectingElement.style.color = 'red';
}


function sendMessage(event) {
    var messageContent = messageInput.value.trim();
    if(messageContent && stompClient) {
        var chatMessage = {
            sender: username,
            content: messageInput.value,
            type: 'CHAT'
        };
        stompClient.send("/app/chat.sendMessage", {}, JSON.stringify(chatMessage));
        messageInput.value = '';
    }
    event.preventDefault();
}


function onMessageReceived(payload) {
    var message = JSON.parse(payload.body);

    var messageElement = document.createElement('li');

    if(message.type === 'JOIN') {
        messageElement.classList.add('event-message');
        message.content = message.sender + ' joined!';
    } else if (message.type === 'LEAVE') {
        messageElement.classList.add('event-message');
        message.content = message.sender + ' left!';
	} else if (message.type == 'GAME'){
		messageElement.classList.add('event-message');
        //message.content = message.content;
    } else {
        messageElement.classList.add('chat-message');

        var avatarElement = document.createElement('i');
		//avatarElement.src = "img/" + message.cards[0].name;
		//avatarElement.style = "width:50px;height:80px;"
        var avatarText = document.createTextNode(message.sender[0]);
        avatarElement.appendChild(avatarText);
        avatarElement.style['background-color'] = getAvatarColor(message.sender);

        messageElement.appendChild(avatarElement);

        var usernameElement = document.createElement('span');
        var usernameText = document.createTextNode(message.sender);
        usernameElement.appendChild(usernameText);
        messageElement.appendChild(usernameElement);
    }

    var textElement = document.createElement('p');
    var messageText = document.createTextNode(message.content);
    textElement.appendChild(messageText);

    messageElement.appendChild(textElement);

    messageArea.appendChild(messageElement);
    messageArea.scrollTop = messageArea.scrollHeight;
}

function onBoardRecieved(payload){
	var message = JSON.parse(payload.body);
	
	if (divOppBoard == null || divOppBoard.length != message.opponents.length){
		if (divOppBoard != null)
			removeAllChildren(divOppArea);
		
		txtOppName = [];
		txtOppHand = [];
		txtOppRank = [];
		txtOppShield = [];
		divOppBoard = [];
		
		for (var i = 0; i < message.opponents.length; i++){
			var newFieldArea = divSelfFieldArea.cloneNode(true);
			txtOppName.push(newFieldArea.querySelector('.txtName'));
			txtOppHand.push(newFieldArea.querySelector('.txtHand'));
			txtOppRank.push(newFieldArea.querySelector('.txtRank'));
			txtOppShield.push(newFieldArea.querySelector('.txtShield'));
			divOppBoard.push(newFieldArea.querySelector('.DivFieldArea'));
			
			removeAllChildren(divOppBoard[i])
			divOppArea.appendChild(newFieldArea);
		}
	}
	
	removeAllChildren(handArea);
	removeAllChildren(divSelfBoard);
	for (var i = 0; i < divOppBoard.length; i++)
		removeAllChildren(divOppBoard[i]);
	
	txtSelfName.innerHTML = "Name: " + message.name;
	txtSelfHand.innerHTML = "Cards: " + message.hand.length;
	txtSelfRank.innerHTML = "Rank: " + message.rank;
	txtSelfShield.innerHTML = "Shields: " + message.sheilds;
	for (var i = 0; i < message.hand.length; i++)
		handArea.appendChild(createCardElement(message.hand[i]));
	for (var i = 0; i < message.board.length; i++)
		divSelfBoard.appendChild(createCardElement(message.board[i]));
	
	for (var i = 0; i < message.opponents.length; i++){
		txtOppName[i].innerHTML = "Name: " + message.opponents[i].name;
		txtOppHand[i].innerHTML = "Cards: " + message.opponents[i].hand;
		txtOppRank[i].innerHTML = "Rank: " + message.opponents[i].rank;
		txtOppShield[i].innerHTML = "Shields: " + message.opponents[i].sheilds;
		for (var j = 0; j < message.board.length; j++)
			divOppBoard.appendChild(createCardElement(message.opponents[i].board[j]));
	}
	
	removeAllChildren(divCurStoryArea);
	removeAllChildren(divStageArea);
	if (message.storyCard != null){
		divCurStoryArea.appendChild(createCardElement(message.storyCard));
	}
	if (message.questCards != null && message.questCards.length > 0){
		curQuestStageCards = message.questCards;
	} else {
		curQuestStageCards = null;
	}
	displayStage(curDisplayStage);
}

function playCard(card){
	if(card && stompClient) {
        var cardMessage = {
			user : username,
            name: card.alt,
            address: card.src,
			index: card.index
        };
        stompClient.send("/app/board.playCard", {}, JSON.stringify(cardMessage));
    }
}

function onInputRecieved(payload){
	var message = JSON.parse(payload.body);
	
	txtInputArea.innerHTML = message.message;
	removeAllChildren(divInputBtns);
	for (var i = 0; i < message.options.length; i++){
		divInputBtns.appendChild(createButtonElement(message.options[i], i));
	}
}

function selectInput(option){
	if(stompClient) {
        var InputMessage = {
			user : username,
			message: option.innerHTML,
            selected: option.num
        };
        stompClient.send("/app/input.select", {}, JSON.stringify(InputMessage));
    }
}

function selectStage(){
	if(stompClient) {
        var InputMessage = {
			user : username,
			message: "QuestStage",
            selected: curDisplayStage
        };
        stompClient.send("/app/input.select", {}, JSON.stringify(InputMessage));
    }
}

function displayStage(index){
	curDisplayStage = index;
	removeAllChildren(divStageArea);
	if (curQuestStageCards == null){
		curDisplayStage = 0;
		txtCurStage.innerHTML = "Stage 1";
	} else {
		if (curDisplayStage >= curQuestStageCards.length)
			curDisplayStage = curQuestStageCards.length - 1;
		else if (curDisplayStage < 0)
			curDisplayStage = 0;
		
		for (var i = 0; i < curQuestStageCards[curDisplayStage].length; i++){
			ivOppBoard.appendChild(createCardElement(curQuestStageCards[curDisplayStage][i]));
		}
		txtCurStage.innerHTML = "Stage " + (curDisplayStage + 1);
	}
}

function createButtonElement(name, index){
	var btnElement = document.createElement('button');
	btnElement.innerHTML = name;
	btnElement.num = index;
	btnElement.classList.add('buttonCont');
	btnElement.onclick = function(){ selectInput(btnElement); };
	return btnElement;
}

function createCardElement(params){
	var cardElement = document.createElement('img');
	cardElement.src = params.address;
	cardElement.alt = params.name;
	cardElement.index = params.index;
	cardElement.style = "width:50px;height:80px;"
	cardElement.onclick = function(){ playCard(cardElement); };
	return cardElement;
}

function removeAllChildren(obj){
	while (obj.firstChild)
		obj.removeChild(obj.firstChild);
	return obj;
}

function getAvatarColor(messageSender) {
    var hash = 0;
    for (var i = 0; i < messageSender.length; i++) {
        hash = 31 * hash + messageSender.charCodeAt(i);
    }
    var index = Math.abs(hash % colors.length);
    return colors[index];
}

btnNextStage.onclick = function(){ displayStage(curDisplayStage + 1); };
btnPrevStage.onclick = function(){ displayStage(curDisplayStage - 1); };
divStageArea.onclick = selectStage;

usernameForm.addEventListener('submit', connect, true)
messageForm.addEventListener('submit', sendMessage, true)