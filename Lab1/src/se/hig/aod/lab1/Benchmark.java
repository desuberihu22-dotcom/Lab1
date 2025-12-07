package se.hig.aod.lab1;

import java.util.ArrayList;
import java.util.Random;

public class Benchmark {

        public static void main(String[] args) {
                int[] sizes = { 100, 1000, 5000, 10000, 20000 };
                int numberOfSearches = 1000;

                System.out.println("Benchmark: BinarySearchTree vs ArrayList (Search Performance)");
                System.out.println("-------------------------------------------------------------");
                System.out.printf("%-10s | %-15s | %-15s%n", "Size", "BST (ns)", "ArrayList (ns)");
                System.out.println("-------------------------------------------------------------");

                for (int size : sizes) {
                        runBenchmark(size, numberOfSearches);
                }
        }

        private static void runBenchmark(int size, int numberOfSearches) {
                BinarySearchTree<Integer> bst = new BinarySearchTree<>();
                ArrayList<Integer> arrayList = new ArrayList<>();
                Random random = new Random();

                int[] data = new int[size];
                for (int i = 0; i < size; i++) {
                        data[i] = random.nextInt(size * 10);
                        bst.addElement(data[i]);
                        arrayList.add(data[i]);
                }

                int[] searchKeys = new int[numberOfSearches];
                for (int i = 0; i < numberOfSearches; i++) {
                        searchKeys[i] = random.nextInt(size * 10);
                }

                long startTime = System.nanoTime();
                for (int key : searchKeys) {
                        bst.searchElement(key);
                }
                long bstDuration = (System.nanoTime() - startTime) / numberOfSearches;

                startTime = System.nanoTime();
                for (int key : searchKeys) {
                        arrayList.contains(key);
                }
                long listDuration = (System.nanoTime() - startTime) / numberOfSearches;

                System.out.printf("%-10d | %-15d | %-15d%n", size, bstDuration, listDuration);
        }
}
