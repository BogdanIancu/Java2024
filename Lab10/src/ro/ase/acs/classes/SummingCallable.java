package ro.ase.acs.classes;

import java.util.concurrent.Callable;

public class SummingCallable implements Callable<Long> {
    private int startIndex;
    private int endIndex;
    private int[] array;

    public SummingCallable(int startIndex, int endIndex, int[] array) {
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.array = array;
    }

    @Override
    public Long call() throws Exception {
        long sum = 0;
        for(int i=startIndex; i<endIndex; i++){
            sum += array[i];
        }
        return sum;
    }
}
