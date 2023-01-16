package org.labs.lab2;

import java.util.ArrayList;
import java.util.List;

import org.labs.lab1.City;
import org.labs.lab1.Person;
import org.labs.lab4.BadValidationException;

public class TxtCityTest extends TxtSerializeTest<City> {
    TxtCityTest() {
        super(City.class);
    }

    @Override
    protected City createObject() throws BadValidationException {
        List<Person> listPeople = new ArrayList<>();
        listPeople.add(Person.builder().setId(1).setFirstName("Ivan").setLastName("Tkachuk").setIsEmployed(false)
                .setIsFemale(false).build());
        listPeople.add(Person.builder().setId(1).setFirstName("Andrii").setLastName("Liashenko").setIsEmployed(false)
                .setIsFemale(false).build());
        return City.builder().setName("Lviv").setState("Ukraine").setListPeople(listPeople).build();
    }

    @Override
    protected String getFilename() {
        return "city.txt";
    }
}
