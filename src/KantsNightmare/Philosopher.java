package KantsNightmare;
import java.util.Random;
import java.util.concurrent.locks.*;

public class Philosopher implements Runnable{

    int philosopher;
    //All philosophers have access to the same forks, but this is the resource to be shared over time
    static Lock[] forks;
    int meatballs;

    public Philosopher(int n){
        philosopher = n;
        meatballs = 5;
        forks = new Lock[5];
        for(int i = 0; i < 5; i++){
            forks[i] = new ReentrantLock();
        }
    }


    @Override
    public void run() {
        while(meatballs > 0) {
            //Fork for left hand correlates to the fork in array at out current philosopher number:
            //Note that if a philosopher picks up a fork...
            if(forks[philosopher].tryLock()) {
                //...it will print
                //But if another philosopher tries to pick up right, it will not, but the initial
                //one will relinquish left since it won't have access to right
                System.out.println(philosopher + " Using left fork " + philosopher);
                //then plus one for right hand fork
                if (forks[(philosopher + 1) % 5].tryLock()) {
                    System.out.println("Using right fork " + (philosopher + 1) % 5);
                    //eata da meatball
                    meatballs--;
                    System.out.println("Philosopher " + philosopher + " has " + meatballs + " meatballs left...");
                    //relinquish the fork
                    //System.out.println("Relinquishing right fork " + (philosopher + 1) % 5);
                    forks[(philosopher + 1) % 5].unlock();
                }
                System.out.println("Relinquish left fork " + philosopher);
                forks[philosopher].unlock();
            }
            //Existential moaning
            //But at random intervals...even better! Since not everybody is at the same place at the same time...
            Random rand = new Random();
            try {
                Thread.sleep(rand.nextInt(20));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
