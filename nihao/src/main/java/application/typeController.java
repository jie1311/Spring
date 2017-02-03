package application;

import entities.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import repository.TypeRepository;

@RestController
@EnableMongoRepositories(basePackages = {"repository"})
public class typeController {

    private static final String template = "Hello, %s %s-%s!";
    @Autowired
    private TypeRepository repository;

    @RequestMapping("/index")
    public String getAllType() {
        String result = "";
        for (Type type : repository.findAll()) {
            result += String.format(template, type.getManufacturer(), type.getModel(), type.getSubModel()) + "<br />";
        }
    return result;
    }

//    public addType() {
//        repository.save(new Type("", "", ""));
//    }
}
