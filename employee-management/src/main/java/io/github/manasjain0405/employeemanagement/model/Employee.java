package io.github.manasjain0405.employeemanagement.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name="Employee")
@Table(name="employees")
public class Employee implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "first_name", nullable = false)
    String firstName;

    @Column(name = "last_name",nullable = false)
    String lastName;

    @Column(name = "phone_no", nullable = false)
    long phoneNo;

    @Column(name = "address")
    String address;

    public Employee() {
    }

    public Employee(Long id, String firstName, String lastName, long phoneNo, String address) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNo = phoneNo;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public long getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(long phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
