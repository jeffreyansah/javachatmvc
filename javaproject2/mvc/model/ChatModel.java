package mvc.model;

import java.util.List;
import java.util.ArrayList;
import observer.ChatObserver;
import observer.Observable;

public class ChatModel implements Observable, Cloneable {

	private List<String> message;

	/**
	 * Associates with <{ChatObserver}>
	 */
	private List<ChatObserver> forumObserverList = new ArrayList<>();

	// Constructor
	public ChatModel() {
		message = new ArrayList<String>();
	}

	public void addMessage(String msg) {
		message.add(msg);
		inform();
	}

	public String getMessages() {
		String allMessage = "";
		for (String msg : message) {
			allMessage += msg;
		}
		return allMessage;
	}

	@Override
	public void attach(ChatObserver forumObserver) {
		forumObserverList.add(forumObserver);

	}

	@Override
	public void detach(ChatObserver forumObserver) {
		forumObserverList.remove(forumObserver);

	}

	@Override
	public void inform() {
		List<ChatObserver> observerList = forumObservers();
		while (observerList.iterator().hasNext()) {
			observerList.iterator().next().update();
		}
	}

	public List<ChatObserver> forumObservers() {

		List<ChatObserver> observersList = new ArrayList<>();
		for (ChatObserver copy : forumObserverList) {
			try {
				observersList.add((ChatObserver) copy.clone());
			} catch (CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return observersList;
	}

}
