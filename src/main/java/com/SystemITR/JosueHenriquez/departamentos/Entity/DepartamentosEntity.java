package com.SystemITR.JosueHenriquez.departamentos.Entity;

import com.SystemITR.JosueHenriquez.empleados.DTO.EmpleadosDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "")
    private List<EmpleadosDTO> listaEmpleados =  new ArrayList<>();
}
