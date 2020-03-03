package controllers;

import bd.BaseDatos;
import java.util.ArrayList;
import javax.swing.JLabel;
import logic.Expense;
import logic.Vehicle;
import middlewares.VehicleMiddleware;
import presentation.logic.ConvertMoney;

/**
 *
 * @author Alejo
 */
public class VehicleController 
{
    private VehicleMiddleware middleware;
    private ConvertMoney money;
    private BaseDatos bd;

    public VehicleController(BaseDatos bd) 
    {
        this.bd = bd;
        this.middleware = new VehicleMiddleware();
        this.money = new ConvertMoney();
    }
    
    public ArrayList<Vehicle> index()
    {
        return this.bd.getCrudVehicle().getAllVehicles();
    }
    
    public boolean create(String description, int fuel)
    {
        //System.out.println("ennnntrroooo");
        boolean midd = this.middleware.createVehicle(description, fuel);
        if(midd)
        {
//System.out.println("PASOOO ELL MIDDLEWARE");
            return this.bd.getCrudVehicle().addVehicle(description, fuel);
        }
        else
            return false; 
    }    
    
    public boolean update(Vehicle oldVehicle, String desc, int fuel)
    {
        return this.bd.getCrudVehicle().updateVehicle(oldVehicle, desc, fuel);
    }
    
    public boolean delete(int id)
    {
        if(id < 1)
            return false;
        
        return this.bd.getCrudVehicle().deleteVehicle(id);
    }
    
    public ArrayList<Expense> getAllExpensesOfVehicle(int id, String initial,
                                                String finish, JLabel txtTotal)
    {
        ArrayList<Expense> expenses = this.bd.getCrudExpense().getAllExpensesFromVehicle(id, initial, finish);
        double value = this.bd.getCrudExpense().getTotalWithVehicle(initial, finish, id);
        String varcharTotal = this.money.run(value);
        txtTotal.setText(varcharTotal);
        
        return expenses;
    }
    
}
