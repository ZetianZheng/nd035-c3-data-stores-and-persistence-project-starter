package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.data.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface PetRepository extends JpaRepository<Pet, Long> {
    /** find pets by customer id, use to getPetsByOwner **/
    List<Pet> findPetByCustomerId(Long customerId);
}
