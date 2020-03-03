package controllers;

import bd.BaseDatos;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;
import logic.Expense;
import logic.Fuel;
import logic.Vehicle;
import presentation.logic.ConfigTables;
import presentation.logic.PaintTables;

/**
 *
 * @author Alejo
 */
public class MainController 
{
    private PaintTables paintTables;
    private ConfigTables configTables;
    private BaseDatos bd;
    
    //******CONTROLLERS******CONTROLLERS******CONTROLLERS*****//
    private FuelController fuelController;
    private VehicleController vehicleController;
    private ExpenseController expenseController;

    public MainController(BaseDatos bd, ConfigTables configTables) 
    {
        this.paintTables = new PaintTables();    
        this.configTables = configTables;
        this.bd = bd;
        
        this.fuelController = new FuelController(bd);
        this.vehicleController = new VehicleController(bd);
        this.expenseController = new ExpenseController(bd);
    }
    
    public void indexFuel()
    {
        ArrayList<Fuel> fuels = this.fuelController.index();
        this.paintTables.paintTableFuels(fuels, this.configTables.getFuelModel());
        //System.out.println("# de combustibles " + fuels.size());
    }    
    
    public void indexVehicle()
    {
        ArrayList<Vehicle> vehicles = this.vehicleController.index();
        this.paintTables.paintTableVehicles(vehicles, this.configTables.getVehicleModel());
    }
    
    public void indexExpense()
    {
        ArrayList<Expense> expenses = this.expenseController.index();
        if(expenses != null)
            this.paintTables.paintTableExpenses(expenses, this.configTables.getExpenseModel());
    }
    
    public void indexExpenseWithDate(String initial, String finish, JLabel txtTotal)
    {        
        ArrayList<Expense> expenses = this.expenseController.indexWithDate(initial, finish, txtTotal);
//System.out.println("#Expesnses => " + expenses.size());
        if(expenses != null)
            this.paintTables.paintTableExpenses(expenses, this.configTables.getExpenseModel());
    }
    
    public void indexExpensesOfVehicles(int id, JLabel txtTotal, 
                    DefaultTableModel model,String initialDate, String finishDate)
    {
        ArrayList<Expense> expenses = this.vehicleController.getAllExpensesOfVehicle
                                    (id, initialDate, finishDate, txtTotal);
        this.paintTables.paintTableExpensesVehicle(expenses, model);        
       
    }

    public FuelController getFuelController() {
        return fuelController;
    }

    public VehicleController getVehicleController() {
        return vehicleController;
    }

    public ExpenseController getExpenseController() {
        return expenseController;
    }

    public BaseDatos getBd() {
        return bd;
    }
    
    
    
    
}
