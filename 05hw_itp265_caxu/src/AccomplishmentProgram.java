import java.util.*;

/**
 * Accomplishment Program that runs program
 *
 * @author Connie Xu
 * @version Mar 8, 2021
 * ITP 265, Spring 2021, Coffee Section
 * Email: caxu@usc.edu
 * Homework 05
 *
 */

public class AccomplishmentProgram {

	 //initializer
	  private BFF sc;
	  private FileReader sc1;
	  private Accomplishment database[];
	  private static final String fileName= "kaggle-datasets-firsts-TABBED.txt";
	  private static final int DEFAULT_SIZE = 479;
	  private int numSlotsFilled;
	private Scanner entryScanner;

	  public AccomplishmentProgram(){
	  sc = new BFF();
	  sc1 = new FileReader();
	    database = new
	    		Accomplishment[DEFAULT_SIZE];
	    numSlotsFilled = 0;
	    }
	    
	 //welcome message
	  public void displayWelcome() {
	    String intro = "This data is from Kaggle's Tidy Tuesday Week 24 and comes from Wikipedia. It is a celebration of "
	            +"Black Lives,\n\ttheir achievements, and many of their battles against racism across their lives."
	            + "\n\tThis is in emphasis that Black Lives Matter and we're focusing on a celebration of these lives. ";

	    String info = "The firsts.csv dataset has 479 records of African-Americans breaking the color "
	            +"barrier across a \n\twide range of topics. The raw text has been adapted from Wikipedia to "
	            + " highlight:\n\t** The year of the event\n\t** The role/action/topic"
	            + "\n\t** The person or people involved\n\t** Addition of gender\n\t** A category of topics";
	    
	    String citation = "Original data set can be found here: https://www.kaggle.com/jessemostipak/african-american-achievements/";

	    System.out.println("INTRO: \n\t" + intro + "\n\nINFO: \n\t" + info + "\n\n" + citation);
	    }
	        
	  //run menu
	  public void run() {
	    readDataFromFile(fileName);
	    Menu choice; 

	    do{
	        System.out.println("*****   Menu   *****");
	        Menu.print();
	        System.out.println("**********************************************");
	        int num = sc.inputInt("Choose > ", 0, Menu.getNumberOfMenuItems());
	        choice = Menu.getOption(num);
	        switch(choice){
	            case PRINT: printFacts(); break; //make this
	            case QUIZ:  playQuiz(); break;
	            case FEMALE: printFemale(); break;
	            case MALE: printMale(); break;
	            case CATEGORY: printCategory(); break;
	            case QUIT: System.out.println("Goodbye."); break;
	       }
	    }
	    while(choice != Menu.QUIT);
	   }


	 //run main
	  public static void main(String[] args) {
	        AccomplishmentProgram program = new AccomplishmentProgram();
	        program.displayWelcome();
	        program.run();
	    }
	   
	 //read data and put in list
	  public void readDataFromFile(String file){
	    ArrayList<String> fileData = sc1.readFile(file);
	  
	    for(int i = 1; i < fileData.size(); i++){
	        String entry = fileData.get(i);
	        entryScanner = new Scanner(entry);
	        entryScanner.useDelimiter("\t"); 
	        int year = entryScanner.nextInt();
	        String accomplishment = entryScanner.next();
	        String person = entryScanner.next();
	        String gender = entryScanner.next();
	        Boolean genderCheck = false;
	        if (gender.contains("Female")){
	            genderCheck = true;
	        }
	        String category = entryScanner.next();
	        Category cat = Category.getCategoryFromDescription(category);
	        Accomplishment c = new Accomplishment(year, accomplishment, person, genderCheck, cat);
	        addData(c);
	    }
	    
	  }
	  
	 //add data to list if not full
	  private boolean addData(Accomplishment c){ 
	      boolean addedData = false;
	      if(numSlotsFilled < database.length) {
	        database[numSlotsFilled] = c;
	        numSlotsFilled++;
	        addedData = true;
	        }
	        return addedData;
	  }
	    
	  
	  //print facts
	  public void printFacts(){ 
	    for (int i = 0; i < database.length; i++) {
	        System.out.println(database[i]);
	    }
	  }

	  //play quiz
	  public void playQuiz(){
	    int score = 0;
	    
	    Random objGenerator = new Random();
	    for (int i = 0; i< 10; i++){
	              int randomNumber = objGenerator.nextInt(DEFAULT_SIZE);
	              int x = sc.inputInt( "In what year did " + database[randomNumber].getPerson() + " become the " + database[randomNumber].getAccomplishment()+ "? (5 year range)", 1738, 2019);
	              System.out.println(x);
	              if ((database[randomNumber].getYear() - x) <= 5 && (x - database[randomNumber].getYear()) >= -5) {
	                 score = score + 1;
	                 System.out.println("That was correct!");
	                 System.out.println("Your score is now: " + score); 
	                }
	              else
	                System.out.println("Sorry you were not within 5 years!");
	                System.out.println("The correct answer was: " + database[randomNumber].getYear());
	                System.out.println("Your score is now: " + score);
	                }
	    System.out.println("Thanks for playing! Your final score was: " + score);
	   }
	    
	  //print out females
	  public void printFemale(){ 
	    for (int i = 0; i < database.length; i++){
	        if (database[i].isFemale()){
	            System.out.println(database[i]);
	        }
	       
	    }
	  }
	   
	  //print not females
	  public void printMale(){ 
	    for (int i = 0; i < database.length; i++){
	        if (!database[i].isFemale()){
	            System.out.println(database[i]);
	        }
	    }
	    
	  }
	    
	  //print out the categories
	  public void printCategory(){ 
	    Category.getCategoryOptions();
	    int userInput = sc.inputInt("Which category would you like to see?", 1, 8);
	    switch(userInput){
	        case 1:
	        	printCertainCategory("Social & Jobs");
	        	break;
	        case 2:
	        	printCertainCategory("Arts & Entertainment");
	        	break;
	        case 3:
	        	printCertainCategory("Religion");
	        	break;
	        case 4:
	        	printCertainCategory("Military");
	        	break;
	        case 5:
	        	printCertainCategory("Education & Science");
	        	break;
	        case 6:
	        	printCertainCategory("Law");
	        	break;
	        case 7:
	        	printCertainCategory("Politics");
	        	break;
	        case 8:
	        	printCertainCategory("Sports");
	        	break;
	    }
	  }
	   
	  //print a certain category
	  public void printCertainCategory(String x){
	    for (int i = 0; i < database.length; i++){
	        if (database[i].getCategory().toString().contains(x)){
	            System.out.println(database[i]);
	            
	        }
	    }
	    
	    }
}
