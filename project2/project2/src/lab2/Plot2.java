package lab2;

/**
 * A RabbitModel is used to simulate the growth
 * of a population of rabbits. 
 */
public class Plot2
{
  /**
   * Instance variable to count the number of rabbits
   */
	private int numRabbit;
  
  /**
   * Constructs a new RabbitModel.
   */
  public Plot2()
  {
    numRabbit = 500;
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
    numRabbit /= 2;
  
  }
  
  /**
   * Sets or resets the state of the model to the 
   * initial conditions.
   */
  public void reset()
  {
    numRabbit = 500;
  }
}
