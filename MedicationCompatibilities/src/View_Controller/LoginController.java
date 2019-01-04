
package View_Controller;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.time.Instant;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ben garrison
 */
public class LoginController implements Initializable {

    @FXML
    private TextField userNameField;
    @FXML
    private PasswordField passwordField;
    
    @FXML
    private Button newMedButton;
    @FXML
    private Button modifyMedButton;
    @FXML
    private Button deleteMedButton;
    @FXML
    private Button returnButton;
    
       
    @FXML 
    private void NewMedButtonClicked(ActionEvent event) throws IOException                     
    {            
    
        boolean login = String.valueOf(userNameField.getText()).equals("test") && 
                String.valueOf(passwordField.getText()).equals("test");
        
        boolean empty = String.valueOf(userNameField.getText()).equals("") || 
                String.valueOf(passwordField.getText()).equals("");
        
        if(login){                            
                                     
            Parent AddMedicationParent = FXMLLoader.load(getClass().getResource("AddMedication.fxml"));
            Scene AddMedicationScene = new Scene(AddMedicationParent);
            Stage AddMedicationStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            AddMedicationStage.setScene(AddMedicationScene);
            AddMedicationStage.show();     	
        }
        
        else if(empty)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Warning");
            alert.setHeaderText("Please enter a user name and password.");       
            alert.showAndWait();
        }                    
        else
        {                          
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Warning");
            alert.setHeaderText("User name or password is incorrect.");       
            alert.showAndWait();                          
        }       
    }
    
    @FXML 
    private void ModifyMedButtonClicked(ActionEvent event) throws IOException                     
    {            
    
        boolean login = String.valueOf(userNameField.getText()).equals("test") && 
                String.valueOf(passwordField.getText()).equals("test");
        
        boolean empty = String.valueOf(userNameField.getText()).equals("") || 
                String.valueOf(passwordField.getText()).equals("");
        
        if(login){                            
                                     
            Parent ModifyMedicationParent = FXMLLoader.load(getClass().getResource("ModifyMedication.fxml"));
            Scene ModifyMedicationScene = new Scene(ModifyMedicationParent);
            Stage ModifyMedicationStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            ModifyMedicationStage.setScene(ModifyMedicationScene);
            ModifyMedicationStage.show();     	
        }
        
        else if(empty)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Warning");
            alert.setHeaderText("Please enter a user name and password.");       
            alert.showAndWait();
        }                    
        else
        {                          
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Warning");
            alert.setHeaderText("User name or password is incorrect.");       
            alert.showAndWait();                          
        }       
    }
    
   @FXML 
    private void DeleteMedButtonClicked(ActionEvent event) throws IOException                     
    {            
    
        boolean login = String.valueOf(userNameField.getText()).equals("test") && 
                String.valueOf(passwordField.getText()).equals("test");
        
        boolean empty = String.valueOf(userNameField.getText()).equals("") || 
                String.valueOf(passwordField.getText()).equals("");
        
        if(login){                            
                                     
            Parent DeleteMedicationParent = FXMLLoader.load(getClass().getResource("DeleteMedication.fxml"));
            Scene DeleteMedicationScene = new Scene(DeleteMedicationParent);
            Stage DeleteMedicationStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            DeleteMedicationStage.setScene(DeleteMedicationScene);
            DeleteMedicationStage.show();     	
        }
        
        else if(empty)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Warning");
            alert.setHeaderText("Please enter a user name and password.");       
            alert.showAndWait();
        }                    
        else
        {                          
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Warning");
            alert.setHeaderText("User name or password is incorrect.");       
            alert.showAndWait();                          
        }       
    }
    
    @FXML 
    private void ReturnButtonClicked(ActionEvent event) throws IOException                     
    {                                              
                                
            Parent HomeScreenParent = FXMLLoader.load(getClass().getResource("HomeScreen.fxml"));
            Scene HomeScreenScene = new Scene(HomeScreenParent);
            Stage HomeScreenStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            HomeScreenStage.setScene(HomeScreenScene);
            HomeScreenStage.show();     	
     
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
