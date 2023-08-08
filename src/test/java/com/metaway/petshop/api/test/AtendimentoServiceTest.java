package com.metaway.petshop.api.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.metaway.petshop.api.dao.request.CadastroAtendimentoRequest;
import com.metaway.petshop.api.entities.Atendimento;
import com.metaway.petshop.api.entities.Pets;
import com.metaway.petshop.api.repository.atendimento.AtendimentoRepository;
import com.metaway.petshop.api.repository.pets.PetsRepository;
import com.metaway.petshop.api.service.atendimento.AtendimentoService;

@ExtendWith(MockitoExtension.class)
public class AtendimentoServiceTest {

    @Mock
    private AtendimentoRepository atendimentoRepository;

    @Mock
    private PetsRepository petRepository;

    @InjectMocks
    private AtendimentoService atendimentoService;

    private Atendimento mockAtendimento;
    private Pets mockPet;

    @BeforeEach
    public void setUp() {
        mockPet = Pets.builder().id(1).nome("Doggo").build();
        mockAtendimento = Atendimento.builder().id(1).descricaoAtendimento("Description").pet(mockPet).build();
    }

    @Test
    public void testCreate() {
        when(petRepository.findById(mockPet.getId())).thenReturn(Optional.of(mockPet));
        when(atendimentoRepository.save(any(Atendimento.class))).thenReturn(mockAtendimento);

        Atendimento result = atendimentoService.create(mockAtendimento);

        assertNotNull(result);
        assertEquals(mockAtendimento.getId(), result.getId());
    }

    @Test
    public void testUpdate() {
        when(atendimentoRepository.findById(mockAtendimento.getId())).thenReturn(Optional.of(mockAtendimento));
        when(atendimentoRepository.save(any(Atendimento.class))).thenReturn(mockAtendimento);

        Atendimento result = atendimentoService.update(mockAtendimento);

        assertNotNull(result);
        assertEquals(mockAtendimento.getId(), result.getId());
    }

    @Test
    public void testFindById() {
        when(atendimentoRepository.findById(mockAtendimento.getId())).thenReturn(Optional.of(mockAtendimento));

        Optional<Atendimento> result = atendimentoService.findById(mockAtendimento.getId());

        assertTrue(result.isPresent());
        assertEquals(mockAtendimento.getId(), result.get().getId());
    }

    @Test
    public void testUpdateFromRequest() {
        // Simulate the request data (you can add more data if necessary)
        CadastroAtendimentoRequest request = new CadastroAtendimentoRequest();
        request.setDescricaoAtendimento("Updated Description");

        when(atendimentoRepository.findById(mockAtendimento.getId())).thenReturn(Optional.of(mockAtendimento));
        when(atendimentoRepository.save(any(Atendimento.class))).thenReturn(mockAtendimento);

        Atendimento result = atendimentoService.updateFromRequest(mockAtendimento.getId(), request);

        assertNotNull(result);
        assertEquals("Updated Description", result.getDescricaoAtendimento());
    }

    // You can add more tests like delete, findAll and others...
}

