package multithreading;

import java.util.List;
import java.util.concurrent.Callable;

public class MyCallableImpl implements Callable<Integer> {
    private List<Integer> list;

    public MyCallableImpl(List<Integer> list) {
        this.list = list;
    }

    @Override
    public Integer call() throws Exception {
        return list.stream()
                .mapToInt(i -> i)
                .sum();
    }
}
