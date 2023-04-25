public class SessionTime {

    private static void newThread() {
        Thread thread = new Thread(() -> {
            System.out.println("Прошло 5 секунд");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        thread.start();
    }

    public static void timeFromStartSession() throws InterruptedException {

        for (int time = 1; time < 3600; time++) {
            if (time % 5 == 0) {
                newThread();
            } else {
                System.out.println(time + " сек.");
            }
            Thread.sleep(1000);
        }
    }
}
