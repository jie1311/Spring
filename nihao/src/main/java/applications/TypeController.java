package applications;

import entities.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import repositories.TypeRepository;
import org.springframework.ui.Model;

@Controller
@EnableMongoRepositories(basePackages = {"repositories"})
public class TypeController {

    @Autowired
    private TypeRepository repository;

    @RequestMapping("/index")
    public String index(Model model) {
        String result = "";
        for (Type type : repository.findAll()) {
            result += String.format("Hello, %s %s-%s!", type.getManufacturer(), type.getModel(), type.getSubModel()) + " ";
        }
        model.addAttribute("types", result);
        System.out.println(model);
        return "index";
    }

//    public addType() {
//        repository.save(new Type("", "", ""));
//    }
}
