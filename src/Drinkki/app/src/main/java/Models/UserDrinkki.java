package Models;

public class UserDrinkki {
    private int Id;
    private String Name;
    private double Weight;
    private String Gender;
    private double WaterNeeded;

    public UserDrinkki(String name, double weight, String gender) {
        Name = name;
        Weight = weight;
        Gender = gender;

        //Nombre de litre journalier necessaire
        WaterNeeded = (weight - 20) * 15 +1500;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public double getWeight() {
        return Weight;
    }

    public void setWeight(double weight) {
        Weight = weight;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public double getWaterNeeded() {
        return WaterNeeded;
    }

    public void setWaterNeeded(double waterNeeded) {
        WaterNeeded = waterNeeded;
    }
}
