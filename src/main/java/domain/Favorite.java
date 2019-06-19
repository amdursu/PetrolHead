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
public class Favorite implements java.io.Serializable{
    private Car car;
    private Drivetrain drivetrain;

    public Favorite(Car car, Drivetrain drivetrain) {
        this.car = car;
        this.drivetrain = drivetrain;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Drivetrain getDrivetrain() {
        return drivetrain;
    }

    public void setDrivetrain(Drivetrain drivetrain) {
        this.drivetrain = drivetrain;
    }
    
    
}
