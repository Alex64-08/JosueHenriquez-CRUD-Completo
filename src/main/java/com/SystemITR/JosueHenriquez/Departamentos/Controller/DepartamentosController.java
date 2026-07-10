package com.SystemITR.JosueHenriquez.Departamentos.Controller;

import com.SystemITR.JosueHenriquez.Departamentos.DTO.DepartamentosDTO;
import com.SystemITR.JosueHenriquez.Departamentos.Service.DepartamentoService;
import com.SystemITR.JosueHenriquez.Response.ApiResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController @RequestMapping("/api/departamentos")
public class DepartamentosController {

    //Inyección de dependencias del servicio de departamentos
    private final DepartamentoService service;
    public DepartamentosController(DepartamentoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<DepartamentosDTO>> nuevoDepartamento(@Valid @RequestBody DepartamentosDTO json) {

        try {
            DepartamentosDTO dto = service.nuevoDepartamento(json);
            if (dto != null) {
                log.info("Nuevo departamento registrado: " + dto);
                ApiResponse<DepartamentosDTO> respuesta = new ApiResponse<>(true, "Datos ingresados exitosamente", dto);
                return ResponseEntity.ok(respuesta);
            }

            log.warn("Intento de inserción fallido: " + json);
            ApiResponse<DepartamentosDTO> respuestaFallida = new ApiResponse<>(false, "Intento fallido de inserción.",null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuestaFallida);
        } catch (Exception e) {
            log.error("El proceso presentó un fallo inesperado, consulte con el administrador.");
            e.printStackTrace();
            ApiResponse<DepartamentosDTO> respuesta = new ApiResponse<>(false, "El proceso no se pudo completar: ", json);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuesta);
        }

    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<DepartamentosDTO>>> obtenerDatos() {
        try {
            List<DepartamentosDTO> lista = service.obtenerTodo();
            if (lista != null) {
                log.info("Datos de departamentos consultados.");
                ApiResponse<List<DepartamentosDTO>> respuestaExito = new ApiResponse<>(true, "Datos encontrados", lista);
                return ResponseEntity.ok(respuestaExito);
            }

            log.info("Datos no encontrados.");
            ApiResponse<List<DepartamentosDTO>> respuestaNoEncontrada = new ApiResponse<>(true, "Datos no encontrados", lista);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(respuestaNoEncontrada);
        } catch (Exception e) {
            log.error("El proceso presentó un fallo inesperado, consulte con el administrador.");
            e.printStackTrace();
            ApiResponse<List<DepartamentosDTO>> respuesta = new ApiResponse<>(false, "Error, no se pudo completar.", null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuesta);
        }
    }
}
