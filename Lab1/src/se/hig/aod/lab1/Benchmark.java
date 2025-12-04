package se.hig.aod.lab1;

import java.util.*;

public class Benchmark {

        public static void main(String[] args) {
                int[] sizes = { 100, 1000, 5000, 10000, 20000 };
                int numberOfSearches = 1000;

                System.out.println("Benchmark: BinarySearchTree vs ArrayList (Search Performance)");
                System.out.println("-------------------------------------------------------------");
                System.out.printf("%-10s | %-15s | %-15s%n", "Size", "BST (ns)", "ArrayList (ns)");
                System.out.println("-------------------------------------------------------------");
        }
}
