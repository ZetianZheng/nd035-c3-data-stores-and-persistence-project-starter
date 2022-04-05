package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.data.Customer;
import com.udacity.jdnd.course3.critter.data.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    /**
     * use to: get owner by pet
     * equal to: … where x.firstname like ?1 (parameter bound wrapped in %)
     * customer -> pet: one to many, so one pet only referenced to one customer
     * **/
    Customer findByPetsContaining(Pet pet);
}
