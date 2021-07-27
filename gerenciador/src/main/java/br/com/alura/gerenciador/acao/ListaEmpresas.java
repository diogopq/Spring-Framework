package br.com.alura.gerenciador.acao;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.alura.gerenciador.modelo.Banco;
import br.com.alura.gerenciador.modelo.Empresa;

public class ListaEmpresas implements Acao {

	public String executa(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {

		long inicio = System.currentTimeMillis();
		System.out.println("Listando empresas");
		Banco banco = new Banco();

		List<Empresa> lista = banco.getEmpresas();

		request.setAttribute("empresas", lista);

		long fim = System.currentTimeMillis();
		System.out.println(fim - inicio);
		return "forward:listaEmpresas.jsp";
	}
}
