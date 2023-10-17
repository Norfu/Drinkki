package Models;

import java.util.Date;

public class DrunkDay {
    private int Id;
    private Date DrunkDate;
    private double DrunkWater;

    public DrunkDay(int id, Date drunkDate, double drunkWater) {
        Id = id;
        DrunkDate = drunkDate;
        DrunkWater = drunkWater;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public Date getDrunkDate() {
        return DrunkDate;
    }

    public void setDrunkDate(Date drunkDate) {
        DrunkDate = drunkDate;
    }

    public double getDrunkWater() {
        return DrunkWater;
    }

    public void setDrunkWater(double drunkWater) {
        DrunkWater = drunkWater;
    }
}
