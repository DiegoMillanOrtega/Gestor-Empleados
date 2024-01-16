package com.diego;

import com.diego.models.Employee;
import com.diego.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

@SpringBootApplication
public class CursoSpringApplication implements CommandLineRunner {
	@Autowired
	private EmployeeService employeeService;
	private static final Logger logger = LoggerFactory.getLogger(CursoSpringApplication.class);


	public static void main(String[] args) {
		SpringApplication.run(CursoSpringApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Scanner scanner = new Scanner(System.in);
		int opcion;

		do {
			System.out.println("1. Agregar Empleado");
			System.out.println("2. Eliminar Empleado");
			System.out.println("3. Actualizar Empleado");
			System.out.println("4. Mostrar Empleados");
			System.out.println("0. Salir");
			System.out.print("Ingrese su opción: ");

			opcion = scanner.nextInt();

			switch (opcion) {
				case 1:
					System.out.println("Ingrese el nombre del empleado: ");
					String firstName = scanner.next();
					System.out.println("Ingrese el apellido del empleado: ");
					String lastName = scanner.next();
					System.out.println("Ingrese el salario del empleado: ");
					Long salary = scanner.nextLong();

					Employee newEmployee = new Employee();
					newEmployee.setFirstName(firstName);
					newEmployee.setLastName(lastName);
					newEmployee.setSalary(salary);

					this.employeeService.saveEmployee(newEmployee);

					break;

				case 2:
					System.out.println("Proporciona el ID del empleado a eliminar");
					Long id = scanner.nextLong();

					employeeService.deleteEmployeeById(id);

					break;

				case 3:
					Employee updatedEmployee = new Employee();

					System.out.println("Proporciona el ID del empleado ha actualizar");
					Long idUpdate = scanner.nextLong();
					updatedEmployee.setId(idUpdate);

					System.out.println("1. Actualizar nombre");
					System.out.println("2. Actualizar apellido");
					System.out.println("3. Actualizar salario");
					System.out.println("4. Ingrese su opción: ");
					int opcionActualizar = scanner.nextInt();

					switch (opcionActualizar) {
						case 1:
							System.out.println("Ingrese el nuevo nombre del empleado: ");
							String updateName = scanner.next();
							updatedEmployee.setFirstName(updateName);
							break;

						case 2:
							System.out.println("Ingrese el nuevo apellido del empleado: ");
							String updateLastName = scanner.next();
							updatedEmployee.setLastName(updateLastName);
							break;

						case 3:
							System.out.println("Ingrese el nuevo salario del empleado: ");
							Long updateSalary = scanner.nextLong();
							updatedEmployee.setSalary(updateSalary);
							break;
					}
					employeeService.saveEmployee(updatedEmployee);
					break;

				case 4:
					List<Employee> employeeList = employeeService.employeeList();
					logger.info("employees obtained");
					for (Employee employee : employeeList) {
						logger.info(employee.toString());
					}

					break;
			}

		} while (opcion != 0) ;
		scanner.close();

	}
}
