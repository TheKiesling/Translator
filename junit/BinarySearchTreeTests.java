package BinarySearchTree;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BinarySearchTreeTests {

	private StringComparator comparator = new StringComparator();
	private BinarySearchTree bst = new BinarySearchTree(comparator);
	
	@Test
	void insertTest() {
		bst.insert("K", "J");
		assertEquals(1, bst.count());
		assertEquals(false, bst.isEmpty());
		bst.insert("J", "K");
		assertEquals(2, bst.count());
		assertEquals(false, bst.isEmpty());
	}
	@Test
	void searchTest() {
		bst.insert("K", "J");
		bst.insert("J", "K");
		assertEquals("J", bst.find("K"));
		assertEquals("K", bst.find("J"));
	}

}
