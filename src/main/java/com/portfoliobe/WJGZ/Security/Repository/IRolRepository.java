
package com.portfoliobe.WJGZ.Security.Repository;


import com.portfoliobe.WJGZ.Security.Entity.Rol;
import com.portfoliobe.WJGZ.Security.Enums.RolNombre;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRolRepository extends JpaRepository<Rol, Integer>{
   Optional<Rol> findByRolNombre(RolNombre rolnombre); 
    
}
