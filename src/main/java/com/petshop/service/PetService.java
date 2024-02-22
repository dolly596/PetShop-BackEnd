package com.petshop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petshop.DTO.PetDTO;
import com.petshop.entities.Pet;
import com.petshop.repository.PetRepository;

@Service
public class PetService {
	
	private final PetRepository PetRepository;

	@Autowired
	public PetService(PetRepository PetRepository) {
		this.PetRepository = PetRepository;
	}

	public List<Pet> buscaTodosDog(){
		return PetRepository.findAll();
	}

	public Pet buscaDogId (Long id) {
		Optional <Pet> dog = PetRepository.findById(id);
		return dog.orElse(null);			
	}

	public PetDTO salvaDog(PetDTO petDTO) {
		Pet Dog = new Pet (petDTO.nome(), petDTO.nascimento(), petDTO.cuidador());
		Pet salvarDog = PetRepository.save(Dog);
		return new PetDTO(salvarDog.getId(), salvarDog.getNome(), salvarDog.getNascimento(), salvarDog.getCuidador());
	}

	public PetDTO alterarDog (Long id, PetDTO petDTO) {
		Pet existeDog = PetRepository.findById(id).orElseThrow(() -> new RuntimeException("Animal não encontrado"));
		existeDog.setNome(petDTO.nome());
		existeDog.setNome(petDTO.nascimento());
		existeDog.setNome(petDTO.cuidador());
		
		Pet atualizarDog = PetRepository.save(existeDog);
		return new PetDTO(atualizarDog.getId(), atualizarDog.getNome(), atualizarDog.getNascimento(), atualizarDog.getCuidador());
	}

	public boolean apagarDog(Long id) {
		Optional <Pet> existeDog= PetRepository.findById(id);
		if (existeDog.isPresent()) {
			PetRepository.deleteById(id);
			return true;
		}
		return false;
	}
	
	public List<Pet> buscarTodos(){
		return PetRepository.findAll();
	}
	public Pet buscarPorId (Long id){
		Optional <Pet> dog = PetRepository.findById(id);
		return dog.orElse(null);
	}
}
