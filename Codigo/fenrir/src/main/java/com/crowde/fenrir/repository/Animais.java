package com.crowde.fenrir.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crowde.fenrir.model.Animal;

@Repository
public interface Animais extends JpaRepository<Animal, Long> {	
	public List<Animal> findByClienteNomeStartingWithIgnoreCase(String nomeCliente);
	public List<Animal> findByClienteTelefoneCelularStartingWithIgnoreCase(String telefone);
	public List<Animal> findByClienteCpfCnpjStartingWithIgnoreCase(String cpf);
	public List<Animal> findByNomeStartingWithIgnoreCase(String nomeAnimal);
	public List<Animal> findByProntuarioMatriculaStartingWithIgnoreCase(String matricula);
	public Animal findByProntuarioId(Long id);
}
