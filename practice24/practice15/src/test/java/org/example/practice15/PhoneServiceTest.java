package org.example.practice15;

import org.example.practice15.model.Manufacture;
import org.example.practice15.model.Phone;
import org.example.practice15.repository.PhoneRepository;
import org.example.practice15.service.EmailService;
import org.example.practice15.service.PhoneService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
class PhoneServiceTest {

    @InjectMocks
    private PhoneService phoneService;

    @Mock
    private PhoneRepository phoneRepository;

    @Mock
    private EntityManager entityManager;

    @Mock
    private EmailService emailService;

    @Test
    void testFindPhones() {
        // Setting up mocks for Criteria API
        CriteriaBuilder criteriaBuilder = mock(CriteriaBuilder.class);
        CriteriaQuery<Phone> criteriaQuery = mock(CriteriaQuery.class);
        Root<Phone> phoneRoot = mock(Root.class);
        Predicate predicate1 = mock(Predicate.class);
        Predicate predicate2 = mock(Predicate.class);
        Predicate predicate3 = mock(Predicate.class);
        TypedQuery<Phone> typedQuery = mock(TypedQuery.class);

        when(entityManager.getCriteriaBuilder()).thenReturn(criteriaBuilder);
        when(criteriaBuilder.createQuery(Phone.class)).thenReturn(criteriaQuery);
        when(criteriaQuery.from(Phone.class)).thenReturn(phoneRoot);

        // Mocking phoneRoot.get("name") with explicit generic type
        Path<String> namePath = mock(Path.class);
        when(phoneRoot.<String>get("name")).thenReturn(namePath);
        when(criteriaBuilder.like(namePath, "%Galaxy%")).thenReturn(predicate1);

        // Mocking phoneRoot.get("creationYear") with explicit generic type
        Path<Integer> creationYearPath = mock(Path.class);
        when(phoneRoot.<Integer>get("creationYear")).thenReturn(creationYearPath);
        when(criteriaBuilder.equal(creationYearPath, 2022)).thenReturn(predicate2);

        // Mocking phoneRoot.get("manufacture").get("id") with explicit generic types
        Path<Manufacture> manufacturePath = mock(Path.class);
        when(phoneRoot.<Manufacture>get("manufacture")).thenReturn(manufacturePath);

        Path<Long> manufactureIdPath = mock(Path.class);
        when(manufacturePath.<Long>get("id")).thenReturn(manufactureIdPath);
        when(criteriaBuilder.equal(manufactureIdPath, 1L)).thenReturn(predicate3);

        // Combining predicates
        Predicate[] predicates = new Predicate[] {predicate1, predicate2, predicate3};
        when(criteriaQuery.where(predicates)).thenReturn(criteriaQuery);
        when(entityManager.createQuery(criteriaQuery)).thenReturn(typedQuery);

        List<Phone> expectedPhones = Arrays.asList(new Phone());
        when(typedQuery.getResultList()).thenReturn(expectedPhones);

        // Calling the method under test
        List<Phone> actualPhones = phoneService.findPhones("Galaxy", 2022, 1L);

        // Assertions
        assertEquals(expectedPhones, actualPhones);
    }

    @Test
    void testFindById() {
        Phone phone = new Phone();
        phone.setId(1L);
        when(phoneRepository.findById(1L)).thenReturn(Optional.of(phone));

        Optional<Phone> result = phoneService.findById(1L);

        assertTrue(result.isPresent());
        assertEquals(phone, result.get());
    }

    @Test
    void testSave() {
        Phone phone = new Phone();
        phone.setName("Test Phone");

        when(phoneRepository.save(phone)).thenReturn(phone);

        Phone result = phoneService.save(phone);

        assertEquals(phone, result);
        verify(emailService, times(1)).sendEmail(anyString(), anyString(), anyString());
    }

    @Test
    void testDeleteById() {
        doNothing().when(phoneRepository).deleteById(1L);

        phoneService.deleteById(1L);

        verify(phoneRepository, times(1)).deleteById(1L);
    }

    @Test
    void testExistsById() {
        when(phoneRepository.existsById(1L)).thenReturn(true);

        boolean exists = phoneService.existsById(1L);

        assertTrue(exists);
    }
}
