package org.example.practice15.service;

import org.example.practice15.model.Manufacture;
import org.example.practice15.repository.ManufactureRepository;
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

    @Autowired
    private ManufactureRepository manufactureRepository;

    @Autowired
    private EntityManager entityManager;

    public List<Manufacture> findManufactures(String name, String address) {
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
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    public Optional<Manufacture> findById(Long id) {
        return manufactureRepository.findById(id);
    }

    public Manufacture save(Manufacture manufacture) {
        return manufactureRepository.save(manufacture);
    }

    public void deleteById(Long id) {
        manufactureRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return manufactureRepository.existsById(id);
    }
}
