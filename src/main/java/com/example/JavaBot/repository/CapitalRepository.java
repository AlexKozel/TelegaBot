package com.example.JavaBot.repository;

import com.example.JavaBot.Entity.CapitalsInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CapitalRepository extends CrudRepository<CapitalsInfo, Integer> {
}
