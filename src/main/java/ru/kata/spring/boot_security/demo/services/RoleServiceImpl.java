package ru.kata.spring.boot_security.demo.services;

import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.dao.RoleRepository;
import ru.kata.spring.boot_security.demo.models.Role;

import java.util.LinkedList;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService{
private final RoleRepository repository;

    public RoleServiceImpl(RoleRepository repository) {
        this.repository = repository;
    }


    @Override
    public List<Role> allRoles() {
        List<Role> list = new LinkedList<>();
        repository.findAll().forEach(list::add);
        return list;
    }
    @Override
    public List<Role> findAll() {
        return repository.findAll();
    }
}
