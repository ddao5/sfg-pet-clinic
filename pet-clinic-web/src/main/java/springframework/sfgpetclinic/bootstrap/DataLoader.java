package springframework.sfgpetclinic.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import springframework.sfgpetclinic.model.Owner;
import springframework.sfgpetclinic.model.Vet;
import springframework.sfgpetclinic.services.OwnerService;
import springframework.sfgpetclinic.services.VetService;
import springframework.sfgpetclinic.services.map.OwnerServiceMap;
import springframework.sfgpetclinic.services.map.VetServiceMap;


@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;

    //Refactoring later using DI
    public DataLoader() {
        ownerService = new OwnerServiceMap();
        vetService = new VetServiceMap();
    }

    //id assignment must be refactored
    @Override
    public void run(String... args) throws Exception {
        Owner owner1 = new Owner();
        owner1.setId(1L);
        owner1.setFirstName("Michael");
        owner1.setLastName("Weston");

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setId(2L);
        owner2.setFirstName("Fiona");
        owner2.setLastName("Glenanne");

        ownerService.save(owner2);

        System.out.println("Loaded Owners....");

        Vet vet1 = new Vet();
        vet1.setId(1L);
        vet1.setFirstName("Sam");
        vet1.setLastName("Axe");

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setId(2L);
        vet2.setFirstName("Sam");
        vet2.setLastName("Axe");

        vetService.save(vet2);

        System.out.println("Loaded Vets...");
    }
}
