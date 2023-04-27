import java.util.concurrent.*;

public class SessionTime {
    private static void timer(boolean isFiveSecCounter) {
        for (int i = 1; i < Integer.MAX_VALUE; i++) {
            if (isFiveSecCounter) {
                if (i % 5 == 0) {
                    System.out.println("Прошло 5 секун");
                }
            } else {
                if (i % 5 != 0) {
                    System.out.println(i + " сек.");
                }
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void timeFromStartSession() {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        executor.execute(() -> timer(false));
        executor.execute(() -> timer( true));
    }
}


