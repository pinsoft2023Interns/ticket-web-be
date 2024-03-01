package com.pinsoft.ticketwebbe.controller;

import com.pinsoft.ticketwebbe.dto.BusRequest;
import com.pinsoft.ticketwebbe.dto.BusResponse;
import com.pinsoft.ticketwebbe.entity.Bus;
import com.pinsoft.ticketwebbe.entity.Company;
import com.pinsoft.ticketwebbe.service.BusService;
import com.pinsoft.ticketwebbe.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
public class BusController {
    @Autowired
    BusService busService;

    @Autowired
    CompanyService companyService;

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

    @PostMapping("/bus")
    public Bus add(@RequestBody BusRequest busRequest){
        return busService.save(busRequest);
    }


}
