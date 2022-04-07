package com.udacity.jdnd.course3.critter.data;

import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.time.LocalDate;

/** pet entity design **/

@Entity
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="pet_type", length = 50)
    private String type;

    @Nationalized
    @Column(length = 50)
    private String name;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    private LocalDate birthDate;
    private String notes;

    /** constructor **/
    public Pet(Long id, String type, String name, Customer customer, LocalDate birthDate, String notes) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.customer = customer;
        this.birthDate = birthDate;
        this.notes = notes;
    }

    public Pet(String type, String name, Customer customer, LocalDate birthDate, String notes) {
        this.type = type;
        this.name = name;
        this.customer = customer;
        this.birthDate = birthDate;
        this.notes = notes;
    }

    public Pet() {

    }

    /** getter and setter **/
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
