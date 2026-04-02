package br.com.ucsal.olimpiadas.service;

import br.com.ucsal.olimpiadas.model.Prova;
import br.com.ucsal.olimpiadas.repository.ProvaRepository;

public class ProvaService {

	private ProvaRepository repository;

	public ProvaService(ProvaRepository repository) {
		this.repository = repository;
	}
	
	public Prova cadastrarProva(String titulo) {
		if (titulo == null || titulo.isBlank()) {
			 throw new IllegalArgumentException("Título inválido");
		}
		Prova prova = new Prova();
		prova.setTitulo(titulo);
		return repository.salvar(prova);
	}
	
}
