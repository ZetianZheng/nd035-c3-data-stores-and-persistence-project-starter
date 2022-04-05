package com.udacity.jdnd.course3.critter.transfer;

import com.udacity.jdnd.course3.critter.controller.UserController;
import com.udacity.jdnd.course3.critter.data.Customer;
import com.udacity.jdnd.course3.critter.data.Pet;
import com.udacity.jdnd.course3.critter.pet.PetDTO;
import com.udacity.jdnd.course3.critter.service.CustomerService;
import com.udacity.jdnd.course3.critter.service.PetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PetTransfer {
    private final CustomerService customerService;
    private final PetService petService;
    private Logger logger = LoggerFactory.getLogger(UserController.class);

    public PetTransfer(CustomerService customerService, PetService petService) {
        this.customerService = customerService;
        this.petService = petService;
    }

    public Pet convertToPet(PetDTO petDTO) {
        Pet pet = new Pet();
        BeanUtils.copyProperties(petDTO, pet);

        /** set customer **/
        Long customerId = petDTO.getOwnerId();
        Customer customer = customerService.getById(customerId);
        pet.setCustomer(customer);

        return pet;
    }

    public List<Pet> convertToPetList(List<Long> petIds) {
        List<Pet> pets = new ArrayList<>();
        if (petIds == null || petIds.size() == 0) {
            logger.warn("petIds is null!");
            return pets;
        }

        for (Long petId : petIds) {
            pets.add(petService.getPetById(petId));
        }
        return pets;
    }

    public PetDTO convertToPetDTO(Pet pet) {
        PetDTO petDTO = new PetDTO();
        BeanUtils.copyProperties(pet, petDTO);

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
