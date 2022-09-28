/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfoliobe.WJGZ.Controller;

import com.portfoliobe.WJGZ.Dto.dtoEducacion;
import com.portfoliobe.WJGZ.Entity.Educacion;
import com.portfoliobe.WJGZ.Security.Controller.Mensaje;
import com.portfoliobe.WJGZ.Service.SEducacion;
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
@RequestMapping("/educacion")
//@CrossOrigin(origins ="http://localhost:4200")
@CrossOrigin(origins ="https://fendportfoliowjgz.web.app/main")
public class CEducacion {
    
    @Autowired 
    SEducacion sEducacion;
    
    
    @GetMapping("/lista")
    public ResponseEntity<List<Educacion>> list(){
        
        List<Educacion> list = sEducacion.list();
        return new ResponseEntity(list, HttpStatus.OK);
               
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<Educacion> getById(@PathVariable("id") int id){
     if(!sEducacion.existsById(id)){
         return new ResponseEntity(new Mensaje("No existe el id"), HttpStatus.BAD_REQUEST);
                 }
     Educacion educacion = sEducacion.getOne(id).get();
     return new ResponseEntity(educacion, HttpStatus.OK);
    }
    
    
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoEducacion dtoeduc){
       if(StringUtils.isBlank(dtoeduc.getNombreEdu()))
           return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
    
       if(sEducacion.existsByNombreEdu(dtoeduc.getNombreEdu()))
           return new ResponseEntity(new Mensaje("Esa Educacion existe"), HttpStatus.BAD_REQUEST);
       
       Educacion educacion = new Educacion(dtoeduc.getNombreEdu(), dtoeduc.getDescripcionEdu());
       sEducacion.save(educacion);
       
       return new ResponseEntity(new Mensaje("Educacion agregada exitosamente"), HttpStatus.OK);
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoEducacion dtoeduc){
        if(!sEducacion.existsById(id))
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);
        
        if(sEducacion.existsByNombreEdu(dtoeduc.getNombreEdu()) && sEducacion.getByNombreEdu(dtoeduc.getNombreEdu()).get().getId() != id)
            return new ResponseEntity(new Mensaje("Educacion existe"), HttpStatus.BAD_REQUEST);
        
        if(StringUtils.isBlank(dtoeduc.getNombreEdu()))
            return new ResponseEntity(new Mensaje("Nombre Obligatorio"), HttpStatus.BAD_REQUEST);
        
        Educacion educacion = sEducacion.getOne(id).get();
        educacion.setNombreEdu(dtoeduc.getNombreEdu());
        educacion.setDescripcionEdu(dtoeduc.getDescripcionEdu());
        
        sEducacion.save(educacion);
        return new ResponseEntity(new Mensaje("Educacion actualizada"), HttpStatus.OK);
    }
  
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
        if(!sEducacion.existsById(id))
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);
        sEducacion.delete(id);
        
        return new ResponseEntity(new Mensaje("Educacion eliminada"), HttpStatus.OK);
    }
    
    
    
    
    
    
    
}
