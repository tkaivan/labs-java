package org.labs.lab0;

public class Variant6 {
    public enum UNIT_OF_LENGTH {
        DECIMETER, KILOMETER, METER, MILLIMETER, CENTIMETER
    }

    /**
     * @param number a two-digit number
     * @return a array number where the first digit is tens, the second digit is
     * units
     */
    public int[] integerNumbersTask(int number) {
        return new int[]{number / 10, number % 10};
    }

    /**
     * @param first  the first number
     * @param second the second number
     * @param third  the third number
     * @return true if {@code first} < {@code second} < {@code third} otherwise
     * false
     */
    public boolean booleanTask(int first, int second, int third) {
        return first < second && second < third;
    }

    /**
     * @param first  the first number
     * @param second the second number
     * @return the largest number
     */
    public int ifTask(int first, int second) {
        return first < second ? second : first;
    }

    /**
     * @param unit   the unit of length
     * @param length
     * @return length in meters
     */
    public double switchTask(UNIT_OF_LENGTH unit, double length) {
        return switch (unit) {
            case DECIMETER -> length * 0.1;
            case KILOMETER -> length * 1000;
            case METER -> length;
            case MILLIMETER -> length * 0.001;
            case CENTIMETER -> length * 0.01;
        };
    }

    /**
     * @param price price of 1kg of sweets
     * @return array of costs 1.2, 1.4, ... 2 kg of sweets
     */
    public double[] forTask(double price) {
        if (price < 1) {
            throw new IllegalArgumentException("Invalid price: " + price);
        }

        double[] costs = new double[5];
        int i = 0;
        for (double kg = 1.2; kg <= 2; kg += 0.2, i++) {
            costs[i] = Math.ceil(kg * price * 100) / 100;
        }
        return costs;
    }

    /**
     * @param number
     * @return double factorial
     */
    public double whileTask(int number) {
        if (number < 0) {
            throw new IllegalArgumentException("Argument should be more than 0");
        }

        double factorial = 1;
        while (number > 0) {
            factorial *= number;
            number -= 2;
        }
        return factorial;
    }

    /**
     * @param length the length of array
     * @param first  the first number
     * @param second the second number
     * @return an integer array of size {@code length}, the first element of which
     * is
     * equal to {@code first},
     * the second is equal to {@code second}, and each subsequent element is
     * equal
     * to the sum of all previous one
     */
    public int[] arrayTask(int length, int first, int second) {
        if (length < 2) {
            throw new IllegalArgumentException("Argument should be more than 2");
        }

        int[] array = new int[length];
        int sum = first + second;
        array[0] = first;
        array[1] = second;

        for (int i = 2; i < length; i++) {
            array[i] = sum;
            sum += array[i];
        }
        return array;
    }

    /**
     * @param rows         rows of matrix
     * @param columns      columns of matrix
     * @param number       number
     * @param setOfNumbers set of numbers
     * @return matrix where each column of the matrix contains elements of a
     * geometric progression
     */
    public int[][] matrixTask(int rows, int columns, int number, int[] setOfNumbers) {
        int[][] matrix = new int[rows][columns];

        System.arraycopy(setOfNumbers, 0, matrix[0], 0, setOfNumbers.length);

        for (int i = 1; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                matrix[i][j] = matrix[i - 1][j] * number;
            }
        }

        return matrix;
    }
}
