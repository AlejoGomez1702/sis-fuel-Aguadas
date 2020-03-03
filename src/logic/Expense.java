package logic;

/**
 *
 * @author Alejo
 */
public class Expense 
{
    private int id;
    private int vehicleId;
    private String date;
    private String invoiceCode;
    private double value;
    private Vehicle vehicle;

    public Expense(int id, int vehicleId, String date, String invoiceCode, 
                            double value, Vehicle vehicle) 
    {
        this.id = id;
        this.vehicleId = vehicleId;
        this.date = date;
        this.invoiceCode = invoiceCode;
        this.value = value;
        this.vehicle = vehicle;
    }        
    
    public int getId() {
        return id;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public String getDate() {
        return date;
    }

    public String getInvoiceCode() {
        return invoiceCode;
    }

    public double getValue() {
        return value;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }
    
    
    
}
