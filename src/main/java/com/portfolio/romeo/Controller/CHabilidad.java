/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.romeo.Controller;

import com.portfolio.romeo.Dto.dtoHabilidad;
import com.portfolio.romeo.Entity.Habilidad;
import com.portfolio.romeo.Security.Controller.Mensaje;
import com.portfolio.romeo.Service.SHabilidad;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("habilidades")
@CrossOrigin(origins = "http://localhost:4200")
public class CHabilidad {
    @Autowired
    SHabilidad sHabilidad;
    
    @GetMapping("/lista")
    public ResponseEntity<List<Habilidad>> list(){
        List<Habilidad> list = sHabilidad.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<Habilidad> getById(@PathVariable("id")int id){
        if(!sHabilidad.existsById(id))
            return new ResponseEntity(new Mensaje("no existe esta Habilidad"), HttpStatus.NOT_FOUND);
        Habilidad habilidad = sHabilidad.getOne(id).get();
        return new ResponseEntity(habilidad, HttpStatus.OK);
    }
    
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoHabilidad dtohabilidad){
        if(StringUtils.isBlank(dtohabilidad.getNombreH()))
            return new ResponseEntity(new Mensaje("El nombre es OBLIGATORIO"), HttpStatus.BAD_REQUEST);
        if(sHabilidad.existsByNombreH(dtohabilidad.getNombreH()))
            return new ResponseEntity(new Mensaje("La habilidad ya existe"), HttpStatus.BAD_REQUEST);
        Habilidad habilidad = new Habilidad(dtohabilidad.getNombreH(),dtohabilidad.getDescripcionH(),dtohabilidad.getCantidad());
        sHabilidad.save(habilidad);
        
        return new ResponseEntity(new Mensaje("Habilidad Agregada"),HttpStatus.OK);
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoHabilidad dtohabilidad){
        if(!sHabilidad.existsById(id))
            return new ResponseEntity(new Mensaje("La Id de la habilidad no existe"),HttpStatus.BAD_REQUEST);
        
        if(sHabilidad.existsByNombreH(dtohabilidad.getNombreH()) && sHabilidad.getByNombreH(dtohabilidad.getNombreH()).get().getId() != id)
            return new ResponseEntity(new Mensaje("Esa Habilidad ya existe"), HttpStatus.BAD_REQUEST);
        
        if(StringUtils.isBlank(dtohabilidad.getNombreH()))
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        
        Habilidad habilidad = sHabilidad.getOne(id).get();
        habilidad.setNombreH(dtohabilidad.getNombreH());
        habilidad.setDescripcionH(dtohabilidad.getDescripcionH());
        habilidad.setCantidad(dtohabilidad.getCantidad());
        
        sHabilidad.save(habilidad);
        return new ResponseEntity(new Mensaje("Habilidad actualizada"),HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
        if(!sHabilidad.existsById(id))
            return new ResponseEntity(new Mensaje("La Id de la habilidad no existe"),HttpStatus.BAD_REQUEST);
        
        sHabilidad.delete(id);
        return new ResponseEntity(new Mensaje("La habilidad ha sido eliminada"), HttpStatus.OK);
    }
}
