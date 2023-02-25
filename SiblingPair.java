

/**
 * This class is used to store each element in the Authentication Pairs Route to Root (APRR).
 * @author William Nguyen
 * @param <X> Type of input of SiblingPair class
 */
public class SiblingPair<X> {
	/**
	 * The generic instance variable leftSibiling is the left pair.
	 */
	public X leftSibling;
	/**
	 * The generic instance variable rightSibling is the right pair.
	 */
	public X rightSibling;
    
    /**
	 * The constructor that takes generic inputs of the left and right pair.
	 * @param leftS left pair
	 * @param rightS right pair
	 */
    public SiblingPair(X leftS, X rightS) { 
     //throw IllegalArgumentException for invalid parameters
    	
    	if (leftS == null) {
    		throw new IllegalArgumentException();
    	}
    	
    	leftSibling = leftS;
    	rightSibling = rightS;
    }
    
    /**
     * Constructor the sets it to null if both pairs are specified at input.
     */
    public SiblingPair(){
    	this(null, null);
    }
    
    /**
     * Get method to get the left pair.
     * @return left pair
     */
    public X getLeftSibling() {
        return leftSibling;
    }
    /**
     * Get method to get the right pair.
     * @return right pair
     */
    public X getRightSibling() {
        return rightSibling;
    }
    
    
    
}
