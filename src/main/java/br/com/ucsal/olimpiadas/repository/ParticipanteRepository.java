package br.com.ucsal.olimpiadas.repository;

import java.util.ArrayList;
import java.util.List;

import br.com.ucsal.olimpiadas.Participante;
import br.com.ucsal.olimpiadas.Prova;

public class ParticipanteRepository extends BaseRepository<Participante> {
	
	/*private final List<Participante> participantes = new ArrayList<>();
	private long proximoParticipanteId = 1;
	
	public Participante salvar(Participante p) {
		p.setId(proximoParticipanteId++);
		participantes.add(p);
		return p;
		
	}
	
	public List<Participante> listar(){
		return participantes;
	}
	
	public Participante buscarPorId(long id) {
		for(Participante p : participantes) {
			if(id == p.getId()) {
				return p;
			}
		}
		return null;		
	}*/
	
}
