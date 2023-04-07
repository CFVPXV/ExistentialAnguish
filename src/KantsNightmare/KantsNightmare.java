package KantsNightmare;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class KantsNightmare {


    public static void main(String[] args){



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
