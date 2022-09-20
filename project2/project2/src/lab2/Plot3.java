package lab2;

import java.util.Random;

/**
 * A RabbitModel is used to simulate the growth
 * of a population of rabbits. 
 */
public class Plot3
{
  /**
   * Instance variable to count the number of rabbits
   */
	private int numRabbit;
  
  /**
   * Constructs a new RabbitModel.
   */
  public Plot3()
  {
    numRabbit = 0;
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
	  Random rnd = new Random();
	  numRabbit += rnd.nextInt(10);
  }
  
  /**
   * Sets or resets the state of the model to the 
   * initial conditions.
   */
  public void reset()
  {
    numRabbit = 0;
  }
}
