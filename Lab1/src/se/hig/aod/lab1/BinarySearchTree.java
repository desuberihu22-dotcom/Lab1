package se.hig.aod.lab1;

/**
 * En enkel generisk implementation av ett binärt sökträd (BST).
 * <p>
 * Trädet lagrar element som kan jämföras och tillåter inte dubbletter.
 * Metoder som stöds är att lägga till ett element, söka efter ett element,
 * hämta trädets storlek samt skriva ut trädet i sorterad ordning.
 *
 * @param <T> en datatyp som implementerar Comparable
 */
public class BinarySearchTree<T extends Comparable<? super T>>
        implements SearchableDataStructure<T> {

    /** Antal element i trädet. */
    private int size;

    /** Rotnoden i trädet. */
    private TreeNode root;

    /**
     * Returnerar antalet element som finns i trädet.
     *
     * @return antal element i trädet
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Lägger till ett nytt element i det binära sökträdet.
     * <p>
     * Om elementet redan finns i trädet ignoreras det.
     *
     * @param newElement elementet som ska läggas in
     * @throws IllegalArgumentException om newElement är null
     */
    @Override
    public void addElement(T newElement) {
        if (newElement == null) {
            throw new IllegalArgumentException("Element kan inte vara null");
        }
        root = recursiveAdd(root, newElement);
    }

    /**
     * Rekursiv hjälpmetod som lägger in elementet på rätt plats i trädet.
     *
     * @param current    noden som undersöks
     * @param newElement elementet som ska läggas till
     * @return den (eventuellt nya) noden i detta delträd
     */
    private TreeNode recursiveAdd(TreeNode current, T newElement) {
        if (current == null) {
            size++;
            return new TreeNode(newElement);
        }

        int comparison = newElement.compareTo(current.data);

        if (comparison < 0) {
            current.left = recursiveAdd(current.left, newElement);
        } else if (comparison > 0) {
            current.right = recursiveAdd(current.right, newElement);
        }
        // vid comparison == 0 görs ingenting → inga dubbletter

        return current;
    }

    /**
     * Söker efter ett visst element i trädet.
     *
     * @param elementToFind elementet som ska sökas
     * @return elementet om det hittas, annars null
     */
    @Override
    public T searchElement(T elementToFind) {
        if (elementToFind == null) {
            return null;
        }
        TreeNode result = recursiveSearch(root, elementToFind);
        return result != null ? result.data : null;
    }

    /**
     * Rekursiv hjälpmetod för att söka efter ett element.
     *
     * @param current       noden som för närvarande undersöks
     * @param elementToFind elementet som söks
     * @return noden om den hittas, annars null
     */
    private TreeNode recursiveSearch(TreeNode current, T elementToFind) {
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

    /**
     * Returnerar en sträng med trädets innehåll i sorterad ordning.
     * <p>
     * Innehållet skrivs ut med mellanslag mellan elementen.
     *
     * @return en sorterad strängrepresentation av trädet
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        recursiveToString(root, sb);
        return sb.toString();
    }

    /**
     * Rekursiv inorder-traversering som bygger upp en sorterad sträng.
     *
     * @param current noden som undersöks
     * @param sb      StringBuilder som strängen byggs i
     */
    private void recursiveToString(TreeNode current, StringBuilder sb) {
        if (current != null) {
            recursiveToString(current.left, sb);

            if (sb.length() > 0) {
                sb.append(" ");
            }
            sb.append(current.data);

            recursiveToString(current.right, sb);
        }
    }

    /**
     * Intern nodklass som lagrar ett värde samt pekare till vänster och höger barn.
     */
    private class TreeNode {
        private T data;
        private TreeNode left;
        private TreeNode right;

        /**
         * Skapar en ny nod som innehåller det givna värdet.
         *
         * @param data värdet som ska lagras i noden
         */
        private TreeNode(T data) {
            this.data = data;
        }
    }
}
