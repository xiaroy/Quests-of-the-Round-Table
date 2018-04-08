package server.messages;

import model.cards.Card;

public class CardMessage {

	private String user;
	private String name;
	private String address;
	
	public void setUser(String user) { this.user = user; }
	public String getUser() { return user; }
	
	public void setName(String name) { this.name = name; }
	public String getName() { return name; }
	
	public void setAddress(String address) { this.address = address; }
	public String getAddress() { return address; }
	
	public void setCard(Card card) {
		this.setName(card.getName());
		this.setAddress("img/" + card.GetCardType() + "/" + card.getName() + ".png");
	}
	
}
