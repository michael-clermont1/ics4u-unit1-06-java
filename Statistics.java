/*
* This is a program that calculates mean, median and mode
* after reading in a text file into an array.
*
* @author  Michael Clermont
* @version 1.0
* @since   2022-10-27
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
* This is the statistics program.
*/
final class Statistics {

    /**
    * Prevent instantiation
    * Throw an exception IllegalStateException.
    * if this ever is called
    *
    * @throws IllegalStateException
    *
    */
    private Statistics() {
        throw new IllegalStateException("Cannot be instantiated");
    }

    /**
    * The mean() function.
    *
    * @param arrayOfIntegers the collection of integers
    * @return the mean of the integers
    */
    public static double mean(final Integer[] arrayOfIntegers) {
        double total = 0;
        double mean = 0;
        for (int counter = 0; counter < arrayOfIntegers.length; counter++) {
            total = total + arrayOfIntegers[counter];
        }
        final double length = arrayOfIntegers.length;
        if (length > 0) {
            mean = total / length;
        }
        return mean;
    }

    /**
    * The median() function.
    *
    * @param arrayOfIntegers the collection of integers
    * @return the median of the integers
    */
    public static double median(final Integer[] arrayOfIntegers) {
        double returnValue = 0;
        final int middle = arrayOfIntegers.length / 2;
        if (arrayOfIntegers.length % 2 == 1) {
            returnValue = arrayOfIntegers[middle];
        } else {
            returnValue = (arrayOfIntegers[middle - 1]
                            + arrayOfIntegers[middle]) / 2.0;
        }
        return returnValue;
    }

    /**
    * The mode() function.
    *
    * @param numbers the collection of integers
    * @return the mode of the integers
    */
    public static List<Integer> mode(final Integer[] numbers) {
        int maxCount = 0;
        final List<Integer> modes = new ArrayList<Integer>();
        for (int counter = 0; counter < numbers.length; counter++) {
            int count = 0;
            for (int counter2 = 0; counter2 < numbers.length; counter2++) {
                if (numbers[counter2] == numbers[counter]) {
                    count++;
                }
                if (count > maxCount) {
                    maxCount = count;
                    modes.add(numbers[counter]);
                }
            }
        }
        return modes;
    }

    /**
    * The starting main() function.
    *
    * @param args No args will be used
    */
    public static void main(final String[] args) {
        Integer tempNumber;
        final ArrayList<Integer> listOfNumbers = new ArrayList<Integer>();
        final Path filePath = Paths.get("set1.txt");
        final Charset charset = Charset.forName("UTF-8");

        try (BufferedReader reader = Files.newBufferedReader(
                                     filePath, charset)) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                tempNumber = Integer.parseInt(line);
                listOfNumbers.add(tempNumber);
            }
        } catch (IOException errorCode) {
            System.err.println(errorCode);
        }

        final Integer[] arrayOfNumbers = listOfNumbers.toArray(new Integer[0]);
        System.out.println(Arrays.toString(arrayOfNumbers));

        System.out.println("\nCalculating stats...");
        final double mean = mean(arrayOfNumbers);
        final double median = median(arrayOfNumbers);
        final List<Integer> mode = mode(arrayOfNumbers);

        System.out.println("The mean is: " + mean);
        System.out.println("The median is: " + median);
        System.out.println("The mode(s) is/are: "
                            + Arrays.toString(mode.toArray()));

        System.out.println("\nDone.");
    }
}
