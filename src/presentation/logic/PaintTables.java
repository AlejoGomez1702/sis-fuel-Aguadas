package presentation.logic;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import logic.Expense;
import logic.Fuel;
import logic.Vehicle;

/**
 *
 * @author Alejo
 */
public class PaintTables 
{           
    public void paintTableFuels(ArrayList<Fuel> fuels, DefaultTableModel tableModel)
    {
        this.clearDataFromTable(tableModel);
        int numF = fuels.size();
        Fuel fuel;
        for(int i = 0; i < numF; i++)
        {
            fuel = fuels.get(i);
             String [] fila ={fuel.getId()+"", fuel.getName()+"",fuel.getValue()+""};
                                
             tableModel.addRow(fila);
        }
    }
    
    public void paintTableVehicles(ArrayList<Vehicle> vehicles, DefaultTableModel tableModel)
    {
        this.clearDataFromTable(tableModel);
        int numV = vehicles.size();
        Vehicle vehicle;
        for(int i = 0; i < numV; i++)
        {
            vehicle = vehicles.get(i);
            String [] fila ={vehicle.getId()+"", vehicle.getDescription()+"",
                vehicle.getFuel().getName()+""};
                                
            tableModel.addRow(fila);
        }
    }
    
    public void paintTableExpenses(ArrayList<Expense> expenses, DefaultTableModel tableModel)
    {
        this.clearDataFromTable(tableModel);
        int numE = expenses.size();
        Expense expense;
        for(int i = 0; i < numE; i++)
        {
            expense = expenses.get(i);
            String [] fila ={expense.getId()+"", expense.getVehicle().getDescription()+"",
                expense.getDate()+"", expense.getInvoiceCode()+"", expense.getValue() + ""};
                                
            tableModel.addRow(fila);
        }
    }
    
    public void paintTableExpensesVehicle(ArrayList<Expense> expenses, DefaultTableModel tableModel)
    {
        this.clearDataFromTable(tableModel);
        int numE = expenses.size();
        Expense expense;
        for(int i = 0; i < numE; i++)
        {
            expense = expenses.get(i);
            String [] fila ={expense.getId()+"", expense.getDate()+"", 
                    expense.getInvoiceCode()+"", expense.getValue() + ""};
                                
            tableModel.addRow(fila);
        }
    }
    
    
    /**
     * Hace el barrido de la información contenida en una tabla.
     * @param modeloTabla Modelo de la tabla que se desea barrer.
     */
    public void clearDataFromTable(DefaultTableModel modeloTabla)
    {        
        int a =modeloTabla.getRowCount()-1;
        for(int i=a; i>=0; i--)
        {
            modeloTabla.removeRow(i);
        }            
    }    
}
