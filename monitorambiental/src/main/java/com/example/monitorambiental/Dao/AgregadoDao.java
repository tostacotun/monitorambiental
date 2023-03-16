package com.example.monitorambiental.Dao;

import com.example.monitorambiental.model.Agregado;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AgregadoDao extends MongoRepository<Agregado, String> {

}
