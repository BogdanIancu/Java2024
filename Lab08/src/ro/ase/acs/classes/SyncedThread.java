package ro.ase.acs.classes;

public class SyncedThread implements Runnable {
    private static int a=0;
    private static int b=0;
    private static Object lock = new Object();
    private String name;

    @Override
    public void run() {
        for (int i=0;i<3;i++) {
            synchronized (lock){
                System.out.println(name + ": a= " + a + " b= " + b);
                a++;

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                b++;
            }

        }
    }

    public void setName(String name) {
        this.name = name;
    }
}
