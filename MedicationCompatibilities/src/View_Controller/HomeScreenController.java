
package View_Controller;

import Model.IntravenousMedication;
import db_utility.db_connector;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
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
public class HomeScreenController implements Initializable {

    @FXML
    private TextField searchTextField;
    
    @FXML  
    private TableView<IntravenousMedication> medTableView;
    @FXML
    private TableColumn<IntravenousMedication, String> medColumn;
    @FXML
    private TableColumn<IntravenousMedication, String> d5Column;
    @FXML
    private TableColumn<IntravenousMedication, String> nsColumn;
    @FXML
    private TableColumn<IntravenousMedication, String> halfNsColumn;
    @FXML
    private TableColumn<IntravenousMedication, String> lrColumn;
    @FXML
    private TableColumn<IntravenousMedication, String> d10Column;
    @FXML
    private TableColumn<IntravenousMedication, String> h2oColumn;
    
    @FXML
    private Button searchButton;
    @FXML
    private Button adminButton;
    @FXML
    private Button reportsButton;
    
    ObservableList<IntravenousMedication> compatibilityList = FXCollections.observableArrayList();
    
    
    String tMedication;
    String tD5W;
    String tNormalSaline;
    String tHalfNormalSaline;
    String tLactatedRingers;
    String tD10W;
    String tSterileWater;
    
    
    @FXML
    private void adminButtonClicked(ActionEvent event) throws Exception {       
        
        Parent LoginParent = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene LoginScene = new Scene(LoginParent);
        Stage LoginStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        LoginStage.setScene(LoginScene);
        LoginStage.show();
        
    }
    
    @FXML
    private void reportsButtonClicked(ActionEvent event) throws Exception {
        
        Parent ReportsParent = FXMLLoader.load(getClass().getResource("Reports.fxml"));
        Scene ReportsScene = new Scene(ReportsParent);
        Stage ReportsStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        ReportsStage.setScene(ReportsScene);
        ReportsStage.show();
        
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        populateMedications();
        
        medColumn.setCellValueFactory(new PropertyValueFactory<>("medication"));
        d5Column.setCellValueFactory(new PropertyValueFactory<>("d5W"));
        nsColumn.setCellValueFactory(new PropertyValueFactory<>("normalSaline"));
        halfNsColumn.setCellValueFactory(new PropertyValueFactory<>("halfNormalSaline"));
        lrColumn.setCellValueFactory(new PropertyValueFactory<>("lactatedRingers"));
        d10Column.setCellValueFactory(new PropertyValueFactory<>("d10W"));
        h2oColumn.setCellValueFactory(new PropertyValueFactory<>("sterileWater"));
        medTableView.setItems(compatibilityList);
        
        FilteredList<IntravenousMedication> filteredCompatibilityList = new FilteredList<>(compatibilityList, p -> true);
        
        searchTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredCompatibilityList.setPredicate(Medication -> {
  
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                
                String upperCaseFilter = newValue.toUpperCase();
                
                if (Medication.getMedication().toUpperCase().contains(upperCaseFilter)) {
                    return true;
                } else if (Medication.getMedication().toUpperCase().contains(upperCaseFilter)) {
                    return true; 
                }
                return false;
            });
        });
        
        SortedList<IntravenousMedication> sortedCompatibilityList = new SortedList<>(filteredCompatibilityList);

        sortedCompatibilityList.comparatorProperty().bind(medTableView.comparatorProperty());
        
        medTableView.setItems(sortedCompatibilityList);
        
    }

    public void populateMedications() {
        medTableView.getItems().clear();
        
        try (
                PreparedStatement statement = db_connector.getConn().prepareStatement("select compatibilities.medication, \n"
                        + "compatibilities.D5W, compatibilities.NormalSaline, compatibilities.HalfNormalSaline, \n"
                        + "compatibilities.LactatedRingers, compatibilities.SterileWater, compatibilities.D10W from compatibilities \n"
                        +   "order by medication");
                ResultSet rs = statement.executeQuery();) {

            while (rs.next()) {

                tMedication = rs.getString("compatibilities.medication");
                tD5W = rs.getString("compatibilities.D5W");
                tNormalSaline = rs.getString("compatibilities.NormalSaline");
                tHalfNormalSaline = rs.getString("compatibilities.HalfNormalSaline");
                tLactatedRingers = rs.getString("compatibilities.LactatedRingers");
                tD10W = rs.getString("compatibilities.D10W");
                tSterileWater = rs.getString("compatibilities.SterileWater");
                                               
                compatibilityList.add(new IntravenousMedication(tMedication, tD5W, tNormalSaline, tHalfNormalSaline, 
                        tLactatedRingers, tD10W, tSterileWater));
            }

        } catch (SQLException sqe) {
            System.out.println("Check your SQL" + sqe.getMessage());
        } catch (Exception e) {
            System.out.println("Something besides SQL went wrong.");
        }
    }
    
}
