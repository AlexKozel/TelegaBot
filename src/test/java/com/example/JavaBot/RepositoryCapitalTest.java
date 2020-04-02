package com.example.JavaBot;

import com.example.JavaBot.Entity.CapitalsInfo;
import com.example.JavaBot.repository.CapitalRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@DataJpaTest
public class RepositoryCapitalTest {

    private final CapitalsInfo PARIS = new CapitalsInfo("Paris", "la france");
    private final CapitalsInfo BERLIN = new CapitalsInfo("Berlin", "quadratisch praktisch gut");

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CapitalRepository repository;

    @Test
    public void findByIdTest() {
        entityManager.persist(PARIS);
        entityManager.flush();
        Optional<CapitalsInfo> currentCapital = repository.findById(5);
        assertNotNull(currentCapital);
        currentCapital.ifPresent(capitalsInfo -> assertEquals(capitalsInfo.getName(), PARIS.getName()));
    }

    @Test
    public void findByNameTest() {
        entityManager.persist(PARIS);
        entityManager.flush();
        Optional<CapitalsInfo> currentCapital = repository.findByName(PARIS.getName());
        assertNotNull(currentCapital);
        currentCapital.ifPresent(capitalsInfo -> assertEquals(capitalsInfo.getName(), PARIS.getName()));
    }

    @Test
    public void findAllTest() {
        entityManager.persist(PARIS);
        entityManager.flush();
        List<CapitalsInfo> infoList = new ArrayList<>();
        int sizeBefore = infoList.size();
        infoList = repository.findAll();
        int sizeAfter = infoList.size();
        assertTrue(infoList.size() > 0);
        assertTrue(sizeBefore < sizeAfter);
    }

    @Test
    public void deleteByIdTest() {
        entityManager.persist(PARIS);
        entityManager.flush();

        int sizeBefore = repository.findAll().size();
        repository.deleteById(1);
        int sizeAfter = repository.findAll().size();
        assertTrue(sizeBefore > sizeAfter);
    }

    @Test
    public void updateByIdTest() {
        repository.update(BERLIN.getName(), BERLIN.getDescription(), 1);
        System.out.println(repository.findAll());
        assertEquals(repository.findById(1).get().getName(), BERLIN.getName());
    }

    @Test
    public void addCapitalTest() {
        int sizeBefore = repository.findAll().size();
        repository.save(PARIS);
        int sizeAfter = repository.findAll().size();
        assertTrue(sizeBefore < sizeAfter);

    }
}
