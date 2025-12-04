package se.hig.aod.lab1;

/**
 * A Java interface for a searchable data structure, 
 * i.e. a data structure that can store elements
 * that can also be searched for.
 * 
 * @author Hanna Holmgren
 *
 * @param <T> The data type that is to be stored in the data structure.
 */
public interface SearchableDataStructure<T> {

	/**
	 * Returns the number of elements in the data structure
	 * 
	 * @return an integer representing the size of the BST
	 */
	public int size();
	
	/**
	 * Adds a new element to the data structure
	 * 
	 * @param newElement, the element to be added 
	 */
	public void addElement(T newElement);
	
	/**
	 * Searches for an element in the data structure. Returns the searched element if it exists, otherwise null.
	 * 
	 * @param elementToFind, the element that one is looking for.
	 * @return T, the element searched for. If it does not exist, returns null.
	 */
	public T searchElement(T elementToFind);
	
	/**
	 * Converts the data structure to a sorted string representation of the data structure. 
	 * The string should only consist of the elements ordered in sorted order, with no 
	 * blank spaces or commas. Example of possible outputs:
	 * "123456789"
	 * "abcdefg" 
	 * 
	 * @return a sorted string representation of the data 
	 */
	public String toString();
}
