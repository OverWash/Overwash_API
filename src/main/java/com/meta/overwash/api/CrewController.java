package com.meta.overwash.api;

import com.meta.overwash.domain.ReservationDTO;
import com.meta.overwash.domain.WashingCompleteDTO;
import com.meta.overwash.service.CrewService;
import com.meta.overwash.service.ReservationConfirmedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/crew")
public class CrewController {

    @Autowired
    private CrewService crewService;

    @Autowired
    private ReservationConfirmedService rcService;

    @GetMapping("/pickup-list-limit")
    public List<ReservationDTO> pickupListLimit() throws Exception {
        return crewService.getToBeCollectListLimit();
    }

    @GetMapping("/to-be-limit")
    public List<WashingCompleteDTO> toBeDeliveryListLimit() throws Exception {
        return crewService.getWcListLimit();
    }

//    @GetMapping("")


}
