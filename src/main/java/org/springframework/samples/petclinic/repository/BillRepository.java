package org.springframework.samples.petclinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.samples.petclinic.model.Bill;
import org.springframework.stereotype.Repository;

public interface BillRepository extends JpaRepository<Bill, Integer>{

}
