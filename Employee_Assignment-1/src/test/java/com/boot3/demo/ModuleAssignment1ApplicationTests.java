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

import com.boot3.demo.Entity.Module; // Import Module class
import com.boot3.demo.Repository.Module_Repository; // Import Module repository
import com.boot3.demo.Service.Module_Service; // Import Module service

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class ModuleAssignment1ApplicationTests {

    @InjectMocks
    private Module_Service moduleService; // Use Module service

    @Mock
    private Module_Repository moduleRepository; // Use Module repository

    @Test
    void contextLoads() {
        // Your test initialization code
    }

    @Test
    void testCreateModule() {
        // Create a sample module object
        Module moduleToCreate = new Module("Sample Module Name"); // Use your Module class

        // Mock the save method of the repository
        when(moduleRepository.save(moduleToCreate)).thenReturn(moduleToCreate);

        // Call the service method
        Module createdModule = moduleService.createModule(moduleToCreate); // Use your service method

        // Assert that the createdModule is not null and has the expected name
        assertNotNull(createdModule);
        assertEquals("Sample Module Name", createdModule.getName());
    }

    @Test
    void testGetAllModules() {
        // Create a list of sample modules
        List<Module> sampleModules = List.of(
            new Module("Module 1"), // Use your Module class
            new Module("Module 2")  // Use your Module class
        );

        // Mock the findAll method of the repository
        when(moduleRepository.findAll()).thenReturn(sampleModules);

        // Call the service method
        List<Module> allModules = moduleService.getAllModules(); // Use your service method

        // Assert that the list of modules is not empty and has the expected size
        assertNotNull(allModules);
        assertEquals(2, allModules.size());
    }

    @Test
    void testGetModuleById() {
        // Create a sample module object
        Module module = new Module("Sample Module");

        // Mock the findById method of the repository
        when(moduleRepository.findById(1L)).thenReturn(Optional.of(module));

        // Call the service method
        Optional<Module> foundModule = moduleService.getModuleById(1L);

        // Assert that the foundModule is not empty and has the expected name
        assertTrue(foundModule.isPresent());
        assertEquals("Sample Module", foundModule.get().getName());
    }

    @Test
    void testGetModuleByIdNotFound() {
        // Mock the findById method of the repository to return an empty Optional
        when(moduleRepository.findById(1L)).thenReturn(Optional.empty());

        // Call the service method
        Optional<Module> foundModule = moduleService.getModuleById(1L);

        // Assert that the foundModule is empty (not found)
        assertTrue(foundModule.isEmpty());
    }

    @Test
    void testUpdateModule() {
        // Create a sample module object
        Module updatedModule = new Module("Updated Module");

        // Mock the existsById and save methods of the repository
        when(moduleRepository.existsById(1L)).thenReturn(true);
        when(moduleRepository.save(updatedModule)).thenReturn(updatedModule);

        // Call the service method
        Module resultModule = moduleService.updateModule(1L, updatedModule);

        // Assert that the resultModule is not null and has the expected name
        assertNotNull(resultModule);
        assertEquals("Updated Module", resultModule.getName());
    }

    @Test
    void testDeleteModule() {
        // Use lenient stubbing to avoid UnnecessaryStubbingException
        lenient().when(moduleRepository.existsById(1L)).thenReturn(true);

        // Call the service method
        moduleService.deleteModule(1L);

        // Verify that the deleteById method of the repository was called with the correct ID
        verify(moduleRepository).deleteById(1L);
    }
}


