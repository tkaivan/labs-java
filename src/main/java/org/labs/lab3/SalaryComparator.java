package org.labs.lab3;

import org.labs.lab1.Person;
import org.labs.lab1.Worker;

import java.util.Comparator;

public class SalaryComparator implements Comparator<Person> {
    @Override
    public int compare(Person o1, Person o2) {
        Worker w1 = (Worker) o1;
        Worker w2 = (Worker) o2;
        return w1.compareTo(w2);
    }
}