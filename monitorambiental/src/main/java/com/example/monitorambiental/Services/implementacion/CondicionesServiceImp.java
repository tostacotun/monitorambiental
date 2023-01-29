package com.example.monitorambiental.Services.implementacion;

import com.example.monitorambiental.Services.CondicionesService;
import com.example.monitorambiental.Dao.CondicionesDao;
import com.example.monitorambiental.model.Condiciones;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CondicionesServiceImp  implements CondicionesService {
    @Autowired
    private CondicionesDao condicionesDao;
    @Override
    @Transactional(readOnly = false)
    public Condiciones save(Condiciones condiciones){ return condicionesDao.save(condiciones);}
    @Override
    @Transactional(readOnly = false)
    public void delete(String id) {condicionesDao.deleteById(id);}
    @Override
    @Transactional(readOnly = true)
    public Condiciones findById(String id){return condicionesDao.findById(id).orElse(null);}
    @Override
    @Transactional(readOnly = true)
    public List<Condiciones> findAll(){return (List<Condiciones>) condicionesDao.findAll();}

}
