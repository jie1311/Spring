package application;


import entities.Aircraft;
import entities.Airport;
import formValidators.CompareForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import repositories.AircraftRepository;
import repositories.AirportRepository;

import javax.validation.Valid;
import java.util.ArrayList;

@Controller
public class CompareCotroller {
    @Autowired
    private AircraftRepository aircraftRepository;

    @Autowired
    private AirportRepository airportRepository;

    @GetMapping("/compare")
    public String compare(CompareForm compareForm, Model model) throws Exception {
        initialPage(model);
        return "compare";
    }

    @PostMapping("/compare")
    public String compareRange(@Valid CompareForm compareForm, BindingResult bindingResult , Model model){
        int range = aircraftRepository.findById(compareForm.getAircraftId()).getRange();
        Airport org = airportRepository.findById(compareForm.getOrgAirportId());
        Airport des = airportRepository.findById(compareForm.getDesAirportId());
        int distance = Calculator.range(org, des);
        if (range > distance) {
            model.addAttribute("reachable", "Yes.");
        } else {
            model.addAttribute("reachable", "No.");
        }
        initialPage(model);
        return "compare";
    }

    private void initialPage(Model model) {
        ArrayList aircrafts = new ArrayList<>();
        for (Aircraft aircraft : aircraftRepository.findAll()) {
            aircrafts.add(aircraft);
        }
        model.addAttribute("aircrafts", aircrafts);

        ArrayList airports = new ArrayList<>();
        for (Airport airport : airportRepository.findAll()) {
            airports.add(airport);
        }
        model.addAttribute("airports", airports);
    }

}
