package com.example.JavaBot;


import com.example.JavaBot.Entity.CapitalsInfo;
import com.example.JavaBot.Service.CapitalsInfoService;
import com.example.JavaBot.dao.CapitalDao;
import com.example.JavaBot.repository.CapitalRepository;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

public class CapitalServiceTest {

    private final CapitalsInfo PARIS = new CapitalsInfo("Paris", "la france");
    private final Optional<CapitalsInfo> MOSCOW = Optional.of(new CapitalsInfo("Москва", "quadratisch praktisch gut"));

    CapitalDao daoMock = mock(CapitalDao.class);
    CapitalsInfoService service = new CapitalsInfoService(daoMock);

    @Test
    public void findByIdTest(){
        when(daoMock.findById(1)).thenReturn(MOSCOW);
        Optional<CapitalsInfo> capitalsInfo = service.findById(1);
        assertEquals(MOSCOW.get().getName(), capitalsInfo.get().getName());
        verify(daoMock).findById(1);
    }

    @Test
    public void findByNameTest(){
        when(daoMock.findByName(MOSCOW.get().getName())).thenReturn(MOSCOW);
        Optional<CapitalsInfo> capitalsInfo = service.findByName(MOSCOW.get().getName());
        assertEquals(capitalsInfo, MOSCOW);
        verify(daoMock).findByName(MOSCOW.get().getName());
    }

    @Test
    public void findAllTest(){
        List<CapitalsInfo> list = new ArrayList<>();
        list.add(PARIS);
        when(daoMock.findAll()).thenReturn(list);
        assertEquals(list, service.findAll());
        verify(daoMock).findAll();
    }

    @Test
    public void updateByIdTest(){
        when(daoMock.updateById(PARIS.getName(), PARIS.getDescription(), 1)).thenReturn(1);
        assertEquals(1, service.updateById(PARIS.getName(), PARIS.getDescription(), 1));
        verify(daoMock).updateById(PARIS.getName(), PARIS.getDescription(), 1);
    }

    @Test
    public void createTest(){
        service.create(PARIS);
        verify(daoMock).addCapital(PARIS);
    }

    @Test
    public void deleteByIdTest(){
        service.deleteById(1);
        verify(daoMock).deleteById(1);
    }
}
