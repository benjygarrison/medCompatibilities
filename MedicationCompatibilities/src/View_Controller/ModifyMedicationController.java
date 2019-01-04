
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
import javafx.scene.control.ComboBox;
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
public class ModifyMedicationController implements Initializable {

    @FXML
    private TableView <IntravenousMedication> medTableView;
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
    private Button modifyButton;
    @FXML
    private Button returnButton;
    
    @FXML
    private ComboBox <String>compatComboBox;        
    
    ObservableList<IntravenousMedication> compatibilityList = FXCollections.observableArrayList();
    
    String tMedication;
    String tD5W;
    String tNormalSaline;
    String tHalfNormalSaline;
    String tLactatedRingers;
    String tD10W;
    String tSterileWater;
    
    @FXML
    private void modifyClicked(ActionEvent event) throws Exception {
        
    try{    
        TablePosition pos = medTableView.getSelectionModel().getSelectedCells().get(0);
        int row = pos.getRow();
        IntravenousMedication item = medTableView.getItems().get(row);
        TableColumn col = pos.getTableColumn(); 
        TableColumn med = medColumn;
        TableColumn D5W = d5Column;
        TableColumn NS = nsColumn;
        TableColumn halfNS = halfNsColumn;
        TableColumn LR = lrColumn;
        TableColumn D10W = d10Column;
        TableColumn H2O = h2oColumn;

        String data = (String) col.getCellObservableValue(item).getValue();
        String data1 = (String) med.getCellObservableValue(item).getValue();
        String data2 = (String) D5W.getCellObservableValue(item).getValue();
        String data3 = (String) NS.getCellObservableValue(item).getValue();
        String data4 = (String) halfNS.getCellObservableValue(item).getValue();
        String data5 = (String) LR.getCellObservableValue(item).getValue();
        String data6 = (String) D10W.getCellObservableValue(item).getValue();
        String data7 = (String) H2O.getCellObservableValue(item).getValue();
        
        String updateD5W = compatComboBox.getValue();
        String updateNS = compatComboBox.getValue();
        String updateHalfNS = compatComboBox.getValue();
        String updateLR = compatComboBox.getValue();
        String updateD10W = compatComboBox.getValue();
        String updateH2O = compatComboBox.getValue();

        if (col == medColumn) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Warning");
            alert.setHeaderText("Medication names cannot be modified"
                    + "(to maintain the integrity of the medication formulary.)");       
            alert.showAndWait();                               
        }
        
        if (col == d5Column){
            try {
                PreparedStatement ps = db_connector.getConn().prepareStatement("UPDATE compatibilities "
                        + "SET D5W = ? WHERE medication = ? ");
                                                               
                ps.setString(1, updateD5W);
                ps.setString(2, data1);
                ps.execute();
                
            } catch (SQLException ex) {
                System.out.println("Check your SQL" + ex.getMessage());
            }
        }
        
        if (col == nsColumn){
            try {
                PreparedStatement ps = db_connector.getConn().prepareStatement("UPDATE compatibilities "
                        + "SET NormalSaline = ? WHERE medication = ? ");
                                                               
                ps.setString(1, updateNS);
                ps.setString(2, data1);
                ps.execute();
                
            } catch (SQLException ex) {
                System.out.println("Check your SQL" + ex.getMessage());
            }
        }
        
        if (col == halfNsColumn){
            try {
                PreparedStatement ps = db_connector.getConn().prepareStatement("UPDATE compatibilities "
                        + "SET HalfNormalSaline = ? WHERE medication = ? ");
                                                               
                ps.setString(1, updateHalfNS);
                ps.setString(2, data1);
                ps.execute();               
              
            } catch (SQLException ex) {
                System.out.println("Check your SQL" + ex.getMessage());
            }
        }
        
        if (col == lrColumn){
            try {
                PreparedStatement ps = db_connector.getConn().prepareStatement("UPDATE compatibilities "
                        + "SET LactatedRingers = ? WHERE medication = ? ");
                                                               
                ps.setString(1, updateLR);
                ps.setString(2, data1);
                ps.execute();
               
            } catch (SQLException ex) {
                System.out.println("Check your SQL" + ex.getMessage());
            }
        }
        
        if (col == d10Column){
            try {
                PreparedStatement ps = db_connector.getConn().prepareStatement("UPDATE compatibilities "
                        + "SET D10W = ? WHERE medication = ? ");
                                                               
                ps.setString(1, updateD10W);
                ps.setString(2, data1);
                ps.execute();
                
            } catch (SQLException ex) {
                System.out.println("Check your SQL" + ex.getMessage());
            }
        }
        
        if (col == h2oColumn){
            try {
                PreparedStatement ps = db_connector.getConn().prepareStatement("UPDATE compatibilities "
                        + "SET SterileWater = ? WHERE medication = ? ");
                                                               
                ps.setString(1, updateH2O);
                ps.setString(2, data1);
                ps.execute();
                
            } catch (SQLException ex) {
                System.out.println("Check your SQL" + ex.getMessage());
            }
        }
        

        }catch (Exception e){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setContentText("Please click on a medication \n"
        + "and then click the Modify button.");
        alert.showAndWait();}
        
    populateMedications();
    }
          
    @FXML
    private void returnButtonClicked(ActionEvent event) throws IOException{                     
        Parent HomeScreenParent = FXMLLoader.load(getClass().getResource("HomeScreen.fxml"));
        Scene HomeScreenScene = new Scene(HomeScreenParent);
        Stage HomeScreenStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        HomeScreenStage.setScene(HomeScreenScene);
        HomeScreenStage.show(); 
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
        
        compatComboBox.getItems().addAll("YES", "NO");
        compatComboBox.getSelectionModel().selectFirst();
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
