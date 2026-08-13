package com.SystemITR.JosueHenriquez.empleados.Entity;

import com.SystemITR.JosueHenriquez.departamentos.Entity.DepartamentosEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter @Entity
@Table(name = "TBEMPLEADOS")
public class EmpleadosEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EMPLEADO_ID")
    private Long id;
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "APELLIDO")
    private String apellido;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "FECHA_INGRESO")
    private String fechaIngreso;
    @Column(name = "SALARIO")
    private Double salario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DEPARTAMENTO_ID")
    private DepartamentosEntity departamento;
}
