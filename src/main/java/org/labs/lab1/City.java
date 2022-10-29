package org.labs.lab1;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Objects;

import org.labs.lab2.TxtSerializable;
import org.labs.lab3.ComparatorFactory;
import org.labs.lab4.Validator;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class City implements TxtSerializable<City> {
    @NotNull
    @Pattern(regexp = "[A-z][a-z]{0,19}")
    private String name;
    @NotNull
    @Pattern(regexp = "[A-z][a-z]{0,19}")
    private String state;
    private final List<Person> people = new ArrayList<>();

    /**
     * Provider access to name
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Provide access to state
     *
     * @return state
     */
    public String getState() {
        return state;
    }

    /**
     * Provide access to {@code people}
     *
     * @return list of people
     */
    public List<Person> getListPeople() {
        return people;
    }

    @Override
    public String toString() {
        return "City={" +
                " name='" + getName() + "'" +
                ", state='" + getState() + "'" +
                ", people='" + people + "'" +
                "}";
    }

    public static Builder builder() {
        return new City().new Builder();
    }

    public List<Person> sortedByLastName() {
        return this.people.stream()
                .sorted(ComparatorFactory.lexicographic())
                .toList();
    }

    public List<Person> filterByAge(int age) {
        return this.people.stream()
                .filter(person -> person.getAge() == age)
                .toList();
    }

    public Optional<Person> maxSalary() {
        return this.people.stream().max(ComparatorFactory.salary());
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof City city)) {
            return false;
        }
        return Objects.equals(name, city.name) && Objects.equals(state, city.state)
                && Objects.equals(people, city.people);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, state, people);
    }

    @Override
    public String serializeToString(City city) {
        return "City={" +
                " name='" + getName() + "'" +
                ", state='" + getState() + "'" +
                ", people='" + people + "'" +
                "}";
    }

    @Override
    public City deserializeFromString(String data) {
        var parts = Serialize.getPartsOfCity(data);
        var people = Serialize.getPeopleFromString(data);
        return City.builder()
                .setName(parse(parts[0]))
                .setState(parse(parts[1]))
                .setListPeople(people)
                .build();
    }

    private static class Serialize {
        static final String pattern = "people='[";

        static String[] getPartsOfCity(String data) {
            return data.substring(0, data.indexOf(pattern)).split(",");
        }

        static List<Person> getPeopleFromString(String data) {
            List<Person> people = new ArrayList<>();
            String personsString = data.substring(data.indexOf(pattern) + pattern.length(), data.indexOf("]"));
            String[] personsStrings = personsString.split("Person=");
            for (String person : personsStrings) {
                if (person.length() > 0) {
                    people.add(Person.builder().build().deserializeFromString(person));
                }
            }
            return people;
        }
    }

    public class Builder {
        /**
         * Set name to City
         *
         * @return Builder for City
         */
        public Builder setName(String name) {
            City.this.name = name;
            return this;
        }

        /**
         * Set state to City
         *
         * @return Builder for City
         */
        public Builder setState(String state) {
            City.this.state = state;
            return this;
        }

        /**
         * Set people to {@code City}
         *
         * @return Builder for City
         */
        public Builder setListPeople(List<Person> list) {
            City.this.people.addAll(list);
            return this;
        }

        public City build() {
            return Validator.validateAndReturn(City.this);
        }

        public Builder addPerson(Person person) {
            City.this.people.add(person);
            return this;
        }
    }
}
