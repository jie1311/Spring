package entities;

import org.springframework.data.annotation.Id;

public class Type {

    @Id
    private String id;

    private String manufacturer;
    private String model;
    private String subModel;

    public Type (String manufacturer, String model, String subModel) {
        this.manufacturer = manufacturer;
        this.model = model;
        this.subModel = subModel;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSubModel() {
        return subModel;
    }

    public void setSubModel(String subModel) {
        this.subModel = subModel;
    }

    public String getType() {
        return String.format("Manufacturer:%s Model:%s-%s", manufacturer, model, subModel);
    }
}
