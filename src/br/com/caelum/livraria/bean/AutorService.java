package br.com.caelum.livraria.bean;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;


import br.com.caelum.livraria.dao.AutorDao;
import br.com.caelum.livraria.modelo.Autor;

@Stateless // Por padrão, qualquer chamada ao Session Bean já é transacional.
public class AutorService {

	@Inject
	AutorDao dao;
	
	public void adiciona(Autor autor) throws LivrariaException{
		dao.salva(autor);
		throw new LivrariaException();
	}
	
	public List<Autor> todosAutores(){
		return dao.todosAutores();
	}
}
