package org.labs.lab1;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

import org.labs.lab2.TxtSerializable;
import org.labs.lab4.BadValidationException;
import org.labs.lab4.Validator;

/**
 * Implements Person
 */
public class Person implements TxtSerializable<Person> {
    @Positive(message = "Id must be greater than zero")
    protected int id;
    @JsonIgnore
    protected LocalDate birthday;

    @Pattern(regexp = "[A-z][a-z]{0,19}")
    protected String firstName;

    @Pattern(regexp = "[A-z][a-z]{0,19}")
    protected String lastName;

    protected boolean employed;

    protected boolean female;

    /**
     * Provide access to id
     *
     * @return person id
     */
    public int getId() {
        return this.id;
    }

    /**
     * Provide access to birthday
     *
     * @return person birthday
     */
    @JsonIgnore
    public LocalDate getBirthday() {
        return this.birthday;
    }

    /**
     * Provide aceess to age
     * 
     * @return age
     */
    @JsonIgnore
    public int getAge() {
        return Period.between(this.birthday, LocalDate.now()).getYears();
    }

    /**
     * Provide access to first name
     *
     * @return person first name
     */
    public String getFirstName() {
        return this.firstName;
    }

    /**
     * Provide access to last name
     *
     * @return person last name
     */
    public String getLastName() {
        return this.lastName;
    }

    /**
     * Provide access to {@code employed}
     *
     * @return true if employed otherwise false
     */
    public boolean isEmployed() {
        return this.employed;
    }

    /**
     * Provide access to {@code female}
     *
     * @return returns {@code true} if female otherwise {@code false}
     */
    public boolean isFemale() {
        return this.female;
    }

    /**
     * Create builder for Person
     *
     * @return Person Builder
     */
    public static Builder builder() {
        return new Person().new Builder();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Person person)) {
            return false;
        }
        return id == person.id && birthday == person.birthday && Objects.equals(firstName, person.firstName)
                && Objects.equals(lastName, person.lastName) && employed == person.employed && female == person.female;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, birthday, firstName, lastName, employed, female);
    }

    @Override
    public String toString() {
        return "Person={" +
                " id='" + getId() + "'" +
                ", birthday='" + getBirthday() + "'" +
                ", firstName='" + getFirstName() + "'" +
                ", lastName='" + getLastName() + "'" +
                ", isEmployed='" + isEmployed() + "'" +
                ", isFemale='" + isFemale() + "'" +
                "}";
    }

    @Override
    public String serializeToString(Person object) {
        return "Person={" +
                " id='" + object.getId() + "'" +
                ", firstName='" + object.getFirstName() + "'" +
                ", lastName='" + object.getLastName() + "'" +
                ", isEmployed='" + object.isEmployed() + "'" +
                ", isFemale='" + object.isFemale() + "'" +
                "}";
    }

    @Override
    public Person deserializeFromString(String data) throws BadValidationException {
        String[] parts = data.substring(0, data.indexOf("}")).split(",");
        if (parts.length > 5) {
            return Person.builder().setId(Integer.parseInt(parse(parts[0])))
                    .setBirthday(null)
                    .setFirstName(parse(parts[2]))
                    .setLastName(parse(parts[3]))
                    .setIsEmployed(Boolean.parseBoolean(parse(parts[4])))
                    .setIsFemale(Boolean.parseBoolean(parse(parts[5]))).build();
        } else {
            return Person.builder().setId(Integer.parseInt(parse(parts[0])))
                    .setBirthday(null)
                    .setFirstName(parse(parts[1]))
                    .setLastName(parse(parts[2]))
                    .setIsEmployed(Boolean.parseBoolean(parse(parts[3])))
                    .setIsFemale(Boolean.parseBoolean(parse(parts[4]))).build();
        }
    }

    public class Builder {
        /**
         * Set the id to Person
         *
         * @param id person id
         * @return Builder
         */
        public Builder setId(int id) {
            Person.this.id = id;
            return this;
        }

        /**
         * Set the age to Person
         *
         * @param birthday person birthday
         * @return Builder
         */
        public Builder setBirthday(LocalDate birthday) {
            Person.this.birthday = birthday;
            return this;
        }

        /**
         * Set the first name to Person
         *
         * @param firstName person first name
         * @return Builder
         */
        public Builder setFirstName(String firstName)  {
            Person.this.firstName = firstName;
            return this;
        }

        /**
         * Set the last name to Person
         *
         * @param lastName person last name
         * @return Builder
         */
        public Builder setLastName(String lastName) {
            Person.this.lastName = lastName;
            return this;
        }

        /**
         * Set if employed Person
         *
         * @param isEmployed true if employed otherwise false
         * @return Builder
         */
        public Builder setIsEmployed(boolean isEmployed) {
            Person.this.employed = isEmployed;
            return this;
        }

        /**
         * Set if Person is female
         *
         * @param isFemale {@code true} if female otherwise {@code false}
         * @return Builder
         */
        public Builder setIsFemale(boolean isFemale) {
            Person.this.female = isFemale;
            return this;
        }

        /**
         * Build the Person object
         *
         * @return Person object
         */
        public Person build() throws BadValidationException {
            return Validator.validateAndReturn(Person.this);
        }
    }
}
