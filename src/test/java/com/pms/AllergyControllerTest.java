package com.pms;

import com.pms.controller.AllergyController;
import com.pms.entity.Allergy;

import com.pms.service.impl.AllergyServiceImpl;
import org.junit.jupiter.api.Test;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AllergyController.class)
public class AllergyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AllergyServiceImpl allergyService;

    @Test
    public void testFindAllAllergies() throws Exception {
        List<Allergy> allergies = Arrays.asList(
                new Allergy("1", "Peanut allergy", "nuts"),
                new Allergy("2", "Shellfish allergy","nuts"));

        when(allergyService.getAllergies()).thenReturn(allergies);

        mockMvc.perform(get("/api/v1/allergies")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()));
    }

    @Test
    public void testFindAllergyById() throws Exception {
        Allergy allergy = new Allergy("1", "Peanut allergy", "nuts");

        when(allergyService.getAllergyById(anyString())).thenReturn(allergy);

        mockMvc.perform(get("/api/v1/allergy")
                .param("allergyId", "1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()));
    }
    
    
}
