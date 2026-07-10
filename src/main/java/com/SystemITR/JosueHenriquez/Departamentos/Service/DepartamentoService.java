package com.SystemITR.JosueHenriquez.Departamentos.Service;

import com.SystemITR.JosueHenriquez.Departamentos.DTO.DepartamentosDTO;
import com.SystemITR.JosueHenriquez.Departamentos.Entity.DepartamentosEntity;
import com.SystemITR.JosueHenriquez.Departamentos.Repository.DepartamentosRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
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
}
