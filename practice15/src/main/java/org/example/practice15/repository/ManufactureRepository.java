package org.example.practice15.repository;


import org.example.practice15.model.Manufacture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManufactureRepository extends JpaRepository<Manufacture, Long> {
}
