package models.person;

import java.util.Objects;

public abstract class Person {
    private String id;
    private String fullName;
    private String dayOfBirth;
    private String gender;
    private String identity;
    private String numberPhone;
    private String email;
    public Person() {
    }
    public Person(String id, String fullName, String dayOfBirth, String gender, String identity, String numberPhone, String email) {
        this.id = id;
        this.fullName = fullName;
        this.dayOfBirth = dayOfBirth;
        this.gender = gender;
        this.identity = identity;
        this.numberPhone = numberPhone;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDayOfBirth() {
        return dayOfBirth;
    }

    public void setDayOfBirth(String dayOfBirth) {
        this.dayOfBirth = dayOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getNumberPhone() {
        return numberPhone;
    }

    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return String.format("%s,,%s,,%s,,%s,,%s,,%s,,%s",
                id, fullName, dayOfBirth, gender, identity, numberPhone, email);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(id, person.id) && identity.equals(person.identity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, identity);
    }
}
