package com.SystemITR.JosueHenriquez.Departamentos.Service;

import com.SystemITR.JosueHenriquez.Departamentos.DTO.DepartamentosDTO;
import com.SystemITR.JosueHenriquez.Departamentos.Entity.DepartamentosEntity;
import com.SystemITR.JosueHenriquez.Departamentos.Repository.DepartamentosRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class DepartamentoService {

    private final DepartamentosRepository repo;

    public DepartamentoService(DepartamentosRepository repo) {
        this.repo = repo;
    }

    public DepartamentosDTO nuevoDepartamento(@Valid DepartamentosDTO dto) {
        
        try{
            //Convertir a Entity
            DepartamentosEntity entity = convertirEntity(dto);
            //Guardar en la base de datos
            DepartamentosEntity entitySave = repo.save(entity);
            //Devolver una Respuesta
            return convertirDTO(entitySave);
        } catch (Exception e) {
            log.error("Error al guardar la información del departamento: " + e.getMessage());
            return null;
        }
    }

    private DepartamentosEntity convertirEntity(@Valid DepartamentosDTO dto) {

        DepartamentosEntity objEntity = new DepartamentosEntity();
        objEntity.setNombreDepto(dto.getNombreDepto());
        objEntity.setAbreviacion(dto.getAbreviacion());
        objEntity.setUbicacion(dto.getUbicacion());
        return objEntity;
    }

    private DepartamentosDTO convertirDTO(@Valid DepartamentosEntity entity) {

        DepartamentosDTO objDTO = new DepartamentosDTO();
        objDTO.setNombreDepto(entity.getNombreDepto());
        objDTO.setAbreviacion(entity.getAbreviacion());
        objDTO.setUbicacion(entity.getUbicacion());
        return objDTO;
    }

    public List<DepartamentosDTO> obtenerTodo() {
        List<DepartamentosEntity> data = repo.findAll();
        return data.stream().map(this::convertirDTO).collect(Collectors.toList());
    }

    public DepartamentosDTO buscarDepartamento(Long id) {
        Optional<DepartamentosEntity> entidadOpcional =  repo.findById(id);
        return entidadOpcional.map(this::convertirDTO).orElse(null);
    }

    public boolean eliminarData(Long id) {
       if (repo.existsById(id)) {
           repo.deleteById(id);
           return true;
       }

       return false;
    }

    public DepartamentosDTO actualizar(Long id, @Valid DepartamentosDTO dto) {
        try {
            Optional<DepartamentosEntity> entidadOpcional =  repo.findById(id);

            if (entidadOpcional.isPresent()) {
                DepartamentosEntity entidad = entidadOpcional.get();

                entidad.setNombreDepto(dto.getNombreDepto());
                entidad.setAbreviacion(dto.getAbreviacion());
                entidad.setUbicacion(dto.getUbicacion());

                DepartamentosEntity datosGuardados = repo.save(entidad);

                return convertirDTO(datosGuardados);
            }

            return null;

        } catch (Exception e) {
            log.error("Ups, ocurrió un error al procesar la información.");
            return null;
        }
    }

    public DepartamentosDTO buscarAbreviatura(String abreviatura) {
        try {
            Optional<DepartamentosEntity> registro = repo.findByAbreviacion(abreviatura);

            if (registro.isPresent()) {
                return convertirDTO(registro.get());
            }
            log.warn("No existe nigún departamento con abreviatura: " + abreviatura);
            return null;

        } catch (Exception e) {
            log.error("Ocurrió un error durante el proceso.");
            return null;
        }
    }
}
