package ro.ase.acs.main;

import ro.ase.acs.classes.SyncedThread;
import ro.ase.acs.classes.UnsyncedThread;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
//        UnsyncedThread t1=new UnsyncedThread();
//        t1.start();
//
//        UnsyncedThread t2=new UnsyncedThread();
//        t2.start();
        SyncedThread t3 = new SyncedThread();
        t3.setName("T3");
        new Thread(t3).start();

        SyncedThread t4 = new SyncedThread();
        t4.setName("T4");
        new Thread(t4).start();


        new Thread(()->
                System.out.println("Message from another thread!")).start();

        List<Integer> list = new ArrayList<>();
        for(int i=0; i<1000; i++){
            list.add(i+1);
        }
        long result = list.parallelStream().mapToInt(i->i).sum();
        System.out.println(result);
        System.out.println("Main ended");
    }
}