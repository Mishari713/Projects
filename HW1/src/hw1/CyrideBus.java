package hw1;

/**
 * A model of a bus that simulate ridership and location of the city bus.
 * 
 * @author Mishari Alharbi
 *
 */
public class CyrideBus {

	/**
	 * The stop number -1.
	 */
	public static final int BUS_GARAGE = -1;

	/**
	 * The number of stops the bus has.
	 */
	private int numStops;

	/**
	 * The current stop the bus is at.
	 */
	private int currentStop;

	/**
	 * The current capacity of passengers the bus has at any stop.
	 */
	private int currentCapacity;

	/**
	 * The number of passengers currently on the bus.
	 */
	private int numPassengers;

	/**
	 * The number of the total passengers who rode the bus.
	 */
	private int totalRiders;

	/**
	 * The maximum capacity of passengers the bus can have.
	 */
	private int maxCapacity;

	/**
	 * Constructs a new bus with the given maximum capacity that will travel among
	 * the given number of stops.
	 * 
	 * @param givenMaxCapacity
	 * @param givenNumStops
	 */
	public CyrideBus(int givenMaxCapacity, int givenNumStops) {
		numStops = givenNumStops;
		currentStop = BUS_GARAGE;
		currentCapacity = givenMaxCapacity;
		maxCapacity = givenMaxCapacity;

	}

	/**
	 * Returns the current capacity of the bus. This is zero while the bus is out of
	 * service, andis equal to the maximum capacity when in service.
	 * 
	 * @return currentCapacity
	 */
	public int getCurrentCapacity() { // Accessor

		return currentCapacity;
	}

	/**
	 * Returns the current stop number.
	 * 
	 * @return currentStop
	 */
	public int getCurrentStop() { // Accessor

		return currentStop;
	}

	/**
	 * Returns the current number of passengers on the bus.
	 * 
	 * @return numPassengers
	 */
	public int getNumPassengers() { // Accessor

		return numPassengers;
	}

	/**
	 * Returns the total number of passengers who have gotten on this bus since it
	 * was constructed
	 * 
	 * @return totalRiders
	 */
	public int getTotalRiders() { // Accessor

		return totalRiders;
	}

	/**
	 * Returns true if this bus is in service, that is, its current capacity is
	 * nonzero.
	 * 
	 * @return
	 */
	public boolean isInService() { // Accessor

		return currentCapacity != 0;
	}

	/**
	 * Simulates the bus traveling to its next stop. The given number of people get
	 * off and the given number of people get on.
	 * 
	 * @param peopleOff
	 * @param peopleOn
	 */
	public void nextStop(int peopleOff, int peopleOn) { // Mutator
		currentStop = (currentStop + 1 + numStops) % numStops;
		numPassengers -= peopleOff;
		numPassengers = Math.max(numPassengers, 0);
		totalRiders -= numPassengers;
		numPassengers += peopleOn;
		numPassengers = Math.min(numPassengers, currentCapacity);
		totalRiders += numPassengers;
	}

	/**
	 * Places this bus in service; that is, sets the current capacity to the maximum
	 * value.
	 */
	public void placeInService() { // Mutator
		currentCapacity = maxCapacity;
	}

	/**
	 * Takes this bus out of service. The current capacity becomes zero, all
	 * passengers get off, and the bus is teleported to BUS_GARAGE.
	 */
	public void removeFromService() { // Mutator
		currentCapacity = 0;
		currentStop = BUS_GARAGE;
		numPassengers = 0;
	}
}
