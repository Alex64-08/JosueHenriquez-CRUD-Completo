package com.SystemITR.JosueHenriquez.Departamentos.Controller;

import com.SystemITR.JosueHenriquez.Departamentos.DTO.DepartamentosDTO;
import com.SystemITR.JosueHenriquez.Response.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController @RequestMapping("/api/departamentos")
public class DepartamentosController {

    @PostMapping
    public ResponseEntity<ApiResponse<DepartamentosDTO>> nuevoDepartamento(@Valid @RequestBody DepartamentosDTO json) {

        try {
            ApiResponse<DepartamentosDTO> respuesta = new ApiResponse<>(true, "Datos ingresados exitosamente", json);
            return ResponseEntity.ok(respuesta);
        } catch (Exception e) {
            e.printStackTrace();
            ApiResponse<DepartamentosDTO> respuesta = new ApiResponse<>(false, "El proceso no se pudo completar: ", json);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuesta);
        }

    }
}
