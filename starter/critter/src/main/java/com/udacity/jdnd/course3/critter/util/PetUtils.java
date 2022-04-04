package com.udacity.jdnd.course3.critter.util;

import com.udacity.jdnd.course3.critter.data.Customer;
import com.udacity.jdnd.course3.critter.data.Pet;
import com.udacity.jdnd.course3.critter.pet.PetDTO;
import com.udacity.jdnd.course3.critter.service.CustomerService;
import com.udacity.jdnd.course3.critter.service.PetService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PetUtils {
    CustomerService customerService;
    PetService petService;

    public Pet convertToPet(PetDTO petDTO) {
        Pet pet = new Pet();
        BeanUtils.copyProperties(pet, petDTO);

        /** set customer **/
        Long customerId = petDTO.getOwnerId();
        Customer customer = customerService.getById(customerId);
        pet.setCustomer(customer);

        return pet;
    }

    public List<Pet> convertToPetList(List<Long> petIds) {
        if (petIds == null) {
            return null;
        }
        List<Pet> pets = new ArrayList<>();
        for (Long petId : petIds) {
            pets.add(petService.getPetById(petId));
        }
        return pets;
    }

    public PetDTO convertToPetDTO(Pet pet) {
        PetDTO petDTO = new PetDTO();
        BeanUtils.copyProperties(petDTO, pet);

        /** set customer id **/
        petDTO.setOwnerId(pet.getCustomer().getId());

        return petDTO;
    }

    public List<PetDTO> convertToPetDTOList(List<Pet> pets) {
        return pets.stream()
                .map(this::convertToPetDTO)
                .collect(Collectors.toList());
    }

    public List<Long> convertToPetIds(List<Pet> pets) {
        return pets.stream()
                .map(Pet::getId)
                .collect(Collectors.toList());
    }


}
