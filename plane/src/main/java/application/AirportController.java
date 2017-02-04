package application;

import entities.Airport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import repositories.AirportRepository;

import java.util.ArrayList;

@Controller
public class AirportController {

    @Autowired
    private AirportRepository repository;

    @RequestMapping("/airport")
    public String aircraft(@RequestParam(value="manufacturer", required=false, defaultValue="All") String manufacturer, Model model) {
        ArrayList airs = new ArrayList<>();
        for (Airport airport : repository.findAll()){
            airs.add(airport.getAirport());
        }
        model.addAttribute("airs", airs);
        System.out.println(model);
        return "airport";
    }
}
