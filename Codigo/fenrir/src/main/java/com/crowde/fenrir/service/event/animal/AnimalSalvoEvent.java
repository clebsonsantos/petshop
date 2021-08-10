package com.crowde.fenrir.service.event.animal;

import org.springframework.util.StringUtils;

import com.crowde.fenrir.model.Animal;

public class AnimalSalvoEvent {
	
	private Animal animal;
	
	public AnimalSalvoEvent(Animal animal) {
		this.animal = animal;
	}

	public Animal getAnimal() {
		return animal;
	}
	
	public boolean temFoto() {
		return !StringUtils.isEmpty(animal.getImagem());
	}
	
	public boolean isNovaFoto() {
		return animal.isNovaFoto();
	}
}
