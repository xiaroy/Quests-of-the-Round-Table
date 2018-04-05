package test.spring;

public class ChatMessage {
	private MessageType type;
    private String content;
    private String sender;
    private Card[] cards = {new Card(), new Card()};

    public enum MessageType {
        CHAT,
        JOIN,
        LEAVE
    }
    
    public class Card {
    	private String name = "Ally/King Arthur.png";
    	
    	public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
    
    public Card[] getCards() {
        return cards;
    }

    public void setStuff(Card[] cards) {
        this.cards = cards;
    }
}
