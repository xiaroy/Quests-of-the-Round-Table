package server;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import controller.WebController;
import model.player.Player;
import server.messages.BoardMessage;
import server.messages.CardMessage;
import server.messages.ChatMessage;
import server.messages.InputMessage;
import ui.MainFrame;
import view.GameView;

@SpringBootApplication
@Controller
@Component
@Configuration
@EnableWebSocketMessageBroker
public class SpringServer implements WebSocketMessageBrokerConfigurer {

	public static void main(String[] args) {
		startServer();
		//SpringApplication.run(SpringServer.class, args);
	}
	
	public static void startServer() {
		SpringApplication.run(SpringServer.class, new String[0]);
	}
	
	private static HashMap<String, WebController> controllers = new HashMap<>();
	private static MainFrame mainFrame = null;
	
	private static final Logger logger = LoggerFactory.getLogger(SpringServer.class);
	
	@Autowired
    private SimpMessageSendingOperations messagingTemplate;
	
	public static void setMainFrame(MainFrame mFrame) { mainFrame = mFrame; }
	
	/*Server Configuration methods (implemented from WebSocketMessageBrokerConfigurer)*/
	@Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/app");
        registry.enableSimpleBroker("/topic", "/board", "/input");
    }
    /*^^^Server Configuration methods (implemented from WebSocketMessageBrokerConfigurer)^^^*/
	
    /*Handling Connecting and Disconnecting Sockets*/
	@EventListener
    public void handleWebSocketConnectListener(SessionConnectedEvent event) {
        logger.info("Received a new web socket connection");
    }

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());

        String username = (String) headerAccessor.getSessionAttributes().get("username");
        if(username != null) {
            logger.info("User Disconnected : " + username);
            
            for (String name : controllers.keySet()) {
            	if (name.equals(username)) {
            		controllers.remove(name);
            		break;
            	}
            }

            ChatMessage chatMessage = new ChatMessage();
            chatMessage.setType(ChatMessage.MessageType.LEAVE);
            chatMessage.setSender(username);

            messagingTemplate.convertAndSend("/topic/public", chatMessage);
        }
    }
    /*^^^Handling Connecting and Disconnecting Sockets^^^*/
	
    /*Message Handlers*/
	@MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        return chatMessage;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessage addUser(@Payload ChatMessage chatMessage, 
                               SimpMessageHeaderAccessor headerAccessor) {
    	if (mainFrame != null) {
	    	Player p = new Player(chatMessage.getSender());
	    	WebController c = new WebController(p, mainFrame.getControllerHub(), this);
	    	mainFrame.addPlayer(p, c);
	    	controllers.put(chatMessage.getSender(), c);
    	}
    	
        // Add username in web socket session
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        return chatMessage;
    }
    
    @MessageMapping("/board.playCard")
    public void playCard(@Payload CardMessage card) {
    	WebController c = controllers.get(card.getUser());
    	if (c != null)
    		c.playCard(card.getName(), card.getIndex());
    }
    
    public void sendBoard(GameView view) {
    	System.out.println("Sending message to /board/" + view.GetPerspectiveName());
    	BoardMessage board = new BoardMessage();
    	board.createMessage(view);
    	messagingTemplate.convertAndSend("/board/" + view.GetPerspectiveName(), board);
    }
    
    @MessageMapping("/input.select")
    public void getInput(@Payload InputMessage message) {
    	WebController c = controllers.get(message.getUser());
    	if (c != null)
    		c.selectedInput(message.getSelected());
    }
    
    public void sendInput(InputMessage message) {
    	messagingTemplate.convertAndSend("/input/" + message.getUser(), message);
    }
    /*^^^Message Handlers^^^*/
}
