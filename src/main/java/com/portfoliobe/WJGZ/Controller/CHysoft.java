
package com.portfoliobe.WJGZ.Controller;

import com.portfoliobe.WJGZ.Dto.dtoHysoft;
import com.portfoliobe.WJGZ.Entity.Hysoft;
import com.portfoliobe.WJGZ.Security.Controller.Mensaje;
import com.portfoliobe.WJGZ.Service.SHysoft;
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
@CrossOrigin(origins = "http://localhost:8080")
//@CrossOrigin(origins ="https://fendportfoliowjgz.web.app/main")
@RequestMapping("/rhysoft")
public class CHysoft {

    @Autowired
    SHysoft sHysoft;

    @GetMapping("/lista")
    public ResponseEntity<List<Hysoft>> list() {

        List<Hysoft> list = sHysoft.list();
        return new ResponseEntity(list, HttpStatus.OK);

    }    
    @GetMapping("/detail/{id}")
    public ResponseEntity<Hysoft> getById(@PathVariable("id") int id) {
        if (!sHysoft.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe el id"), HttpStatus.BAD_REQUEST);
        }
        Hysoft hysoft = sHysoft.getOne(id).get();
        return new ResponseEntity(hysoft, HttpStatus.OK);
    }

    @PostMapping("/crearExp")
    public ResponseEntity<?> create(@RequestBody dtoHysoft dtohysoft) {
        if (StringUtils.isBlank(dtohysoft.getNombre())) {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }

        if (sHysoft.existsByNombre(dtohysoft.getNombre())) {
            return new ResponseEntity(new Mensaje("Esa habilidad existe"), HttpStatus.BAD_REQUEST);
        }

        Hysoft hysoft = new Hysoft(dtohysoft.getNombre(), dtohysoft.getPorcentaje());
        sHysoft.save(hysoft);

        return new ResponseEntity(new Mensaje("Experiencia agregada exitosamente"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoHysoft dtohysoft) {
        if (!sHysoft.existsById(id)) {
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);
        }

        if (sHysoft.existsByNombre(dtohysoft.getNombre()) && sHysoft.getByNombre(dtohysoft.getNombre()).get().getId() != id) {
            return new ResponseEntity(new Mensaje("Habilidad existe"), HttpStatus.BAD_REQUEST);
        }

        if (StringUtils.isBlank(dtohysoft.getNombre())) {
            return new ResponseEntity(new Mensaje("Nombre Obligatorio"), HttpStatus.BAD_REQUEST);
        }

        Hysoft hysoft = sHysoft.getOne(id).get();
        hysoft.setNombre(dtohysoft.getNombre());
        hysoft.setPorcentaje(dtohysoft.getPorcentaje());

        sHysoft.save(hysoft);
        return new ResponseEntity(new Mensaje("Habilidad actualizada"), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!sHysoft.existsById(id)) {
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);
        }
        sHysoft.delete(id);

        return new ResponseEntity(new Mensaje("Habilidad eliminada"), HttpStatus.OK);
    }

}