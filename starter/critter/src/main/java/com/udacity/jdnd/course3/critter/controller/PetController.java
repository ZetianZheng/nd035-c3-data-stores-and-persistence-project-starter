package com.udacity.jdnd.course3.critter.controller;

import com.udacity.jdnd.course3.critter.data.Customer;
import com.udacity.jdnd.course3.critter.data.Pet;
import com.udacity.jdnd.course3.critter.pet.PetDTO;
import com.udacity.jdnd.course3.critter.service.CustomerService;
import com.udacity.jdnd.course3.critter.service.PetService;
import com.udacity.jdnd.course3.critter.transfer.PetTransfer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {
    /** @Autowired **/
    private final PetTransfer petTransfer;
    private final PetService petService;
    private final CustomerService customerService;
    private Logger logger = LoggerFactory.getLogger(UserController.class);

    public PetController(PetTransfer petTransfer, PetService petService, CustomerService customerService) {
        this.petTransfer = petTransfer;
        this.petService = petService;
        this.customerService = customerService;
    }

    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {
        logger.info(petDTO.toString());
        if (petDTO == null) {
            return null;
        }
        /** save pet **/
        Pet pet = petTransfer.convertToPet(petDTO);
        petService.save(pet);

        /** get Customer and update Customer pets**/
        Customer customer = customerService.getById(petDTO.getOwnerId());
        customer.addPet(pet);
        customerService.save(customer);

        /** return petDTO **/
        return petTransfer.convertToPetDTO(pet);
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {
        Pet pet = petService.getPetById(petId);
        return petTransfer.convertToPetDTO(pet);
    }

    /** todo: 要将:convertToPetDTO改为静态? **/
    @GetMapping
    public List<PetDTO> getPets(){
        List<Pet> pets = petService.getAllPets();
        return petTransfer.convertToPetDTOList(pets);
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
        List<Pet> pets = petService.getPetsByCustomerId(ownerId);
        return petTransfer.convertToPetDTOList(pets);
    }


}
