package middlewares;

import java.awt.HeadlessException;
import java.util.Calendar;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;

/**
 *
 * @author Alejo
 */
public class ExpenseMiddleware 
{
    public boolean initialCheck(String vehicle, Calendar date, String invoiceCode, JTextField value)
    {
        if(vehicle.equals("") || date == null || invoiceCode.equals("") || value == null)
            return false;
        
        try {
            Double.parseDouble(value.getText());
        } catch (NumberFormatException e) {
            return false;
        }
               
        return true;
    }
    
    public double checkCount(JTextField spinner)
    {
        double count = -1;
        
        try 
        {
            count = Double.parseDouble(spinner.getText());
        }
        catch(HeadlessException | NumberFormatException e) 
        {
            JOptionPane.showMessageDialog(null,"Por Favor Digite Un Valor Númerico(#Galones)");
        }
        
        return count;
    }
    
    public int getVehicleCode(String info)
    {
        String[] data = info.split("#");
        int id = -1;
        
        try {
            id = Integer.parseInt(data[0]);
        } catch (Exception e) 
        {
            return -1;
        }
        
        return id;             
    }
        
}
