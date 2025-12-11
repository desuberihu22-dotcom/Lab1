package se.hig.aod.lab1;

import java.io.*;
import java.util.*;

public class Benchmark {
        public static void main(String[] args) {
                ArrayList<Integer> allData = null;
                try {
                        allData = loadDataFromFile("Lab1/Data/unique_integers.txt");
                } catch (IOException e) {
                        System.err.println("Error loading data file: " + e.getMessage());
                        return;
                }
                int numberOfSearches = 10000;
                int[] sizes = { 10000, 20000,40000, 80000, 160000, 320000, 640000, 1280000, 2560000};

                String l = "%-9s|";

                System.out.printf(l + l + l + " (Time in nanosecounds)\n", "Size", "BST     ", "ArrayList");

                Random random = new Random();
                int[] searchKeys = new int[numberOfSearches];
                int maxActualSize = Math.min(sizes[sizes.length - 1], allData.size());

                for (int i = 0; i < numberOfSearches; i++) {
                        if (i % 2 == 0 && maxActualSize > 0) {
                                searchKeys[i] = allData.get(random.nextInt(maxActualSize));
                        } else {
                                searchKeys[i] = random.nextInt(Integer.MAX_VALUE);
                        }
                }

                for (int size : sizes) {
                        runBenchmark(size, numberOfSearches, allData, searchKeys);
                }
        }

        private static void runBenchmark(int size, int numberOfSearches, ArrayList<Integer> dataSource, int[] searchKeys) {
                BinarySearchTree<Integer> bst = new BinarySearchTree<>();
                ArrayList<Integer> arrayList = new ArrayList<>();

                int actualSize = Math.min(size, dataSource.size());

                for (int i = 0; i < actualSize; i++) {
                        int value = dataSource.get(i);
                        bst.addElement(value);
                        arrayList.add(value);
                }

                long startTime = System.nanoTime();
                for (int key : searchKeys) {
                        bst.searchElement(key);
                }
                long stopTime = System.nanoTime();
                long bstDuration = (stopTime - startTime) / numberOfSearches;

                startTime = System.nanoTime();
                for (int key : searchKeys) {
                        arrayList.contains(key);
                }
                long stopTime2 = System.nanoTime();
                long listDuration = (stopTime2 - startTime) / numberOfSearches;

                String l = "%-9d |";

                System.out.printf(l + l + l + "\n", actualSize, bstDuration, listDuration);
        }

        private static ArrayList<Integer> loadDataFromFile(String filename) throws IOException {
                ArrayList<Integer> data = new ArrayList<>();
                BufferedReader reader = new BufferedReader(new FileReader(filename));

                String line = reader.readLine();

                while (line != null) {
                        line = line.trim();
                        if (!line.isEmpty()) {
                                try {
                                        data.add(Integer.parseInt(line));
                                } catch (NumberFormatException e) {
                                }
                        }
                        line = reader.readLine();
                }
                reader.close();
                System.out.println("Loaded " + data.size() + " integers from " + filename + "\n");
                return data;
        }
}
