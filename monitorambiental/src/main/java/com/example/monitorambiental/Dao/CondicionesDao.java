package com.example.monitorambiental.Dao;

import com.example.monitorambiental.model.Condiciones;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CondicionesDao extends MongoRepository<Condiciones, String> {
}
