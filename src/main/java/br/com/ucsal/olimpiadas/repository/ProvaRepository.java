package br.com.ucsal.olimpiadas.repository;

import java.util.ArrayList;
import java.util.List;

import br.com.ucsal.olimpiadas.Prova;

public class ProvaRepository extends BaseRepository<Prova> {
	
	/*private final List<Prova> provas = new ArrayList<>();
	private long proximaProvaId = 1;
	
	public Prova salvarProva(Prova p)
	{
		p.setId(proximaProvaId++);
		provas.add(p);
		return p;
	}
	
	public List<Prova> listar() {
		return provas;
	}
	
	public Prova buscarPorId(long id) {
		for(Prova p : provas) {
			if(id == p.getId()) {
				return p;
			}
		}
		return null;		
	}
	*/
	
}
