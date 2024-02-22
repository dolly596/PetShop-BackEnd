package com.petshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.petshop.DTO.PetDTO;
import com.petshop.entities.Pet;
import com.petshop.service.PetService;

import jakarta.validation.Valid;




@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/petshop")
public class PetController {
		
		private final PetService petService;

		@Autowired
		public PetController(PetService petService) {
			this.petService = petService;
		}

		@GetMapping ("/{id}")

		public ResponseEntity<Pet> buscaDogIdControlId (@ PathVariable Long id) {
			Pet dog = petService.buscaDogId(id);
			if (dog != null) {
				return ResponseEntity.ok(dog);
			}
			else {
				return ResponseEntity.notFound().build();
			}
		}

		@GetMapping("/")
		public ResponseEntity<List<Pet>> buscaTodosDogControl(){
			List<Pet> dog = petService.buscaTodosDog();
			return ResponseEntity.ok(dog);
		}
		@PostMapping("/")
		public ResponseEntity<PetDTO> salvaDogControl(@RequestBody  @Valid PetDTO petDTO){
			PetDTO salvaDog= petService.salvaDog(petDTO);
			return ResponseEntity.status(HttpStatus.CREATED).body(salvaDog);
		}
		@PutMapping("/{id}")
		public ResponseEntity<PetDTO> alterarDogControl(@PathVariable Long id, @RequestBody @Valid PetDTO petDTO){
			PetDTO alterarDog = petService.alterarDog(id, petDTO);
			if(alterarDog != null) {
				return ResponseEntity.ok(alterarDog);
			}
			else {
				return ResponseEntity.notFound().build();
			}
		}
		@DeleteMapping("/{id}")
		public ResponseEntity<Pet> apagaDogControl(@PathVariable Long id){
			boolean dog = petService.apagarDog(id);
			if (dog) {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			}
			else {
				return ResponseEntity.notFound().build();
			}
		}
}
