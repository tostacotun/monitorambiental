package com.example.monitorambiental.Controller;

import com.example.monitorambiental.Services.AgregadoService;
import com.example.monitorambiental.model.Agregado;
import com.example.monitorambiental.model.Condiciones;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/operaciones")
public class AgregadoController {
    @Autowired
    private AgregadoService agregadoService;
    @GetMapping("/agregado")
    public List<Agregado> Agregado(){return agregadoService.Agregado();}


}
