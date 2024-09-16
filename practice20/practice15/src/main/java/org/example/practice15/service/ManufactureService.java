package org.example.practice15.service;

import org.example.practice15.model.Manufacture;
import org.example.practice15.repository.ManufactureRepository;
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
public class ManufactureService {

    private static final Logger logger = LoggerFactory.getLogger(ManufactureService.class);

    @Autowired
    private ManufactureRepository manufactureRepository;

    @Autowired
    private EntityManager entityManager;

    public List<Manufacture> findManufactures(String name, String address) {
        logger.info("Finding manufactures with name: {}, address: {}", name, address);

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Manufacture> criteriaQuery = criteriaBuilder.createQuery(Manufacture.class);
        Root<Manufacture> manufactureRoot = criteriaQuery.from(Manufacture.class);

        List<Predicate> predicates = new ArrayList<>();

        if (name != null && !name.isEmpty()) {
            predicates.add(criteriaBuilder.like(manufactureRoot.get("name"), "%" + name + "%"));
        }
        if (address != null && !address.isEmpty()) {
            predicates.add(criteriaBuilder.like(manufactureRoot.get("address"), "%" + address + "%"));
        }

        criteriaQuery.where(predicates.toArray(new Predicate[0]));
        List<Manufacture> manufactures = entityManager.createQuery(criteriaQuery).getResultList();

        logger.info("Found {} manufactures", manufactures.size());
        return manufactures;
    }

    public Optional<Manufacture> findById(Long id) {
        logger.info("Finding manufacture by id: {}", id);
        return manufactureRepository.findById(id);
    }

    public Manufacture save(Manufacture manufacture) {
        logger.info("Saving manufacture: {}", manufacture);
        return manufactureRepository.save(manufacture);
    }

    public void deleteById(Long id) {
        logger.info("Deleting manufacture by id: {}", id);
        manufactureRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        boolean exists = manufactureRepository.existsById(id);
        logger.info("Manufacture with id {} exists: {}", id, exists);
        return exists;
    }
}
