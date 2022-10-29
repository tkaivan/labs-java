package org.labs.lab1;

import java.time.LocalDate;
import java.util.Objects;

import org.labs.lab4.Validator;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

/**
 * Implements Worker
 */
public class Worker extends Person implements Comparable<Worker> {
    @Min(value = 1)
    @Max(value = 100000)
    private int salary;
    private int experience;

    /**
     * Provide access to experience
     *
     * @return experience
     */
    public int getExperience() {
        return this.experience;
    }

    /**
     * Provide access to salary
     *
     * @return salary
     */
    public int getSalary() {
        return this.salary;
    }

    /**
     *
     */
    public void updateExperience() {
        this.experience += 1;
    }

    /**
     * Create builder for Worker
     *
     * @return Worker Builder
     */
    public static Builder builder() {
        return new Worker().new Builder();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Worker worker)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        return salary == worker.salary;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), salary);
    }

    @Override
    public String toString() {
        return super.toString() + ":Worker={" +
                "salary='" + getSalary() + "'" +
                ", experience='" + getExperience() + "'" +
                "}";
    }

    @Override
    public int compareTo(Worker o) {
        return this.salary - o.salary;
    }


    public class Builder extends Person.Builder {
        /**
         * @return Builder for Worker
         */
        public Builder setExperience(int experience) {
            Worker.this.experience = experience;
            return this;
        }

        /**
         * @return Builder for Worker
         */
        public Builder setSalary(int salary) {
            Worker.this.salary = salary;
            return this;
        }

        @Override
        public Builder setId(int id) {
            return (Builder) super.setId(id);
        }

        @Override
        public Builder setBirthday(LocalDate birthday) {
            return (Builder) super.setBirthday(birthday);
        }

        @Override
        public Builder setFirstName(String firstName) throws IllegalArgumentException {
            return (Builder) super.setFirstName(firstName);
        }

        @Override
        public Builder setLastName(String lastName) throws IllegalArgumentException {
            return (Builder) super.setLastName(lastName);
        }

        @Override
        public Builder setIsEmployed(boolean isEmployed) {
            return (Builder) super.setIsEmployed(isEmployed);
        }

        @Override
        public Builder setIsFemale(boolean isFemale) {
            return (Builder) super.setIsFemale(isFemale);
        }

        public Worker build() {
            return Validator.validateAndReturn(Worker.this);
        }
    }
}
