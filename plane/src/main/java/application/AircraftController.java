package application;

import entities.Aircraft;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import repositories.AircraftRepository;

import java.util.ArrayList;

@Controller
public class AircraftController {

    @Autowired
    private AircraftRepository repository;

    @RequestMapping("/aircraft")
    public String aircraft(@RequestParam(value="manufacturer", required=false, defaultValue="all") String manufacturer, Model model) {
        ArrayList airs = new ArrayList<>();
        if (manufacturer.equals("all")) {
            for (Aircraft aircraft : repository.findAll()) {
                airs.add(aircraft.getType());
            }
        } else {
            for (Aircraft aircraft : repository.findByManufacturer(manufacturer)) {
                airs.add(aircraft.getType());
            }
        }
        model.addAttribute("airs", airs);
        return "aircraft";
    }
}
