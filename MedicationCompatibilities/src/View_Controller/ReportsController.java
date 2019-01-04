
package View_Controller;

import Model.IntravenousMedication;
import db_utility.db_connector;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ben garrison
 */
public class ReportsController implements Initializable {
    
    @FXML
    private TableView<IntravenousMedication> d5Table;
    @FXML
    private TableColumn<IntravenousMedication, String> d5wColumn;
    
    @FXML
    private TableView<IntravenousMedication> nsTable;    
    @FXML
    private TableColumn<IntravenousMedication, String> nsColumn;
    
    @FXML
    private TableView<IntravenousMedication> halfNsTable;
    @FXML
    private TableColumn<IntravenousMedication, String> halfNsColumn;
    
    @FXML
    private TableView<IntravenousMedication> lrTable;
    @FXML
    private TableColumn<IntravenousMedication, String> lrColumn;    
    
    @FXML
    private TableView<IntravenousMedication> d10Table;
    @FXML
    private TableColumn<IntravenousMedication, String> d10wColumn;
    
    @FXML
    private TableView<IntravenousMedication> h2oTable;
    @FXML
    private TableColumn<IntravenousMedication, String> h2oColumn;
    
    @FXML
    private TextField timestamp;

    
    @FXML
    private Button returnButton;
    
    Timestamp now = new Timestamp(System.currentTimeMillis());
    String currentTime = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(now);
       
    ObservableList<IntravenousMedication> compatibilityListD5W = FXCollections.observableArrayList();
    ObservableList<IntravenousMedication> compatibilityListNS = FXCollections.observableArrayList();
    ObservableList<IntravenousMedication> compatibilityListHalfNS = FXCollections.observableArrayList();
    ObservableList<IntravenousMedication> compatibilityListLR = FXCollections.observableArrayList();
    ObservableList<IntravenousMedication> compatibilityListD10W = FXCollections.observableArrayList();
    ObservableList<IntravenousMedication> compatibilityListH2O = FXCollections.observableArrayList();
     
    String tMedication;
    
                               
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
                
        PopulateReportD5WTable();
        PopulateReportNSTable();
        PopulateReportHalfNSTable();
        PopulateReportLRTable();
        PopulateReportD10WTable();
        PopulateReportH2OTable();
        
        timestamp.setText("Compatibility List by Solution report run at: " + currentTime);
        
        d5wColumn.setCellValueFactory(new PropertyValueFactory<>("medication"));
        d5Table.setItems(compatibilityListD5W);
        
        nsColumn.setCellValueFactory(new PropertyValueFactory<>("medication"));
        nsTable.setItems(compatibilityListNS);
        
        halfNsColumn.setCellValueFactory(new PropertyValueFactory<>("medication"));
        halfNsTable.setItems(compatibilityListHalfNS);
        
        lrColumn.setCellValueFactory(new PropertyValueFactory<>("medication"));
        lrTable.setItems(compatibilityListLR);
        
        d10wColumn.setCellValueFactory(new PropertyValueFactory<>("medication"));
        d10Table.setItems(compatibilityListD10W);
        
        h2oColumn.setCellValueFactory(new PropertyValueFactory<>("medication"));
        h2oTable.setItems(compatibilityListH2O);
                      
    } 
    
    public void PopulateReportD5WTable() {
        
        d5Table.getItems().clear();
        
        try (
                PreparedStatement statement = db_connector.getConn().prepareStatement("select compatibilities.medication from compatibilities where compatibilities.D5W = 'YES' \n"
                        +   "order by medication");
                ResultSet rs = statement.executeQuery();) {

            while (rs.next()) {

                tMedication = rs.getString("compatibilities.medication");
                                               
                compatibilityListD5W.add(new IntravenousMedication(tMedication));
            }

        } catch (SQLException sqe) {
            System.out.println("Check your SQL" + sqe.getMessage());
        } catch (Exception e) {
            System.out.println("Something besides SQL went wrong.");
        }
    }
    
    public void PopulateReportNSTable() {
        
      nsTable.getItems().clear();
        
        try (
                PreparedStatement statement = db_connector.getConn().prepareStatement("select compatibilities.medication \n"
                        + "from compatibilities WHERE compatibilities.NormalSaline = 'YES' \n"
                        +   "order by medication");
                ResultSet rs = statement.executeQuery();) {

            while (rs.next()) {

                tMedication = rs.getString("compatibilities.medication");
                                               
                compatibilityListNS.add(new IntravenousMedication(tMedication));
            }

        } catch (SQLException sqe) {
            System.out.println("Check your SQL" + sqe.getMessage());
        } catch (Exception e) {
            System.out.println("Something besides SQL went wrong.");
        }  

    }
    
    public void PopulateReportHalfNSTable() {
        
        halfNsTable.getItems().clear();
        
        try (
                PreparedStatement statement = db_connector.getConn().prepareStatement("select compatibilities.medication \n"
                        + "from compatibilities WHERE compatibilities.HalfNormalSaline = 'YES' \n"
                        +   "order by medication");
                ResultSet rs = statement.executeQuery();) {

            while (rs.next()) {

                tMedication = rs.getString("compatibilities.medication");
                                               
                compatibilityListHalfNS.add(new IntravenousMedication(tMedication));
            }

        } catch (SQLException sqe) {
            System.out.println("Check your SQL" + sqe.getMessage());
        } catch (Exception e) {
            System.out.println("Something besides SQL went wrong.");
        }
        
    }
    
    public void PopulateReportLRTable() {
        
        lrTable.getItems().clear();
        
        try (
                PreparedStatement statement = db_connector.getConn().prepareStatement("select compatibilities.medication \n"
                        + "from compatibilities WHERE compatibilities.LactatedRingers = 'YES' \n"
                        +   "order by medication");
                ResultSet rs = statement.executeQuery();) {

            while (rs.next()) {

                tMedication = rs.getString("compatibilities.medication");
                                               
                compatibilityListLR.add(new IntravenousMedication(tMedication));
            }

        } catch (SQLException sqe) {
            System.out.println("Check your SQL" + sqe.getMessage());
        } catch (Exception e) {
            System.out.println("Something besides SQL went wrong.");
        }
        
    }
    
    public void PopulateReportD10WTable() {
        
        d10Table.getItems().clear();
        
        try (
                PreparedStatement statement = db_connector.getConn().prepareStatement("select compatibilities.medication \n"
                        + "from compatibilities WHERE compatibilities.D10W = 'YES' \n"
                        +   "order by medication");
                ResultSet rs = statement.executeQuery();) {

            while (rs.next()) {

                tMedication = rs.getString("compatibilities.medication");
                                               
                compatibilityListD10W.add(new IntravenousMedication(tMedication));
            }

        } catch (SQLException sqe) {
            System.out.println("Check your SQL" + sqe.getMessage());
        } catch (Exception e) {
            System.out.println("Something besides SQL went wrong.");
        }
        
    }    

    public void PopulateReportH2OTable() {
        
        h2oTable.getItems().clear();
        
        try (
                PreparedStatement statement = db_connector.getConn().prepareStatement("select compatibilities.medication \n"
                        + "from compatibilities WHERE compatibilities.SterileWater = 'YES' \n"
                        +   "order by medication");
                ResultSet rs = statement.executeQuery();) {

            while (rs.next()) {

                tMedication = rs.getString("compatibilities.medication");
                                               
                compatibilityListH2O.add(new IntravenousMedication(tMedication));
            }

        } catch (SQLException sqe) {
            System.out.println("Check your SQL" + sqe.getMessage());
        } catch (Exception e) {
            System.out.println("Something besides SQL went wrong.");
        }
        
    }
     
}