package org.example;

import org.example.model.Animal;
import org.example.model.Person;
import org.example.model.Role;
import org.example.model.Species;
import org.example.repository.AnimalRepository;
import org.example.repository.PersonRepository;
import org.example.repository.RoleRepository;
import org.example.repository.SpeciesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class Main implements CommandLineRunner {

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private SpeciesRepository speciesRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private RoleRepository roleRepository;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        // 1. findAll - afficher tous les animaux
        System.out.println("=== findAll ===");
        List<Animal> animaux = animalRepository.findAll();
        animaux.forEach(System.out::println);

        // 2.1 save - créer un nouvel animal
        System.out.println("\n=== new species ===");
        Species pipotam = new Species();
        pipotam.setCommonName("Pipotam");
        pipotam.setLatinName("Pipotamus fictus");
        speciesRepository.save(pipotam);
        Animal nouveauPipotam = new Animal();
        nouveauPipotam.setName("NON YUKI LES GAAARS");
        nouveauPipotam.setColor("blanc");
        nouveauPipotam.setSex("M");
        nouveauPipotam.setSpecies(pipotam);
        Animal pipotamSauvegarde = animalRepository.save(nouveauPipotam);
        System.out.println("Animal créé : " + pipotamSauvegarde);

        // 2.2 save - créer un autre nouvel animal
        System.out.println("\n=== save ===");
        Species especeChat = speciesRepository.findById(1).orElseThrow();
        Animal nouvelAnimal = new Animal();
        nouvelAnimal.setName("Ame");
        nouvelAnimal.setColor("Noir et blanc et gris");
        nouvelAnimal.setSex("M");
        nouvelAnimal.setSpecies(especeChat);
        Animal animalSauvegarde = animalRepository.save(nouvelAnimal);
        System.out.println("Animal créé : " + animalSauvegarde);

        // 3. findById - rechercher par id
        System.out.println("\n=== findById ===");
        Optional<Animal> trouve = animalRepository.findById(animalSauvegarde.getId());
        trouve.ifPresent(a -> System.out.println("Trouvé : " + a));

        // 2.3 save - créer un rôle et une personne
        //System.out.println("\n=== new person & role ===");
        //Role role = new Role();
        //role.setLabel("Admin");
        //roleRepository.save(role);

        //Person mcsoupape = new Person();
        //mcsoupape.setFirstname("Mc");
        //mcsoupape.setLastname("Soupape");
        //mcsoupape.setLogin("McSoupape");
        //mcsoupape.setMdp("password");
        //mcsoupape.setAge(24);
        //mcsoupape.setActive(true);
        //mcsoupape.setRoles(List.of(role));
        //personRepository.save(mcsoupape);
        //System.out.println("Personne créée : " + mcsoupape);
        //

        // 4. delete - supprimer et vérifier
        System.out.println("\n=== delete ===");
        animalRepository.delete(animalSauvegarde);
        System.out.println("Nombre d'animaux après suppression : " + animalRepository.findAll().size());
    }
}
