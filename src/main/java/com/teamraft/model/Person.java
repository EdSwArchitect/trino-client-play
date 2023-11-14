package com.teamraft.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Person {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    long theId;
    long updatedAt;
    String firstName;
    String lastName;
    long age;

    public Person(long theId, long updatedAt, String firstName, String lastName, long age) {
        this.theId = theId;
        this.updatedAt = updatedAt;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public long getTheId() {
        return theId;
    }

    public void setTheId(long theId) {
        this.theId = theId;
    }

    public long getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(long updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public long getAge() {
        return age;
    }

    public void setAge(long age) {
        this.age = age;
    }

    @Override
    public String toString() {
        try {
            return objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            final StringBuffer sb = new StringBuffer("Person{");
            sb.append("theId=").append(theId);
            sb.append(", updatedAt=").append(updatedAt);
            sb.append(", firstName='").append(firstName).append('\'');
            sb.append(", lastName='").append(lastName).append('\'');
            sb.append(", age=").append(age);
            sb.append('}');
            return sb.toString();
        }
    }
}
