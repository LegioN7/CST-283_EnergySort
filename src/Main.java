import java.io.*;
import java.util.Scanner;

// CST-283
// Aaron Pelto
// Winter 2024

// Using the provided array from energy.txt
// Alter the bubble sort algorithm to sort the array in descending order and sort the data chronologically by year
public class Main {
    static final String FILENAME = "energy.txt";
    static final int NUMBER_MONTHS = 36;

    public static void main(String[] args) {
        int[] month = new int[NUMBER_MONTHS];
        int[] year = new int[NUMBER_MONTHS];
        int[] energy = new int[NUMBER_MONTHS];

        try   // Capture file
        {
            File dataFileRef = new File(FILENAME);
            String oneLine = "";

            // Check for file existence.  If not found, display error and crash
            if (!dataFileRef.exists()) {
                System.out.println("File not found");
                System.exit(0);
            }

            int i = 0;

            Scanner inputFile = new Scanner(dataFileRef);
            while (inputFile.hasNext()) {
                // File processing loop.
                oneLine = inputFile.nextLine();

                String[] dataLine = oneLine.split(",");

                month[i] = Integer.parseInt(dataLine[0]);
                year[i] = Integer.parseInt(dataLine[1]);
                energy[i] = Integer.parseInt(dataLine[2]);

                i++;
            }
        } catch (IOException e)    // If file error, display message and crash
        {
            System.out.println("File error");
            System.exit(0);
        }

        displayArrays(month, year, energy);           // Display initial lists

        sortChronologically(month, year, energy);     // Sort by time
        displayArrays(month, year, energy);           // Display

        sortByDescendingEnergy(month, year, energy);  // Sort energy usage - descending
        displayArrays(month, year, energy);           // Display

    }

    // Method displays parallel arrays in current order
    public static void displayArrays(int[] month, int[] year, int[] energy) {
        System.out.println("\n");
        for (int i = 0; i < NUMBER_MONTHS; i++) {
            System.out.printf("%2d-%4d ==> %5d%n", month[i], year[i], energy[i]);
        }
        System.out.println("\n");
    }

    // Sort arrays ascending chronologically
    public static void sortChronologically(int[] month, int[] year, int[] energy) {
        System.out.println("Sorting chronologically");
        for (int i = 0; i < NUMBER_MONTHS - 1; i++) {
            for (int j = 0; j < NUMBER_MONTHS - i - 1; j++) {
                // If month is greater or year is greater, swap
                // If month is equal, compare year
                // If month and year are equal, do nothing
                // If month is less, do nothing
                // If month is equal and year is less, do nothing
                // If month is less and year is greater, swap
                if (month[j] > month[j + 1] || (month[j] == month[j + 1] && year[j] > year[j + 1])) {
                    // Swap month
                    int tempMonth = month[j];
                    month[j] = month[j + 1];
                    month[j + 1] = tempMonth;

                    // Swap year
                    int tempYear = year[j];
                    year[j] = year[j + 1];
                    year[j + 1] = tempYear;

                    // Swap energy
                    int tempEnergy = energy[j];
                    energy[j] = energy[j + 1];
                    energy[j + 1] = tempEnergy;
                }
            }
        }
    }

    // Sort arrays descending by energy usage
    public static void sortByDescendingEnergy(int[] month, int[] year, int[] energy) {
        System.out.println("Sorting by descending energy usage");
        for (int i = 0; i < NUMBER_MONTHS - 1; i++) {
            for (int j = 0; j < NUMBER_MONTHS - i - 1; j++) {
                if (energy[j] > energy[j + 1]) {
                    // Swap month
                    int tempMonth = month[j];
                    month[j] = month[j + 1];
                    month[j + 1] = tempMonth;

                    // Swap year
                    int tempYear = year[j];
                    year[j] = year[j + 1];
                    year[j + 1] = tempYear;

                    // Swap energy
                    int tempEnergy = energy[j];
                    energy[j] = energy[j + 1];
                    energy[j + 1] = tempEnergy;
                }
            }
        }
    }
}