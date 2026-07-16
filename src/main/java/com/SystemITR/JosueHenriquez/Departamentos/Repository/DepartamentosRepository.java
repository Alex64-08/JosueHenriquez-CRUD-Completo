package com.SystemITR.JosueHenriquez.Departamentos.Repository;

import com.SystemITR.JosueHenriquez.Departamentos.Entity.DepartamentosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartamentosRepository extends JpaRepository<DepartamentosEntity, Long> {

    Optional<DepartamentosEntity> findByAbreviacion(String abreviatura);
}
