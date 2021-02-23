package multithreading;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class MyForkJoinImpl extends RecursiveTask<Integer> {
    private static final int SUB_LIST_SIZE = 10;
    private static final int THRESHOLD = 1000000;
    private List<Integer> list;

    public MyForkJoinImpl(List<Integer> list) {
        this.list = list;
    }

    @Override
    protected Integer compute() {
        if (list.size() > THRESHOLD) {
            return ForkJoinTask.invokeAll(createSubtasks(list)).stream()
                    .mapToInt(ForkJoinTask::join)
                    .sum();
        } else {
            return processing();
        }
    }

    private Collection<MyForkJoinImpl> createSubtasks(List<Integer> list) {
        List<MyForkJoinImpl> dividedTasks = new ArrayList<>();
        for (int i = 0; i < list.size(); i += SUB_LIST_SIZE) {
            dividedTasks.add(new MyForkJoinImpl(new ArrayList<>(list.subList(i,
                    Math.min(list.size(), i + SUB_LIST_SIZE)))));
        }
        return dividedTasks;
    }

    private Integer processing() {
        return list.stream()
                .mapToInt(Integer::valueOf)
                .sum();
    }
}
