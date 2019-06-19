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
public class Drivetrain {
    private String carModel;
    private String engine;
    private String transmission;
    private int power;
    private int torque;
    private int popularity;

    public Drivetrain(String carModel, String engine, String transmission, int power, int torque, int popularity) {
        this.carModel = carModel;
        this.engine = engine;
        this.transmission = transmission;
        this.power = power;
        this.torque = torque;
        this.popularity = popularity;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getTorque() {
        return torque;
    }

    public void setTorque(int torque) {
        this.torque = torque;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }
    
    
}
