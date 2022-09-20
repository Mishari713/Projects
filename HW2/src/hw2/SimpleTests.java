package hw2;
/**
 * Some very simple test cases as described in the assignment pdf...
 */
public class SimpleTests
{
  public static void main(String[] args)
  {
    Corkball game = new Corkball(3);
//    System.out.println(game);
//System.out.println("first inning- first half (T)");
//    game.strike(false);
//    System.out.println(game);  // one strike
//    game.strike(false);
//    System.out.println(game);  // 0 strikes, one out
//    game.strike(false);
//    System.out.println(game);  // one strike, one out
//    game.strike(false);
//    System.out.println(game);  // 0 strike, two outs
//    
//    game.strike(true); // batter is immediately out for swung strike
//    System.out.println(game.isTopOfInning()); // should be false now
//    
//    System.out.println("first inning- second half (B)");
//    System.out.println(game);         // bottom of 1st inning, 0 outs
//    
//    game.strike(true);
//    System.out.println(game);
//    game.strike(true);
//    System.out.println(game);
//    game.strike(true);
//    
//    System.out.println("second inning- first half (T)"); 
//    System.out.println(game);
//    
//    game.strike(true);
//    System.out.println(game);
//    game.strike(true);
//    System.out.println(game);
//    game.strike(true);
//    System.out.println("second inning- second half (B)");
//    System.out.println(game);
//    
//    game.strike(true);
//    System.out.println(game);
//    game.strike(true);
//    System.out.println(game);
//    game.strike(true);
//    System.out.println("third inning- first half (T)");
//    System.out.println(game);
//    
//    game.strike(true);
//    System.out.println(game);
//    game.strike(true);
//    System.out.println(game);
//    game.caughtFly();
//    System.out.println("third inning- second half (B)");
//    System.out.println(game);
//    
//    game.caughtFly();
//    System.out.println(game);
//    game.caughtFly();
//    System.out.println(game);
//    game.caughtFly();
//    System.out.println("Game ended!");
//    System.out.println(game.whichInning());
//    System.out.println(game.gameEnded());
//    
//    game.caughtFly();
//    System.out.println(game);
//    game.caughtFly();
//    System.out.println(game);
//    game.caughtFly();
//    System.out.println(game);
//    System.out.println(game.whichInning());
//    System.out.println(game.gameEnded());
    
    //Below lines (70-74) doesn's work which is good but need to check if it's fine.
//    game.strike(true);
//    System.out.println(game);
//    game.strike(true);
//    System.out.println(game);
//    game.strike(true);
//    System.out.println(game.whichInning());
//    System.out.println(game.gameEnded());
//    System.out.println(game.whichInning());
    
	  
	  
    // try some hits, look at the bases
//    game = new Corkball(3);
//    game.hit(15);
//    System.out.println(game.runnerOnBase(1)); // true
//    System.out.println(game.getBases());      // Xoo    
//    game.hit(15);
//    System.out.println(game.getBases());      // XXo   
//    game.hit(15);
//    System.out.println(game.getBases());      // XXX
//    game.hit(15);
//    System.out.println(game.getBases());      // XXX
//    System.out.println(game.getTeam0Score()); // 1
//   
//    // try hitting a double now
//    game.hit(150);
//    System.out.println(game.getBases());      // oXX
//    System.out.println(game.getTeam0Score()); // 3
    
    // try counting balls
//    game = new Corkball(3);
//    game.ball();
//    System.out.println(game.getBallCount()); // 1
//    game.ball();
//    System.out.println(game.getBallCount()); // 2
//    game.ball();
//    System.out.println(game.getBallCount()); // 3
//    game.strike(true);
//    System.out.println(game.getBallCount()); // 0, since it's a new batter
//    
    // effect of a walk on the runners on base
//    game = new Corkball(3);
//    game.hit(225);  // a triple
//    System.out.println(game.getBases());  // ooX   
//    game.ball();
//    game.ball();
//    game.ball();
//    game.ball();
//    System.out.println(game.getBallCount()); // 4
//    game.ball();  //  a walk 
//    System.out.println(game.getBases());  // XoX 

//    game = new Corkball(3);
//    game.hit(250);
//    System.out.println(game.getBases());
//    game.hit(150);
//    System.out.println(game.getBases());
//    game.hit(200);
//    System.out.println(game.getTeam0Score() + " " + game.getTeam1Score());
//    System.out.println(game.getBases());
//    System.out.println(game.isTopOfInning());
//    game.strike(true);
//    game.strike(true);
//    game.strike(true);
//    
//    System.out.println(game.getTeam0Score() + " " + game.getTeam1Score() + " 2 0");
//    System.out.println(game.isTopOfInning());
//
//    System.out.println(game.getTeam0Score() + " " + game.getTeam1Score());
//    game.hit(250);
//
//    System.out.println(game.getTeam0Score() + " " + game.getTeam1Score());
//    System.out.println(game.getBases());
//
//    System.out.println(game.getTeam0Score() + " " + game.getTeam1Score());
    
//    game = new Corkball(3);
//    game.hit(250);
//    game.hit(10);
//    game.hit(10);
//    game.ball();
//    game.ball();
//    game.hit(150);
//    System.out.println(game.isTopOfInning());
//    game.ball();
//    game.ball();
//    game.ball();
//    game.strike(false);
//    game.hit(200);
//    System.out.println(game.isTopOfInning());
//    game.ball();
//    System.out.println(game.isTopOfInning());
//    game.ball();
//    System.out.println(game.isTopOfInning());
//    game.strike(false);
//    System.out.println(game.isTopOfInning());
//    game.strike(true);
//    System.out.println(game.isTopOfInning());
    
    
    game = new Corkball(3);
    game.hit(150);
    game.hit(100);
    game.ball();
    game.ball();
    System.out.println(game.getCurrentOuts());
    game.strike(false);
    System.out.println(game.getCurrentOuts());
    game.ball();
    game.ball();
    game.ball();
    System.out.println(game.getCurrentOuts());
    game.strike(false);
    System.out.println(game.getCurrentOuts());
    game.ball();
    game.ball();
    System.out.println(game.getCurrentOuts());
    game.strike(false);
    game.strike(false);
    System.out.println(game.getCurrentOuts());
    game.ball();
    game.ball();
    game.ball();
    game.hit(200);
    game.ball();
    System.out.println(game.isTopOfInning());
    game.caughtFly();
    System.out.println(game.isTopOfInning());
    game.strike(false);
    
    game.ball();
    game.strike(false);
    System.out.println(game.isTopOfInning());
  }
}
