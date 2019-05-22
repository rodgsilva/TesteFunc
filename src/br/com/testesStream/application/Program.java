package br.com.testesStream.application;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import br.com.testesStream.entity.Paciente;

public class Program {

	public static void main(String[] args) {

		List<Paciente> list = new ArrayList<>();
		list.add(new Paciente("Rodrigo", "Masc", 100.00, 35, 1.75));
		list.add(new Paciente("Maria", "Fem", 15.00, 5, 1.10));
		list.add(new Paciente("Cristiane", "Fem", 75.00, 41, 1.55));
		list.add(new Paciente("João", "Masc", 90.00, 75, 1.85));
		list.add(new Paciente("Pedro", "Masc", 85.00, 22, 1.70));
		list.add(new Paciente("Gabi", "Fem", 85.00, 60, 1.65));

		int sum = list.size();
		System.out.println("Quantidade de paciente = " + sum);
		String sexo = "Masc";

		Comparator<String> comp = (s1, s2) -> s1.toUpperCase().compareTo(s2.toUpperCase());
		List<String> homeMediaIdade = new ArrayList();

		homeMediaIdade = list.stream().filter(p -> p.getSexo().equals(sexo)).map(p -> p.getNome()).sorted(comp)
				.collect(Collectors.toList());
		System.out.println("Homes = " + homeMediaIdade);

		double avg = list.stream().filter(p -> p.getSexo().equals(sexo)).map(p -> p.getIdade()).reduce((int) 0.0,
				(x, y) -> x + y) / list.size();
		System.out.println("Idade média dos homens = " + avg);

		List<String> qtdMulher = list.stream().filter(p -> p.getAltura() > 1.60).filter(p -> p.getAltura() < 1.70)
				.filter(p -> p.getPeso() > 70).filter(p -> p.getSexo().equals("Fem")).map(p -> p.getNome()).sorted(comp)
				.collect(Collectors.toList());
		System.out.println("Nome= " + qtdMulher + " Quantidade de mulher 1.60 e 1.70 / 70kg = " + qtdMulher.size());
		
		
		List<String> qtdPessoa18a25 = list.stream().filter(p -> p.getIdade() > 18).filter(p -> p.getIdade() < 25)
				.map(p -> p.getNome()).sorted(comp).collect(Collectors.toList());
		System.out.println("Qtd pessoa entre 18 e 25 = " + qtdPessoa18a25.size());

		Comparator<Paciente> comp2 = Comparator.comparing(Paciente::getIdade);
		Paciente pasMaisVelho = list.stream().filter(p -> p.getSexo().equals("Fem")).max(comp2).get();
		System.out.println("Pasiente mais velha = " + pasMaisVelho.getNome());

		Comparator<Paciente> comp3 = Comparator.comparing(Paciente::getIdade);
		Paciente pasMaisBaixa = list.stream().filter(p -> p.getSexo().equals("Fem")).min(comp3).get();
		System.out.println("Pasiente mais velha = " + pasMaisBaixa.getNome());

	}

}
