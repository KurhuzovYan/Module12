import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

public class FizzBuzz {

    int n;
    private Semaphore semFizz = new Semaphore(0);
    private Semaphore semBuzz = new Semaphore(0);
    private Semaphore semFizzBuzz = new Semaphore(0);
    private Semaphore semNumber = new Semaphore(1);

    public FizzBuzz(int counter) {
        this.n = counter;
    }

    private void fizz(Runnable runFizz) throws InterruptedException {
        semFizz.acquire();
        runFizz.run();
        semNumber.release();
    }

    private void buzz(Runnable runBuzz) throws InterruptedException {
        semBuzz.acquire();
        runBuzz.run();
        semNumber.release();
    }

    private void fizzbuzz(Runnable runFizzBuzz) throws InterruptedException {
        semFizzBuzz.acquire();
        runFizzBuzz.run();
        semNumber.release();
    }

    private void number(IntConsumer num) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            semNumber.acquire();
            if (i % 3 == 0 && i % 5 == 0) {
                semFizzBuzz.release();
            } else if (i % 5 == 0) {
                semBuzz.release();
            } else if (i % 3 == 0){
                semFizz.release();
            } else {
                num.accept(i);
                semNumber.release();
            }
        }
    }


    public void start() throws InterruptedException {

        Runnable runFizz = () -> System.out.println("fizz");
        Runnable runBuzz = () -> System.out.println("buzz");
        Runnable runFizzBuzz = () -> System.out.println("fizzbuzz");
        IntConsumer printNum = num -> System.out.print(num);

        Thread threadA = new Thread(() -> {
            try {
                new FizzBuzz(n).fizz(runFizz);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread threadB = new Thread(() -> {
            try {
                new FizzBuzz(n).buzz(runBuzz);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread threadC = new Thread(() -> {
            try {
                new FizzBuzz(n).fizzbuzz(runFizzBuzz);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread threadD = new Thread(() -> {
            try {
                new FizzBuzz(n).number(printNum);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }
}

