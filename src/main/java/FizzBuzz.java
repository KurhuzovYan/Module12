import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FizzBuzz {
    private int n;
    private int current;
    private Lock lock = new ReentrantLock();

    public FizzBuzz(int n) {
        this.n = n;
        this.current = 1;
    }

    private void fizz() {
        while (current < n) {
            lock.lock();
            if (current % 3 == 0 && current % 5 != 0) {
                printAndIncrement("fizz");
            }
            lock.unlock();
        }
    }

    private void buzz() {
        while (current < n) {
            lock.lock();
            if (current % 3 != 0 && current % 5 == 0) {
                printAndIncrement("buzz");
            }
            lock.unlock();
        }
    }

    private void fizzBuzz() {
        while (current < n) {
            lock.lock();
            if (current % 3 == 0 && current % 5 == 0) {
                printAndIncrement("fizzbuzz");
            }
            lock.unlock();
        }
    }

    private void number() {
        while (current < n) {
            lock.lock();
            if (current % 3 != 0 && current % 5 != 0) {
                printAndIncrement(String.valueOf(current));
            }
            lock.unlock();
        }
    }

    private void printAndIncrement(String str) {
        System.out.print(str);
        if (current != n) System.out.print(", ");
        current++;
    }

    public void start() {
        FizzBuzz fizzBuzz = new FizzBuzz(n);
        ExecutorService executor = Executors.newFixedThreadPool(4);

        executor.execute(() -> fizzBuzz.fizz());
        executor.execute(() -> fizzBuzz.buzz());
        executor.execute(() -> fizzBuzz.fizzBuzz());
        executor.execute(() -> fizzBuzz.number());
        executor.shutdown();
    }
}
