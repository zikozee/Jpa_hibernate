package com.ziko.entities.onetoone;

import jakarta.persistence.*;

@Entity
@Table(name="users") // user is a reserved keyword
@SecondaryTable(
        name = "user_details",
        pkJoinColumns = @PrimaryKeyJoinColumn(name = "id")// if composite foreignKey, use @PrimaryKeyJoinColumns
)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Column(table = "user_details")
    private String description; // say this is found in a table called user_details

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
