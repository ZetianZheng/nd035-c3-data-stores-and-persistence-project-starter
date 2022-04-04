package com.udacity.jdnd.course3.critter.controller;

import com.udacity.jdnd.course3.critter.data.Customer;
import com.udacity.jdnd.course3.critter.data.Pet;
import com.udacity.jdnd.course3.critter.pet.PetDTO;
import com.udacity.jdnd.course3.critter.service.CustomerService;
import com.udacity.jdnd.course3.critter.service.PetService;
import com.udacity.jdnd.course3.critter.util.PetUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {
    /** @Autowired **/
    PetUtils petUtils;
    PetService petService;
    CustomerService customerService;

    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {
        /** save pet **/
        Pet pet = petUtils.convertToPet(petDTO);
        petService.save(pet);

        /** get Customer and update Customer pets**/
        Customer customer = customerService.getById(petDTO.getOwnerId());
        customer.addPet(pet);
        customerService.save(customer);

        /** return petDTO **/
        return petUtils.convertToPetDTO(pet);
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {
        Pet pet = petService.getPetById(petId);
        return petUtils.convertToPetDTO(pet);
    }

    /** todo: 要将:convertToPetDTO改为静态? **/
    @GetMapping
    public List<PetDTO> getPets(){
        List<Pet> pets = petService.getAllPets();
        return petUtils.convertToPetDTOList(pets);
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
        List<Pet> pets = petService.getPetsByCustomerId(ownerId);
        return petUtils.convertToPetDTOList(pets);
    }


}
