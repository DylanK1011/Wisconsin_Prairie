//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    P02 Wisconsin Prairie
// Course:   CS 300 Fall 2020
//
// Author:   Dylan Krejci
// Email:    dkrejci@wisc.edu
// Lecturer: Hobbes LeGault)
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:         NONE
// Online Sources:  NONE
//
///////////////////////////////////////////////////////////////////////////////
import java.util.Random;
import processing.core.PApplet;
import processing.core.PImage;

public class WisconsinPrairie {
  private static PApplet processing;     // PApplet object that represents the graphic
  									     // interface of the WisconsinPrairie application
  
  private static PImage backgroundImage; // PImage object that represents the background image
  
  private static Cow[] cows;             // array storing the current cows present in the prairie
  
  private static Random randGen;         // Generator of random numbers
  
  /**
   * Sets up the properties of the application's environment
   * 
   * @param processingObj represents a reference to the graphical interface of the application
   */
  public static void setup(PApplet processingObj) {
    processing = processingObj; // initializes variable processing to the value
                                // input as a parameter
    
    // initialize and set image of background
    backgroundImage = processing.loadImage("images/background.png");
    
    // Draw the background image at the center of the screen
    
    // width [resp. height]: System variable of the processing library that stores
    // the width [restp. height] of the display window
    
    // Creates a number randomizer
    randGen = new Random();
    
    // Creates a new cow array with up to ten locations
    cows = new Cow[10];
    
    // Fills cows array with null values
    cows[0] = null;
    cows[1] = null;
    cows[2] = null;
    cows[3] = null;
    cows[4] = null;
    cows[5] = null;
    cows[6] = null;
    cows[7] = null;
    cows[8] = null;
    cows[9] = null;
    
    // Calls callback method draw()
    draw();
  }
  
  /**
   * Draws and updates the application display window.
   * This callback method is called in an infinite loop
   */
  public static void draw() {
	  
	// Continually resets background image to avoid apparent object duplication 
	// (one cow being moved and leaving behind images at past locations)  
	processing.image(backgroundImage, processing.width / 2, processing.height / 2);
	  
	// Loops through cows array and draws any non-null Cow objects onto the background
	for (int i = 0; i < cows.length; i++) {
	  if (cows[i] != null) {
		// cows[i].draw() method is already established in the Cow API
		cows[i].draw();
      }
    }
  }
  /**
   * Checks if the mouse is over a given cow whose reference is provided
   * as input parameter
   * 
   * @param cow reference to a given cow object
   * @return true if the mouse is over the given cow object (i.e. over
   * 		the image of the cow), false otherwise
   */
  public static boolean isMouseOver(Cow cow) {
	  
	// Checks that the mouse is located within the area of each Cow object
	if (processing.mouseX <= cow.getImage().width/2 + cow.getPositionX() && // Is mouse to the left of the object edge?
	processing.mouseX >= cow.getPositionX() - cow.getImage().width/2 && // Is mouse to the right of the object edge?
    processing.mouseY <= cow.getImage().height/2 + cow.getPositionY() && // Is mouse below the top of object?
	processing.mouseY >= cow.getPositionY() - cow.getImage().height/2) { // Is mouse above the bottom of object?
	  // The mouse is over at least one cow object
	  return true;
    }
	// The mouse is not over any cow object
	return false;
  }
  
  /**
   * Callback method called each time the user presses the mouse
   */
  public static void mousePressed() {
	
	// Loops through cows array. If the isMouseOver() method returns true, 
	// the lowest index object is set to dragging
	for (int i = 0; i < cows.length; i++) {
      if (cows[i] != null) {
	    if (isMouseOver(cows[i])) {
	      cows[i].setDragging(true);
	      // Break ensures that only one object is dragged at a time
	      break;
	    }
	  }
	}
  }
  
  /**
   * Callback method called each time the mouse is released
   */
  public static void mouseReleased() {
	  
	// Loops through cows array. If the mouse is released, every cow object will be set to not dragging
	for (int i = 0; i < cows.length; i++) {
      if (cows[i] != null) {
	    cows[i].setDragging(false);
	  }
	}
  }
  
  /**
   * Callback method called each time the user presses a key
   * 
   */
  public static void keyPressed() {
	
	// If 'c' or 'C' is pressed, a new Cow object will be added 
	// at the lowest available index, up to 10
    if (processing.key == 'c' || processing.key == 'C') {
      for (int i = 0; i < cows.length; i++) {
    	  if (cows[i] == null) {
    		cows[i] = new Cow(processing, randGen.nextInt(processing.width), randGen.nextInt(processing.height));
    		// Break ensures that only one cow object is added at a time
    		break;
    	  }
    	}
      }
    // If 'd' or 'D' is pressed AND the mouse is over a cow object, 
    // remove the lowest index object for which each is true
    if (processing.key == 'D' || processing.key == 'd') {
      for (int i = 0; i < cows.length; i++) {
    	if (cows[i] != null) {
          if (isMouseOver(cows[i])) {
    	    cows[i] = null;
    	    // Break ensures that only one cow object is removed at a time
    	    break;
          }
        }
      }
    }
  }
  /**
   * Main method that will call the startApplication() method,
   * which in turn utilizes all callback methods created above
   * 
   * @param args
   */
  public static void main(String[] args) {
	Utility.startApplication();
  }
}
