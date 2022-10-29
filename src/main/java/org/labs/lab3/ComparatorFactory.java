package org.labs.lab3;

import org.labs.lab1.Person;

import java.util.Comparator;

public class ComparatorFactory {
    private static final Comparator<Person> FIRSTNAMPERSON_COMPARATOR = new LexicographicComparator();
    private static final Comparator<Person> AGEPERSON_COMPARATOR = new AgeComparator();
    private static final Comparator<Person> SALARYWORKER_COMPARATOR = new SalaryComparator();

    public static Comparator<Person> lexicographic() {
        return FIRSTNAMPERSON_COMPARATOR;
    }

    public static Comparator<Person> age() {
        return AGEPERSON_COMPARATOR;
    }

    public static Comparator<Person> salary() {
        return SALARYWORKER_COMPARATOR;
    }
}