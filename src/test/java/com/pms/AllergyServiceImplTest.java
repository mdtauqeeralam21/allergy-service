package com.pms;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.pms.entity.Allergy;
import com.pms.exception.AllergyServiceException;
import com.pms.repository.AllergyRepository;
import com.pms.service.impl.AllergyServiceImpl;

@ExtendWith(MockitoExtension.class)
public class AllergyServiceImplTest {

    @Mock
    private AllergyRepository allergyRepository;

    @InjectMocks
    private AllergyServiceImpl allergyService;

  

    @Test
    public void testGetAllergies() throws AllergyServiceException {
        List<Allergy> allergies = new ArrayList<Allergy>();
        allergies.add(new Allergy("1", "Peanut allergy","nuts"));
        allergies.add(new Allergy("2", "Lactose intolerance", "nuts"));
        when(allergyRepository.findAll()).thenReturn(allergies);
        List<Allergy> result = allergyService.getAllergies();
        assertThat(result).isNotNull();
        assertEquals(2, result.size());
    }
    
    
    @Test
    public void testGetAllergiesWithEmptyList() throws AllergyServiceException {
        List<Allergy> allergies = new ArrayList<Allergy>();
        when(allergyRepository.findAll()).thenReturn(allergies);
        allergyService.getAllergies();
    }

    @Test
    public void testGetAllergyById() throws AllergyServiceException {
        Allergy allergy = new Allergy("1", "Peanut allergy", "nuts");
        when(allergyRepository.findByAllergyId("1")).thenReturn(allergy);
        Allergy result = allergyService.getAllergyById("1");
        assertEquals(allergy, result);
    }

    @Test
    public void testGetAllergyByIdWithNullResult() throws AllergyServiceException {
        //when(allergyRepository.findByAllergyId("1")).thenReturn(null);
        assertThrows(AllergyServiceException.class, () -> {
        	allergyService.getAllergyById(null);
        });
    	}
    }


