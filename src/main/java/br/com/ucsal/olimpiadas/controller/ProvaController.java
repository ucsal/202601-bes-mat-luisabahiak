package br.com.ucsal.olimpiadas.controller;

import java.util.Scanner;

import br.com.ucsal.olimpiadas.model.Prova;
import br.com.ucsal.olimpiadas.repository.ProvaRepository;
import br.com.ucsal.olimpiadas.service.ProvaService;

public class ProvaController {

	private final ProvaService service;
	private final ProvaRepository repository;

	public ProvaController(ProvaService service,ProvaRepository repository ) {
		this.service = service;
		this.repository = repository;
	}


	public void cadastrar(Scanner in) {
		System.out.print("Título da prova: ");
		var titulo = in.nextLine();

		try {
			Prova prova = service.cadastrarProva(titulo);
			System.out.println("Prova criada: " + prova.getId());
		}
		catch (Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	public Long escolherProva(Scanner in) {
		System.out.println("\nProvas:");
		for (Prova p : repository.listar()) {
			System.out.printf("  %d) %s%n", p.getId(), p.getTitulo());
		}
		System.out.print("Escolha o id da prova: ");

		try {
			long id = Long.parseLong(in.nextLine());
			Prova prova = repository.buscarPorId(id);

			if (prova == null) {
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
