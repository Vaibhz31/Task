package com.boot3.demo.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot3.demo.Entity.Module;
import com.boot3.demo.Exception.ModuleNotFoundException;
import com.boot3.demo.Repository.Module_Repository;

@Service
public class Module_Service {

    @Autowired
    private Module_Repository moduleRepository;

    public List<Module> getAllModules() {
        return moduleRepository.findAll();
    }

    public Optional<Module> getModuleById(Long id) {
        return moduleRepository.findById(id);
    }

    public Module createModule(Module module) {
        return moduleRepository.save(module);
    }

    public Module updateModule(Long id, Module module) {
        if (moduleRepository.existsById(id)) {
            module.setId(id);
            return moduleRepository.save(module);
        } else {
            throw new ModuleNotFoundException("Module not found with id: " + id);
        }
    }

    public void deleteModule(Long id) {
        moduleRepository.deleteById(id);
    }
}
