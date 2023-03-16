package com.example.monitorambiental.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



@Data
@Document(collection = "condiciones")
public class Agregado {
    @Id
    private String id;
    private double temperatura;
    private double humedad;
    private int horas;
}
