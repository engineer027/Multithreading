package multithreading;

public class Main {
    public static void main(String[] args) {
        ExecutorServiceCalculator executorServiceCalculator =
                new ExecutorServiceCalculator(ListOfNumbersCreator.getGenerateList(1000000));
        System.out.println("executorService " + executorServiceCalculator.getSum(100));

        MyForkJoinImpl forkJoin = new MyForkJoinImpl(ListOfNumbersCreator.getGenerateList(1000000));
        System.out.println("forkJoin " + forkJoin.compute());
    }
}
