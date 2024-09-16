package org.example.practice15;

import org.example.practice15.model.Manufacture;
import org.example.practice15.repository.ManufactureRepository;
import org.example.practice15.service.EmailService;
import org.example.practice15.service.ManufactureService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ManufactureServiceTest {

    @InjectMocks
    private ManufactureService manufactureService;

    @Mock
    private ManufactureRepository manufactureRepository;

    @Mock
    private EntityManager entityManager;

    @Mock
    private EmailService emailService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindManufactures() {
        CriteriaBuilder criteriaBuilder = mock(CriteriaBuilder.class);
        CriteriaQuery<Manufacture> criteriaQuery = mock(CriteriaQuery.class);
        Root<Manufacture> root = mock(Root.class);
        Predicate predicate = mock(Predicate.class);
        TypedQuery<Manufacture> typedQuery = mock(TypedQuery.class);

        when(entityManager.getCriteriaBuilder()).thenReturn(criteriaBuilder);
        when(criteriaBuilder.createQuery(Manufacture.class)).thenReturn(criteriaQuery);
        when(criteriaQuery.from(Manufacture.class)).thenReturn(root);

        when(criteriaBuilder.like(root.get("name"), "%Samsung%")).thenReturn(predicate);
        when(criteriaBuilder.like(root.get("address"), "%Seoul%")).thenReturn(predicate);

        when(criteriaQuery.where(any(Predicate[].class))).thenReturn(criteriaQuery);
        when(entityManager.createQuery(criteriaQuery)).thenReturn(typedQuery);

        List<Manufacture> expectedManufactures = Arrays.asList(new Manufacture());
        when(typedQuery.getResultList()).thenReturn(expectedManufactures);

        List<Manufacture> actualManufactures = manufactureService.findManufactures("Samsung", "Seoul");

        assertEquals(expectedManufactures, actualManufactures);
    }

    @Test
    void testFindById() {
        Manufacture manufacture = new Manufacture();
        manufacture.setId(1L);
        when(manufactureRepository.findById(1L)).thenReturn(Optional.of(manufacture));

        Optional<Manufacture> result = manufactureService.findById(1L);

        assertTrue(result.isPresent());
        assertEquals(manufacture, result.get());
    }

    @Test
    void testSave() {
        Manufacture manufacture = new Manufacture();
        manufacture.setName("Test Manufacture");

        when(manufactureRepository.save(manufacture)).thenReturn(manufacture);

        Manufacture result = manufactureService.save(manufacture);

        assertEquals(manufacture, result);
        verify(emailService, times(1)).sendEmail(anyString(), anyString(), anyString());
    }

    @Test
    void testDeleteById() {
        doNothing().when(manufactureRepository).deleteById(1L);

        manufactureService.deleteById(1L);

        verify(manufactureRepository, times(1)).deleteById(1L);
    }

    @Test
    void testExistsById() {
        when(manufactureRepository.existsById(1L)).thenReturn(true);

        boolean exists = manufactureService.existsById(1L);

        assertTrue(exists);
    }
}
