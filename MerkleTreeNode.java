/**
 * The class is the basis of our MerkleTreNode/Binary tree.
 * 
 * @author William
 * @version 1.0
 */

public class MerkleTreeNode{
    /**
	 * The instance variable parent is the node that the left and right point to.
	 */
    private MerkleTreeNode parent;
    /**
     * The instance variable left is the left child that points to the parent.
     */
    private MerkleTreeNode left;
    /**
     * The instance variable right is the right child that points to the parent.
     */
    private MerkleTreeNode right;
    /**
     * The instance variable str is the string file stored in the node, our "data".
     */
    private String str;

	/**
	 * Develop a default MerkleTreeNode constructor that initializes the instance variables to null.
	 */
	public MerkleTreeNode(){
		this(null, null, null, null); 
	}
	
 /**
	 * Takes input and stores it into our MerkleTreeNode.
	 * @param parent input of the parent
	 * @param left input of the left child
	 * @param right input of the right child
	 * @param str input of the string/file
	 */
	public MerkleTreeNode(MerkleTreeNode parent,MerkleTreeNode left,MerkleTreeNode right,String str){
		this.parent = parent;
		this.left = left;
		this.right = right;
		this.str = str;
		
	}

	
	/**
	 * Get method for parent.
	 * @return parent
	 */
	public MerkleTreeNode getParent(){
		return parent;
	}
	/**
	 * Get method for left child.
	 * @return left child
	 */
	public MerkleTreeNode getLeft(){
		return left;
	}
	/**
	 * Get method for right child.
	 * @return right child
	 */
	public MerkleTreeNode getRight(){
		return right;
	}
	/**
	 * Get method for string/file.
	 * @return string
	 */
	public String getStr(){
		return str;
	}
	/**
	 * Set method for setting the parent.
	 * @param parent input
	 */
	public void setParent(MerkleTreeNode parent){
		if (parent == null) {
			throw new IllegalArgumentException();
		}
		this.parent = parent;
	}
	/**
	 * Set method for setting the left child.
	 * @param left input
	 */
	public void setLeft(MerkleTreeNode left){
		if (left == null) {
			throw new IllegalArgumentException();
		}
		this.left = left;
	}
	/**
	 * Set method for setting the right child.
	 * @param right input
	 */
	public void setRight(MerkleTreeNode right){
		if (right == null) {
			throw new IllegalArgumentException();
		}
		this.right = right;
	}
	
	/**
	 * Set method for setting the string or file.
	 * @param str string input
	 */
	public void setStr(String str){
		if (str == null || str.equals("")) {
			throw new IllegalArgumentException();
		}
		this.str = str;
	}        
        
}