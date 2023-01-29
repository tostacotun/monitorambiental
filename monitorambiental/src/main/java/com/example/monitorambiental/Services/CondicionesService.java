package com.example.monitorambiental.Services;

import com.example.monitorambiental.model.Condiciones;

import java.util.List;

public interface CondicionesService {
    public Condiciones save(Condiciones condiciones);
    public void delete(String id);
    public Condiciones findById(String id);
    public List<Condiciones> findAll();
}
