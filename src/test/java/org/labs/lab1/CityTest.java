package org.labs.lab1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class CityTest {
    List<Person> people = new ArrayList<>();
    City city;

    CityTest() throws Exception {
        Worker person1 = Worker.builder().setBirthday(LocalDate.of(1991, 10, 12)).setExperience(3)
                .setFirstName("Dmytro")
                .setLastName("Yakubovych")
                .setSalary(1000)
                .setId(1)
                .setIsEmployed(false)
                .setIsFemale(false).build();
        Worker person2 = Worker.builder().setBirthday(LocalDate.of(2002, 10, 9)).setExperience(3)
                .setFirstName("Ivan")
                .setLastName("Tkachuk")
                .setSalary(2000)
                .setId(1)
                .setIsEmployed(false)
                .setIsFemale(false).build();

        people.add(person1);
        people.add(person2);
        city = City.builder().setName("Lviv").setState("Lvivska").setListPeople(people).build();
    }

    @Test
    void filterByAgeTest() {
        List<Worker> expected = new ArrayList<>();
        expected.add((Worker) people.get(1));

        assertEquals(expected, city.filterByAge(20));
    }

    @Test
    void maxSalaryTest() {
        var expected = people.get(1);
        var personWithMaxSalary = city.maxSalary();
        assertTrue(personWithMaxSalary.isPresent());
        assertEquals(expected, personWithMaxSalary.get());
    }

    @Test
    void sortedByLastNameTest() {
        List<Person> expected = new ArrayList<>();
        expected.add(people.get(1));
        expected.add(people.get(0));

        assertEquals(expected, city.sortedByLastName());
    }
}
