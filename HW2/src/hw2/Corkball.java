package hw2;

/**
 * Model of an obscure game called "Corkball" or sometimes "Fuzzball", generally
 * played in the St Louis area. It is vaguely similar to baseball, except that
 * it is much simpler since there are no actual bases and the runners are
 * imaginary. See the assignment pdf for further explanation.
 * 
 * @author Mishari Alharbi
 */
public class Corkball {
	/**
	 * Number of strikes causing a player to be out.
	 */
	public static final int MAX_STRIKES = 2;

	/**
	 * Number of balls causing a player to walk.
	 */
	public static final int MAX_BALLS = 5;

	/**
	 * Number of outs before the teams switch.
	 */
	public static final int MAX_OUTS = 3;

	/**
	 * An instance variable holds the current number of inning.
	 */
	private int currentInning;

	/**
	 * An instance variable to determine if the current inning is top or bottom.
	 */
	private boolean isTopInning;

	/**
	 * An instance variable to count the number of balls a team have.
	 */
	private int ballCounts;

	/**
	 * An instance variable to count the number of strikes a team have.
	 */
	private int strikeCounts;

	/**
	 * An instance variable to count the number of outs a team have.
	 */
	private int outCounts;

	/**
	 * An instance variable used to hold the maximum innings per game.
	 */
	private int maxInning;

	/**
	 * An instance variable used to hold the imaginary runner on the first base.
	 */
	private boolean firstBase;

	/**
	 * An instance variable used to hold the imaginary runner on the second base.
	 */
	private boolean secondBase;

	/**
	 * An instance variable used to hold the imaginary runner on the third base.
	 */
	private boolean thirdBase;

	/**
	 * An instance variable to capture team 0 score.
	 */
	private int team0Score;

	/**
	 * An instance variable to capture team 1 score.
	 */
	private int team1Score;

	/**
	 * Constructs a game that has the given number of innings and starts at the top
	 * of inning 1.
	 * 
	 * @param giveNuminnings
	 */
	public Corkball(int givenNumInnings) {
		maxInning = givenNumInnings;
		currentInning = 1;
		isTopInning = true;
	}

	/**
	 * Returns true if the game is over, false otherwise.
	 * 
	 * @return
	 */
	public boolean gameEnded() {
		return currentInning >= (maxInning + 1);
	}

	/**
	 * Returns true if there is a runner on the indicated base, false otherwise.
	 * Returns false if the given argument is anything other than 1, 2, or3.
	 * 
	 * @param which
	 * @return
	 */
	public boolean runnerOnBase(int which) {
		if (which == 1) {
			return firstBase;
		} else if (which == 2) {
			return secondBase;
		} else if (which == 3) {
			return thirdBase;
		} else {
			return false;
		}
	}

	/**
	 * Returns the current inning. Innings are numbered starting at 1. When the game
	 * is over, this method returns the game's total number of innings, plus one.
	 * 
	 * @return
	 */
	public int whichInning() {
		return currentInning;
	}

	/**
	 * Returns true if it's the first half of the inning (team 0 is at bat).
	 * 
	 * @return
	 */
	public boolean isTopOfInning() {
		return isTopInning;
	}

	/**
	 * Returns the number of outs for the team currently at bat.
	 * 
	 * @return
	 */
	public int getCurrentOuts() {
		return outCounts;
	}

	/**
	 * Returns the number of called strikes for the current batter.
	 * 
	 * @return
	 */
	public int getCalledStrikes() {
		return strikeCounts;
	}

	/**
	 * Returns the count of balls for the current batter.
	 * 
	 * @return
	 */
	public int getBallCount() {
		return ballCounts;
	}

	/**
	 * Returns the score for team 0.
	 * 
	 * @return
	 */
	public int getTeam0Score() {
		return team0Score;
	}

	/**
	 * Returns the score for team 1.
	 * 
	 * @return
	 */
	public int getTeam1Score() {
		return team1Score;
	}

	/**
	 * Method called to indicate a strike for the current batter. If the swung
	 * parameter is true, the batter is immediately out. Otherwise, 1 is added to
	 * the batters current count of called strikes (possibly resulting in the batter
	 * being out).
	 * 
	 * This method does nothing if the game has ended.
	 * 
	 * @param swung
	 */
	public void strike(boolean swung) {
		if (gameEnded()) {

		} else {
			if (swung) {
				outCounts += 1;
				ballCounts = 0;
				if (outCounts >= MAX_OUTS) {
					outCounts = 0;
					switchingTeams();
					if ((isTopInning) && (currentInning <= maxInning)) {
						currentInning += 1;
					}
				}
			} else {
				strikeCounts += 1;
				if (strikeCounts >= MAX_STRIKES) {
					outCounts += 1;
					ballCounts = 0;
					strikeCounts = 0;
					if (outCounts >= MAX_OUTS) {
						outCounts = 0;
						switchingTeams();
						if ((isTopInning) && (currentInning <= maxInning)) {
							currentInning += 1;
						}
					}
				}
			}
		}
	}

	/**
	 * A method to switch teams and resets the bases and strike, out, and ball
	 * counts.
	 */
	private void switchingTeams() {
		if (gameEnded()) {
			return;
		}
		isTopInning = !isTopInning;
		firstBase = false;
		secondBase = false;
		thirdBase = false;
		outCounts = 0;
		strikeCounts = 0;
		ballCounts = 0;
	}

	/**
	 * Method called to indicate that the batter is out due to a caught fly
	 * 
	 * This method does nothing if the game has ended.
	 */
	public void caughtFly() {
		if (gameEnded()) {

		} else {
			strikeCounts = 0;
			outCounts += 1;
			ballCounts = 0;
			if (outCounts == MAX_OUTS) {
				outCounts = 0;
				switchingTeams();
				if ((isTopInning) && (currentInning <= maxInning)) {
					currentInning += 1;
				}

			}
		}
	}

	/**
	 * Method called to indicate a bad pitch at which the batter did not swing. This
	 * method adds 1 the the batter's count of balls, possibly resulting in a walk.
	 * 
	 * This method does nothing if the game has ended.
	 */
	public void ball() {
		if (gameEnded()) {

		} else {
			ballCounts += 1;
			if (ballCounts == MAX_BALLS) {
				strikeCounts = 0;
				if (!firstBase) {
					firstBase = true;
				} else if (firstBase && !secondBase) {
					secondBase = true;
				} else if (secondBase && !thirdBase) {
					thirdBase = true;
				} else if (thirdBase) {
					if (isTopInning) {
						team0Score += 1;
					} else {
						team1Score += 1;
					}
				}
				ballCounts = 0;
			}
		}
	}

	/**
	 * A helper method for shifting the runners when hit() is true
	 */
	private void runnerShifter() {
		if (thirdBase) {
			if (isTopInning) {
				team0Score += 1;
			} else {
				team1Score += 1;
			}
			thirdBase = false;
		}
		if (secondBase) {
			thirdBase = true;
			secondBase = false;
		}
		if (firstBase) {
			secondBase = true;
			firstBase = false;
		}
	}

	/***
	 * Method called to indicate that the batter hit the ball. The interpretation of
	 * the distance parameter is as follows: less than 15: the hit is a foul and the
	 * batter is immediately out. at least 15, but less than 150: the hit is a
	 * single. An imaginary runner advances to first base, and all other runners on
	 * base advance by 1. If there was a runner on third base, the score increases
	 * by 1. at least 150, but less than 200: the hit is a double. An imaginary
	 * runner advances to second base, and all other runners on base advance by 2.
	 * If there were runners on second or third, the score increases by 1 or 2. at
	 * least 200, but less than 250: the hit is a triple. An imaginary runner
	 * advances to third base, and all other runners on base advance by 3. If there
	 * were runners on first, second, or third, the score is increased by 1, 2, or
	 * 3. at least 250: the hit is a home run. All imaginary runners currently on
	 * base advance to home. The score is increased by 1 plus the number of runners
	 * on base.
	 * 
	 * This method does nothing if the game has ended.
	 * 
	 * @param distance
	 */
	public void hit(int distance) {
		if (gameEnded()) {

		} else {
			ballCounts = 0;
			if (distance < 15) {
				outCounts += 1;
				if (outCounts == MAX_OUTS) {
					outCounts = 0;
					switchingTeams();
					if ((isTopInning) && (currentInning <= maxInning)) {
						currentInning += 1;
					}

				}

			} else if ((15 <= distance) && (distance < 150)) {
				runnerShifter();
				strikeCounts = 0;
				firstBase = true;
			} else if ((150 <= distance) && (distance < 200)) {
				runnerShifter();
				runnerShifter();
				strikeCounts = 0;
				secondBase = true;
			} else if ((200 <= distance) && (distance < 250)) {
				runnerShifter();
				runnerShifter();
				runnerShifter();
				strikeCounts = 0;
				thirdBase = true;
			} else {
				runnerShifter();
				firstBase = true;
				runnerShifter();
				runnerShifter();
				runnerShifter();
				strikeCounts = 0;
			}
		}

	}

	/**
	 * Returns a three-character string representing the players on base, in the
	 * order first, second, and third, where 'X' indicates a player is present and
	 * 'o' indicates no player. For example, the string "XXo" means that there are
	 * players on first and second but not on third.
	 * 
	 * @return three-character string showing players on base
	 */
	public String getBases() {
		return (runnerOnBase(1) ? "X" : "o") + (runnerOnBase(2) ? "X" : "o") + (runnerOnBase(3) ? "X" : "o");
	}

	/**
	 * Returns a one-line string representation of the current game state. The
	 * format is:
	 * 
	 * <pre>
	 *    ooo Inning:1 (T) Score:0-0 Balls:0 Strikes:0 Outs:0
	 * </pre>
	 * 
	 * The first three characters represent the players on base as returned by the
	 * <code>getBases()</code> method. The 'T' after the inning number indicates
	 * it's the top of the inning, and a 'B' would indicate the bottom. The score
	 * always shows team 0 first.
	 * 
	 * @return one-line string representation of the game state
	 */
	public String toString() {
		String bases = getBases();
		String topOrBottom = (isTopOfInning() ? "T" : "B");
		String fmt = "%s Inning:%d (%s) Score:%d-%d Balls:%d Strikes:%d Outs:%d";
		return String.format(fmt, bases, whichInning(), topOrBottom, getTeam0Score(), getTeam1Score(), getBallCount(),
				getCalledStrikes(), getCurrentOuts());
	}

}
