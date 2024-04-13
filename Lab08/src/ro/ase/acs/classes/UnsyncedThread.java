package ro.ase.acs.classes;

public class UnsyncedThread extends Thread{
    private static int a=0;
    private static int b=0;

    @Override
    public void run() {
        super.run();
        for (int i=0;i<3;i++) {
            System.out.println("a= " + a + " b= " + b);
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
