
package com.portfoliobe.WJGZ.Repository;

import com.portfoliobe.WJGZ.Entity.Hysoft;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RHysoft extends JpaRepository<Hysoft, Integer>{
    Optional<Hysoft> findByNombre(String nombre);
    public boolean existsByNombre(String nombre);
    
    
}
