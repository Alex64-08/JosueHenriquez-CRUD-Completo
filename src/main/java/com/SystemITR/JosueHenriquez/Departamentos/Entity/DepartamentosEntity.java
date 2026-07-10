package com.SystemITR.JosueHenriquez.Departamentos.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity @Getter
@Setter @Table(name = "TBDEPARTAMENTOS")
public class DepartamentosEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DEPARTAMENTO_ID")
    private long id;
    @Column(name = "NOMBRE_DEPT")
    private String nombreDepto;
    @Column(name = "ABREVIATURA")
    private String abreviacion;
    @Column(name = "UBICACION")
    private String ubicacion;
}
