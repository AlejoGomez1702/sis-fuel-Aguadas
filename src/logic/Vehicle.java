package logic;

/**
 *
 * @author Alejo
 */
public class Vehicle 
{
    private int id;
    private String description;
    private int fuelId;
    private Fuel fuel;

    public Vehicle(int id, String description, int fuelId, Fuel fuel) 
    {
        this.id = id;
        this.description = description;
        this.fuelId = fuelId;
        this.fuel = fuel;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public int getFuelId() {
        return fuelId;
    }

    public Fuel getFuel() {
        return fuel;
    }
    
    
    
        
    
}
