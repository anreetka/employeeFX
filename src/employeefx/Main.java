/**
 * Group Member Names: Anreet
 * Group Member Student Numbers: 991671857
 * Final Project
 * Date: April 9th, 2023
 */
package employeefx;

import content.Employee;
import content.EmployeeFile;
import static content.EmployeeFile.readFile;
import content.SearchStage;
import java.util.ArrayList;
import java.util.Optional;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {
    //labels for the interface
    private Label lblID = new Label("ID: ");
    private Label lblName = new Label("Name: ");
    private Label lblCity = new Label("City: ");
    private Label lblPosition = new Label("Position: ");
    private Label lblEmpty1 = new Label(" ");
    private Label lblEmpty2 = new Label(" ");
    private Label lblEmpty3 = new Label(" ");
    private Label lblEmpty4 = new Label(" ");
    
    //textfields for the interface
    private TextField txtID = new TextField();
    private TextField txtName = new TextField();
    private TextField txtCity = new TextField();
    private TextField txtPosition = new TextField();
    
    //buttons for the interface
    private Button btnFirst = new Button("First");
    private Button btnNext = new Button("Next");
    private Button btnPrevious = new Button("Previous");
    private Button btnLast = new Button("Last");
    
    //buttons to update, delete and a add a new record
    private Button btnUpdate = new Button("Update");
    private Button btnDelete = new Button("Delete");
    private Button btnAdd = new Button("Add");
    
    //button to search 
    private Button btnSearch = new Button("Search");
    
    //Arraylist to load data
    ArrayList<Employee> employeeList = new ArrayList();
    int index = 0;
    
    @Override
    public void start(Stage stage){
        
        loadEmployeeList();
        
        
        btnFirst.setOnAction(e ->{
            
            txtID.setText(Integer.toString(employeeList.get(0).getID()));
            txtName.setText(employeeList.get(0).getName());
            txtCity.setText(employeeList.get(0).getCity());
            txtPosition.setText(employeeList.get(0).getPosition());
            
        });
        
        btnNext.setOnAction(e -> {
            index = index + 1;
            txtID.setText(Integer.toString(employeeList.get(index).getID()));
            txtName.setText(employeeList.get(index).getName());
            txtCity.setText(employeeList.get(index).getCity());
            txtPosition.setText(employeeList.get(index).getPosition());
            
        });
        
        btnPrevious.setOnAction(e -> {
            if(index > 0){
                index = index - 1;
            }
            txtID.setText(Integer.toString(employeeList.get(index).getID()));
            txtName.setText(employeeList.get(index).getName());
            txtCity.setText(employeeList.get(index).getCity());
            txtPosition.setText(employeeList.get(index).getPosition());
            
        });
        
        btnLast.setOnAction(e -> {
            index = (employeeList.size()) - 1; 
            txtID.setText(Integer.toString(employeeList.get(index).getID()));
            txtName.setText(employeeList.get(index).getName());
            txtCity.setText(employeeList.get(index).getCity());
            txtPosition.setText(employeeList.get(index).getPosition());
        });
        
        btnUpdate.setOnAction(e -> {
            try{
                int updatedID = Integer.parseInt(txtID.getText());
                String updatedName = txtName.getText();
                String updatedCity = txtCity.getText();
                String updatedPosition = txtPosition.getText();

                Employee updatedEmployee = new Employee(updatedID, updatedName, updatedCity, updatedPosition);                
                index = -1;

                for(int i = 0; i < employeeList.size(); i++){
                    if(employeeList.get(i).getID()==updatedID || employeeList.get(i).getName()==updatedName || employeeList.get(i).getPosition() ==updatedPosition || employeeList.get(i).getCity()==updatedCity){
                        index = i;
                        break;
                    }
                }
                  
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation");
                alert.setHeaderText("Update Employee Details");
                alert.setContentText("Are you sure you would like to update the employee details?");
                Optional<ButtonType> answer = alert.showAndWait();
                
                if(answer.get()==ButtonType.OK){
                    if(index != -1){
                        employeeList.set(index, updatedEmployee);
                    }
                    else{
                        Alert errorAlert = new Alert(Alert.AlertType.CONFIRMATION);
                        errorAlert.setTitle("Error");
                        errorAlert.setHeaderText("Employee Update");
                        errorAlert.setContentText("Would you like to update the new information as new employee?");  
                        Optional<ButtonType> response = errorAlert.showAndWait();
                        
                        if(response.get() == ButtonType.OK){
                            employeeList.add(updatedEmployee);
                        }else{
                            Alert bAlert = new Alert(Alert.AlertType.INFORMATION);
                            bAlert.setContentText("Information not added.");
                            bAlert.showAndWait();
                        }
                    }
                }else if(answer.get() == ButtonType.CANCEL){
                    txtID.setText(Integer.toString(employeeList.get(index).getID()));
                    txtName.setText(employeeList.get(index).getName());
                    txtCity.setText(employeeList.get(index).getCity());
                    txtPosition.setText(employeeList.get(index).getPosition());                    
                } 
            }catch(NumberFormatException a){
                Alert errorAlert2 = new Alert(Alert.AlertType.ERROR);
                errorAlert2.setTitle("Error");
                errorAlert2.setHeaderText("Invalid Input");
                errorAlert2.setContentText("Please enter a valid integer for ID");
                errorAlert2.showAndWait();
                
            }catch(Exception a){
                System.out.println(a);
            }
        
        });
        
        btnDelete.setOnAction(e -> {
                try{
                int oldID = Integer.parseInt(txtID.getText());
                String oldName = txtName.getText();
                String oldCity = txtCity.getText();
                String oldPosition = txtPosition.getText();

                Employee oldEmployee = new Employee(oldID, oldName, oldCity, oldPosition);                
                index = -1;
                
                for(int i = 0; i < employeeList.size(); i++){
                    if(employeeList.get(i).getID()==oldID){
                        index = i;
                        break;
                    }
                }
                
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation");
                alert.setHeaderText("Delete Employee");
                alert.setContentText("Are you sure you would like to delete the employee details?");
                Optional<ButtonType> answer = alert.showAndWait();
                if(answer.get()==ButtonType.OK){
                    if(index != -1){
                        employeeList.remove(index);
                        txtID.clear();
                        txtName.clear();
                        txtCity.clear();
                        txtPosition.clear();
                    }
                    else{
                        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                        errorAlert.setTitle("Error");
                        errorAlert.setHeaderText("Employee not found");
                        errorAlert.setContentText("The employee you are trying to delete does not exist in the list or you didn't update the changes made.");   
                        errorAlert.showAndWait();
                    }
                }else if(answer.get() == ButtonType.CANCEL){
                    txtID.setText(Integer.toString(employeeList.get(index).getID()));
                    txtName.setText(employeeList.get(index).getName());
                    txtCity.setText(employeeList.get(index).getCity());
                    txtPosition.setText(employeeList.get(index).getPosition());                    
                }           
            }catch(NumberFormatException a){
                Alert errorAlert2 = new Alert(Alert.AlertType.ERROR);
                errorAlert2.setTitle("Error");
                errorAlert2.setHeaderText("Invalid Input");
                errorAlert2.setContentText("Please enter a valid integer for ID");
                errorAlert2.showAndWait();                
            }catch(Exception a){
                System.out.println(a);
            }
        });
        
        btnAdd.setOnAction(e -> {
            
            txtID.clear();
            txtName.clear();
            txtCity.clear();
            txtPosition.clear();
            
            txtID.requestFocus();
                                


        });
        
        btnSearch.setOnAction(e -> {
            SearchStage searchstage = new SearchStage();
            searchstage.startSecondStage(employeeList);
        });
        
        stage.setOnCloseRequest((e)->{
            EmployeeFile.writeFile(employeeList);
        });
        
        Scene scene = new Scene(getGrid(), 350, 400);
        scene.getStylesheets().add("/content/TheStyle.css");
        stage.setScene(scene);
        stage.setTitle(" ");
        stage.show();
    }
    
    public GridPane getGrid(){
        GridPane pane = new GridPane();
        pane.add(lblID, 0, 0);
        pane.add(lblName, 0, 1);
        pane.add(lblCity, 0,2 );
        pane.add(lblPosition, 0, 3);
        pane.add(txtID, 1, 0);
        pane.add(txtName, 1, 1);
        pane.add(txtCity, 1, 2);
        pane.add(txtPosition, 1, 3);
        pane.add(lblEmpty1, 1, 4);
        pane.add(btnPrevious, 1, 7);
        pane.add(btnNext, 3, 7);
        pane.add(lblEmpty2, 1, 6);
        pane.add(btnFirst, 1, 5);
        pane.add(lblEmpty3, 1, 8);
        pane.add(btnLast, 3, 5);
        pane.add(btnAdd, 1, 9);
        pane.add(lblEmpty4, 1, 10);
        pane.add(btnDelete, 3, 9);
        pane.add(btnUpdate, 1, 11);
        pane.add(btnSearch, 3, 11);   
        
        return pane;
    }
    
    public void loadEmployeeList(){
        readFile(employeeList);
    }
    
    public static void main(String[] args) {
        Application.launch(args);
    }
    
       
}
