package lab2;

import java.util.Random;

/**
 * A RabbitModel is used to simulate the growth
 * of a population of rabbits. 
 */
public class RabbitModel
{
  /**
   * Instance variables to count the number of rabbits per year.
   */
	private int numRabbit;
	private int yearBefore;
	private int lastYear;
  /**
   * Constructs a new RabbitModel.
   */
  public RabbitModel()
  {
    numRabbit = 0;
    yearBefore = 0;
	lastYear = 0;
  }  
 
  /**
   * Returns the current number of rabbits.
   * @return
   *   current rabbit population
   */
  public int getPopulation()
  {
    // TODO - returns a dummy value so code will compile
    return numRabbit;
  }
  
  /**
   * Updates the population to simulate the
   * passing of one year.
   */
  public void simulateYear()
  {
	   yearBefore = numRabbit;
	   numRabbit += lastYear;
	   lastYear = yearBefore;
  }
  
  /**
   * Sets or resets the state of the model to the 
   * initial conditions.
   */
  public void reset()
  {
	yearBefore = 0;
    numRabbit = 1;
    lastYear = 1;
  }
}
