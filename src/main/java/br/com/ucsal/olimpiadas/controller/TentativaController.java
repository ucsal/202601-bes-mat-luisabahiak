package br.com.ucsal.olimpiadas.controller;

import java.util.Scanner;

import br.com.ucsal.olimpiadas.model.Questao;
import br.com.ucsal.olimpiadas.model.Resposta;
import br.com.ucsal.olimpiadas.model.Tentativa;
import br.com.ucsal.olimpiadas.repository.ParticipanteRepository;
import br.com.ucsal.olimpiadas.repository.ProvaRepository;
import br.com.ucsal.olimpiadas.repository.QuestaoRepository;
import br.com.ucsal.olimpiadas.repository.TentativaRepository;
import br.com.ucsal.olimpiadas.service.TentativaService;

public class TentativaController {
	private final ParticipanteRepository participanteRepository;
	private final ProvaRepository provaRepository;
	private final QuestaoRepository questaoRepository;
	private final TentativaRepository tentativaRepository;
	private final ProvaController provaController;
	private final ParticipanteController participanteController;
	private final TentativaService tentativaService;
	
	public TentativaController(ParticipanteRepository participanteRepository, ProvaRepository provaRepository,
			QuestaoRepository questaoRepository, TentativaRepository tentativaRepository, ProvaController provaController, ParticipanteController participanteController, TentativaService tentativaService) {
		this.participanteRepository = participanteRepository;
		this.provaRepository = provaRepository;
		this.questaoRepository = questaoRepository;
		this.tentativaRepository = tentativaRepository;
		this.provaController = provaController;
		this.participanteController = participanteController;
		this.tentativaService = tentativaService;
	}
	
	public void aplicarProva(Scanner in) {
		if (participanteRepository.listar().isEmpty()) {
			System.out.println("cadastre participantes primeiro");
			return;
		}
		if (provaRepository.listar().isEmpty()) {
			System.out.println("cadastre provas primeiro");
			return;
		}

		var participanteId = participanteController.escolherParticipante(in);
		if (participanteId == null)
			return;

		var provaId = provaController.escolherProva(in);
		if (provaId == null)
			return;
		
		var questoesDaProva = questaoRepository.listar().stream().filter(q -> q.getProvaId() == provaId).toList();

		if (questoesDaProva.isEmpty()) {
			System.out.println("esta prova não possui questões cadastradas");
			return;
		}

		var tentativa = new Tentativa();
		tentativaRepository.salvar(tentativa);
		tentativa.setProvaId(provaId);

		System.out.println("\n--- Início da Prova ---");

		for (var q : questoesDaProva) {
			System.out.println("\nQuestão #" + q.getId());
			System.out.println(q.getEnunciado());

			System.out.println("Posição inicial:");
			imprimirTabuleiroFen(q.getFenInicial());

			for (var alt : q.getAlternativas()) {
			    System.out.println(alt);
			}

			System.out.print("Sua resposta (A–E): ");
			char marcada;
			try {
				marcada = Questao.normalizar(in.nextLine().trim().charAt(0));
			} catch (Exception e) {
				System.out.println("resposta inválida (marcando como errada)");
				marcada = 'X';
			}

			var r = new Resposta();
			r.setQuestaoId(q.getId());
			r.setAlternativaMarcada(marcada);
			r.setCorreta(q.isRespostaCorreta(marcada));

			tentativa.getRespostas().add(r);
		}

		tentativaRepository.salvar(tentativa);

		
		int nota = tentativaService.calcularNota(tentativa);
		System.out.println("\n--- Fim da Prova ---");
		System.out.println("Nota (acertos): " + nota + " / " + tentativa.getRespostas().size());
	}
	
	public void listarTentativas() {
		System.out.println("\n--- Tentativas ---");
		for (Tentativa t : tentativaRepository.listar()) {
			System.out.printf("#%d | participante=%d | prova=%d | nota=%d/%d%n", t.getId(), t.getParticipanteId(),
					t.getProvaId(), tentativaService.calcularNota(t), t.getRespostas().size());
		}
	}

	
	public void imprimirTabuleiroFen(String fen) {

		String parteTabuleiro = fen.split(" ")[0];
		String[] ranks = parteTabuleiro.split("/");

		System.out.println();
		System.out.println("    a b c d e f g h");
		System.out.println("   -----------------");

		for (int r = 0; r < 8; r++) {

			String rank = ranks[r];
			System.out.print((8 - r) + " | ");

			for (char c : rank.toCharArray()) {

				if (Character.isDigit(c)) {
					int vazios = c - '0';
					for (int i = 0; i < vazios; i++) {
						System.out.print(". ");
					}
				} else {
					System.out.print(c + " ");
				}
			}

			System.out.println("| " + (8 - r));
		}

		System.out.println("   -----------------");
		System.out.println("    a b c d e f g h");
		System.out.println();
	}
	
	
}
