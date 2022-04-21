/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author firas
 */
public class MyDB {
    
    final String url = "jdbc:mysql://localhost:3306/xtreme_gym";
    final String username = "root";
    final String password = "";
    private Connection connection ;
   private  static MyDB instance;
   // limiter la connection db en utilisant Singleton
   //étape 1 : rendre instance private
   //étape 2  : static
   //étape 3 : établir une méthode getInstance()
          
    
    private MyDB(){
        try {
            connection = DriverManager.getConnection(url, username , password);
            System.out.println("Connexion établie");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    
}
    
    public  static MyDB getInstance(){
        if (instance == null)
            instance = new MyDB();
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
    
}
