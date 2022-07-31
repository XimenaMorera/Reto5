
package Controlador;

import Modelo.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class CbSucursal {
    
    //resulset devuelve la lista
    //el select necesita stamente
    
    Connection connection;
    Conexion conexion = new Conexion();
    Statement st;
    ResultSet rs;

    public CbSucursal() {
        
    }

    public ArrayList getListaSucursales() {
       System.out.println("Ingrese a la funccion getListaSucursales");
       ArrayList mListaSucursales = new ArrayList();
       Sucursal sucursal = null;
        try {
            connection = conexion.getConnection();
            st = connection.createStatement();
            rs = st.executeQuery("Select idSucursal, nombreSucursal FROM 'sucursal';");
            while (rs.next()){
            sucursal = new Sucursal();
            sucursal.setIdSucursal(rs.getInt("id sucursal"));
            sucursal.setNombreSucursal(rs.getString("nombreSucursal"));
            mListaSucursales.add(sucursal);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return mListaSucursales;
    }

   
}
