public class Main {
    public static void main(String[] args) throws InterruptedException {

//        SessionTime.timeFromStartSession();
//        System.out.println(FizzBuzzExample.checkDigit(15));

        FizzBuzz fb = new FizzBuzz(15);
        fb.start();
    }
}