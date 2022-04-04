package com.udacity.jdnd.course3.critter.data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customer")
@AttributeOverrides({
        @AttributeOverride(name="id",
                column = @Column(name="customer_id")),
        @AttributeOverride(name="name",
                column = @Column(name="customer_name"))
})
public class Customer extends User{
    /**
     * make sure to specify mappedBy. Lazy fetch optional,
     * but often a good idea for collection attributes
     *
     * if it is removed, it will also remove any Plants associated with it at the same time:
     * added CascadeType.REMOVE to automatically clear any associated plants when removed
     */

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Pet> pets;

    /** constructor **/
    public Customer(List<Pet> pets) {
        this.pets = pets;
    }

    public Customer() {

    }

    /** setter and getter **/
    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }

    /** add pet to pet lists **/
    public void addPet(Pet pet) {
        if (pets == null) {
            pets = new ArrayList<>();
        }

        this.pets.add(pet);
    }
}
