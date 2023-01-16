package org.labs.lab2;

import java.util.ArrayList;
import java.util.List;

import org.labs.lab1.City;
import org.labs.lab1.Person;
import org.labs.lab4.BadValidationException;

public class XmlCityTest extends XmlSerializeTest<City> {
    XmlCityTest() {
        super(City.class);
    }

    @Override
    protected City createObject() throws BadValidationException {
        List<Person> listPeople = new ArrayList<>();
        listPeople.add(Person.builder().setId(1).setFirstName("Ivan").setLastName("Tkachuk").build());
        listPeople.add(Person.builder().setId(2).setFirstName("Andrii").setLastName("Liashenko").build());
        return City.builder().setName("Lviv").setState("Ukraine").setListPeople(listPeople).build();
    }
}
