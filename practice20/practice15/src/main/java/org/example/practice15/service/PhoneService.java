package org.example.practice15.service;

import org.example.practice15.model.Phone;
import org.example.practice15.repository.PhoneRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(PhoneService.class);

    @Autowired
    private PhoneRepository phoneRepository;

    @Autowired
    private EntityManager entityManager;

    public List<Phone> findPhones(String name, Integer creationYear, Long manufactureId) {
        logger.info("Finding phones with name: {}, creationYear: {}, manufactureId: {}", name, creationYear, manufactureId);

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
        List<Phone> phones = entityManager.createQuery(criteriaQuery).getResultList();

        logger.info("Found {} phones", phones.size());
        return phones;
    }

    public Optional<Phone> findById(Long id) {
        logger.info("Finding phone by id: {}", id);
        return phoneRepository.findById(id);
    }

    public Phone save(Phone phone) {
        logger.info("Saving phone: {}", phone);
        return phoneRepository.save(phone);
    }

    public void deleteById(Long id) {
        logger.info("Deleting phone by id: {}", id);
        phoneRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        boolean exists = phoneRepository.existsById(id);
        logger.info("Phone with id {} exists: {}", id, exists);
        return exists;
    }
}
