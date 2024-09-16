package org.example.practice15.service;

import org.example.practice15.model.Phone;
import org.example.practice15.repository.PhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PhoneService {

    @Autowired
    private PhoneRepository phoneRepository;

    @Autowired
    private EntityManager entityManager;

    public List<Phone> findPhones(String name, Integer creationYear, Long manufactureId) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Phone> criteriaQuery = criteriaBuilder.createQuery(Phone.class);
        Root<Phone> phoneRoot = criteriaQuery.from(Phone.class);

        List<Predicate> predicates = new ArrayList<>();

        if (name != null && !name.isEmpty()) {
            predicates.add(criteriaBuilder.like(phoneRoot.get("name"), "%" + name + "%"));
        }
        if (creationYear != null) {
            predicates.add(criteriaBuilder.equal(phoneRoot.get("creationYear"), creationYear));
        }
        if (manufactureId != null) {
            predicates.add(criteriaBuilder.equal(phoneRoot.get("manufacture").get("id"), manufactureId));
        }

        criteriaQuery.where(predicates.toArray(new Predicate[0]));
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    public Optional<Phone> findById(Long id) {
        return phoneRepository.findById(id);
    }

    public Phone save(Phone phone) {
        return phoneRepository.save(phone);
    }

    public void deleteById(Long id) {
        phoneRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return phoneRepository.existsById(id);
    }
}
