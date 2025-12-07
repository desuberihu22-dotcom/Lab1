package se.hig.aod.lab1;

public class BinarySearchTree<T extends Comparable<? super T>> implements SearchableDataStructure<T> {

        int size;
        Treenode root;

        @Override
        public int size() {
                return size;
        }

        @Override
        public void addElement(T newElement) {
                if (newElement == null) {
                        throw new IllegalArgumentException("Element cannot be null");
                }
                root = recursiveAdd(root, newElement);
        }

        private Treenode recursiveAdd(Treenode current, T newElement) {
                if (current == null) {
                        size++;
                        return new Treenode(newElement);
                }
                int comparison = newElement.compareTo(current.data);
                if (comparison < 0) {
                        current.left = recursiveAdd(current.left, newElement);
                } else if (comparison > 0) {
                        current.right = recursiveAdd(current.right, newElement);
                }
                return current;
        }

        @Override
        public T searchElement(T elementToFind) {
                if (elementToFind == null) {
                        return null;
                }
                Treenode result = recursiveSearch(root, elementToFind);
                return result != null ? result.data : null;
        }

        @Override
        public String toString() {
                StringBuilder sb = new StringBuilder();
                recursiveToString(root, sb);
                return sb.toString();
        }

        private void recursiveToString(Treenode current, StringBuilder sb) {
                if (current != null) {
                        recursiveToString(current.left, sb);
                        sb.append(current.data);
                        recursiveToString(current.right, sb);
                }
        }

        private Treenode recursiveSearch(Treenode current, T elementToFind) {
                if (current == null) {
                        return null;
                }

                int comparison = elementToFind.compareTo(current.data);
                if (comparison == 0) {
                        return current;
                } else if (comparison < 0) {
                        return recursiveSearch(current.left, elementToFind);
                } else {
                        return recursiveSearch(current.right, elementToFind);
                }
        }

        class Treenode {
                private T data;
                private Treenode left;
                private Treenode right;

                public Treenode(T data) {
                        this.data = data;
                }
        }

}
