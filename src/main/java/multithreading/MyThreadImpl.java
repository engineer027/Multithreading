package multithreading;

import org.apache.log4j.Logger;

public class MyThreadImpl extends Thread {
    private static final Logger logger = Logger.getLogger(MyThreadImpl.class);
    private final Counter counter;

    public MyThreadImpl(Counter counter) {
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
