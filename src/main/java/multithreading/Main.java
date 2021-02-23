package multithreading;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        MyCallableImpl callable = new MyCallableImpl(getGenerateList(1000000));
        System.out.println("executorService " + callable.getSum());

        MyForkJoinImpl forkJoin = new MyForkJoinImpl(getGenerateList(1000000));
        System.out.println("forkJoin " + forkJoin.compute());
    }

    private static List<Integer> getGenerateList(int size) {
        List<Integer> list = new ArrayList<>(size);
        IntStream.range(1, size)
                .forEach(list::add);
        return list;
    }
}
