package com.udacity.jdnd.course3.critter.data;

import org.hibernate.annotations.Nationalized;

import javax.persistence.*;

/**
 * inherited by Customer and Employee;
 * because I used attributeOverrides: https://docs.oracle.com/javaee/6/api/javax/persistence/AttributeOverride.html
 * must be applied to an entity that extends a mapped superclass or to an embedded field
 */
//@Entity
//@Table(name = "user")
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@MappedSuperclass
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    @Nationalized
    private String name;

    @Column(length = 500)
    private String notes;

    @Column(name = "phone_number", length = 20)
    private String phoneNumber;

    /** constructor **/
    public User() {
    }

    public User(Long id, String name, String notes, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.notes = notes;
        this.phoneNumber = phoneNumber;
    }
    /** getter and setter **/
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
