package se.hig.aod.lab1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.hig.aod.lab1.BinarySearchTree;

import static org.junit.jupiter.api.Assertions.*;

public class BinarySearchTreeTest {

        private BinarySearchTree<Integer> bst;
        private BinarySearchTree<Character> charBst;

        @BeforeEach
        public void setUp() {
                bst = new BinarySearchTree<>();
                charBst = new BinarySearchTree<>();
        }

        @Test
        public void testEmptyTree() {
                assertEquals(0, bst.size(), "Size should be 0 for empty tree");
                assertNull(bst.searchElement(10), "Search in empty tree should return null");
                assertEquals("", bst.toString(), "toString for empty tree should be empty string");
        }

        @Test
        public void testAddElement() {
                bst.addElement(5);
                assertEquals(1, bst.size());
                assertEquals(5, bst.searchElement(5));

                bst.addElement(3);
                assertEquals(2, bst.size());
                assertEquals(3, bst.searchElement(3));

                bst.addElement(7);
                assertEquals(3, bst.size());
                assertEquals(7, bst.searchElement(7));
        }

        @Test
        public void testToStringSortedOrder() {
                bst.addElement(5);
                bst.addElement(3);
                bst.addElement(7);
                bst.addElement(1);
                bst.addElement(9);

                // Expected order: 1, 3, 5, 7, 9
                assertEquals("13579", bst.toString(), "toString should return elements in sorted order");
        }

        @Test
        public void testToStringCharacters() {
                charBst.addElement('d');
                charBst.addElement('b');
                charBst.addElement('f');
                charBst.addElement('a');
                charBst.addElement('c');
                charBst.addElement('e');
                charBst.addElement('g');

                // Expected order: abcdefg
                assertEquals("abcdefg", charBst.toString());
        }

        @Test
        public void testSearchElement() {
                bst.addElement(10);
                bst.addElement(5);
                bst.addElement(15);

                assertEquals(10, bst.searchElement(10));
                assertEquals(5, bst.searchElement(5));
                assertEquals(15, bst.searchElement(15));

                assertNull(bst.searchElement(99), "Should return null for non-existent element");
        }

        @Test
        public void testAddNullElement() {
                assertThrows(IllegalArgumentException.class, () -> {
                        bst.addElement(null);
                }, "Adding null should throw IllegalArgumentException");
        }

        @Test
        public void testSearchNullElement() {
                bst.addElement(10);
                assertNull(bst.searchElement(null), "Searching for null should return null");
        }

        @Test
        public void testDuplicates() {
                bst.addElement(5);
                bst.addElement(5);
                // Depending on implementation, duplicates might be ignored or added.
                // The current implementation adds duplicates to the right (comparison >= 0
                // check in recursiveAdd?)
                // Let's check the code:
                // if (comparison < 0) left... else if (comparison > 0) right...
                // It does NOT handle comparison == 0, so it does nothing!
                // So size should remain 1.

                assertEquals(1, bst.size(),
                                "Adding duplicate should not increase size (based on current implementation)");
                assertEquals("5", bst.toString());
        }
}
