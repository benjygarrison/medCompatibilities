
package View_Controller;

import db_utility.db_connector;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ben garrison
 */
public class AddMedicationController implements Initializable {

    @FXML
    private TextField medTextField;
    
    @FXML
    private ComboBox<String> d5ComboBox;
    @FXML
    private ComboBox<String> nsComboBox;
    @FXML
    private ComboBox<String> halfNsComboBox;
    @FXML
    private ComboBox<String> lrComboBox;
    @FXML
    private ComboBox<String> d10ComboBox;
    @FXML
    private ComboBox<String> h2oComboBox;
    
    @FXML
    private Button addMedButton;
    @FXML
    private Button returnButton;
    
    
    @FXML
    private void addMedication(ActionEvent event){
        
        String getMed = medTextField.getText().toUpperCase();
        String getD5W = d5ComboBox.getValue();
        String getNS = nsComboBox.getValue();
        String getHalfNS = halfNsComboBox.getValue();
        String getLR = lrComboBox.getValue();
        String getD10W = d10ComboBox.getValue();
        String getSterileWater = h2oComboBox.getValue();
        
        try{
              PreparedStatement ps = db_connector.getConn().prepareStatement("INSERT INTO "
                + "compatibilities (medication, D5W, NormalSaline, HalfNormalSaline, LactatedRingers, SterileWater, D10W) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)");

                        ps.setString(1, getMed);
                        ps.setString(2, getD5W);
                        ps.setString(3, getNS);
                        ps.setString(4, getHalfNS);
                        ps.setString(5, getLR);
                        ps.setString(6, getD10W);
                        ps.setString(7, getSterileWater);
                        
                if(getMed.length() == 0 || getMed == null){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setContentText("Please enter a medication.");
                    alert.showAndWait();
                }        
                        
                ps.execute(); 
                               
            }catch (SQLException ex){System.out.println("Check your SQL" + ex.getMessage());}  
    }
    
    @FXML
    private void returnButtonClicked(ActionEvent event) throws IOException {                     
        Parent HomeScreenParent = FXMLLoader.load(getClass().getResource("HomeScreen.fxml"));
        Scene HomeScreenScene = new Scene(HomeScreenParent);
        Stage HomeScreenStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        HomeScreenStage.setScene(HomeScreenScene);
        HomeScreenStage.show(); 
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        d5ComboBox.getItems().addAll("YES", "NO");
        nsComboBox.getItems().addAll("YES", "NO");
        halfNsComboBox.getItems().addAll("YES", "NO");
        lrComboBox.getItems().addAll("YES", "NO");
        d10ComboBox.getItems().addAll("YES", "NO");
        h2oComboBox.getItems().addAll("YES", "NO");              
        d5ComboBox.getSelectionModel().selectFirst();
        nsComboBox.getSelectionModel().selectFirst();
        halfNsComboBox.getSelectionModel().selectFirst();
        lrComboBox.getSelectionModel().selectFirst();
        d10ComboBox.getSelectionModel().selectFirst();
        h2oComboBox.getSelectionModel().selectFirst();
    }    
    
}
