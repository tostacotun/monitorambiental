package com.example.monitorambiental.Services.implementacion;

import com.example.monitorambiental.Dao.AgregadoDao;
import com.example.monitorambiental.Services.AgregadoService;
import com.example.monitorambiental.model.Agregado;
import com.example.monitorambiental.model.Condiciones;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class AgregadoServiceImp implements AgregadoService {
    @Autowired
    MongoClient cliente;
    @Autowired
    MongoConverter conversor;
    /*@Autowired
    private AgregadoDao agregadoDao;*/

    @Override
    @Transactional(readOnly = true)
    public List<Agregado> Agregado(){
        System.out.println("aca colocamos la peticion");
        MongoDatabase database = cliente.getDatabase("monitor");
        MongoCollection<Document> colecion = database.getCollection("condiciones");
        AggregateIterable<Document> resultado = colecion.aggregate(Arrays.asList(new Document("$set",
                new Document("horas",
                        new Document("$hour", "$fecha")))));


        final List<Agregado> agregado = new ArrayList<>();
        resultado.forEach(documento -> {
            agregado.add(conversor.read(Agregado.class,documento));
        });
        /*System.out.println(agregado);*/
        return agregado;
    }

}
