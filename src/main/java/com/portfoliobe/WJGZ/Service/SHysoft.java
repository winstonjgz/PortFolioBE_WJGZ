
package com.portfoliobe.WJGZ.Service;

import com.portfoliobe.WJGZ.Entity.Hysoft;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.portfoliobe.WJGZ.Repository.RHysoft;

@Transactional
@Service
public class SHysoft {
    @Autowired
    RHysoft rhysoft;
    
    public List<Hysoft> list(){
        return rhysoft.findAll();
    }
    
    public Optional<Hysoft> getOne(int id){
        return rhysoft.findById(id);
        
    }
    
    public Optional<Hysoft> getByNombre(String nombre){
        return rhysoft.findByNombre(nombre);
    }
    
    public void save(Hysoft skill){
        rhysoft.save(skill);
    }
    
    public void delete(int id){
        rhysoft.deleteById(id);
    }
    
    public boolean existsById(int id){
        return rhysoft.existsById(id);
    }
    public boolean existsByNombre(String nombre){
        return rhysoft.existsByNombre(nombre);
    }
    
    
}

