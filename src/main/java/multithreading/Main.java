package multithreading;

public class Main {
    public static void main(String[] args) {
        Counter counter = new Counter();
        Thread myThread = new MyThreadImpl(counter);
        Thread myRunnable = new Thread(new MyRunnableImpl(counter));
        myThread.start();
        myRunnable.start();
    }
}
