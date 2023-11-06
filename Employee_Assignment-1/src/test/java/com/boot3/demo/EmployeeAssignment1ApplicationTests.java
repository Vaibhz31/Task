package com.boot3.demo;

import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.boot3.demo.Entity.Employee;
import com.boot3.demo.Repository.Employee_Repository;
import com.boot3.demo.Service.Employee_Service;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class EmployeeAssignment1ApplicationTests {

	@InjectMocks
    private Employee_Service employeeService;

    @Mock
    private Employee_Repository employeeRepository;
	
	@Test
	void contextLoads() {
	}
	@Test
    void testCreateEmployee() {
        // Create a sample employee object
        Employee employeeToCreate = new Employee("Hari Vedas");

        // Mock the save method of the repository
        when(employeeRepository.save(employeeToCreate)).thenReturn(employeeToCreate);

        // Call the service method
        Employee createdEmployee = employeeService.createEmployee(employeeToCreate);

        // Assert that the createdEmployee is not null and has the expected name
        assertNotNull(createdEmployee);
        assertEquals("Hari Vedas", createdEmployee.getName());
    }
	@Test
    void testGetAllEmployees() {
        // Create a list of sample employees
        List<Employee> sampleEmployees = List.of(
            new Employee("Javed Jaffrey"),
            new Employee("Will Smith")
        );

        // Mock the findAll method of the repository
        when(employeeRepository.findAll()).thenReturn(sampleEmployees);

        // Call the service method
        List<Employee> allEmployees = employeeService.getAllEmployees();

        // Assert that the list of employees is not empty and has the expected size
        assertNotNull(allEmployees);
        assertEquals(2, allEmployees.size());
}
	@Test
    void testGetEmployeeById() {
        // Create a sample employee object
        Employee employee = new Employee("Varun Dave");

        // Mock the findById method of the repository
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));

        // Call the service method
        Optional<Employee> foundEmployee = employeeService.getEmployeeById(1L);

        // Assert that the foundEmployee is not empty and has the expected name
        assertTrue(foundEmployee.isPresent());
        assertEquals("Varun Dave", foundEmployee.get().getName());
}
	@Test
    void testGetEmployeeByIdNotFound() {
        // Mock the findById method of the repository to return an empty Optional
        when(employeeRepository.findById(1L)).thenReturn(Optional.empty());

        // Call the service method
        Optional<Employee> foundEmployee = employeeService.getEmployeeById(1L);

        // Assert that the foundEmployee is empty (not found)
        assertTrue(foundEmployee.isEmpty());
    }
	@Test
	void testUpdateEmployee() {
	    // Create a sample employee object
	    Employee updatedEmployee = new Employee("Updated Name");

	    // Mock the existsById and save methods of the repository
	    when(employeeRepository.existsById(1L)).thenReturn(true);
	    when(employeeRepository.save(updatedEmployee)).thenReturn(updatedEmployee);

	    // Call the service method
	    Employee resultEmployee = employeeService.updateEmployee(1L, updatedEmployee);

	    // Assert that the resultEmployee is not null and has the expected name
	    assertNotNull(resultEmployee);
	    assertEquals("Updated Name", resultEmployee.getName());
	}
	@Test
	void testDeleteEmployee() {
	    // Use lenient stubbing to avoid UnnecessaryStubbingException
	    lenient().when(employeeRepository.existsById(1L)).thenReturn(true);

	    // Call the service method
	    employeeService.deleteEmployee(1L);

	    // Verify that the deleteById method of the repository was called with the correct ID
	    verify(employeeRepository).deleteById(1L);
	}
}