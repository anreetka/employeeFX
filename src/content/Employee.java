/**
 * Group Member Names: Anreet
 * Group Member Student Numbers: 991671857
 * Final Project
 * Date: April 9th, 2023
 */

package content;

/**
 *
 * @author anree
 */
public class Employee {
    private int ID;
    private String name;
    private String city;
    private String position;
    
    public Employee(int ID, String name, String city, String position){
        this.ID = ID;
        this.name = name;
        this.city=city;
        this.position = position;
    }
    
    public int getID(){
        return this.ID;
    }
    public String getName(){
        return this.name;
    }
    
    public String getCity(){
        return this.city;
    }
    
    public String getPosition(){
        return this.position;
    }
}
