package com.pinsoft.ticketwebbe.controller;

import com.pinsoft.ticketwebbe.dto.StationRequest;
import com.pinsoft.ticketwebbe.dto.StationUpdateRequest;
import com.pinsoft.ticketwebbe.entity.Station;
import com.pinsoft.ticketwebbe.service.StationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequiredArgsConstructor
public class StationController {

    private final StationService stationService;

    @GetMapping("/station")
    public Collection<Station> get(){
        return stationService.listAll();
    }

    @GetMapping("/station/{id}")
    public Station get(@PathVariable Long id){
        return stationService.get(id);
    }

    @DeleteMapping("/station/{id}")
    public void delete(@PathVariable Long id){
        stationService.delete(id);
    }

    @PostMapping("/station")
    public Station add(@RequestBody StationRequest stationRequest) {
        return stationService.save(stationRequest);
    }
    @PutMapping("/station/{id}")
    public Station update(@RequestBody StationUpdateRequest stationUpdateRequest){
        return stationService.update(stationUpdateRequest);
    }

}
