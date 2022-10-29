package org.labs.lab5;

import java.time.LocalDate;
import java.time.Period;

/**
 * Implements Person
 */
@Table("persons")
public class Person {
    @Column("id")
    protected int id;
    @Column("first_name")
    protected String firstName;
    @Column("last_name")
    protected String lastName;

    /**
     * Provide access to id
     *
     * @return person id
     */
    public int getId() {
        return this.id;
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
     * Create builder for Person
     *
     * @return Person Builder
     */
    public static Builder builder() {
        return new Person().new Builder();
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
         * Set the first name to Person
         *
         * @param firstName person first name
         * @return Builder
         */
        public Builder setFirstName(String firstName) throws IllegalArgumentException {
            Person.this.firstName = firstName;
            return this;
        }

        /**
         * Set the last name to Person
         *
         * @param lastName person last name
         * @return Builder
         */
        public Builder setLastName(String lastName) throws IllegalArgumentException {
            Person.this.lastName = lastName;
            return this;
        }

        /**
         * Build the Person object
         *
         * @return Person object
         */
        public Person build() {
            return Person.this;
        }
    }
}
