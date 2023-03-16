package com.example.monitorambiental.Services.implementacion;
//
import com.example.monitorambiental.Services.CondicionesService;
import com.example.monitorambiental.Dao.CondicionesDao;
import com.example.monitorambiental.model.Condiciones;
//

import com.mongodb.client.*;

import org.bson.Document;
//

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
//

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Date;
@Service
public class CondicionesServiceImp  implements CondicionesService {
    @Autowired
    MongoClient cliente;
    @Autowired
    MongoConverter conversor;
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
    /*consulta de los ultimos dias*/
    @Override
    @Transactional(readOnly = true)
    public List<Condiciones> findByfiltro(String tiempo_inicial, String tiempo_final){
        MongoDatabase database = cliente.getDatabase("monitor");
        MongoCollection<Document> colecion = database.getCollection("condiciones");
        AggregateIterable<Document> resultado = colecion.aggregate(Arrays.asList(new Document("$match",
                new Document("fecha",
                new Document("$gte",
                new Date(Long.parseLong(tiempo_inicial)))
                .append("$lte",
                new Date(Long.parseLong(tiempo_final)))))));

        final List<Condiciones> condiciones = new ArrayList<>();
        resultado.forEach(documento -> {
            condiciones.add(conversor.read(Condiciones.class,documento));
        });
        return condiciones;
    }

}
