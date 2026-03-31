package br.com.ucsal.olimpiadas.repository;

import java.util.ArrayList;
import java.util.List;
import br.com.ucsal.olimpiadas.Categoria;

public abstract class BaseRepository<T extends Categoria> {

	protected List<T> lista= new ArrayList<>();
	protected long proximoId = 1;
	
	public T salvar(T t) {
		t.setId(proximoId++);
		lista.add(t);
		return t;
		
	}
	
	public List<T> listar(){
		return lista;
	}
	
	public T buscarPorId(long id) {
		for(T t : lista) {
			if(id == t.getId()) {
				return t;
			}
		}
		return null;		
	}
	
}
