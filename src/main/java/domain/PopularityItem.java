/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.Comparator;

/**
 *
 * @author andrei
 */
public class PopularityItem implements Comparable<PopularityItem>{
    private int drivetrainID;
    private int popularity;

    public PopularityItem(int drivetrainID, int popularity) {
        this.drivetrainID = drivetrainID;
        this.popularity = popularity;
    }

    public int getDrivetrainID() {
        return drivetrainID;
    }

    public void setDrivetrainID(int drivetrainID) {
        this.drivetrainID = drivetrainID;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    @Override
    public int compareTo(PopularityItem o) {
        if(popularity > o.popularity){
            return 1;
        }
        else if(popularity == o.popularity){
            return 0;
        }
        else{
            return -1;
        }
    }
}
