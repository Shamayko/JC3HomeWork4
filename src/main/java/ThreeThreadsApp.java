public class ThreeThreadsApp {
    private final Object monitor = new Object();
    private volatile char currentChar = 'A';

    public static void main(String[] args) {

        ThreeThreadsApp tta = new ThreeThreadsApp();


        Thread threadA = new Thread(() -> tta.printA());

        Thread threadB = new Thread(() -> tta.printB());

        Thread threadC = new Thread(() -> tta.printC());

        threadA.start();
        threadB.start();
        threadC.start();

    }

    public void printA() {
        synchronized (monitor) {
            for (int i = 0; i < 5; i++) {
                while (currentChar != 'A') {
                    try {
                        monitor.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.print('A');
                currentChar = 'B';
                monitor.notifyAll();
            }

        }
    }

    public void printB() {
        synchronized (monitor) {
            for (int i = 0; i < 5; i++) {
                while (currentChar != 'B') {
                    try {
                        monitor.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.print('B');
                currentChar = 'C';
                monitor.notifyAll();
            }
        }
    }

    public void printC() {
        synchronized (monitor) {
            for (int i = 0; i < 5; i++) {
                while (currentChar != 'C') {
                    try {
                        monitor.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println('C');
                currentChar = 'A';
                monitor.notifyAll();
            }
        }
    }

}
