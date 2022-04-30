/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;
import service.ReservationService;

/**
 * FXML Controller class
 *
 * @author firas
 */
public class ChartResController implements Initializable {

    @FXML
    private Button evicon;
    @FXML
    private Button resicon;
    @FXML
    private Circle shpActive;
    @FXML
    private Label lblActive;
    @FXML
    private Label lblOffline;
    @FXML
    private Label lblExp;
    @FXML
    private Label lblExpired;
    @FXML
    private Label lbl7;
    @FXML
    private Label lbl30;
    @FXML
    private Label lbl90;
    @FXML
    private Label lblClose;
   
     int[] nb;
    private NumberAxis numberAxis;
    private CategoryAxis xAxis;
     private ObservableList<String> catNames = FXCollections.observableArrayList();
    private BarChart<String,Integer> barchart;
    @FXML
    private PieChart piechart;
     private Statement st;
    private ResultSet rs;
    private Connection cnx;
    /**
     * Initializes the controller class.
     */
     ObservableList<PieChart.Data>data=FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        ReservationService res = new ReservationService();
           Map<String,Integer> mp=res.NbResByEv();
        String[] nom = new String[mp.size()];
         nb = new int [mp.size()];
        int i=0 ;
        for(Map.Entry<String,Integer>elt :mp.entrySet()){
            nom[i]=elt.getKey();
            nb[i]=elt.getValue(); 
            i++;}
        
        // Convert it to a list and add it to our ObservableList of months.
      catNames.addAll(Arrays.asList(nom));
        
        // Assign the month names as categories for the horizontal axis.
     
        // TODO
    }    
    
    private void stat()
    {
          
           
    
           
        try {
            String query = "SELECT COUNT(*),nbr_personnes FROM reservation GROUP BY id" ;
            
            PreparedStatement PreparedStatement = cnx.prepareStatement(query);
            rs = PreparedStatement.executeQuery();
            
            
            while (rs.next()){
                data.add(new PieChart.Data(rs.getString("nbr_personnes"),rs.getInt("COUNT(*)")));
            }
            
            
            piechart.setTitle("Statistiques des nombre de personnes qui ont réservé");
            piechart.setLegendSide(Side.LEFT);
            piechart.setData(data);
        } catch (SQLException ex) {
            Logger.getLogger(ChartResController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }

    @FXML
    private void goToEv(ActionEvent event) {
    }

    @FXML
    private void goToRes(ActionEvent event) {
    }
    
}
