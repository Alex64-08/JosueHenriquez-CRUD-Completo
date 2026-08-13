package com.SystemITR.JosueHenriquez.empleados.Repository;

import com.SystemITR.JosueHenriquez.empleados.DTO.EmpleadosDTO;
import com.SystemITR.JosueHenriquez.empleados.Entity.EmpleadosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpleadosRepository extends JpaRepository<EmpleadosEntity, Long> {

    boolean existsByEmail(String email);
}
