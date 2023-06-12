/**
 * Group Member Names: Anreet
 * Group Member Student Numbers: 991671857
 * Final Project
 * Date: April 9th, 2023
 */

package content;

import java.util.ArrayList;
import java.util.Optional;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author anree
 */
public class SearchStage extends Stage {
    
        Label lblByCity = new Label("Search by City: ");
        TextField byCity = new TextField();
        Label lblByPosition = new Label("Search by Position: ");
        TextField byPosition = new TextField();
        Label results = new Label("The result will appear here.");
        Label showResults = new Label();
        Button search = new Button("Search Now");
        
        //empty labels
        Label emptyLbl = new Label(" ");
        Label emptyLbl2 = new Label(" ");
    
    public void startSecondStage (ArrayList <Employee> employeeList){
              
        search.setOnAction(e -> {
           String position = byPosition.getText(); 
           String city = byCity.getText();
           StringBuilder sDetails = new StringBuilder();
           sDetails.append("");
           int employeeFound = 0;
           
           results.setText("Here are the details:");
           for(Employee employee: employeeList){
               if(employee.getCity().contains(city) && employee.getPosition().contains(position)){
                  sDetails.append("City: "+ city + "\nPosition: "+ position + "\nName: "+ employee.getName() + "\nID: "+ employee.getID() + '\n');
                  employeeFound++;
               }
           }
           
           showResults.setText(sDetails.toString());
           
           if(employeeFound == 0){
               
                        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                        errorAlert.setTitle("Error");
                        errorAlert.setHeaderText("No match found");
                        errorAlert.setContentText("The employee details you have entered does not match the employee data."); 
                        errorAlert.showAndWait();
                        
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);  
                        alert.setTitle("Confirmation");
                        alert.setHeaderText("Try again?");
                        alert.setContentText("Would you like to enter the data again?"); 
                        Optional<ButtonType> answer = alert.showAndWait();
                        
                        if(answer.get()==ButtonType.OK){
                            byCity.clear();
                            byPosition.clear();
                            results.setText("The result will appear here.");
                            byCity.requestFocus();
                        }else{
                            results.setText("No match found!");
                        }
           }
        });
        

        
        Stage stage = new Stage();
        Scene scene = new Scene(getGrid(), 350, 250);
        scene.getStylesheets().add("/content/TheStyle.css");
        stage.setScene(scene);
        stage.setTitle("Search records");
        stage.show();
                
    }
    
    public GridPane getGrid(){
        GridPane pane = new GridPane();
        pane.add(lblByCity, 0, 0);
        pane.add(lblByPosition, 0, 1);
        pane.add(emptyLbl, 0, 2);
        pane.add(search, 0,3 );
        pane.add(emptyLbl2, 0, 4);
        pane.add(results, 0, 5);
        pane.add(byCity, 1, 0);
        pane.add(byPosition, 1, 1);
        pane.add(showResults, 0, 6);
         
        return pane;
    }
    
}
