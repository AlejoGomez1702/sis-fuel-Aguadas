package bd.cruds;

import bd.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import logic.Fuel;
import logic.Vehicle;

/**
 *
 * @author Alejo
 */
public class CrudVehicle 
{
    /**
     * Conexión con la base de datos.
     */
    private Conexion conexion;
    
    private CrudFuel crudFuel;

    public CrudVehicle(Conexion conexion, CrudFuel crudFuel) 
    {
        this.conexion = conexion;
        this.crudFuel = crudFuel;
    }
    
    public boolean addVehicle(String description, int fuelId)
    {
        boolean bandera = true;              
        
        String consulta = "INSERT INTO vehicle (description, fuel_id)"
                + " VALUES (?, ?)";
        try 
        {
            PreparedStatement ps = this.conexion.getConexion().prepareStatement(consulta);
            ps.setString(1, description);
            ps.setDouble(2, fuelId);
            ps.executeUpdate();            
        } catch (SQLException e) 
        {
            bandera = false;
System.out.println("PASOOO A LA EXCEPCIÓN");
        }                                
        
        return bandera;          
    }
    
    public Vehicle getVehicle(int id)
    {
        if(id < 1)
        {
//System.out.println("Entra en menor que 1");            
            return null;            
        }
        
        String consulta = "SELECT * FROM vehicle WHERE id = ?";
        Vehicle vehicle = null;
        Fuel fuel = null;
        String description;
        int fuelId;
        
        try 
        {
//System.out.println("LA CONSULTA SI ENTRAAA");
            PreparedStatement ps = this.conexion.getConexion().prepareStatement(consulta);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();    
            while(rs.next())
            {
//System.out.println("SIIII ENCONTROOO");
                description = rs.getString("description");
                fuelId = rs.getInt("fuel_id");
                fuel = this.crudFuel.getFuel(fuelId);
                vehicle = new Vehicle(id, description, fuelId, fuel);
                break;
            }            
            
        } catch (SQLException e) 
        {
System.out.println("Entra la excepción");  
            return null;
        }
        
        return vehicle;
    }
    
    public boolean updateVehicle(Vehicle oldVehicle, String desc, int fuel)
    {
        if(oldVehicle == null || desc.equals("") || fuel < 1)
            return false;
        
        boolean bandera = true;
        //int id = fuel.getId();
        //String name = fuel.getName();
        //double value = fuel.getValue();
        String consulta = "UPDATE vehicle SET description = ?, fuel_id = ? WHERE id = ?";
        try 
        {
            PreparedStatement ps = this.conexion.getConexion().prepareStatement(consulta);
            ps.setString(1, desc);
            ps.setInt(2, fuel);
            ps.setInt(3, oldVehicle.getId());
            ps.executeUpdate(); 
        } catch (SQLException e) 
        {
System.out.println("ENTRA EN LA EXCEPCIOOONN: " + e.getMessage());
            return false;
        }
        return bandera;
    }   
        
    public ArrayList<Vehicle> getAllVehicles()
    {
        ArrayList<Vehicle> vehicles = new ArrayList<>();
        
        String consulta = "SELECT * FROM vehicle";
        Vehicle vehicle = null;
        int id;
        String description;
        int fuelId;
        Fuel fuel;
        
        try 
        {
            ResultSet rs = this.conexion.getStatement().executeQuery(consulta);
            while(rs.next())
            {
                id = rs.getInt("id");
                description = rs.getString("description");
                fuelId = rs.getInt("fuel_id");
                fuel = this.crudFuel.getFuel(fuelId);
                
                vehicle = new Vehicle(id, description, fuelId, fuel);
                vehicles.add(vehicle);                
            }            
        } catch (SQLException e) 
        {
            return null;
        }
        
        return vehicles;
    }
    
    public boolean deleteVehicle(int id)
    {
        if(id < 1)
            return false;
        
        boolean bandera = true;
        String consulta = "DELETE FROM vehicle WHERE id = ?";
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
