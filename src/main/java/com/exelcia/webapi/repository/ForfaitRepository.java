package com.exelcia.webapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.exelcia.webapi.model.Forfait;

@Repository
public interface ForfaitRepository extends JpaRepository<Forfait, Long> {

}
