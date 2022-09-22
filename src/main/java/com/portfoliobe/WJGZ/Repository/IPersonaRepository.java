
package com.portfoliobe.WJGZ.Repository;

import com.portfoliobe.WJGZ.Entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author wgsig
 */

@Repository
public interface IPersonaRepository extends JpaRepository<Persona, Long>{
    
}
