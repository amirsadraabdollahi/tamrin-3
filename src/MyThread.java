
public class MyThread extends Thread implements Runnable {
        private int countSecond = 3;

    public MyThread() {
    }

    public int getCountSecond() {
        return countSecond;
    }

    public void increaseCountSecond(int countSecond) {
        this.countSecond += countSecond;
    }

    @Override
    public void run() {
        super.run();
        while (true) {
            try {
                Thread.sleep(10);
                countSecond += 1;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
