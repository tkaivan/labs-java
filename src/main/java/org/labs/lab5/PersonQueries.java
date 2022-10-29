package org.labs.lab5;

public class PersonQueries extends BaseQuery<Person> {
  public PersonQueries() {
    super(Person.class);
  }

  @Override
  @Condition("id = %s")
  public String check(Object value) throws Exception {
    return check(this.getClass(), value);
  }
}
