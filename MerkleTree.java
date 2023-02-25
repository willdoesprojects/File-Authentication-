/**
 * Merklee Tree data structure construction.
 * @author William Nguyen
 * @version 1.0
 */

import java.util.*;

/**
 * This is the class representing the complete MerkleTree.
 */

public class MerkleTree { 
	/**
	 * Instance variable of the root which is the top of the tree.
	 */
	public static MerkleTreeNode root;
	/**
	 * Instance variable of numberOfFiles is the.
	 */
	public int numberOfFiles;
    /**
	 * The ArrayList initialized as MerkleTreeNode data type only consists of leave nodes.
	 */
    public ArrayList<MerkleTreeNode> leaves;
 /**
     * This class constructs our Merkle Tree from the bottom up, it uses for loops to first
     * store the items in the leaves array, then it uses another ArrayList with the leaves and
     * keeps creating the parents until it reaches the root, it does so by calculating the depth
     * and the amount of nodes it needs.
     * 
     * @param files the array of strings it inputs
     * @return root that is encrypted
     */
	public String constructMerkleTree(String[] files){
		//Task 3: You are required to develop the code for the constructMerkleTree method.
		//Running time complexity of this method: O(n) where n is the number of files (size of the files array)
		//You can assume that the size of the files will be given as 2^n
		//throw IllegalArgumentException for invalid parameters
		
		leaves = new ArrayList<MerkleTreeNode>();
        numberOfFiles = files.length;
        //create leaves
        for(int i = 0; i < numberOfFiles; i++){
            MerkleTreeNode leaf = new MerkleTreeNode(null, null, null, files[i]);
            leaves.add(leaf);
        }
        
        ArrayList<MerkleTreeNode> tree = new ArrayList<MerkleTreeNode>();
        tree.addAll(leaves);
        
        int dup = numberOfFiles;
        int count = 1;
        
        while (dup != 1) {
        	dup /= 2;
        	count += 1;
        }
       
        count = (int) Math.pow(2, count);
        count -= 2;
        for (int i = 0; i < count; i+=2){
        	MerkleTreeNode parent = new MerkleTreeNode(null, tree.get(i), tree.get(i+1), Hashing.cryptHash(tree.get(i).getStr() + tree.get(i+1).getStr()));
	        tree.get(i).setParent(parent);
	        tree.get(i+1).setParent(parent);
	        tree.add(parent);      
	            
        }
     
        
        
        

        root = tree.get(tree.size()-1);
        
        
        
        return root.getStr();
    
	}
       
        
        
    
	/*     
		SiblingPair is a generic data structure defined in SiblingPair.java
	*/
	/**
	 * sendAppr method is a method in which it stores the pairs into an ArrayList of the
	 * encryptions of each individual pair in each level. We use a while loop to consistently shrink
	 * our search size by half each time.
	 * 
	 * @param fileIndex the indicated index for our file array
	 * @return pairs the ArrayList consiting of the pairs
	 */
	public ArrayList<SiblingPair<String>> sendAppr(int fileIndex){
		
		ArrayList<SiblingPair<String>> pairs = new ArrayList<SiblingPair<String>>();
		
		MerkleTreeNode leftCurrent;
		MerkleTreeNode rightCurrent;
		
		if(fileIndex % 2 == 0) {
			leftCurrent = leaves.get(fileIndex);
			rightCurrent = leaves.get(fileIndex + 1);
		}
		
		else {
			rightCurrent = leaves.get(fileIndex);
			leftCurrent = leaves.get(fileIndex - 1);
		}
		
		SiblingPair<String> newPair = new SiblingPair<String>(leftCurrent.getStr(), rightCurrent.getStr());
		
		pairs.add(newPair);
		
		leftCurrent = leftCurrent.getParent();
		rightCurrent = rightCurrent.getParent();
		while(leftCurrent.getParent() != null && rightCurrent.getParent() != null) {
			leftCurrent = leftCurrent.getParent();
			rightCurrent = rightCurrent.getParent();
			
			SiblingPair<String> newPair2 = new SiblingPair<String>(leftCurrent.getLeft().getStr(), rightCurrent.getRight().getStr());
			
			pairs.add(newPair2);
			
		}
		
		SiblingPair<String> newPairFinal = new SiblingPair<String>(root.getStr(), null);
		pairs.add(newPairFinal);
		
		return pairs;
	}
	
	/**
	 * VerifyIntegrity method verifies that the files has not been tampered with by
	 * hashing the original files again and seeing if it matches with the pairs in the tree. 
	 * @param aprr the arraylist of pairs input
	 * @param rootValue the root values that the customer stores
	 * @return true if verification succeeds, false if not
	 */
	public static boolean verifyIntegrity(ArrayList<SiblingPair<String>> aprr, String rootValue){
		if (root.getStr() != rootValue) {
			return false;
		}
		
		if (aprr.size() % 2 == 0) {
		
			for (int i = 0; i < aprr.size(); i+=2) {
				String temp = Hashing.cryptHash(aprr.get(i).getLeftSibling() + aprr.get(i).getRightSibling());
				if (i == aprr.size() - 2) {
					
					String temp2 = Hashing.cryptHash(aprr.get(i-1).getLeftSibling() + aprr.get(i-1).getRightSibling());
					if (temp2.equals(aprr.get(i).getLeftSibling())) {
						return true;
					}
					else {
						
						return false;
					}
				}
				if (temp.equals(aprr.get(i+1).getLeftSibling()) || temp.equals(aprr.get(i+1).getRightSibling()) )  {
					
					continue;
				}
			}
		
		}
		
		else {
			for (int i = 0; i < aprr.size(); i+=2) {
				String temp = Hashing.cryptHash(aprr.get(i).getLeftSibling() + aprr.get(i).getRightSibling());
				if (i == aprr.size() - 1) {
					
					String temp2 = Hashing.cryptHash(aprr.get(i-1).getLeftSibling() + aprr.get(i-1).getRightSibling());
					if (temp2.equals(aprr.get(i).getLeftSibling())) {
						return true;
					}
					else {
						
						return false;
					}
				}
				if (temp.equals(aprr.get(i+1).getLeftSibling()) || temp.equals(aprr.get(i+1).getRightSibling()) )  {
					
					continue;
				}
			}
		}
		
		return false;
	}
	
	/**
	 * Replaces the indicated file and encrypt the tree again by rehashing each individual parent it is
	 * associated with.
	 * @param fileIndex fileIndex the indicated index for our file array
	 * @param updatedFile updated file, a.k.a., string that wants to be replaced
	 * @return root the new one that is rehashed
	 */
	public String replaceFile(int fileIndex, String updatedFile){
		leaves.get(fileIndex).setStr(updatedFile);
		MerkleTreeNode curr = leaves.get(fileIndex);
		System.out.println(curr.getParent().getRight().getStr());
		while(curr.getParent() != null) {
			curr.getParent().setStr(Hashing.cryptHash(curr.getParent().getLeft().getStr() + curr.getParent().getRight().getStr()));
			curr = curr.getParent();
		}
		
		
		
		return root.getStr();
	}
}
