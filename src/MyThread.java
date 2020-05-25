
public class MyThread extends Thread implements Runnable {
    private boolean isRight;
    private int counter = 0;
    private int countSecond = 3;

    public MyThread() {
        this.isRight = true;
    }

    public int getCounter() {
        return counter;
    }

    public int getCountSecond() {
        return countSecond;
    }

    public boolean getIsRight() {
        return isRight;
    }

    public void setCountSecond(int countSecond) {
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
