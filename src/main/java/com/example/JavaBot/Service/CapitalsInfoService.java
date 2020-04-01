package com.example.JavaBot.Service;

import com.example.JavaBot.Entity.CapitalsInfo;
import com.example.JavaBot.dao.CapitalDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CapitalsInfoService {

    private final CapitalDao dao;

    public CapitalsInfoService(@Autowired CapitalDao dao) {
        this.dao = dao;
    }

    public Optional<CapitalsInfo> findByName(String name)  {
      return  dao.findByName(name);
    }

    public Optional<CapitalsInfo> findById(int id){
        return dao.findById(id);
    }

    public List<CapitalsInfo> findAll(){
        return dao.findAll();
    }

    public void deleteById(int id){
        dao.deleteById(id);
    }

    public int updateById(String name, String description, int id){
        return dao.updateById(name,description,id);
    }

    public void create(CapitalsInfo capitalsInfo){
        dao.addCapital(capitalsInfo);
    }



}
