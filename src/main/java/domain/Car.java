/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @author andrei
 */
public class Car implements Comparable<Car>{
    private String manufacturer;
    private String model;
    private String img;
    private String description;

    public Car(String manufacturer, String model, String img, String description) {
        this.manufacturer = manufacturer;
        this.model = model;
        this.img = img;
        this.description = description;
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int compareTo(Car o) {
        if (o.getManufacturer().equals(manufacturer) && 
                o.getModel().equals(model) &&
                o.getImg().equals(img) &&
                o.getDescription().equals(description)){
            return 1;
        }
        return 0;
    }

    
    
    
    
}
