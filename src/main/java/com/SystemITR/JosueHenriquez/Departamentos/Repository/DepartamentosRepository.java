package com.SystemITR.JosueHenriquez.Departamentos.Repository;

import com.SystemITR.JosueHenriquez.Departamentos.Entity.DepartamentosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartamentosRepository extends JpaRepository<DepartamentosEntity, Long> {

}
