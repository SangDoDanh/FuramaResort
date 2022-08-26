package models.person;

import models.person.Person;

public class Customer extends Person {
    private String rank;
    private String address;

    public Customer() {
    }

    public Customer(String id, String fullName, String dayOfBirth, String gender, String identity, String numberPhone, String email, String rank, String address) {
        super(id, fullName, dayOfBirth, gender, identity, numberPhone, email);
        this.rank = rank;
        this.address = address;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(",,%s,,%s", rank, address);
    }
}
