/**
 * Group Member Names: Anreet
 * Group Member Student Numbers: 991671857
 * Final Project
 * Date: April 9th, 2023
 */

package content;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author anree
 */
public class EmployeeFile{
      
    public static void writeFile(ArrayList<Employee> employeeDetails){
        try{
           FileWriter fw = new FileWriter("Employee.dat");
           BufferedWriter bw = new BufferedWriter(fw);   
           for(Employee employee: employeeDetails){
               bw.write(employee.getID()+ ", " + employee.getName()+ ", " + employee.getCity() + ", " + employee.getPosition());
               bw.newLine();
           }

           //add more employee data
           bw.close();
        }catch(FileNotFoundException e){
            System.out.println("File Not Found!");
        }catch(IOException e){
            System.out.println(e);
        }catch(Exception e){
            System.err.println(e);
        }
    }

    public static void readFile(ArrayList<Employee>employeeDetails){
        try{
            FileReader fr = new FileReader("Employee.dat");
            BufferedReader br = new BufferedReader(fr); 
            
            String line = br.readLine();
            while(line!=null){           
                String delimiter = ",";
                StringTokenizer one = new StringTokenizer(line, delimiter);
                    int ID = Integer.parseInt(one.nextToken());
                    String name = one.nextToken();
                    String city = one.nextToken();
                    String position = one.nextToken();
                    
                    Employee employee = new Employee(ID, name, city, position);
                    employeeDetails.add(employee);
                    
                    line = br.readLine();  
        } 
          br.close();
        }catch(FileNotFoundException e){
            System.out.println("File not Found!");
        }catch(IOException e){
            System.out.println(e);
        }

    }
      
   
    
}
