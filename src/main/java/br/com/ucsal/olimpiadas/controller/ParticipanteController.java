package br.com.ucsal.olimpiadas.controller;

import java.util.Scanner;

import br.com.ucsal.olimpiadas.model.Participante;
import br.com.ucsal.olimpiadas.repository.ParticipanteRepository;

public class ParticipanteController {
	
	private final ParticipanteRepository repository;
	
	public ParticipanteController(ParticipanteRepository repository) {
		this.repository = repository;
		
	}
	
	public void cadastrar(Scanner in) {
		System.out.print("Nome: ");
		var nome = in.nextLine();

		System.out.print("Email (opcional): ");
		var email = in.nextLine();

		if (nome == null || nome.isBlank()) {
			System.out.println("nome inválido");
			return;
		}

		Participante p = new Participante();
		p.setNome(nome);
		p.setEmail(email);
		repository.salvar(p);

		System.out.println("Participante cadastrado: " + p.getId());
	}
	
	public Long escolherParticipante(Scanner in) {
		System.out.println("\nParticipantes:");
		for (Participante p : repository.listar()) {
			System.out.printf("  %d) %s%n", p.getId(), p.getNome());
		}
		System.out.print("Escolha o id do participante: ");

		try {
			long id = Long.parseLong(in.nextLine());
			boolean existe = repository.listar().stream().anyMatch(p -> p.getId() == id);
			if (!existe) {
				System.out.println("id inválido");
				return null;
			}
			return id;
		} catch (Exception e) {
			System.out.println("entrada inválida");
			return null;
		}
	}
	
}
