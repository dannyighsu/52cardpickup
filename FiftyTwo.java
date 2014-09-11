import java.util.Random;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;

/** 52cardpickup simulator.
 *  @author D. Hsu */

public class FiftyTwo {

    /** Class to determine the number of tries it takes to
     * sort a deck of cards through "52 Card Pickup". */
    public static void main(String args[]) {
        int size = 0;
        if (args.length == 0) {
            size = 52;
        } else if (args.length != 1) {
            usage();
        } else {
            size = Integer.parseInt(args[0]);
        }
        int tries = 0;
        ArrayList<Integer> cards = new ArrayList<Integer>();
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < size - 1; i++) {
            cards.add(i);
        }
        do {
            shuffle(cards);
            tries++;
        } while (!sorted(cards));
        System.out.println(tries + " in " + (System.currentTimeMillis() - startTime)
        + " milliseconds");
    }

    /** Check whether ARRAY is sorted. Assumes no duplicate
     *  entries. */
    private static boolean sorted(List<Integer> list) {
        for (int i = 0; i < list.size() - 2; i++) {
            if (list.get(i + 1) <= list.get(i)) {
                return false;
            }
        }
        return true;
    }

    /** The Fisher-Yates shuffle. */
    private static void shuffle(List<Integer> list) {
        Random rand = new Random();
        for (int i = 0; i < list.size() - 1; i++) {
            int index = rand.nextInt(i + 1);
            int a = list.get(index);
            list.set(index, list.get(i));
            list.set(i, a);
        }
    }

    /** Print a brief usage message and exit program abnormally. */
    private static void usage() {
        Scanner usage;
        try {
            usage = new Scanner(new File("Usage.txt"));
        } catch (IOException e) {
            System.out.println("No help found.");
            System.exit(1);
            return;
        }
        while (usage.hasNextLine()) {
            System.out.println(usage.nextLine());
        }
        usage.close();
        System.exit(1);
    }

}
