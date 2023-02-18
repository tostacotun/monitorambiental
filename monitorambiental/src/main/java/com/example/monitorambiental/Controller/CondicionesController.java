package com.example.monitorambiental.Controller;

import com.example.monitorambiental.Services.CondicionesService;
import com.example.monitorambiental.model.Condiciones;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Date;
import java.util.List;


@RestController
@CrossOrigin("*")
@RequestMapping("/Condiciones")
public class CondicionesController {
    @Autowired
    private CondicionesService condicionesService;
    @PostMapping(value = "/subir")
    public ResponseEntity<Condiciones> agregar(@RequestBody Condiciones condiciones){
        Condiciones objeto = condicionesService.save(condiciones);
        return new ResponseEntity<>(objeto, HttpStatus.OK);
    }

    @DeleteMapping(value = "/borrar/{id}")
    public ResponseEntity<Condiciones> eliminar(@PathVariable String id){
        Condiciones objeto = condicionesService.findById(id);
        if (objeto!= null) {
            condicionesService.delete(id);
        }else {
            return new ResponseEntity<>(objeto, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(objeto, HttpStatus.OK);
    }
    @PutMapping(value = "/editar")
    public ResponseEntity<Condiciones> editar(@RequestBody Condiciones condiciones){
        Condiciones objeto = condicionesService.findById(condiciones.getId());
        if (objeto!= null){
            objeto.setFecha(condiciones.getFecha());
            objeto.setTemperatura(condiciones.getTemperatura());
            objeto.setHumedad(condiciones.getHumedad());
            condicionesService.save(objeto);
        }
        else{
            return new ResponseEntity<>(objeto, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return  new ResponseEntity<>(objeto,HttpStatus.OK);
    }
    @GetMapping(value = "/listar")
    public List<Condiciones> consultartodo(){return condicionesService.findAll();}
    @GetMapping(value = "/listar/{id}")
    public Condiciones consultarporid(@PathVariable String id){
        return condicionesService.findById(id);
    }

    @GetMapping("/filtrar")
    public List<Condiciones> filtrar(@RequestParam String tiempo_inicial, @RequestParam String tiempo_final){return condicionesService.findByfiltro(tiempo_inicial,tiempo_final);}

}
