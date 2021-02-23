package multithreading;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        MyExecutorServiceImpl executorService = new MyExecutorServiceImpl(getGenerateList(100));
        System.out.println("executorService " + executorService.getSum());

        MyForkJoinImpl forkJoin = new MyForkJoinImpl(getGenerateList(100));
        System.out.println("forkJoin " + forkJoin.compute());
    }

    private static List<Integer> getGenerateList(int size) {
        List<Integer> list = new ArrayList<>(size);
        IntStream.range(1, size)
                .forEach(list::add);
        return list;
    }
}
