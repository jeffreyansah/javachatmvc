package mvc.controller;

import mvc.model.ChatModel;
import mvc.view.ChatView;
import observer.ChatObserver;

public class ChatController implements ChatObserver {
 
	// Campi di classe
	private ChatModel model;
	private ChatView view;
	private int messageNumber=0;
	//costruttore della classe
	public ChatController(ChatView view, ChatModel model) {
		this.view=view;
		this.model=model;
		view.attach(this);
	}
	
	@Override
	public void update() {
		 messageNumber++;
	        System.out.println(messageNumber);
	}
	public void processSubmitAction(String msg) {
        model.addMessage(msg);
    }

    public void processSelectionAction(int display) {
        view.showDisplay(display);
    }
	//implements clone it makes shallow copy.
	@Override
     public Object clone() throws CloneNotSupportedException{
    	  
		return super.clone();
    		}
}
