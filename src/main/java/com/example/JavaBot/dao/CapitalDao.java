package com.example.JavaBot.dao;

import com.example.JavaBot.Entity.CapitalsInfo;
import com.example.JavaBot.repository.CapitalRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.List;

@Component
public class CapitalDao {

    private final CapitalRepository repository;

    public CapitalDao(@Autowired CapitalRepository repository) {
        this.repository = repository;
    }

    /**
     * might be null
     * @param name
     * @return
     */
    public Optional<CapitalsInfo> findByName(String name){
        return repository.findByName(name);
    }

    /**
     * find by id for Controllers
     * @param id
     * @return
     */
    public Optional<CapitalsInfo> findById(int id){
        return repository.findById(id);
    }

    public List<CapitalsInfo> findAll(){
        return repository.findAll();
    }

    public void deleteById(int id){
        repository.deleteById(id);
    }

    public void deleteByName(String name){
        repository.deleteByName(name);
    }

    public int updateById(String name, String description, int id){
        return repository.update(name,description,id);
    }

    public void addCapital(CapitalsInfo capitalsInfo){
        repository.save(capitalsInfo);
    }

}
