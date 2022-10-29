package org.labs.lab3;

import org.labs.lab1.Person;

import java.util.Comparator;

public class LexicographicComparator implements Comparator<Person> {
    @Override
    public int compare(Person o1, Person o2) {
        return o1.getLastName().compareToIgnoreCase(o2.getLastName());
    }
}