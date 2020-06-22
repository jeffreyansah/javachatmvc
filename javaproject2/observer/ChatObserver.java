package observer;



/**
 * 
 * @author jeff
 * 
 * Interface observer, has method update.
 *
 */
public interface ChatObserver {

	void update();
	Object   clone() throws CloneNotSupportedException;
}
