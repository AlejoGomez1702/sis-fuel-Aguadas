package bd.cruds;

import bd.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import logic.Expense;
import logic.Fuel;
import logic.Vehicle;

/**
 *
 * @author Alejo
 */
public class CrudExpense 
{
    /**
     * Conexión con la base de datos.
     */
    private Conexion conexion;
    
    private CrudVehicle crudVehicle;

    public CrudExpense(Conexion conexion, CrudVehicle crudVehicle) 
    {
        this.conexion = conexion;
        this.crudVehicle = crudVehicle;
    }
    
    public boolean addExpense(int vehicleId, String date, String invoiceCode, double count)
    {
        boolean bandera = true;              
        
        Vehicle vehicle = this.crudVehicle.getVehicle(vehicleId);
        Fuel fuel = vehicle.getFuel();
        double value = count * fuel.getValue();
        
        String consulta = "INSERT INTO expense (vehicle_id, date, invoice_code, value)"
                + " VALUES (?, ?, ?, ?)";
        try 
        {
            PreparedStatement ps = this.conexion.getConexion().prepareStatement(consulta);
            ps.setInt(1, vehicleId);
            ps.setString(2, date);
            ps.setString(3, invoiceCode);
            ps.setDouble(4, value);
            
            ps.executeUpdate();            
        } catch (SQLException e) 
        {
            bandera = false;
System.out.println("PASOOO A LA EXCEPCIÓN");
        }                                
        
        return bandera;          
    }
    
    public ArrayList<Expense> getAllExpensesFromVehicle
                                (int idVehicle, String initial, String finish)
    {
        String consulta = "";
        ArrayList<Expense> expenses = new ArrayList<>();
        if(initial.equals("") && finish.equals(""))
        {
            consulta = "SELECT * FROM expense WHERE vehicle_id = ? ORDER BY id";
        }
        else
        {
            consulta = "SELECT * FROM expense WHERE vehicle_id = ? AND date BETWEEN ? AND ? ORDER BY id";
        }
        
        //consulta = "SELECT * FROM expense WHERE vehicle_id = ? ORDER BY id";
        
        Expense expense = null;
        int id;
        int vehicleId;
        String date;
        String invoiceCode;
        double value;
        
        Vehicle vehicle;
        
        try 
        {
            PreparedStatement ps = this.conexion.getConexion().prepareStatement(consulta);
            ps.setInt(1, idVehicle); 
            if(!initial.equals("") && !finish.equals(""))
            {
                ps.setString(2, initial); 
                ps.setString(3, finish);
            }     
                    
            ResultSet rs = ps.executeQuery();
            
            while(rs.next())
            {
                id = rs.getInt("id");
                vehicleId = rs.getInt("vehicle_id");
                date = rs.getString("date");
                invoiceCode = rs.getString("invoice_code");
                value = rs.getDouble("value");
                
                vehicle = this.crudVehicle.getVehicle(vehicleId);
                
                expense = new Expense(id, vehicleId, date, invoiceCode, value, vehicle);
                expenses.add(expense);
            }
        } catch (SQLException e) 
        {
            return null;
        }
        
        return expenses;        
    }
                                
    public Expense getExpense(int id)
    {
        if(id < 1)
        {
            return null;
        }
        
        String consulta = "SELECT * FROM expense WHERE id = ?";
        Expense expense = null;
        Vehicle vehicle = null;
        int vehicleId;
        String date;
        String invoiceCode;
        double value;
        
        try 
        {
            PreparedStatement ps = this.conexion.getConexion().prepareStatement(consulta);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();    
            while(rs.next())
            {
                vehicleId = rs.getInt("vehicle_id");
                date = rs.getString("date");
                invoiceCode = rs.getString("invoice_code");
                value = rs.getDouble("value");
                vehicle = this.crudVehicle.getVehicle(vehicleId);                
                expense = new Expense(id, vehicleId, date, invoiceCode, value, vehicle);
                
                break;
            }            
            
        } catch (SQLException e) 
        {
            return null;
        }
        
        return expense;
    }
    
    public boolean updateExpense(Expense oldExpense, int idNewVehicle,
                            String newDate, String newInvoice, double countGal)
    {
        if(oldExpense == null)
            return false;
        
        boolean bandera = true;
        
        int id = oldExpense.getId();
        Vehicle v = this.crudVehicle.getVehicle(idNewVehicle);
        
        String consulta = "UPDATE expense SET vehicle_id = ?, date = ?, invoice_code = ?, value = ? WHERE id = ? ";
        try 
        {
            PreparedStatement ps = this.conexion.getConexion().prepareStatement(consulta);
            ps.setInt(1, idNewVehicle);
            ps.setString(2, newDate);
            ps.setString(3, newInvoice);
            ps.setDouble(4, v.getFuel().getValue() * countGal);
            ps.setInt(5, id);
            
            ps.executeUpdate(); 
        } catch (SQLException e) 
        {
System.out.println("ENTRA EN LA EXCEPCIOOONN: " + e.getMessage());
            return false;
        }
        return bandera;
    }                                                         
    
    public ArrayList<Expense> getAllExpensesFromDates(String initialDate, String finishDate)
    {
        ArrayList<Expense> expenses = new ArrayList<>();
        String consulta = "SELECT * FROM expense WHERE date BETWEEN ? AND ? ORDER BY id";
        
        Expense expense = null;
        int id;
        int vehicleId;
        String date;
        String invoiceCode;
        double value;
        
        Vehicle vehicle;
        
        try 
        {
            PreparedStatement ps = this.conexion.getConexion().prepareStatement(consulta);
            ps.setString(1, initialDate);
            ps.setString(2, finishDate);            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next())
            {
                id = rs.getInt("id");
                vehicleId = rs.getInt("vehicle_id");
                date = rs.getString("date");
                invoiceCode = rs.getString("invoice_code");
                value = rs.getDouble("value");
                
                vehicle = this.crudVehicle.getVehicle(vehicleId);
                
                expense = new Expense(id, vehicleId, date, invoiceCode, value, vehicle);
                expenses.add(expense);
            }
        } catch (SQLException e) 
        {
            return null;
        }
        
        return expenses;
    }
    
    public ArrayList<Expense> getAllExpensesFromDatesWithVehicle
                        (String initialDate, String finishDate, int idVehicle)
    {
        ArrayList<Expense> expenses = new ArrayList<>();
        String consulta = "SELECT * FROM expense WHERE date BETWEEN ? AND ? AND vehicle_id = ? ORDER BY id";
        
        Expense expense = null;
        int id;
        int vehicleId;
        String date;
        String invoiceCode;
        double value;
        
        Vehicle vehicle;
        
        try 
        {
            PreparedStatement ps = this.conexion.getConexion().prepareStatement(consulta);
            ps.setString(1, initialDate);
            ps.setString(2, finishDate);   
            ps.setInt(3, idVehicle);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next())
            {
                id = rs.getInt("id");
                vehicleId = rs.getInt("vehicle_id");
                date = rs.getString("date");
                invoiceCode = rs.getString("invoice_code");
                value = rs.getDouble("value");
                
                vehicle = this.crudVehicle.getVehicle(vehicleId);
                
                expense = new Expense(id, vehicleId, date, invoiceCode, value, vehicle);
                expenses.add(expense);
            }
        } catch (SQLException e) 
        {
            return null;
        }
        
        return expenses;
    }   
        
    public ArrayList<Expense> getAllExpenses()
    {
        ArrayList<Expense> expenses = new ArrayList<>();
        
        String consulta = "SELECT * FROM expense";
        Expense expense = null;
        int id;
        int vehicleId;
        String date;
        String invoiceCode;
        double value;
        
        Vehicle vehicle;
        
        try 
        {
            ResultSet rs = this.conexion.getStatement().executeQuery(consulta);
            while(rs.next())
            {
                id = rs.getInt("id");
                vehicleId = rs.getInt("vehicle_id");
                date = rs.getString("date");
                invoiceCode = rs.getString("invoice_code");
                value = rs.getDouble("value");
                
                vehicle = this.crudVehicle.getVehicle(vehicleId);
                
                expense = new Expense(id, vehicleId, date, invoiceCode, value, vehicle);
                expenses.add(expense);                
            }            
        } catch (SQLException e) 
        {
            return null;
        }
        
        return expenses;
    }
    
    public double getTotal(String initialDate, String finishDate)
    {
        double total = 0;
        String consulta = "SELECT SUM(value) AS t FROM expense WHERE date BETWEEN ? AND ?";
        try 
        {
            PreparedStatement ps = this.conexion.getConexion().prepareStatement(consulta);
            ps.setString(1, initialDate);
            ps.setString(2, finishDate);            
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                total = rs.getDouble("t");
                break;                
            }                        
        } catch (SQLException e) 
        {
System.out.println("Excepción => " + e.getMessage());
            return -1;
        }
        
        return total;
    }
    
    public double getTotalWithVehicle(String initialDate, String finishDate, int idVehicle)
    {
        String consulta = "";
        double total = 0;
        if(initialDate.equals("") && finishDate.equals(""))
            consulta = "SELECT SUM(value) AS t FROM expense WHERE vehicle_id = ?";
        else
            consulta = "SELECT SUM(value) AS t FROM expense WHERE date BETWEEN ? AND ? AND vehicle_id = ?";
        try 
        {
            PreparedStatement ps = this.conexion.getConexion().prepareStatement(consulta);
            if(initialDate.equals("") && finishDate.equals(""))
            {
                ps.setInt(1, idVehicle);
            }
            else
            {
                ps.setString(1, initialDate);
                ps.setString(2, finishDate);     
                ps.setInt(3, idVehicle);
            }            
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                total = rs.getDouble("t");
                break;                
            }                        
        } catch (SQLException e) 
        {
System.out.println("Excepción => " + e.getMessage());
            return -1;
        }
        
        return total;
    }
    
    public boolean deleteExpense(int id)
    {
        if(id < 1)
            return false;
        
        boolean bandera = true;
        String consulta = "DELETE FROM expense WHERE id = ?";
        try 
        {
            PreparedStatement ps = this.conexion.getConexion().prepareStatement(consulta);
            ps.setInt(1, id);
            ps.executeUpdate(); 
        } catch (SQLException e) 
        {
            return false;
        }
        
        return bandera;
    }
    
}
