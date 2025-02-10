package dados_funcionarios;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Principal {
 
	public static void main(String[] args) {
		List<Funcionario> funcionarios = new ArrayList<>(Arrays.asList(
				new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"),
	            new Funcionario("João", LocalDate.of(1990, 05, 12), new BigDecimal("2284.38"), "Operador"),
	            new Funcionario("Caio", LocalDate.of(1961, 05, 02), new BigDecimal("9836.14"), "Coordenador"),
	            new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"),
	            new Funcionario("Alice", LocalDate.of(1955, 01, 5), new BigDecimal("2234.68"), "Recepcionista"),
	            new Funcionario("Heltor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"),
	            new Funcionario("Arthur", LocalDate.of(1993, 03, 31), new BigDecimal("4071.84"), "Contador"),
	            new Funcionario("Laura", LocalDate.of(1994, 07, 8), new BigDecimal("3017.45"), "Gerente"),
	            new Funcionario("Heloísa", LocalDate.of(2003, 05, 24), new BigDecimal("1606.85"), "Eletricista"),
	            new Funcionario("Helena", LocalDate.of(1996, 9, 02), new BigDecimal("2799.93"), "Gerente")
	        ));
		
        	// 3.3 - Imprimir todos os funcionários com todas as suas informações( data e valor númerico formatados )
		
        	System.out.println("Todos os funcionários da industria:");
        	funcionarios.forEach(System.out::println);

	        // 3.2 - Remover o João e imprimir lista de funcionários sem ele
        	System.out.println();
        	
        	System.out.println("Lista de funcionários sem o João:");
	        funcionarios.removeIf(f -> f.getNome().equalsIgnoreCase("João"));

	        funcionarios.forEach(System.out::println);
	        
	        System.out.println();
	        // 3.4 - Os funcionários receberam 10% de aumento de salário, atualizar a lista de funcionários com novo valor.
	        System.out.println("Lista de funcionários com o aumento de 10% no salário:");
	        funcionarios.forEach(f -> f.setSalario(f.getSalario().multiply(new BigDecimal("1.10"))));
	        funcionarios.forEach(System.out::println);
	        
	        System.out.println();
	        // 3.5 – Agrupar os funcionários por função em um MAP, sendo a chave a “função” e o valor a “lista de funcionários”.
	        System.out.println("Agrupamento dos funcionários:");
	        Map<String, List<Funcionario>> agrupadosPorFuncao = funcionarios.stream()
	            .collect(Collectors.groupingBy(Funcionario::getFuncao));
	        
	        // 3.6 – Imprimir os funcionários, agrupados por função.
	        agrupadosPorFuncao.forEach((funcao, lista) -> {
	            System.out.println("\nFunção: " + funcao);
	            lista.forEach(System.out::println);
	        });

	        // 3.8 – Imprimir os funcionários que fazem aniversário no mês 10 e 12.( na tabela não tem funcionário que faz aniversário em dezembro)
	        System.out.println("\nFuncionários com aniversário em outubro e dezembro:");
	        funcionarios.stream()
	            .filter(f -> f.getDataNascimento().getMonthValue() == 10 || f.getDataNascimento().getMonthValue() == 12)
	            .forEach(System.out::println);

	        // 3.9 – Imprimir o funcionário com a maior idade, exibir os atributos: nome e idade.
	        Funcionario maisVelho = Collections.min(funcionarios, Comparator.comparing(Funcionario::getDataNascimento));
	        System.out.println("\nFuncionário mais velho: " + maisVelho.getNome() + " - Idade: " + (LocalDate.now().getYear() - maisVelho.getDataNascimento().getYear()));

	        // 3.10 – Imprimir a lista de funcionários por ordem alfabética.
	        funcionarios.sort(Comparator.comparing(Funcionario::getNome));
	        System.out.println("\nFuncionários em ordem alfabética:");
	        funcionarios.forEach(System.out::println);

	        // 3.11 – Imprimir o total dos salários dos funcionários.
	        BigDecimal totalSalarios = funcionarios.stream().map(Funcionario::getSalario).reduce(BigDecimal.ZERO, BigDecimal::add);
	        System.out.println("\nTotal dos salários: R$ " + String.format("%,.2f", totalSalarios));

	        // 3.12 – Imprimir quantos salários mínimos ganha cada funcionário, considerando que o salário mínimo é R$1212.00.
	        BigDecimal salarioMinimo = new BigDecimal("1212.00");
	        System.out.println("\nSalários mínimos por funcionário:");
	        funcionarios.forEach(f -> {
	            BigDecimal quantidadeSalarios = f.getSalario().divide(salarioMinimo, 2, RoundingMode.HALF_UP);
	            System.out.println(f.getNome() + " ganha " + quantidadeSalarios + " salários mínimos.");
	        });

	}
}
