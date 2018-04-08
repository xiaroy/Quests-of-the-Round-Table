package server.messages;

public class InputMessage {
	
	private String user;
	private String message;
	private String[] options;
	private int selected = -1;
	
	public String getUser()  { return user; }
	public void setUser(String user) { this.user = user; }
	
	public String getMessage() { return message; }
	public void setMessage(String message) { this.message = message; }
	
	public String[] getOptions() { return options; }
	public void setOptions(String[] options) { this.options = options; }
	
	public int getSelected() { return selected; }
	public void setSelected(int selected) { this.selected = selected; }
}
