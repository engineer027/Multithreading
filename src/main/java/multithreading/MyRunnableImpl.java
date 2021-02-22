package multithreading;

import org.apache.log4j.Logger;

public class MyRunnableImpl implements Runnable {
    private static final Logger logger = Logger.getLogger(MyRunnableImpl.class);
    private final Counter counter;

    public MyRunnableImpl(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        while (counter.getCounter() < 100) {
            counter.increment();
            logger.info(Thread.currentThread().getName() + " : " + counter.getCounter());
        }
    }
}
