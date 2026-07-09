package com.SystemITR.JosueHenriquez.Departamentos.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity @Getter
@Setter @Table(name = "departamentos")
public class DepartamentosEntity {

    @Id
    @Column(name = "DEPARTAMENTO_ID")
    private long id;
    @Column(name = "NOMBRE_DEPT")
    private String nombreDepto;
    @Column(name = "ABREVIATURA")
    private String abreviacion;
    @Column(name = "UBICACION")
    private String ubicacion;
}
