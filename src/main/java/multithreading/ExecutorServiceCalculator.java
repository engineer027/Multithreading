package multithreading;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorServiceCalculator {
    private static final int SUB_LIST_SIZE = 10;
    private List<Integer> list;

    public ExecutorServiceCalculator(List<Integer> list) {
        this.list = list;
    }

    private List<Callable<Integer>> getCallables(List<Integer> list) {
        List<Callable<Integer>> parts = new ArrayList<>();
        for (int i = 0; i < list.size(); i += SUB_LIST_SIZE) {
            parts.add(new SumCalculatorCallable(new ArrayList<>(list.subList(i,
                    Math.min(list.size(), i + SUB_LIST_SIZE)))));

        }
        return parts;
    }

    public Integer getSum(int thread) {
        ExecutorService executorService = Executors.newFixedThreadPool(thread);
        List<Callable<Integer>> callables = getCallables(list);
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