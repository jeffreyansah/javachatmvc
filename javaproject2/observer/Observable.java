package observer;

/**
 * 
 * @author jeff
 * 
 * Interface Observable, has the following methods attach,detach and inform.
 *
 */
public interface Observable {
	 void attach(ChatObserver forumObserver);

	    void detach(ChatObserver forumObserver);

	    void inform();
}
