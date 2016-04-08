package br.com.caelum.livraria.dao;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.caelum.livraria.modelo.Autor;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER) //opcional, pq é padrão de um session bean
public class AutorDao {
	
	@PersistenceContext
	private EntityManager manager;
	
	@PostConstruct
	private void aposCricao() {
		System.out.println("AutorDao foi criado");
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED) //opcional, pq é padrão de um session bean
	//@TransactionAttribute(TransactionAttributeType.MANDATORY) // significa obrigatório. Nesse caso, o container verifica se já existe uma transação rodando, caso contrário, joga uma exceção. Ou seja, quem faz a chamada deve abrir uma transação.
	//@TransactionAttribute(TransactionAttributeType.NEVER) // Indica que jamais deve haver uma transação em execução. Isso pode ser útil para métodos que obrigatoriamente devem ser executados sem contexto transacional
	public void salva(Autor autor) {
		
		manager.persist(autor);
		
//		throw new RuntimeException("Servico externo deu erro!");
	}
	
	public List<Autor> todosAutores() {
		return manager.createQuery("select a from Autor a", Autor.class).getResultList();
	}

	public Autor buscaPelaId(Integer autorId) {
		Autor autor = this.manager.find(Autor.class, autorId);
		return autor;
	}
	
}
