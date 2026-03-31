package br.com.ucsal.olimpiadas.repository;

import java.util.ArrayList;
import java.util.List;

import br.com.ucsal.olimpiadas.Categoria;
import br.com.ucsal.olimpiadas.Tentativa;

public class TentativaRepository extends BaseRepository<Tentativa> {

	/*private final List<Tentativa> tentativas = new ArrayList<>();
	private long proximaTentativaId = 1;
	
	
	public Tentativa salvar(Tentativa t) {
		t.setId(proximaTentativaId);
		tentativas.add(t);
		proximaTentativaId ++;
		return t;
		
	}
	
	public List<Tentativa> listar(){
		return tentativas;
	}
	
	public Tentativa buscarPorId(long id) {
		for(Tentativa t : tentativas){
			if(t.getId()==id) {
				return t;
			}
		}
		return null;
	}
	*/
	
}
