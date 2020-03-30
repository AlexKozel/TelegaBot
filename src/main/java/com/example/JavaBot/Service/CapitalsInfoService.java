package com.example.JavaBot.Service;

import com.example.JavaBot.Entity.CapitalsInfo;
import com.example.JavaBot.repository.CapitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CapitalsInfoService {

    CapitalRepository repository;

    public CapitalsInfoService(@Autowired CapitalRepository repository) {
        this.repository = repository;
    }

    public void addCapital(CapitalsInfo capitalsInfo){
        repository.save(capitalsInfo);
    }

//    public void saveAll(Map<String,String> capitalsInfoMap){
//        repository.saveAll(capitalsInfoMap);
//    }
}
