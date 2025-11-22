package ru.otus.java.basic.homeworks.homework22;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class ArrayHelperTest {
    ArrayHelper helper;

    @BeforeEach
    public void before() {
        helper = new ArrayHelper();
    }

    @ParameterizedTest
    @MethodSource("provideSliceAfterLastExceptionData")
    public void sliceAfterLastExceptionTest(int[] array) {
        Assertions.assertThrowsExactly(RuntimeException.class, () -> helper.sliceAfterLast(array, 1));
    }

    @ParameterizedTest
    @MethodSource("provideSliceAfterLastRegularData")
    public void sliceAfterLastRegularTest(int[] expected, int[] array, int number) {
        int[] result = helper.sliceAfterLast(array, number);
        Assertions.assertArrayEquals(expected, result);
    }

    @ParameterizedTest
    @MethodSource("provideHasOnlyData")
    public void hasOnly(boolean expected, int[] array, int numberOne,  int numberTwo) {
        boolean result = helper.hasOnly(array, numberOne, numberTwo);
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void hasOnlyExceptionTest() {
        Assertions.assertThrowsExactly(RuntimeException.class, () -> helper.hasOnly(new int[]{1, 2}, 1, 1));
    }

    public static Stream<Arguments> provideSliceAfterLastExceptionData() {
        return Stream.of(
                Arguments.of((Object) null),
                Arguments.of((Object) new int[]{}),
                Arguments.of((Object) new int[]{5, 2, 3, 2, 2})
        );
    }

    public static Stream<Arguments> provideSliceAfterLastRegularData() {
        return Stream.of(
                Arguments.of(new int[]{2, 2}, new int[]{1, 2, 1, 2, 2}, 1),
                Arguments.of(new int[]{}, new int[]{5, 2, 3, 2, 1}, 1),
                Arguments.of(new int[]{1, 5}, new int[]{1, 3, 1, 3, 1, 5}, 3)
        );
    }

    public static Stream<Arguments> provideHasOnlyData() {
        return Stream.of(
                Arguments.of(false, null, 1, 2),
                Arguments.of(false, new int[]{}, 1, 2),
                Arguments.of(true, new int[]{1, 2}, 1, 2),
                Arguments.of(false, new int[]{1, 1}, 1, 2),
                Arguments.of(false, new int[]{1, 3}, 1, 2),
                Arguments.of(true, new int[]{1, 2, 2, 1}, 1, 2),
                Arguments.of(false, new int[]{1, 3, 2, 1}, 1, 3),
                Arguments.of(true, new int[]{1, 3, 3, 1}, 1, 3)
        );
    }
}
