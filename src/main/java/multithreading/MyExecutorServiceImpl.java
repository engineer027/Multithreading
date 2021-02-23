package multithreading;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class MyExecutorServiceImpl {
    private static final int SUB_LIST_SIZE = 10;
    private List<Integer> list;

    public MyExecutorServiceImpl(List<Integer> list) {
        this.list = list;
    }

    private List<List<Integer>> getSublist(List<Integer> list) {
        List<List<Integer>> parts = new ArrayList<>();
        for(int i = 0; i < list.size(); i += SUB_LIST_SIZE) {
            parts.add(new ArrayList<>(list.subList(i, Math.min(list.size(), i + SUB_LIST_SIZE))));

        }
        return parts;
    }

    public Integer getSum() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        List<List<Integer>> lists = getSublist(list);
        List<Callable<Integer>> callables = new ArrayList<>(SUB_LIST_SIZE);
        for (int i = 0; i < SUB_LIST_SIZE; i++) {
            callables.add(new MyCallableImpl(lists.get(i)));
        }
        Integer sum = 0;
        List<Future<Integer>> futures = null;
        try {
            futures = executorService.invokeAll(callables);
            executorService.shutdown();
            for (int i = 0; i < futures.size(); i++) {
                sum = sum + futures.get(i).get();
            }

        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Something happened and we can't calculate the amount", e);
        }
        return sum;
    }
}
