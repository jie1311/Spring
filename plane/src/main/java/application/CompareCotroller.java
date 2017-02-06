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
import java.util.List;

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
        Aircraft aircraft = aircraftRepository.findById(compareForm.getAircraftId());
        Airport org = airportRepository.findById(compareForm.getOrgAirportId());
        Airport des = airportRepository.findById(compareForm.getDesAirportId());
        if (Calculator.reachable(org, des, aircraft)) {
            model.addAttribute("reachable", "Yes.");
        } else {
            List route = searchResult(org, org, des, aircraft, new ArrayList<>());
            String routeString = "";
            for (Airport via : (ArrayList<Airport>) route) {
                routeString += String.format("-%s", via.getIataCode());
            }
            if (route.isEmpty()){
                routeString = "No.";
            } else {
                routeString = String.format("No. %s%s-%s is available.",org.getIataCode(), routeString, des.getIataCode());
            }
            model.addAttribute("reachable", routeString);
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

    private List reachableAirports(Airport org, Aircraft aircraft) {
        List airports = new ArrayList<>();
        for (Airport des : airportRepository.findAll()) {
            if (Calculator.reachable(org, des, aircraft) && !org.getId().equals(des.getId())) {
                airports.add(des);
            }
        }
        return airports;
    }

    private List<Airport> searchResult(Airport org, Airport via, Airport des, Aircraft aircraft, List result) {
        List<Airport> available = reachableAirports(via, aircraft);
        boolean found = false;
        for (Airport via2 : available) {
            if (des.getId().equals(via2.getId())) {
                result.add(via);
                found = true;
                break;
            }
        }
        if (!found) {
            for (Airport via2 : available){
                if (!org.getId().equals(via2.getId())) {
                    searchResult(org, via2, des, aircraft, result);
                    break;
                }
            }
        }
        return result;
    }
}
