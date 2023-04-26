public class SessionTime {

    public static void timeFromStartSession() throws InterruptedException {
        Thread thread = new Thread(() -> System.out.println("Прошло 5 секунд"));

        for (int time = 1; time < 3600; time++) {
            if (time % 5 == 0) {
                System.out.println(thread.getName());
                thread.run();
            } else {
                System.out.println(time + " сек.");
            }
            Thread.sleep(1000);
        }
        thread.start();
    }
}
