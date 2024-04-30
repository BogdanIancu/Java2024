package ro.ase.acs.main;

import ro.ase.acs.classes.SummingCallable;
import ro.ase.acs.classes.SummingThread;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        int[] array = new int[200_000_000];

        for (int i = 0; i < array.length; i++) {
            array[i] = i + 1;
        }

        long startTime = System.currentTimeMillis();
        long sum = 0;
        for (int i = 0; i < array.length; i++) {
            sum += array[i];
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Single thread sum = " + sum +
                " computed in " + (endTime - startTime) + " ms");

        final int NO_OF_THREADS = 4;
        startTime = System.currentTimeMillis();
        sum = 0;
        SummingThread[] threads = new SummingThread[NO_OF_THREADS];
        for (int i = 0; i < NO_OF_THREADS; i++) {
            threads[i] = new SummingThread(
                    array.length / NO_OF_THREADS * i,
                    array.length / NO_OF_THREADS * (i + 1), array);
            threads[i].start();
        }

        for (int i = 0; i < NO_OF_THREADS; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            sum += threads[i].getSum();
        }
        endTime = System.currentTimeMillis();
        System.out.println("Thread array sum = " + sum +
                " computed in " + (endTime - startTime) + " ms");

        sum = 0;
        startTime = System.currentTimeMillis();
        ExecutorService threadPool = Executors.newFixedThreadPool(NO_OF_THREADS);
        threads = new SummingThread[NO_OF_THREADS];
        for (int i = 0; i < NO_OF_THREADS; i++) {
            threads[i] = new SummingThread(
                    array.length / NO_OF_THREADS * i,
                    array.length / NO_OF_THREADS * (i + 1), array);
            threadPool.submit(threads[i]);
        }
        threadPool.shutdown();
        try {
            threadPool.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        for (int i = 0; i < NO_OF_THREADS; i++) {
            sum += threads[i].getSum();
        }
        endTime = System.currentTimeMillis();
        System.out.println("Thread pool sum = " + sum +
                " computed in " + (endTime - startTime) + " ms");

        sum = 0;
        startTime = System.currentTimeMillis();
        threadPool = Executors.newFixedThreadPool(NO_OF_THREADS);
        List<Future<Long>> results = new ArrayList<>();
        for (int i = 0; i < NO_OF_THREADS; i++) {
            SummingCallable callable =
                    new SummingCallable(array.length / NO_OF_THREADS * i,
                            array.length / NO_OF_THREADS * (i + 1), array);
            Future<Long> future = threadPool.submit(callable);
            results.add(future);
        }
        threadPool.shutdown();
        try {
            threadPool.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        for (Future<Long> f : results) {
            try {
                sum += f.get();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        }
        endTime = System.currentTimeMillis();
        System.out.println("Callable sum = " + sum +
                " computed in " + (endTime - startTime) + " ms");

        startTime = System.currentTimeMillis();
        sum = Arrays.stream(array).parallel().mapToLong(x -> x).sum();
        endTime = System.currentTimeMillis();
        System.out.println("Parallel stream sum = " + sum +
                " computed in " + (endTime - startTime) + " ms");
    }
}