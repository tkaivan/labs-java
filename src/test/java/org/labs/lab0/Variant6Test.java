package org.labs.lab0;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import org.labs.lab0.Variant6.UNIT_OF_LENGTH;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

public class Variant6Test {
    private final static Variant6 variant6 = new Variant6();

    @DisplayName("Should calculate the correct tens and units")
    @ParameterizedTest(name = "[{index}] number={0}, result={1}")
    @MethodSource("integerNumbersProvider")
    void integerNumbersTest(int number, int[] result) {
        assertArrayEquals(variant6.integerNumbersTask(number), result);
    }

    @DisplayName("The first number must be less than the second, and the second less than the third")
    @ParameterizedTest(name = "[{index}] {0} < {1} < {2}: {3}")
    @MethodSource("booleanProvider")
    void booleanTest(int first, int second, int third, boolean result) {
        assertEquals(variant6.booleanTask(first, second, third), result);
    }

    @DisplayName("Should return the largest number")
    @ParameterizedTest(name = "[{index}] first={0}, second={1}, result={2}")
    @MethodSource("ifProvider")
    void ifTest(int first, int second, int result) {
        assertEquals(variant6.ifTask(first, second), result);
    }

    @DisplayName("Should calculate the correct length in meters")
    @ParameterizedTest(name = "[{index}] unit={0}, length={1}, result={2}")
    @MethodSource("switchProvider")
    void switchTest(UNIT_OF_LENGTH unit, double length, double result) {
        assertEquals(variant6.switchTask(unit, length), result);
    }

    @DisplayName("Should calculate the correct costs of 1.2, 1.4, ..., 2 kg of sweets")
    @ParameterizedTest(name = "[{index}] price={0}, result={1}")
    @MethodSource("forProvider")
    void forTest(double price, double[] result) {
        assertArrayEquals(variant6.forTask(price), result);
    }

    @DisplayName("Should throw IllegalArgumentException")
    @Test
    void forThrowExceptionTest() {
        assertThrows(IllegalArgumentException.class, () -> variant6.forTask(-1.2));
    }

    @DisplayName("Should calculate the correct double factorial")
    @ParameterizedTest(name = "[{index}] number={0}, result={1}")
    @MethodSource("whileProvider")
    void whileTest(int number, double result) {
        assertEquals(variant6.whileTask(number), result);
    }

    @DisplayName("Should throw IllegalArgumentException")
    @Test
    void whileThrowExceptionTest() {
        assertThrows(IllegalArgumentException.class, () -> variant6.whileTask(-3));
    }

    @ParameterizedTest(name = "[{index}] length={0}, first={1}, second={2}, result={3}")
    @MethodSource("arrayProvider")
    void arrayTest(int length, int first, int second, int[] result) {
        assertArrayEquals(variant6.arrayTask(length, first, second), result);
    }

    @DisplayName("Should throw IllegalArgumentException")
    @Test
    void arrayThrowExceptionTest() {
        assertThrows(IllegalArgumentException.class, () -> variant6.arrayTask(1, 3, 2));
    }

    @ParameterizedTest(name = "[{index}] rows={0}, columns={1}, number={2}, set={3}, result={4}")
    @MethodSource("matrixProvider")
    void matrixTest(int rows, int columns, int number, int[] set, int[][] result) {
        assertArrayEquals(variant6.matrixTask(rows, columns, number, set), result);
    }

    private static Stream<Arguments> integerNumbersProvider() {
        return Stream.of(
                Arguments.of(32, new int[]{3, 2}),
                Arguments.of(14, new int[]{1, 4}),
                Arguments.of(99, new int[]{9, 9}),
                Arguments.of(72, new int[]{7, 2}));
    }

    private static Stream<Arguments> booleanProvider() {
        return Stream.of(
                Arguments.of(1, 2, 3, true),
                Arguments.of(4, 6, 1, false),
                Arguments.of(-1, 0, -1, false),
                Arguments.of(-1, 0, 1, true));
    }

    private static Stream<Arguments> ifProvider() {
        return Stream.of(
                Arguments.of(3, 2, 3),
                Arguments.of(-1, 0, 0),
                Arguments.of(4, 4, 4),
                Arguments.of(7, 9, 9));
    }

    private static Stream<Arguments> switchProvider() {
        return Stream.of(
                Arguments.of(UNIT_OF_LENGTH.DECIMETER, 5.0, 0.5),
                Arguments.of(UNIT_OF_LENGTH.KILOMETER, 34.0, 34000.0),
                Arguments.of(UNIT_OF_LENGTH.METER, 17.56, 17.56),
                Arguments.of(UNIT_OF_LENGTH.MILLIMETER, 127.8, 0.1278),
                Arguments.of(UNIT_OF_LENGTH.CENTIMETER, 356.0, 3.56));
    }

    private static Stream<Arguments> forProvider() {
        return Stream.of(
                Arguments.of(3.5, new double[]{4.2, 4.9, 5.6, 6.3, 7.0}),
                Arguments.of(1.2, new double[]{1.44, 1.68, 1.92, 2.16, 2.4}));
    }

    private static Stream<Arguments> whileProvider() {
        return Stream.of(
                Arguments.of(5, 15.0),
                Arguments.of(10, 3840),
                Arguments.of(3, 3),
                Arguments.of(6, 48),
                Arguments.of(25, 7_905_853_580_625.0));
    }

    private static Stream<Arguments> arrayProvider() {
        return Stream.of(
                Arguments.of(5, 3, 2, new int[]{3, 2, 5, 10, 20}));
    }

    private static Stream<Arguments> matrixProvider() {
        return Stream
                .of(Arguments.of(3, 3, 4, new int[]{1, 2, 3}, new int[][]{{1, 2, 3}, {4, 8, 12}, {16, 32, 48}}));
    }
}