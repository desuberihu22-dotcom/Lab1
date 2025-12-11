package se.hig.aod.lab1;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Utför empiriska studier av söktiden i ett binärt sökträd (BST)
 * enligt uppgift 2 i laboration 1.
 *
 * Använder BinarySearchTree från uppgift 1.
 */
public class Benchmark {

        private static final String DATA_FILE = "unique_integers.txt";

        private static final int NUM_SEARCH_ELEMENTS = 2_500_000;
        private static final int NUM_REPEATS = 10;

        public static void main(String[] args) throws Exception {
                // A: Skapa lista med de 2 500 000 elementen som ska sökas efter
                List<Integer> elementsToSearchFor = loadListFromFile(DATA_FILE, NUM_SEARCH_ELEMENTS);

                // --- Studie 1: shufflad data ---
                int[] sizesStudy1 = {
                                10_000, 20_000, 40_000, 80_000,
                                160_000, 320_000, 640_000,
                                1_280_000, 2_560_000
                };

                System.out.println("=== Studie 1: Shufflad data ===");
                runStudy(sizesStudy1, elementsToSearchFor, false);

                // --- Studie 2: sorterad data ---
                int[] sizesStudy2 = { 10_000, 20_000 };

                System.out.println();
                System.out.println("=== Studie 2: Sorterad data ===");
                runStudy(sizesStudy2, elementsToSearchFor, true);
        }

        /**
         * Kör en studie (antingen shufflad eller sorterad data) för givna N-värden.
         *
         * @param sizes                alla N-värden som ska testas
         * @param elementsToSearchFor  listan med de 2 500 000 elementen att söka efter
         * @param sortInsteadOfShuffle true = sortera data, false = shuffla
         */
        private static void runStudy(int[] sizes,
                        List<Integer> elementsToSearchFor,
                        boolean sortInsteadOfShuffle) throws IOException {

                System.out.println("N\tT(N) [ms]\tkvot");

                long previousAvgTime = -1;

                for (int N : sizes) {
                        long totalTime = 0;

                        for (int r = 0; r < NUM_REPEATS; r++) {
                                // B2: Läs in N element från filen → dataList
                                List<Integer> dataList = loadListFromFile(DATA_FILE, N);

                                // B3: Shuffla eller sortera listan
                                if (sortInsteadOfShuffle) {
                                        Collections.sort(dataList);
                                } else {
                                        Collections.shuffle(dataList);
                                }

                                // B1 + B4: Skapa nytt BST och fyll med dataList
                                BinarySearchTree<Integer> bst = new BinarySearchTree<>();
                                for (int value : dataList) {
                                        bst.addElement(value);
                                }

                                // B5: Mät tiden att söka efter 2 500 000 element
                                long t1 = System.currentTimeMillis();
                                for (int key : elementsToSearchFor) {
                                        bst.searchElement(key);
                                }
                                long elapsed = System.currentTimeMillis() - t1;

                                totalTime += elapsed;
                        }

                        long avgTime = totalTime / NUM_REPEATS;

                        String ratioStr = "-";
                        if (previousAvgTime > 0) {
                                double ratio = (double) avgTime / previousAvgTime;
                                ratioStr = String.format(Locale.US, "%.2f", ratio);
                        }

                        System.out.printf("%d\t%d\t%s%n", N, avgTime, ratioStr);
                        previousAvgTime = avgTime;
                }
        }

        /**
         * Läser in 'size' antal heltal från filen 'path' och returnerar dem som en
         * lista.
         * Om filen tar slut innan 'size' tal har lästs skrivs ett felmeddelande ut.
         */
        private static List<Integer> loadListFromFile(String path, int size)
                        throws FileNotFoundException, IOException {

                List<Integer> list = new ArrayList<>();
                int cnt = 0;

                try (Scanner in = new Scanner(new FileReader(path))) {
                        while (cnt < size && in.hasNextLine()) {
                                String line = in.nextLine();
                                try {
                                        list.add(Integer.parseInt(line));
                                        cnt++;
                                } catch (NumberFormatException nfe) {
                                        System.err.printf(
                                                        "Not an integer while reading from data file \"%s\": %s (ignoring)%n",
                                                        path, nfe.getLocalizedMessage());
                                }
                        }
                }

                if (cnt != size) {
                        System.err.printf("Didn't read %d integers, only %d.%n", size, cnt);
                }

                return list;
        }
}
