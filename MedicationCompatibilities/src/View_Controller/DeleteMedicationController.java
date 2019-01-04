
package View_Controller;

import Model.IntravenousMedication;
import db_utility.db_connector;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ben garrison
 */
public class DeleteMedicationController implements Initializable {
    
    @FXML
    private TableView <IntravenousMedication> medTableView;
    @FXML
    private TableColumn<IntravenousMedication, String> medListColumn;
    
    @FXML
    private TextField medTextField;
    
    @FXML
    private Button deleteMedButton;
    @FXML
    private Button returnButton;
    
    ObservableList<IntravenousMedication> compatibilityList = FXCollections.observableArrayList();
    
    String tMedication;

    @FXML
    private void returnButtonClicked(ActionEvent event) throws IOException{                     
        Parent HomeScreenParent = FXMLLoader.load(getClass().getResource("HomeScreen.fxml"));
        Scene HomeScreenScene = new Scene(HomeScreenParent);
        Stage HomeScreenStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        HomeScreenStage.setScene(HomeScreenScene);
        HomeScreenStage.show(); 
    }
    
    @FXML
    private void deleteClicked(ActionEvent event) throws Exception {
    
    try{    
        TablePosition pos = medTableView.getSelectionModel().getSelectedCells().get(0);
        int row = pos.getRow();
        IntravenousMedication item = medTableView.getItems().get(row);
        TableColumn col = pos.getTableColumn();        

        String data = (String) col.getCellObservableValue(item).getValue();        

        if (col == medListColumn) {
            String deleteMed = data;
            
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Are you sure you want delete this medication?");
            alert.showAndWait()
            .filter(okay -> okay == ButtonType.OK)  
            .ifPresent((ButtonType okay) -> {       
                try {
                    PreparedStatement ps = db_connector.getConn().prepareStatement("DELETE FROM "
                        + "compatibilities WHERE medication = ?");

                    ps.setString(1, deleteMed);                
                    ps.execute();

                }catch (SQLException ex) {
                System.out.println("Check your SQL" + ex.getMessage());}                     
                });                                     
        } 
        
        }catch (Exception e){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setContentText("Please click on a medication \n"
        + "and then click the Delete button.");
        alert.showAndWait();}
        
    populateMedications();
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        populateMedications();
        
        medListColumn.setCellValueFactory(new PropertyValueFactory<>("medication"));
        medTableView.setItems(compatibilityList);
                
    } 
    
    public void populateMedications() {
        medTableView.getItems().clear();
        
        try (
                PreparedStatement statement = db_connector.getConn().prepareStatement("select compatibilities.medication \n"
                        +  "from compatibilities order by medication");
                ResultSet rs = statement.executeQuery();) {

            while (rs.next()) {

                tMedication = rs.getString("compatibilities.medication");
                
                                               
                compatibilityList.add(new IntravenousMedication(tMedication));
            }

        } catch (SQLException sqe) {
            System.out.println("Check your SQL" + sqe.getMessage());
        } catch (Exception e) {
            System.out.println("Something besides SQL went wrong.");
        }
    }
    
}
