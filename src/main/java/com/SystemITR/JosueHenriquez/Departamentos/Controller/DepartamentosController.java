package com.SystemITR.JosueHenriquez.Departamentos.Controller;

import com.SystemITR.JosueHenriquez.Departamentos.DTO.DepartamentosDTO;
import com.SystemITR.JosueHenriquez.Departamentos.Entity.DepartamentosEntity;
import com.SystemITR.JosueHenriquez.Departamentos.Service.DepartamentoService;
import com.SystemITR.JosueHenriquez.Response.ApiResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tools.jackson.core.exc.StreamWriteException;

import java.util.List;

@Slf4j
@RestController @RequestMapping("/api/departamentos")
@CrossOrigin
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

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<DepartamentosDTO>> obtenerDatosPorId(@PathVariable Long id) {
        try {

            DepartamentosDTO dto = service.buscarDepartamento(id);

            if (dto!= null) {
                log.info("Se obtuvieron los datos del departamento: " + dto);
                ApiResponse<DepartamentosDTO> respuestaExito = new ApiResponse<>(true, "Se obtuvieron los datos del departamento con ID: " + id,dto);
                return ResponseEntity.ok(respuestaExito);

            }

            log.info("Datos no encontrados con ID: " + id);
            ApiResponse<DepartamentosDTO> respuesta = new ApiResponse<>(false,"Datos no encontrados con ID: " + id);

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
        } catch (Exception e) {
            log.error("Error crítico al obtener el registro con ID: " + id);
            e.printStackTrace();
            ApiResponse<DepartamentosDTO> respuestaError = new ApiResponse<>(false,"Error crítico al obtener el registro con ID: " + id);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuestaError);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminarDepartamento(@PathVariable Long id){
        try {
            boolean respuesta = service.eliminarData(id);
            if (respuesta) {
                log.info("Departamento con ID: " +id + "eliminado.");
                ApiResponse<Void> respuestaExitosa = new ApiResponse<>(true,"Departamento con ID: " +id + "eliminado.");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuestaExitosa);
            }

            log.info("Departamento con ID: " + id);
            ApiResponse<Void> respuestaNoEncotrada = new ApiResponse<>(false,"Departamento con ID: " + id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuestaNoEncotrada);
        } catch (Exception e) {
            log.error("Error crítico al eliminar el registro con ID: " + id);
            e.printStackTrace();
            ApiResponse<Void> respuestaError = new ApiResponse<>(false,"Error crítico al eliminar el registro con ID: " + id);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuestaError);

        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<DepartamentosDTO>> actualizarData(@PathVariable Long id,@Valid @RequestBody DepartamentosDTO dto) {
        try {
            DepartamentosDTO data = service.actualizar(id,dto);
            if (data != null) {
                log.info("Deparatamento con ID: " + id + "ha sido actualizado.");
                ApiResponse<DepartamentosDTO> respustaExitosa = new ApiResponse<>(true,"Deparatamento con ID: " + id + "ha sido actualizado.",data);
                return ResponseEntity.ok(respustaExitosa);
            }

            log.warn("No se pudo completar la actualización del departamento con ID: " +id);
            ApiResponse<DepartamentosDTO> respuestaNoEncontrada = new ApiResponse<>(false,"No se pudo completar la actualización del departamento con ID: " +id);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuestaNoEncontrada);
        } catch (Exception e) {
            log.error("Error crítico al actualizar el registro con ID: " + id);
            e.printStackTrace();
            ApiResponse<DepartamentosDTO> respuestaError = new ApiResponse<>(false,"Error crítico al actualizar el registro con ID: " + id);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuestaError);
        }
    }

    @GetMapping("/abreviatura/{abreviatura}")
    public ResponseEntity<ApiResponse<DepartamentosDTO>> buscarPorAbreviatura(@PathVariable String abreviatura) {
        try {
            DepartamentosDTO data = service.buscarAbreviatura(abreviatura);

            if (data!= null) {
                log.info("Se obtuvieron los datos del departamento: " + data);
                ApiResponse<DepartamentosDTO> respuestaExito = new ApiResponse<>(true, "Se obtuvieron los datos del departamento con la abreviatura: : " + abreviatura,data);
                return ResponseEntity.ok(respuestaExito);

            }

            log.warn("Datos no encontrados con la abreviatura: " + abreviatura);
            ApiResponse<DepartamentosDTO> respuesta = new ApiResponse<>(false,"Datos no encontrados con ID: " + abreviatura);

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
        } catch (Exception e) {
            log.error("Error crítico al eliminar el registro con la abreviatura: " + abreviatura);
            e.printStackTrace();
            ApiResponse<DepartamentosDTO> respuestaError = new ApiResponse<>(false,"Error crítico al eliminar el registro con la abreviatura: " + abreviatura);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuestaError);
        }
    }
}





