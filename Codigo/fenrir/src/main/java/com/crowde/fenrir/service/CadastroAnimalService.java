package com.crowde.fenrir.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crowde.fenrir.model.Animal;
import com.crowde.fenrir.repository.Animais;
import com.crowde.fenrir.service.event.animal.AnimalSalvoEvent;

@Service
public class CadastroAnimalService {

	@Autowired
	private Animais animais;

	@Autowired
	private ApplicationEventPublisher publisher;

	public List<Animal> todos() {
		return animais.findAll();
	}

	@Transactional
	public void salvar(Animal animal) {

		if(animal.isNovo()) {
			animal.getProntuario().setDataCadastro(new Date());
			animal.getProntuario().setDataUltimaEdicao(new Date());
			animal.setStatusAnimal(true);
			String matricula = gerarMatricula(animal.getProntuario().getDataCadastro());
			animal.getProntuario().setMatricula(matricula);			
		}else {
			Animal animalExistente = animais.findOne(animal.getId());
			animal.setProntuario(animalExistente.getProntuario());
		}

		animais.save(animal);

		publisher.publishEvent(new AnimalSalvoEvent(animal));

	}

	private String gerarMatricula(Date dataCadastro) {
		SimpleDateFormat format = new SimpleDateFormat("ddMMHHmmss");
		return format.format(dataCadastro);
	}
}
