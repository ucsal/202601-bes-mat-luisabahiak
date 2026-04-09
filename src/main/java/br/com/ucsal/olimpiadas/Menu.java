package br.com.ucsal.olimpiadas;

import java.util.Scanner;

import br.com.ucsal.olimpiadas.controller.ParticipanteController;
import br.com.ucsal.olimpiadas.controller.ProvaController;
import br.com.ucsal.olimpiadas.controller.QuestaoController;
import br.com.ucsal.olimpiadas.controller.TentativaController;

public class Menu {
	
	private static final Scanner in = new Scanner(System.in);
	
	private final ParticipanteController partcipanteController;
	private final ProvaController provaController;
	private final QuestaoController questaoController;
	private final TentativaController tentativaController;
	
	
	
	public Menu(ParticipanteController partcipanteController, ProvaController provaController,
			QuestaoController questaoController, TentativaController tentativaController) {
		this.partcipanteController = partcipanteController;
		this.provaController = provaController;
		this.questaoController = questaoController;
		this.tentativaController = tentativaController;
	}



	public void iniciar() {
		while (true) {
			System.out.println("\n=== OLIMPÍADA DE QUESTÕES (V1) ===");
			System.out.println("1) Cadastrar participante");
			System.out.println("2) Cadastrar prova");
			System.out.println("3) Cadastrar questão (A–E) em uma prova");
			System.out.println("4) Aplicar prova (selecionar participante + prova)");
			System.out.println("5) Listar tentativas (resumo)");
			System.out.println("0) Sair");
			System.out.print("> ");

			switch (in.nextLine()) {
			case "1" ->  partcipanteController.cadastrar(in);
			case "2" -> provaController.cadastrar(in);
			case "3" -> questaoController.cadastrar(in);
			case "4" -> tentativaController.aplicarProva(in);
			case "5" -> tentativaController.listarTentativas();
			case "0" -> {
				System.out.println("tchau");
				return;
			}
			default -> System.out.println("opção inválida");
			}
		}
	}
}
