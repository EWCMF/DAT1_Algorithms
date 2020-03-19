import java.util.Arrays;
import java.util.Date;
import java.util.Random;

/*
* This is a tiny library for testing how long a given algorithm takes to execute
*
* Usage:
* 1) replace myAlgorithm() with your own algorithm
* 2) change N to test, how long it takes to execute your code
*
* Be aware: You are likely to crash your apps :)
*
* See results of my execution:
* https://docs.google.com/spreadsheets/d/1bNa1zfQ4yqkKcMb42Kh8lSZjfu97V9hx9i1qyvlfAtw/edit#gid=0
*
* Written by András Ács anac@easj.dk in 2017
*
* */
public class Main {

    private static final int N = 10000; // Change the value of N for more or less iterations

    // der laves tre lister med tilfældigt placeret tal, men det skal være samme liste for hver sorteringsalgoritme,
    // så det samme seed bruges til alle tre.
    private static final long seed = 5;

    // Main er ændret til at udføre test på tre sorteringsalgoritmer i samme kørsel.
    public static void main(String[] args) {

        // Array af int dannes vha. metode der instantierer en ny int array med N antal pladser og fylder den med random tal ud fra given seed.
        int[] list = newList();

        long startTime = MyTimer.currentTimestamp();

        Date date=new Date(startTime);
        System.out.println("Time is " + date.toString() + " (" + date.getTime() + ")\n");
        System.out.println("Calculation starting.");

        myAlgorithm(list); // Først QuickSort fra Liang bogen.

        long endTime = MyTimer.currentTimestamp();
        String resultat = MyTimer.timeElapsed(startTime, endTime) ;
        System.out.println();
        System.out.println(resultat);

        System.out.println();
        System.out.println();

        int[] list2 = newList();

        long startTime2 = MyTimer.currentTimestamp();

        Date date2=new Date(startTime2);
        System.out.println("Time is " + date2.toString() + " (" + date2.getTime() + ")\n");
        System.out.println("Calculation starting.");

        myAlgorithm2(list2); // Min bubble sort fra forrige opgave.

        long endTime2 = MyTimer.currentTimestamp();
        String resultat2 = MyTimer.timeElapsed(startTime2, endTime2) ;
        System.out.println();
        System.out.println(resultat2);

        System.out.println();
        System.out.println();

        int[] list3 = newList();

        long startTime3 = MyTimer.currentTimestamp();

        Date date3=new Date(startTime3);
        System.out.println("Time is " + date3.toString() + " (" + date3.getTime() + ")\n");
        System.out.println("Calculation starting.");

        myAlgorithm3(list3); // Javas Array.sort

        long endTime3 = MyTimer.currentTimestamp();
        String resultat3 = MyTimer.timeElapsed(startTime3, endTime3) ;
        System.out.println();
        System.out.println(resultat3);
    }

    private static int[] newList() {
        Random randomSameSeed = new Random(seed);
        int[] list = new int[N];
        for (int i = 0; i < list.length; i++) {
            list[i] = randomSameSeed.nextInt();
        }
        return list;
    }


    /**
     * This is the algorithm, we are testing in the main method
     */
    private static void myAlgorithm(int[] list) {
        quickSort(list);

    }

    private static void myAlgorithm2(int[] list) {
        bubbleSort(list);
    }

    // Java.utils sortering afprøves.
    private static void myAlgorithm3(int[] list) {
        Arrays.sort(list);
    }

    // Taget fra Liang bogen.
    public static void quickSort(int[] list) {
        quickSort(list, 0, list.length - 1);
    }

    private static void quickSort(int[] list, int first, int last) {
        if (last > first) {
            int pivotIndex = partition(list, first, last);
            quickSort(list, first, pivotIndex - 1);
            quickSort(list, pivotIndex + 1, last);
        }
    }

    /** Partition the array list[first..last] */
    private static int partition(int[] list, int first, int last) {
        int pivot = list[first]; // Choose the first element as the pivot
        int low = first + 1; // Index for forward search
        int high = last; // Index for backward search

        while (high > low) {
            // Search forward from left
            while (low <= high && list[low] <= pivot)
                low++;

            // Search backward from right
            while (low <= high && list[high] > pivot)
                high--;

            // Swap two elements in the list
            if (high > low) {
                int temp = list[high];
                list[high] = list[low];
                list[low] = temp;
            }
        }

        while (high > first && list[high] >= pivot)
            high--;

        // Swap pivot with list[high]
        if (pivot > list[high]) {
            list[first] = list[high];
            list[high] = pivot;
            return high;
        }
        else {
            return first;
        }
    }

    // Egen bubble sort.
    private static void bubbleSort(int[] data) {
        // Variabel her er nødvendig for nemt at bytte plads mellem to tal i array.
        int temp;

        // Indført så man kan se antallet af if-statements, for sammenligning med selection sort.

        for (int i = 0; i < data.length; i++) {

            /* Ved hver iteration bliver sorteret sat til true således at hvis ingen sortering sker for gældende index,
            så bryder den looped uden at behøve at fortsætte.*/
            boolean sorteret = true;

            for (int j = 1; j < (data.length - i); j++) {
                if (data[j - 1] > data[j]) {
                    /* temp er nødvendig da
                    data[j - 1] = data[j]
                    data[j] = data[j - 1]
                    selfølgelig bare giver samme tal i stedet for at bytte plads som er hensigten.
                     */
                    temp = data[j - 1];
                    data[j - 1] = data[j];
                    data[j] = temp;
                    sorteret = false;
                }
            }

            if (sorteret) {
                break;
            }
        }
    }
}
