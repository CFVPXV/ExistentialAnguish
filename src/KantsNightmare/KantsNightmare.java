package KantsNightmare;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class KantsNightmare {


    public static void main(String[] args){

        /*ExecutorService ex = Executors.newFixedThreadPool(5);
        for(int i = 0; i < 5; i++){
            ex.execute(new Philosopher(i));
        }
        ex.shutdown();*/
        //One way...
        /*Philosopher philo1 = new Philosopher(0);
        Philosopher philo2 = new Philosopher(1);
        Philosopher philo3 = new Philosopher(2);
        Philosopher philo4 = new Philosopher(3);
        Philosopher philo5 = new Philosopher(4);

        Thread t1 = new Thread(philo1);
        Thread t2 = new Thread(philo2);
        Thread t3 = new Thread(philo3);
        Thread t4 = new Thread(philo4);
        Thread t5 = new Thread(philo5);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();*/

        Thread[] tp = new Thread[5];
        for(int i = 0; i < 5; i++){
            tp[i] = new Thread(new Philosopher(i));
        }
        for(int i = 0; i < 5; i++){
            tp[i].start();
        }
        for(int i = 0; i < 5; i++){
            try {
                tp[i].join();
            } catch (InterruptedException e) {
                System.out.println("Exception " + e.getMessage());
            }
        }

    }
}
