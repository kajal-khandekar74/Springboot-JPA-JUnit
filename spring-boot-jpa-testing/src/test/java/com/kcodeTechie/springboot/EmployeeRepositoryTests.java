package com.kcodeTechie.springboot;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmployeeRepositoryTests {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	//JUnit test for saveEmployee
	@Test
	@Order(1)
	@Rollback(value = false)
	public void saveEmployeetest() {
		Employee  employee = Employee.builder()
				.firstName("Kajal")
				.lastName("Khandekar")
				.email("kajalkhandekar74@gmail.com")
				.build();
		
		employeeRepository.save(employee);
		
		Assertions.assertThat(employee.getId()).isGreaterThan(0);
	
	}
	
	@Test
	@Order(2)
	public void getEmployeeTest() {
		
		Employee employee = employeeRepository.findById(1L).get();
	
		Assertions.assertThat(employee.getId()).isEqualTo(1L);
		
	}
	
	@Test
	@Order(3)
	public void getListOfEmployeeTest() {
		
		List<Employee> employees = employeeRepository.findAll();
		
		Assertions.assertThat(employees.size()).isGreaterThan(0);
	}
	
	@Test
	@Order(4)
	@Rollback(value = false)
	public void updateEmployeeTest() {
		
		Employee employee = employeeRepository.findById(1L).get();
	
		employee.setEmail("kajal@gmail.com");
		Employee employeeUpdated = employeeRepository.save(employee);
		
		Assertions.assertThat(employeeUpdated.getEmail()).isEqualTo("kajal@gmail.com");
		
	}
	
	@Test
	@Order(5)
	@Rollback(value = false)
	public void deleteEmployeeTest() {
		Employee employee = employeeRepository.findById(1L).get();
		
		employeeRepository.delete(employee);
		
		// employeeRepository.deleteById(1L);
		
		Employee employee1 = null;
		
		Optional<Employee> optionalEmployee = employeeRepository.findByEmail("kajal@gmail.com");
		
		if(optionalEmployee.isPresent()) {
			employee1 = optionalEmployee.get();
		}
		
		Assertions.assertThat(employee1).isNull();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
