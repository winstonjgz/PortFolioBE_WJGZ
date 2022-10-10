package com.portfoliobe.WJGZ.Controller;

import com.portfoliobe.WJGZ.Dto.dtoExperiencia;
import com.portfoliobe.WJGZ.Entity.Experiencia;
import com.portfoliobe.WJGZ.Security.Controller.Mensaje;
import com.portfoliobe.WJGZ.Service.SExperiencia;
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
@RequestMapping("/explab")
@CrossOrigin(origins ={"http://localhost:4200","https://fendportfoliowjgz.web.app/main"})
//@CrossOrigin(origins ="https://fendportfoliowjgz.web.app/main")
public class CExperiencia {
    @Autowired 
    SExperiencia sExperiencia;
    
    
    @GetMapping("/lista")
    public ResponseEntity<List<Experiencia>> list(){
        
        List<Experiencia> list = sExperiencia.list();
        return new ResponseEntity(list, HttpStatus.OK);
               
    }
    @PostMapping("/crearExp")
    public ResponseEntity<?> create(@RequestBody dtoExperiencia dtoexp){
       if(StringUtils.isBlank(dtoexp.getNombreExp()))
           return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
    
       if(sExperiencia.existsByNombreExp(dtoexp.getNombreExp()))
           return new ResponseEntity(new Mensaje("Esa experiencia existe"), HttpStatus.BAD_REQUEST);
       
       Experiencia experiencia = new Experiencia(dtoexp.getNombreExp(), dtoexp.getDescripcionExp());
       sExperiencia.save(experiencia);
       
       return new ResponseEntity(new Mensaje("Experiencia agregada exitosamente"), HttpStatus.OK);
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoExperiencia dtoexp){
        if(!sExperiencia.existsById(id))
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);
        
        if(sExperiencia.existsByNombreExp(dtoexp.getNombreExp()) && sExperiencia.getByNombreExp(dtoexp.getNombreExp()).get().getId() != id)
            return new ResponseEntity(new Mensaje("Experiencia existe"), HttpStatus.BAD_REQUEST);
        
        if(StringUtils.isBlank(dtoexp.getNombreExp()))
            return new ResponseEntity(new Mensaje("Nombre Obligatorio"), HttpStatus.BAD_REQUEST);
        
        Experiencia experiencia = sExperiencia.getOne(id).get();
        experiencia.setNombreExp(dtoexp.getNombreExp());
        experiencia.setDescripcionExp(dtoexp.getDescripcionExp());
        
        sExperiencia.save(experiencia);
        return new ResponseEntity(new Mensaje("Experiencia actualizada"), HttpStatus.OK);
    }
  
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
        if(!sExperiencia.existsById(id))
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);
        sExperiencia.delete(id);
        
        return new ResponseEntity(new Mensaje("Experiencia eliminada"), HttpStatus.OK);
    }
    
    
    
    
    
}
