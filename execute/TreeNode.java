/*
#      #    #######    ########   #######   #          #######   ##      #    #########
#     #        #       #          #         #             #      # #     #    #
#    #         #       #          #         #             #      #  #    #    #
####           #       #####      #######   #             #      #   #   #    #    ####
#    #         #       #                #   #             #      #    #  #    #       #
#     #        #       #                #   #             #      #     # #    #       #
#      #    ########   ########   #######   ########   #######   #      ##    #########
*/



/*********************************************************************
 * A node tree.  These elements have a parent and childrens. Also the
 * value of each node is related by key, that serves to know who element
 * is.
 * <p>
 * SOURCE CODE BY: https://github.com/malonso-uvg/ADT_Ejemplos/blob/main/src/edu/uvg/structures/TreeNode.java
 * @version 03/04/2022
*********************************************************************/
public class TreeNode<K, V> {

	//---------------------------PROPERTIES---------------------------
	private V value;
	private K key;
	private TreeNode<K, V> left;
	private TreeNode<K, V> right;
	private TreeNode<K, V> parent;
	
	//---------------------------METHODS------------------------------
	/*****************************************************************
	 * Constructs a node tree with no data
	 * @param id
	 * @param value
	 */
	public TreeNode(K key, V value) {
		setKey(key);
		setValue(value);
		setLeft(null);
		setRight(null);
		setParent(null);
	}
	//****************************************************************
	
	/*****************************************************************
	 * @return the value
	 */
	public V getValue() {
		return this.value;
	}
	//****************************************************************

	/*****************************************************************
	 * @param value the value to set
	 */
	public void setValue(V value) {
		this.value = value;
	}
	//****************************************************************

	/*****************************************************************
	 * @return the id
	 */
	public K getKey() {
		return this.key;
	}
	//****************************************************************

	/*****************************************************************
	 * @param id the id to set
	 */
	public void setKey(K key) {
		this.key = key;
	}
	//****************************************************************

	/*****************************************************************
	 * @return the left
	 */
	public TreeNode<K, V> getLeft() {
		return this.left;
	}
	//****************************************************************

	/*****************************************************************
	 * @param left the left to set
	 */
	public void setLeft(TreeNode<K, V> left) {
		this.left = left;
	}
	//****************************************************************

	/*****************************************************************
	 * @return the right
	 */
	public TreeNode<K, V> getRight() {
		return this.right;
	}
	//****************************************************************

	/*****************************************************************
	 * @param right the right to set
	 */
	public void setRight(TreeNode<K, V> right) {
		this.right = right;
	}
	//****************************************************************

	/*****************************************************************
	 * @return the parent
	 */
	public TreeNode<K, V> getParent() {
		return this.parent;
	}
	//****************************************************************

	/*****************************************************************
	 * @param parent the parent to set
	 */
	public void setParent(TreeNode<K, V> parent) {
		this.parent = parent;
	}
	//****************************************************************
} 

