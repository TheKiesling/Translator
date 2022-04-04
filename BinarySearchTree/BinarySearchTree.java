/*
#      #    #######    ########   #######   #          #######   ##      #    #########
#     #        #       #          #         #             #      # #     #    #
#    #         #       #          #         #             #      #  #    #    #
####           #       #####      #######   #             #      #   #   #    #    ####
#    #         #       #                #   #             #      #    #  #    #       #
#     #        #       #                #   #             #      #     # #    #       #
#      #    ########   ########   #######   ########   #######   #      ##    #########
*/

package BinarySearchTree;
import java.util.ArrayList;
import java.util.Comparator;

/*********************************************************************
 * A binary search tree structure.  This structure maintains data
 * in an ordered tree.  It does not keep the tree balanced, so performance
 * may degrade if the tree height is not optimal.
 * <p>
 * SOURCE CODE BY: https://github.com/malonso-uvg/ADT_Ejemplos/blob/main/src/edu/uvg/structures/BinarySearchTree.java
 * @version 03/04/2022
*********************************************************************/
public class BinarySearchTree<K, V>{
	
	//---------------------------PROPERTIES---------------------------
	private int count;
	private TreeNode<K, V> root;
	private Comparator<K> keyComparator;
	
	//---------------------------METHODS------------------------------
	/*****************************************************************
	 * Constructs a binary search tree with no data
	 * @param _keyComparator
	 */
	public BinarySearchTree(Comparator<K> keyComparator) {
		this.keyComparator = keyComparator;
		root = null;
		count = 0;
	}
	//****************************************************************
	
	/*****************************************************************
	 * Add a value to binary search tree
	 * @param key
	 * @param value
	 */
	public void insert(K key, V value) {
		if (isEmpty()) {
			root = new TreeNode<K, V>(key, value);
			count++;
		} else 
			internalInsert(root, key, value);
	}
	//****************************************************************

	/*****************************************************************
	 * Remove a value equals to the indicated key. 
	 * @param key
	 * @return Actual value removed from tree
	 */
	public V delete(K key) {
		V tempValue = null;

		if (!isEmpty()) {
			int result = keyComparator.compare(root.getKey(), key);

			if (result > 0) 
				return internalDelete(root.getLeft(), key, true);
			else if (result < 0) 
				return internalDelete(root.getRight(), key, false);
			else { //Root is the removed node
				tempValue = root.getValue();

				if (count() == 1) { //Root is the only element
					tempValue = root.getValue();
					root = null;
				} else {
					if (root.getRight() != null) { //Search the right child leftmost
							
						TreeNode<K, V> leftOfTheRights = root.getRight();
						
						while(leftOfTheRights.getLeft() != null)  
							leftOfTheRights = leftOfTheRights.getLeft(); 
						
						
						//Assigning the left side
						leftOfTheRights.setLeft(root.getLeft());
						if (leftOfTheRights.getLeft() != null)
							leftOfTheRights.getLeft().setParent(leftOfTheRights);
						
						//Assigning the right side
						if (keyComparator.compare(root.getRight().getKey(), leftOfTheRights.getKey()) != 0) { //Only if the leftOfTheRights is different than root.right
							leftOfTheRights.getParent().setLeft(null);
							
							TreeNode<K, V> newRootRight = leftOfTheRights;
							
							while (newRootRight.getRight() != null) 
								newRootRight = newRootRight.getRight();

							newRootRight.setRight(root.getRight());

							if (newRootRight.getRight() != null) 
								newRootRight.getRight().setParent(newRootRight);
							
						}
						
						//Assigning new parents
						if (leftOfTheRights.getRight() != null)
							leftOfTheRights.getRight().setParent(leftOfTheRights);
						
						leftOfTheRights.setParent(null);
						root = leftOfTheRights;
						
					} else { //Search the left child rightmost
						
						TreeNode<K, V> rightOfTheLefts = root.getLeft();
						
						while(rightOfTheLefts.getRight() != null) 
							rightOfTheLefts = rightOfTheLefts.getRight(); 
						
						
						//Assigning the right side
						rightOfTheLefts.setRight(root.getRight());
						if (rightOfTheLefts.getRight() != null)
							rightOfTheLefts.getRight().setParent(rightOfTheLefts);
						
						//Assigning the left side
						if (keyComparator.compare(root.getLeft().getKey(), rightOfTheLefts.getKey()) != 0) { //Only if the rightOfTheLefts is different than root.left
							rightOfTheLefts.getParent().setRight(null);
							
							TreeNode<K, V> newRootLeft = rightOfTheLefts;
							
							while (newRootLeft.getLeft() != null) 
								newRootLeft = newRootLeft.getLeft();
							
							newRootLeft.setLeft(root.getLeft());

							if (newRootLeft.getLeft() != null) 
								newRootLeft.getLeft().setParent(newRootLeft);
						}
						
						//Assigning the new parentes
						if (rightOfTheLefts.getLeft() != null)
							rightOfTheLefts.getLeft().setParent(rightOfTheLefts);
						
						rightOfTheLefts.setParent(null);
						root = rightOfTheLefts;						
					}
				}
				count--;
			}
		}
		return tempValue;
	}
	//****************************************************************

	/*****************************************************************
	 * Search if the value related by the key exists
	 * @param key
	 * @return The value that is related by the key
	 */
	public V find(K key) {
		return internalFind(root, key);
	}
	//****************************************************************

	/*****************************************************************
	 * Determine the number of data values within the tree
	 * @return The number of nodes in the binary search tree
	 */
	public int count() {
		return count;
	}
	//****************************************************************

	/*****************************************************************
	 * Check for an empty binary search tree
	 * @return True if the tree contains no data
	 */
	public boolean isEmpty() {
		return count == 0;
	}
	//****************************************************************

	/*****************************************************************
	 * walk all the tree in order
	 * @param traversal
	 */
	public ArrayList<V> inOrder() {
		ArrayList<V> list = new ArrayList<V>();
		
		internalInOrder(root, list);
		
		return list;
	}
	//****************************************************************
	
	/*****************************************************************
	 * Add a value to binary search tree
	 * @param actual
	 * @param key
	 * @param value
	 */
	private void internalInsert(TreeNode<K, V> actual, K key, V value) {
		
		int result = keyComparator.compare(actual.getKey(), key);
		
		if (result > 0) { //actual id is greater than new id then search in the left side
			
			if (actual.getLeft() == null) { //Doesn't have left children
				TreeNode<K, V> newNode = new TreeNode<K, V>(key, value);
				actual.setLeft(newNode);
				newNode.setParent(actual);
				count++;
			} else 
				internalInsert(actual.getLeft(), key, value);
			
		} else if (result < 0) { //actual id is smaller than the new then search in the right
			if (actual.getRight() == null) { //Doesn't have left children
				TreeNode<K, V> newNode = new TreeNode<K, V>(key, value);
				actual.setRight(newNode);
				newNode.setParent(actual);
				count++;
			} else 
				internalInsert(actual.getRight(), key, value);
		}
		
	}
	//****************************************************************
	
	/*****************************************************************
	 * walk all the tree in order
	 * @param actual
	 * @param traversal
	 */
	private void internalInOrder(TreeNode<K, V> actual, ArrayList<V> traversal) {
		if (actual != null) {
			internalInOrder(actual.getLeft(), traversal);
			
			traversal.add(actual.getValue());
			
			internalInOrder(actual.getRight(), traversal);
		}
	}
	//****************************************************************

	/*****************************************************************
	 * Search if the value related by the key exists
	 * @param actual
	 * @param key
	 * @return The value that is related by the key
	 */
	private V internalFind(TreeNode<K, V> actual, K key) {
		if (actual != null) {
			int result = keyComparator.compare(actual.getKey(), key);
			
			if (result > 0) 
				return internalFind(actual.getLeft(), key);
			else if (result < 0) 
				return internalFind(actual.getRight(), key);
			else 
				return actual.getValue();

		} else 
			return null;
	}
	//****************************************************************
	
	/*****************************************************************
	 * Remove a value equals to the indicated key. 
	 * @param actual
	 * @param key
	 * @param isLeft
	 * @return Actual value removed from tree
	 */
	private V internalDelete(TreeNode<K, V> actual, K key, boolean isLeft) {
		V tempValue = null;

		if (!isEmpty()) {
			int result = keyComparator.compare(root.getKey(), key);

			if (result > 0) 
				return internalDelete(root.getLeft(), key, true);
			else if (result < 0) 
				return internalDelete(root.getRight(), key, false);
			else { //Root is the removed node
				tempValue = root.getValue();

				if (count() == 1) { //Root is the only element
					tempValue = root.getValue();
					root = null;
				} else {
					if (root.getRight() != null) { //Search the right child leftmost
							
						TreeNode<K, V> leftOfTheRights = root.getRight();
						
						while(leftOfTheRights.getLeft() != null)  
							leftOfTheRights = leftOfTheRights.getLeft(); 
						
						
						//Assigning the left side
						leftOfTheRights.setLeft(root.getLeft());
						if (leftOfTheRights.getLeft() != null)
							leftOfTheRights.getLeft().setParent(leftOfTheRights);
						
						//Assigning the right side
						if (keyComparator.compare(root.getRight().getKey(), leftOfTheRights.getKey()) != 0) { //Only if the leftOfTheRights is different than root.right
							leftOfTheRights.getParent().setLeft(null);
							
							TreeNode<K, V> newRootRight = leftOfTheRights;
							
							while (newRootRight.getRight() != null) 
								newRootRight = newRootRight.getRight();

							newRootRight.setRight(root.getRight());

							if (newRootRight.getRight() != null) 
								newRootRight.getRight().setParent(newRootRight);
							
						}
						
						//Assigning new parents
						if (leftOfTheRights.getRight() != null)
							leftOfTheRights.getRight().setParent(leftOfTheRights);
						
						leftOfTheRights.setParent(null);
						root = leftOfTheRights;
						
					} else { //Search the left child rightmost
						
						TreeNode<K, V> rightOfTheLefts = root.getLeft();
						
						while(rightOfTheLefts.getRight() != null) 
							rightOfTheLefts = rightOfTheLefts.getRight(); 
						
						
						//Assigning the right side
						rightOfTheLefts.setRight(root.getRight());
						if (rightOfTheLefts.getRight() != null)
							rightOfTheLefts.getRight().setParent(rightOfTheLefts);
						
						//Assigning the left side
						if (keyComparator.compare(root.getLeft().getKey(), rightOfTheLefts.getKey()) != 0) { //Only if the rightOfTheLefts is different than root.left
							rightOfTheLefts.getParent().setRight(null);
							
							TreeNode<K, V> newRootLeft = rightOfTheLefts;
							
							while (newRootLeft.getLeft() != null) 
								newRootLeft = newRootLeft.getLeft();
							
							newRootLeft.setLeft(root.getLeft());

							if (newRootLeft.getLeft() != null) 
								newRootLeft.getLeft().setParent(newRootLeft);
						}
						
						//Assigning the new parentes
						if (rightOfTheLefts.getLeft() != null)
							rightOfTheLefts.getLeft().setParent(rightOfTheLefts);
						
						rightOfTheLefts.setParent(null);
						root = rightOfTheLefts;						
					}
				}
				count--;
			}
		}
		return tempValue;
	}
	//****************************************************************
}