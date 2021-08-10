package br.com.alura.spring.data.service;

import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.alura.spring.data.orm.UnidadeTrabalho;
import br.com.alura.spring.data.repository.UnidadeRepository;

@Service
public class CrudUnidadeTrabalhoService {
	private Boolean system = true;
	private final UnidadeRepository unidadeRepository;
	
	public CrudUnidadeTrabalhoService(UnidadeRepository unidadeRepository) {
		this.unidadeRepository = unidadeRepository;
	}
	
	public void inicial(Scanner scanner) {
		while (system) {
			System.out.println("Qual ação de unidade deseja executar?");
			System.out.println("0 - Sair");
			System.out.println("1 - Incluir Unidade");
			System.out.println("2 - Alterar Unidade");
			System.out.println("3 - Visualizar Unidades");
			System.out.println("4 - Apagar Unidades");

			int action = scanner.nextInt();

			switch (action) {
			case 1:
				salvar(scanner);
				break;
			case 2:
				atualizar(scanner);
				break;
			case 3:
				visualizar();
				break;
			case 4:
				deletar(scanner);
				break;

			default:
				system = false;
				break;
			}

		}
	}
	
	private void salvar(Scanner scanner) {
		System.out.println("Descricao da Unidade");
		String descricao = scanner.next();
		System.out.println("Endereço da Unidade");
		String endereco = scanner.next();
		UnidadeTrabalho unidade = new UnidadeTrabalho();
		unidade.setDescricao(descricao);
		unidade.setEndereco(endereco);
		unidadeRepository.save(unidade);
		System.out.println("Unidade salva com sucesso");
	}

	private void atualizar(Scanner scanner) {
		System.out.println("Digite o Id da unidade a ser atualizada");
		int id = scanner.nextInt();
		System.out.println("Digite a nova descricao da Unidade");
		String descricao = scanner.next();
		UnidadeTrabalho unidade = new UnidadeTrabalho();
		unidade.setId(id);
		unidade.setDescricao(descricao);
		unidadeRepository.save(unidade);
		System.out.println("Unidade Alterada com sucesso");
	}

	private void visualizar() {
		Iterable<UnidadeTrabalho> unidades = unidadeRepository.findAll();
		// System.out.println(cargos);
		unidades.forEach(unidade -> System.out.println(unidade));
	}
	
	private void deletar(Scanner scanner) {
		System.out.println("Digite o ID da Unidade a ser delatada");
		int id = scanner.nextInt();
		unidadeRepository.deleteById(id);
		System.out.println("Remoção de unidade com sucesso.");
	}
}
