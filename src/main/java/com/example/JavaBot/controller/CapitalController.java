package com.example.JavaBot.controller;

import com.example.JavaBot.Entity.CapitalsInfo;
import com.example.JavaBot.Entity.CapitalsInfoValidator;
import com.example.JavaBot.Service.CapitalsInfoService;
import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CapitalController {

    private final CapitalsInfoService service;
    private final CapitalsInfoValidator validator;

    private final Logger LOG = LoggerFactory.getLogger(CapitalController.class);

    public CapitalController(@Autowired CapitalsInfoService service,@Autowired CapitalsInfoValidator validator) {
        this.service = service;
        this.validator = validator;
    }

    @GetMapping("/capital")
    public List<CapitalsInfo> getAll() {
        LOG.info("Get all capitals");
        return service.findAll();
    }

    @GetMapping("/capital/{id}")
    public Optional<CapitalsInfo> getById(@PathVariable int id) throws NotFoundException {
        LOG.info("Get capital by id - {}", id);
        Optional<CapitalsInfo> currentCapital = service.findById(id);
        if (!currentCapital.isPresent()) {
            throw new NotFoundException("id - " + id + " are not found");
        }
        return currentCapital;
    }

    @PostMapping("/capital")
    public List<CapitalsInfo> addCapital(@RequestBody CapitalsInfo capitalsInfo, BindingResult result) {
        LOG.info("Create a new capital - {}", capitalsInfo);
        validator.validate(capitalsInfo,result);
        if(result.hasErrors()){
            throw new RuntimeException(result.getAllErrors().toString());
        } else {
        service.create(capitalsInfo);
        return service.findAll();
    }}

    @PostMapping("/capital/{id}")
    public Optional<CapitalsInfo> updateCapital(@RequestBody CapitalsInfo capitalsInfo, @PathVariable int id, BindingResult result) {
        LOG.info("Update capital with id={} , to Capital - {}", id, capitalsInfo);
        validator.validate(capitalsInfo,result);
        if(result.hasErrors()){
            throw new RuntimeException(result.getAllErrors().toString());
        } else {
        service.updateById(capitalsInfo.getName(), capitalsInfo.getDescription(), id);
        return service.findById(id);
    }}

    @DeleteMapping("/capital/{id}")
    public void deleteById(@PathVariable int id) {
        LOG.info("Delete capital with id= {}", id);
        service.deleteById(id);
    }
}
