package springframework.sfgpetclinic.services.map;

import org.springframework.stereotype.Service;
import springframework.sfgpetclinic.model.Owner;
import springframework.sfgpetclinic.model.Pet;
import springframework.sfgpetclinic.services.OwnerService;
import springframework.sfgpetclinic.services.PetService;
import springframework.sfgpetclinic.services.PetTypeService;

import java.util.Set;
@Service
public class OwnerServiceMap extends AbstractMapService<Owner, Long> implements OwnerService {
    private final PetTypeService petTypeService;
    private final PetService petService;

    public OwnerServiceMap(PetTypeService petTypeService, PetService petService) {
        this.petTypeService = petTypeService;
        this.petService = petService;
    }

    @Override
    public Set<Owner> findAll() {
        return super.findAll();
    }

    @Override
    public Owner findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Owner save(Owner object) {
        if(object != null) {
            //save a new petType associated with a pet associated with the owner
            if(object.getPets() != null) {
                object.getPets().forEach(pet -> {
                    if(pet.getPetType() != null) {
                        if(pet.getPetType().getId() == null) {
                            pet.setPetType(petTypeService.save(pet.getPetType()));
                        }
                    }
                    //save a new pet associated with the owner
                    if(pet.getId() == null) {
                        Pet savedPet = petService.save(pet);
                        pet.setId(savedPet.getId());
                    }
                    else {
                        throw new RuntimeException("Pet Type is required!");
                    }
                });
            }
            return super.save(object);
        }
        else {
            return null;
        }

    }

    @Override
    public void delete(Owner object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public Owner findByLastName(String lastName) {
        return null;
    }
}
