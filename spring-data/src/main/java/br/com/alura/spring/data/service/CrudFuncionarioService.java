package br.com.alura.spring.data.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.alura.spring.data.orm.Cargo;
import br.com.alura.spring.data.orm.Funcionario;
import br.com.alura.spring.data.orm.UnidadeTrabalho;
import br.com.alura.spring.data.repository.CargoRepository;
import br.com.alura.spring.data.repository.FuncionarioRepository;
import br.com.alura.spring.data.repository.UnidadeRepository;

@Service
public class CrudFuncionarioService {
	private Boolean system = true;
	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	private final FuncionarioRepository funcionarioRepository;
	private final CargoRepository cargoRepository;
	private final UnidadeRepository unidadeRepository;

	public CrudFuncionarioService(FuncionarioRepository funcionarioRepository, CargoRepository cargoRepository,
			UnidadeRepository unidadeRepository) {
		this.funcionarioRepository = funcionarioRepository;
		this.cargoRepository = cargoRepository;
		this.unidadeRepository = unidadeRepository;

	}

	public void inicial(Scanner scanner) {
		while (system) {
			System.out.println("Qual ação de Funcionario deseja executar?");
			System.out.println("0 - Sair");
			System.out.println("1 - Incluir Funcionario");
			System.out.println("2 - Alterar Funcionario");
			System.out.println("3 - Visualizar Funcionarios");
			System.out.println("4 - Apagar Funcionarios");

			int action = scanner.nextInt();

			switch (action) {
			case 1:
				salvar(scanner);
				break;
			case 2:
				atualizar(scanner);
				break;
			case 3:
				visualizar(scanner);
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
		System.out.println("Nome do Funcionario");
		String nome = scanner.next();
		System.out.println("CPF do Funcionario");
		String cpf = scanner.next();
		System.out.println("Salario do funcionário");
		Double salario = scanner.nextDouble();
		System.out.println("Data de contratação do Funcionario");
		String dataContratacao = scanner.next();
		System.out.println("Digite o CargoId");
		Integer cargoId = scanner.nextInt();

		List<UnidadeTrabalho> unidades = unidade(scanner);

		Funcionario funcionario = new Funcionario();
		funcionario.setNome(nome);
		funcionario.setCpf(cpf);
		funcionario.setSalario(salario);
		funcionario.setData(LocalDate.parse(dataContratacao, formatter));
		Optional<Cargo> cargo = cargoRepository.findById(cargoId);
		funcionario.setCargo(cargo.get());
		funcionario.setUnidadeTrabalhos(unidades);
		funcionarioRepository.save(funcionario);
		System.out.println("Funcionario salvo com sucesso");
	}

	private List<UnidadeTrabalho> unidade(Scanner scanner) {
		Boolean isTrue = true;
		List<UnidadeTrabalho> unidades = new ArrayList<>();

		while (isTrue) {
			System.out.println("Digite o unidadeId (Para sair digite 0)");
			Integer unidadeId = scanner.nextInt();

			if (unidadeId != 0) {
				Optional<UnidadeTrabalho> unidade = unidadeRepository.findById(unidadeId);
				unidades.add(unidade.get());
			} else {
				isTrue = false;
			}
		}
		return unidades;
	}

	private void atualizar(Scanner scanner) {
		System.out.println("Digite o Id do funcionario a ser atualizado");
		int id = scanner.nextInt();
		System.out.println("Digite o novo nome do Funcionario");
		String nome = scanner.next();
		Funcionario funcionario = new Funcionario();
		funcionario.setId(id);
		funcionario.setNome(nome);
		funcionarioRepository.save(funcionario);
		System.out.println("Funcionario salvo com sucesso!");
	}

	private void visualizar(Scanner scanner) {
		System.out.println("Qual pagina voce deseja visualizar?");
		Integer page = scanner.nextInt();

		Pageable pageable = PageRequest.of(page, 5, Sort.by(Sort.Direction.ASC, "nome"));

		Page<Funcionario> funcionarios = funcionarioRepository.findAll(pageable);
		// System.out.println(funcionarios);
		System.out.println(funcionarios);
		System.out.println("Pagina atual" + funcionarios.getNumber());
		System.out.println("Total de elementos: " + funcionarios.getTotalElements());
		funcionarios.forEach(funcionario -> System.out.println(funcionario));
	}

	private void deletar(Scanner scanner) {
		System.out.println("Digite o ID do funcionario a ser deletado");
		int id = scanner.nextInt();
		funcionarioRepository.deleteById(id);
		System.out.println("Remoção de Funcionario com sucesso!");
	}
}
