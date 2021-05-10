/**
 * POJO to store data (one piece) that we get from file
 *
 * @author Connie Xu
 * @version Mar 4, 2021
 * ITP 265, Spring 2021, Coffee Section
 * Email: caxu@usc.edu
 * Homework 05
 *
 */
public class Accomplishment {
	//instance vars
	private int year;
    private String accomplishment;
    private String person;
    private boolean isFemale;
    private Category category;
    
    public Accomplishment(int year, String accomplishment, String person, boolean isFemale, Category category){ //constructor
        this.year = year;
        this.accomplishment = accomplishment;
        this.person = person;
        this.isFemale = isFemale;
        this.category = category;
    }
    
    //accessors and mutators
    public int getYear() {
        return year;
    }
    
    public String getAccomplishment() { 
        return accomplishment;
    }
    
    public String getPerson() { 
        return person;
    }
    
    public boolean isFemale() {
        return isFemale;
    }
    
    public Category getCategory() {
        return category;
    }
    
    //to String method
    public String toString() {
    return "Year: " + year
    + ", Accomplishment: " + accomplishment
    + ", Person:" + person
    + ", Is Female:" + isFemale
    + ", Category: " + category;
    }
}
