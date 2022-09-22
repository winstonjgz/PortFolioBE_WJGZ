
package com.portfoliobe.WJGZ.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author wgsig
 */
@Entity

public class Persona {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;
        
        @NotNull
        @Size(min=5, max=30, message="Verifique la longitud de los datos ingresados")
        private String nombre;
        
        @NotNull
        @Size(min=5, max=30, message="Verifique la longitud de los datos ingresados")
        private String apellido;
        
       
        @Size(min=5, max=30, message="Verifique la longitud de los datos ingresados")
        private String img;

        
        
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
        
        
        
}
