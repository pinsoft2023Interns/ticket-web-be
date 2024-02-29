package com.pinsoft.ticketwebbe.controller;

import com.pinsoft.ticketwebbe.entity.Bus;
import com.pinsoft.ticketwebbe.service.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

@RestController
public class BusController {
    @Autowired
    BusService busService;

    @GetMapping("/buses")
    public Collection<Bus> get(){
        return busService.listAll();
    }

    @GetMapping("/bus/{id}")
    public Bus get(@PathVariable Long id){
        return busService.get(id);
    }

    @DeleteMapping("/bus/{id}")
    public void delete(@PathVariable Long id){
        busService.delete(id);
    }
    //TODO
    /*
    Change requestbody to BusDto
     */
    @PostMapping("/buses")
    public Bus add(@RequestBody Bus bus){
        return busService.save(bus);
    }


}
