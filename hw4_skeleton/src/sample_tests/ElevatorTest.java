package sample_tests;
import hw4.Lurker;
import hw4.Missile;
import hw4.Platform;
import hw4.Elevator;
public class ElevatorTest {

public static void main(String[] args) {
Elevator e = new Elevator(50, 200, 10, 10);
e.setBounds(190,220);
e.setVelocity(0, 3);

Lurker l = new Lurker(5,5,2);
l.setVelocity(2,0);
e.addChild(l);
System.out.println(l.getXReal() + ", " + (l.getXReal() + 5)); //Expected: 52, 57
System.out.println(e.getXReal() + ", " + e.getYReal()); //Expected: 50, 200
System.out.println();

e.update();
System.out.println("Bounds: " + l.getMin() + ", " + l.getMax()); //Expected: 50, 60
System.out.println(l.getXReal() + ", " + l.getYReal()); //Expected: 54, 198
System.out.println(e.getXReal() + ", " + e.getYReal()); //Expected: 50, 203
System.out.println();

e.update();
System.out.println("Bounds: " + l.getMin() + ", " + l.getMax()); //Expected: 50, 60
System.out.println(l.getXReal() + ", " + l.getYReal()); //Expected: 55, 201
System.out.println("Velocity: " + l.getDeltaX()); //Expected: -2
}

}
