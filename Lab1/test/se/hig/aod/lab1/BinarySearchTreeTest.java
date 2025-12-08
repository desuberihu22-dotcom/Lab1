package se.hig.aod.lab1;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
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
                assertEquals(0, bst.size());
                assertNull(bst.searchElement(10));
                assertEquals("", bst.toString());
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

                assertEquals("13579", bst.toString());
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

                assertNull(bst.searchElement(99));
        }

        @Test
        public void testAddNullElement() {
                bst.addElement(null);
                assertEquals(1, bst.size());
        }

        @Test
        public void testSearchNullElement() {
                bst.addElement(10);
                assertNull(bst.searchElement(null));
        }

        @Test
        public void testDuplicates() {
                bst.addElement(5);
                bst.addElement(5);
                assertEquals(1, bst.size());
                assertEquals("5", bst.toString());
        }
}
