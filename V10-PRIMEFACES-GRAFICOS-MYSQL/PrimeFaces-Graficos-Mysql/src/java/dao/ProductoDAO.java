/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Producto;


public class ProductoDAO {

    public List<Producto> listar() throws SQLException {
        List<Producto> lista = null;
        ResultSet rs;
        Connection cn = null;
            System.out.println("00-ProductoDAO-listar()");

        try {
             Class.forName("com.mysql.jdbc.Driver");
             cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mitocode?user=root&password=admin");
             
             PreparedStatement  st = cn.prepareStatement("SELECT codigo, nombre, precio FROM Producto");
             rs = st.executeQuery();
             lista = new ArrayList();
             while(rs.next()){
                Producto prod = new Producto();
                prod.setCodigo(rs.getInt("codigo"));
                prod.setNombre(rs.getString("nombre"));
                prod.setPrecio(rs.getDouble("precio"));
            System.out.println("03-ProductoDAO-listar()");
                lista.add(prod);
            System.out.println("04-ProductoDAO-listar()");
            }
        } catch (Exception e) {
            System.out.println("05-ProductoDAO-listar()");
           // throw e;
        } finally {
            if (cn != null){
            cn.close();
            }
        }
        
        return lista;
    }
    
}