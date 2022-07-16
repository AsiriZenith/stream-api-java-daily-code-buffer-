package com.dailyCodeBuffer.stream.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class Application {

	static List<Employee> employees = new ArrayList<>();

	static {
		employees.add(
				new Employee("Shabbier","Dagwood",5000.0,List.of("Project1","Project2"))
		);
		employees.add(
				new Employee("Nikhil","Gupta",6000.0,List.of("Project1","Project3"))
		);
		employees.add(
				new Employee("Shiva","Kumar",5500.0,List.of("Project3","Project4"))
		);
	}

	public static void main(String[] args) {
//		SpringApplication.run(Application.class, args);\

		//foreach
//		employees.stream()
//				.forEach(employee -> System.out.println(employee));

		//map
		//collect
		List<Employee> increasedSal = employees.stream()
				.map(employee -> new Employee(
						employee.getFirstName(),
						employee.getLastName(),
						employee.getSalary() * 1.1,
						employee.getProjects()
				))
				.collect(Collectors.toList());

//		System.out.println(increasedSal);

		//filter
		List<Employee> filterEmployee = employees
				.stream()
				.filter(employee -> employee.getSalary() > 5000.0)
				.map(employee -> new Employee(
						employee.getFirstName(),
						employee.getLastName(),
						employee.getSalary() * 1.1,
						employee.getProjects()
				))
				.collect(Collectors.toList());

//		System.out.println(filterEmployee);

		//findFirst
		Employee firstEmployee = employees
				.stream()
				.filter(employee -> employee.getSalary() > 7000.0)
				.map(employee -> new Employee(
						employee.getFirstName(),
						employee.getLastName(),
						employee.getSalary() * 1.1,
						employee.getProjects()
				))
				.findFirst()
				.orElse(null);

//		System.out.println(firstEmployee);

		//flatMap
		String projects = employees
				.stream()
				.map(employee -> employee.getProjects())
				.flatMap(strings -> strings.stream())
				.collect(Collectors.joining(","));

//		System.out.println(projects);

		//short Circuit operations
		List<Employee> shortCircuit = employees
				.stream()
				.skip(1)
				.limit(1)
				.collect(Collectors.toList());

//		System.out.println(shortCircuit);

		//finite data
//		Stream.generate(Math::random)
//				.limit(5)
//				.forEach(val -> System.out.println(val));

		//sorting
		List<Employee> sortedEmployee = employees
				.stream()
				.sorted((o1, o2) -> o1.getFirstName().compareToIgnoreCase(o2.getFirstName()))
				.collect(Collectors.toList());

//		System.out.println(sortedEmployee);

		//min or max
		 Employee maxSalaryEmp = employees
				.stream()
				.max(Comparator.comparing(Employee::getSalary))
				.orElseThrow(NoSuchElementException::new);

//		System.out.println(maxSalaryEmp);

		//reduce
		Double totalSalary = employees
				.stream()
				.map(employee -> employee.getSalary())
				.reduce(0.0,Double::sum);

		System.out.println(totalSalary);
	}
}
