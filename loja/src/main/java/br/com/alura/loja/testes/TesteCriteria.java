package br.com.alura.loja.testes;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.loja.dao.CategoriaDAO;
import br.com.alura.loja.dao.ProdutoDAO;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JPAUtil;

public class TesteCriteria {

	public static void main(String[] args) {
		popularBancoDeDados();
		
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDAO produtoDao = new ProdutoDAO(em);
		List<Produto> produtos = produtoDao.buscarPorParametrosComCriteria(null, null, null);
		produtos.forEach(p -> System.out.println(p.getNome()));
		
		
	}
	
	private static void popularBancoDeDados() {
		Categoria celulares = new Categoria("CELULARES");
		Categoria eletrodomesticos = new Categoria("ELETRODOMESTICOS");
		Categoria informatica = new Categoria("INFORMATICA");
		
		Produto celular = new Produto("Xiaomi","Celular Modelo Xiaomi", new BigDecimal("800"), celulares);
		Produto batedeira = new Produto("Batedeira", "Batedeira Philco", new BigDecimal("250"), eletrodomesticos);
		Produto notebook = new Produto("MacBook","Notebook MAcBook", new BigDecimal("4000"), informatica);
		
		
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDAO produtoDao = new ProdutoDAO(em);
		CategoriaDAO categoriaDao = new CategoriaDAO(em);
		
		em.getTransaction().begin();
		
		categoriaDao.cadastrar(celulares);
		categoriaDao.cadastrar(eletrodomesticos);
		categoriaDao.cadastrar(informatica);
		
		produtoDao.cadastrar(batedeira);
		produtoDao.cadastrar(celular);
		produtoDao.cadastrar(notebook);
		
		em.getTransaction().commit();
		em.close();
		
		
		

		
	}
}
