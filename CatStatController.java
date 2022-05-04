/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi3;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class CatStatController implements Initializable {

    @FXML
    private PieChart pie;
    @FXML
    private Label nh_lab;
    @FXML
    private Label nf_lab;
    @FXML
    private Label ne_lab;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        int nho=GestionCategorieController.nh , nfe=GestionCategorieController.nf, nen=GestionCategorieController.ne ; 
        pie.setTitle("Stats");
        ObservableList <PieChart.Data> ol = FXCollections.observableArrayList(
        
        new PieChart.Data("Homme", nho),new PieChart.Data("Femme", nfe),new PieChart.Data("Enfant", nen)
                
                
           );
        nh_lab.setText(""+nho); nf_lab.setText(""+nfe); ne_lab.setText(""+nen);
        pie.setData(ol);
        // TODO
        // TODO
        // TODO
    }    

    @FXML
    private void back(ActionEvent event) 
             throws IOException {
        FXMLLoader loader = new FXMLLoader();
        
        loader.setLocation(getClass().getResource("GestionCategorie.fxml"));
        
        
        
        Parent root = loader.load();
        nh_lab.getScene().setRoot(root);
        
        
    }
        
    
    
}
