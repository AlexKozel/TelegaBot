package com.example.JavaBot;

import com.example.JavaBot.Entity.CapitalsInfo;
import com.example.JavaBot.dao.CapitalDao;
import com.example.JavaBot.repository.CapitalRepository;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class CapitalDaoTest {

    private final CapitalsInfo PARIS = new CapitalsInfo("Paris", "la france");
    private final Optional<CapitalsInfo> MOSCOW = Optional.of(new CapitalsInfo("Москва", "quadratisch praktisch gut"));

    CapitalRepository repositoryMock= mock(CapitalRepository.class);
    CapitalDao dao = new CapitalDao(repositoryMock);

    @Test
    public void findByIdTest(){
        when(repositoryMock.findById(1)).thenReturn(MOSCOW);
        Optional<CapitalsInfo> capitalsInfo = dao.findById(1);
        assertEquals(MOSCOW.get().getName(), capitalsInfo.get().getName());
        verify(repositoryMock).findById(1);
    }

    @Test
    public void findByNameTest(){
        when(repositoryMock.findByName(MOSCOW.get().getName())).thenReturn(MOSCOW);
        Optional<CapitalsInfo> capitalsInfo = dao.findByName(MOSCOW.get().getName());
        assertEquals(capitalsInfo, MOSCOW);
        verify(repositoryMock).findByName(MOSCOW.get().getName());
    }

    @Test
    public void findAllTest(){
        List<CapitalsInfo> list = new ArrayList<>();
        list.add(PARIS);
        when(repositoryMock.findAll()).thenReturn(list);
        assertEquals(list, dao.findAll());
        verify(repositoryMock).findAll();
    }

    @Test
    public void updateByIdTest(){
        when(repositoryMock.update(PARIS.getName(), PARIS.getDescription(), 1)).thenReturn(1);
        assertEquals(1, dao.updateById(PARIS.getName(), PARIS.getDescription(), 1));
        verify(repositoryMock).update(PARIS.getName(), PARIS.getDescription(), 1);
    }

    @Test
    public void addCapitalTest(){
        when(repositoryMock.save(PARIS)).thenReturn(PARIS);
        dao.addCapital(PARIS);
        verify(repositoryMock).save(PARIS);
    }

    @Test
    public void deleteByIdTest(){
        dao.deleteById(1);
        verify(repositoryMock).deleteById(1);
    }

}
