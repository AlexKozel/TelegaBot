package com.example.JavaBot;

import com.example.JavaBot.Entity.CapitalsInfo;
import com.example.JavaBot.Entity.CapitalsInfoValidator;
import com.example.JavaBot.Service.CapitalsInfoService;
import com.example.JavaBot.controller.CapitalController;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.BDDMockito.*;

@RunWith(SpringRunner.class)
@WebMvcTest(CapitalController.class)
public class CapitalControllerTest {

    private final Optional<CapitalsInfo> BERLIN = Optional.of(new CapitalsInfo("Berlin", "quadratisch praktisch gut"));
    private final CapitalsInfo PARIS = new CapitalsInfo("Paris", "la france");

    @MockBean
    private CapitalsInfoService service;
    @MockBean
    CapitalsInfoValidator validator;
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getAllTest() throws Exception {
        mockMvc.perform(request(HttpMethod.GET, "/capital"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
        verify(service).findAll();
    }

    @Test
    public void getByIdTest() throws Exception {
        given(service.findById(anyInt())).willReturn(BERLIN);
        mockMvc.perform(request(HttpMethod.GET, "/capital/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
        verify(service).findById(1);
    }

    @Test
    public void addCapitalTest() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        mockMvc.perform(request(HttpMethod.POST, "/capital")
                .contentType(MediaType.APPLICATION_JSON)
                .content(ow.writeValueAsString(PARIS)))
                .andExpect(status().isOk());
        verify(service).create(PARIS);
    }


    @Test
    public void updateCapitalTest() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        mockMvc.perform(request(HttpMethod.POST, "/capital/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(ow.writeValueAsString(PARIS)))
                .andExpect(status().isOk());
        verify(service).updateById(PARIS.getName(), PARIS.getDescription(), 1);
    }


    @Test
    public void deleteCapitalTest() throws Exception {
        mockMvc.perform(request(HttpMethod.DELETE, "/capital/1"))
                .andExpect(status().isOk());
        verify(service).deleteById(1);
    }
}
