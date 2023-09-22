package com.ziko.entities;

import com.ziko.generators.UUIDGenerator;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@Entity //name used here is the name used in the context
@Table(name = "employee") // name used in the table
public class Employee {

    @Id
    @GenericGenerator(name = "UUIDGenerator", type = UUIDGenerator.class)
    @GeneratedValue(generator = "UUIDGenerator")
    @Column(length = 500)
    private String id;
    @Column(columnDefinition = "")
    private String name;
    private String address;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
