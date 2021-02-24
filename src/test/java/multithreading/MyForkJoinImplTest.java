package multithreading;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

public class MyForkJoinImplTest {
    private List<Integer> list;

    @Before
    public void initialise() {
        list = new ArrayList<>(5);
    }

    @Test
    public void positiveNumbers() {
        list.add(1);
        list.add(5);
        list.add(4);
        list.add(10);
        list.add(0);
        MyForkJoinImpl forkJoin = new MyForkJoinImpl(list);
        Integer compute = forkJoin.compute();
        Assert.assertEquals(20, compute.intValue());
    }

    @Test
    public void negativeNumbers() {
        list.add(-1);
        list.add(-5);
        list.add(-4);
        list.add(-10);
        list.add(0);
        MyForkJoinImpl forkJoin = new MyForkJoinImpl(list);
        Integer compute = forkJoin.compute();
        Assert.assertEquals(-20, compute.intValue());;
    }

    @Test
    public void positiveAndNegativeNumbers() {
        list.add(-1);
        list.add(-5);
        list.add(-4);
        list.add(10);
        list.add(0);
        MyForkJoinImpl forkJoin = new MyForkJoinImpl(list);
        Integer compute = forkJoin.compute();
        Assert.assertEquals(0, compute.intValue());
    }

    @Test
    public void nullNumbers() {
        MyForkJoinImpl forkJoin = new MyForkJoinImpl(list);
        Integer compute = forkJoin.compute();
        Assert.assertEquals(0, compute.intValue());
    }

    @After
    public void clearList() {
        list.clear();
    }
}
