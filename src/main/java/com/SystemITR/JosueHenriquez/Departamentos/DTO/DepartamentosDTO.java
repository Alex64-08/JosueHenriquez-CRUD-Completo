package com.SystemITR.JosueHenriquez.Departamentos.DTO;

import jakarta.validation.constraints.NotBlank;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class DepartamentosDTO {

    private long id;
    @NotBlank @Size(max = 50, message = "Nombre del departamento no puede tener más de 50 caracteres")
    private String nombreDepto;
    @NotBlank @Size(max = 5, message = "Abreviacion no puede tener más de 5 caracteres")
    private String abreviacion;
    @NotBlank @Size(max = 100, message = "Ubicacion no puede tener más de 100 caracteres")
    private String ubicacion;

}
