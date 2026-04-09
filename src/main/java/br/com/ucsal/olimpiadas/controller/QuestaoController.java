package br.com.ucsal.olimpiadas.controller;

import java.util.Scanner;

import br.com.ucsal.olimpiadas.model.Questao;
import br.com.ucsal.olimpiadas.repository.ProvaRepository;
import br.com.ucsal.olimpiadas.repository.QuestaoRepository;

public class QuestaoController {
	private final QuestaoRepository questaoRepository;
	private final ProvaRepository provaRepository;
	private final ProvaController provaController;
	


	public QuestaoController(QuestaoRepository qRepository, ProvaRepository pRepository, ProvaController provaController) {
		this.questaoRepository = qRepository;
		this.provaRepository = pRepository;
		this.provaController = provaController;
	}
	
	public void cadastrar(Scanner in) {
		if (provaRepository.listar().isEmpty()) {
			System.out.println("não há provas cadastradas");
			return;
		}

		var provaId = provaController.escolherProva(in);
		if (provaId == null)
			return;

		System.out.println("Enunciado:");
		var enunciado = in.nextLine();

		var alternativas = new String[5];
		for (int i = 0; i < 5; i++) {
			char letra = (char) ('A' + i);
			System.out.print("Alternativa " + letra + ": ");
			alternativas[i] = letra + ") " + in.nextLine();
		}

		System.out.print("Alternativa correta (A–E): ");
		char correta;
		try {
			correta = Questao.normalizar(in.nextLine().trim().charAt(0));
		} catch (Exception e) {
			System.out.println("alternativa inválida");
			return;
		}

		var q = new Questao();
		q.setProvaId(provaId);
		q.setEnunciado(enunciado);
		q.setAlternativas(alternativas);
		q.setAlternativaCorreta(correta);

		questaoRepository.salvar(q);

		System.out.println("Questão cadastrada: " + q.getId() + " (na prova " + provaId + ")");
	
	}
	

}
