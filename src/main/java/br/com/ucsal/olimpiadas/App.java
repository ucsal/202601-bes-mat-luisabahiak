package br.com.ucsal.olimpiadas;

import br.com.ucsal.olimpiadas.controller.ParticipanteController;
import br.com.ucsal.olimpiadas.controller.ProvaController;
import br.com.ucsal.olimpiadas.controller.QuestaoController;
import br.com.ucsal.olimpiadas.controller.TentativaController;
import br.com.ucsal.olimpiadas.model.Prova;
import br.com.ucsal.olimpiadas.model.Questao;
import br.com.ucsal.olimpiadas.repository.ParticipanteRepository;
import br.com.ucsal.olimpiadas.repository.ProvaRepository;
import br.com.ucsal.olimpiadas.repository.QuestaoRepository;
import br.com.ucsal.olimpiadas.repository.TentativaRepository;
import br.com.ucsal.olimpiadas.service.ProvaService;
import br.com.ucsal.olimpiadas.service.TentativaService;

public class App {

	public static void main(String[] args) {
		
		QuestaoRepository questaoRepository = new QuestaoRepository();
		ParticipanteRepository participanteRepository = new ParticipanteRepository();
		TentativaRepository tentativaRepository = new TentativaRepository();
		ProvaRepository provaRepository = new ProvaRepository();
			
		TentativaService tentativaService = new TentativaService();
		ProvaService provaService = new ProvaService(provaRepository);
		
		ParticipanteController participanteController = new ParticipanteController(participanteRepository);
		ProvaController provaController = new ProvaController(provaService, provaRepository);
		QuestaoController questaoController = new QuestaoController(questaoRepository, provaRepository, provaController);
		TentativaController tentativaController = new TentativaController(participanteRepository,provaRepository,questaoRepository, tentativaRepository, provaController, participanteController, tentativaService);
		
		Menu menu = new Menu(participanteController, provaController, questaoController, tentativaController);
		
		seed(provaService, questaoRepository);
		menu.iniciar();
		
		
		
	}
		
static void seed(ProvaService provaService, QuestaoRepository questaoRepository) {

		Prova prova = provaService.cadastrarProva("Olimpíada 2026 • Nível 1 • Prova A");

		Questao q1 = new Questao();
		q1.setProvaId(prova.getId());

		q1.setEnunciado("""
				Questão 1 — Mate em 1.
				É a vez das brancas.
				Encontre o lance que dá mate imediatamente.
				""");

		q1.setFenInicial("6k1/5ppp/8/8/8/7Q/6PP/6K1 w - - 0 1");

		q1.setAlternativas(new String[] { "A) Qh7#", "B) Qf5#", "C) Qc8#", "D) Qh8#", "E) Qe6#" });

		q1.setAlternativaCorreta('C');

		questaoRepository.salvar(q1);
	}
}