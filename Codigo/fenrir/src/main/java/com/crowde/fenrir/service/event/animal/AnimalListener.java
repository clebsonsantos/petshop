package com.crowde.fenrir.service.event.animal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.crowde.fenrir.storage.FotoStorage;

@Component
public class AnimalListener {

	@Autowired
	private FotoStorage fotoStorage;
	
	@EventListener(condition="#evento.temFoto() and #evento.novaFoto")
	public void animalSalvo(AnimalSalvoEvent evento) {
		fotoStorage.salvar(evento.getAnimal().getImagem());		
		
	}
}
