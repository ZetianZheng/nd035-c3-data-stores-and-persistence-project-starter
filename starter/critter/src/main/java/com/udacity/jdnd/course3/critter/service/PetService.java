package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.data.Pet;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import com.udacity.jdnd.course3.critter.exception.PetNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PetService {
    @Autowired
    PetRepository petRepository;

    public Long save(Pet pet) {
        petRepository.save(pet);

        return pet.getId();
    }

    /** [讲讲Java8的Optional类 - SegmentFault 思否](https://segmentfault.com/a/1190000038471657) **/
    public Pet getPetById(Long id) {
        Optional<Pet> pet = petRepository.findById(id);
//        if (pet.isPresent()) {
//            return pet.get();
//        } else {
//            throw new PetNotFoundException("Pet not found");
//        }
        return pet.orElseThrow(PetNotFoundException::new);
    }

    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }

    public List<Pet> getPetsByCustomerId(Long customerId) {
        return petRepository.findPetByCustomerId(customerId);
    }
}
