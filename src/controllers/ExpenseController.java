package controllers;

import bd.BaseDatos;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import logic.Expense;
import middlewares.ExpenseMiddleware;
import presentation.logic.CalendarPresentation;
import presentation.logic.ConvertMoney;

/**
 *
 * @author Alejo
 */
public class ExpenseController 
{
    private BaseDatos bd;
    private ExpenseMiddleware middleware;
    private CalendarPresentation presentation;
    private ConvertMoney money;

    public ExpenseController(BaseDatos bd) 
    {
        this.bd = bd;
        this.middleware = new ExpenseMiddleware();
        this.presentation = new CalendarPresentation();
        this.money = new ConvertMoney();
    }
    
    public ArrayList<Expense> index()
    {
        ArrayList<Expense> expenses;
        expenses = bd.getCrudExpense().getAllExpenses();
        
        return expenses;
    }
    
    // Buscar por fechas.
    public ArrayList<Expense> indexWithDate(String initial, String finish, JLabel txtTotal)
    {
        ArrayList<Expense> expenses;
        expenses = bd.getCrudExpense().getAllExpensesFromDates(initial, finish);
        double total = bd.getCrudExpense().getTotal(initial, finish);
        String varcharTotal = this.money.run(total);
        txtTotal.setText(varcharTotal);
        
        return expenses;
    }
    
    public boolean create(String vehicle, Calendar date, String ivoiceCode, JTextField value)
    {
        int vehicleId;
        String dateVarchar;
        double valueDouble;
        
        boolean check = this.middleware.initialCheck(vehicle, date, ivoiceCode, value);
        double checkCount = this.middleware.checkCount(value);
//System.out.println("1. HASTA ACAAAA SIIII PAAASSAAAA");
        if(check && checkCount > 0)
        {
            vehicleId = this.middleware.getVehicleCode(vehicle);
            dateVarchar = this.presentation.run(date);
//System.out.println("2. HASTA ACAAAA SIIII PAAASSAAAA");
            return this.bd.getCrudExpense().addExpense(vehicleId, dateVarchar, 
                                                        ivoiceCode, checkCount);            
        }
                
        return false;
    }
    
    public boolean update(Expense oldExpense, String vehicle, Calendar date, 
                                        String invoiceCode, JTextField countGal)
    {
        boolean check = this.middleware.initialCheck(vehicle, date, invoiceCode, countGal);
        double checkCount = this.middleware.checkCount(countGal); //Cantidad de galones.
        if(check && checkCount > 0)
        {
            int newVehicleId = this.middleware.getVehicleCode(vehicle);
            String dateVarchar = this.presentation.run(date);
            
            return this.bd.getCrudExpense().updateExpense(oldExpense, 
                            newVehicleId, dateVarchar, invoiceCode, checkCount);
        }        
        
        return false;
    }
    
    public boolean delete(int id)
    {
        return this.bd.getCrudExpense().deleteExpense(id);
    }
    
    
}
