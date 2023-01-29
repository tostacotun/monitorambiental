package com.example.monitorambiental.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import java.util.Date;


@Data
@Document
public class Condiciones {
    @Id
    private String id;
    private Date fecha;
    private double temperatura;
    private double humedad;
}
