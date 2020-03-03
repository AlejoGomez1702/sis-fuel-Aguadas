package bd.cruds;

import bd.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import logic.Fuel;

/**
 *
 * @author Alejo
 */
public class CrudFuel 
{
    /**
     * Conexión con la base de datos.
     */
    private Conexion conexion;

    public CrudFuel(Conexion conexion) 
    {
        this.conexion = conexion;
    }
    
    public boolean addFuel(String name, double value)
    {
        boolean bandera = true;              
        
        String consulta = "INSERT INTO fuel (name, value)"
                + " VALUES (?, ?)";
        try 
        {
            PreparedStatement ps = this.conexion.getConexion().prepareStatement(consulta);
            ps.setString(1, name);
            ps.setDouble(2, value);
            ps.executeUpdate();            
        } catch (SQLException e) 
        {
            bandera = false;
System.out.println("PASOOO A LA EXCEPCIÓN");
        }                                
        
        return bandera;          
    }
    
    public boolean updateFuel(Fuel fuel)
    {
        if(fuel == null)
            return false;
        
        boolean bandera = true;
        int id = fuel.getId();
        String name = fuel.getName();
        double value = fuel.getValue();
        String consulta = "UPDATE fuel SET name = ?, value = ? WHERE id = ? ";
        try 
        {
            PreparedStatement ps = this.conexion.getConexion().prepareStatement(consulta);
            ps.setString(1, name);
            ps.setDouble(2, value);
            ps.setInt(3, id);
            ps.executeUpdate(); 
        } catch (SQLException e) 
        {
System.out.println("ENTRA EN LA EXCEPCIOOONN: " + e.getMessage());
            return false;
        }
        return bandera;
    }
    
    public Fuel getFuel(int id)
    {
        if(id < 1)
        {
            return null;
        }
        
        String consulta = "SELECT * FROM fuel WHERE id = ?";
        Fuel fuel = null;
        String name;
        double value;
        
        try 
        {
            PreparedStatement ps = this.conexion.getConexion().prepareStatement(consulta);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();    
            while(rs.next())
            {
                name = rs.getString("name");
                value = rs.getDouble("value");
                fuel = new Fuel(id, name, value);
                break;
            }            
            
        } catch (SQLException e) 
        {
            return null;
        }
        
        return fuel;
    }
    
    public ArrayList<Fuel> getAllFuels()
    {
        ArrayList<Fuel> fuels = new ArrayList<>();
        
        String consulta = "SELECT * FROM fuel";
        Fuel fuel = null;
        int id;
        String name;
        double value;
        
        try 
        {
            ResultSet rs = this.conexion.getStatement().executeQuery(consulta);
            while(rs.next())
            {
                id = rs.getInt("id");
                name = rs.getString("name");
                value = rs.getDouble("value");
                fuel = new Fuel(id, name, value);
                fuels.add(fuel);
            }            
        } catch (SQLException e) 
        {
            return null;
        }
        
        return fuels;
    }
    
    public boolean deleteFuel(int id)
    {
        if(id < 1)
            return false;
        
        boolean bandera = true;
        String consulta = "DELETE FROM fuel WHERE id = ?";
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
