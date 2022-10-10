package com.portfoliobe.WJGZ.Controller;

import com.portfoliobe.WJGZ.Entity.Persona;
import com.portfoliobe.WJGZ.Interface.IPersonaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author wgsig
 */

@RestController
@CrossOrigin(origins ={"http://localhost:4200","https://fendportfoliowjgz.web.app/main"})
//@CrossOrigin(origins ="https://fendportfoliowjgz.web.app/main")
public class PersonaController {
    @Autowired IPersonaService ipersonaservice;
    
    @GetMapping ("/personas/traer")
    public List<Persona> getPersona(){
        return ipersonaservice.getPersona();
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping ("/personas/crear")
    public String crearPersona(@RequestBody Persona persona){
        ipersonaservice.savePersona(persona);
        return "La persona se creo correctamente";
        
    }
    
    
    @DeleteMapping ("/personas/borrar/{id}")
    public String deletePersona(@PathVariable Long id){
        ipersonaservice.deletePersona(id);
        return "Registro borrado correctamente";
    }
    
    @PutMapping ("/personas/editar/{id}")
    public Persona editPersona (@PathVariable Long id, @RequestParam("nombre") String nuevoNombre,
                                @RequestParam("apellido") String nuevoApellido,
                                @RequestParam("img") String nuevaImg){
        
        Persona persona = ipersonaservice.findPersona(id);
        
        persona.setNombre(nuevoNombre);
        persona.setApellido(nuevoApellido);
        persona.setImg(nuevaImg);
        
        ipersonaservice.savePersona(persona);
        return persona;
    }

            
    
}

