package com.petshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.petshop.entities.Pet;

public interface PetRepository extends JpaRepository<Pet, Long>{

}
