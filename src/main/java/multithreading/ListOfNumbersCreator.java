package multithreading;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class ListOfNumbersCreator {
    public static List<Integer> getGenerateList(int size) {
        List<Integer> list = new ArrayList<>(size);
        IntStream.range(1, size)
                .forEach(list::add);
        return list;
    }
}
