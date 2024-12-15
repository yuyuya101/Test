public class CommunicationObject {
    private int number = 1;
    private boolean numberTurn = true;

    public synchronized void printNumber() throws InterruptedException {
        while (number <= 13) {
            while (!numberTurn) {
                wait();
            }
            System.out.print(number);
            numberTurn = false;
            notifyAll(); // 通知其他线程数字已打印
            number++;
        }
    }

    public synchronized void printLetters() throws InterruptedException {
        char letter = 'A';
        while (letter <= 'Z') {
            while (numberTurn) {
                if (number > 13) {
                    break; // 如果数字超过13，退出循环
                }
                wait();
            }
            System.out.print(letter);
            letter++;
            if (letter <= 'Z') {
                System.out.print((char) (letter)); // 再次打印下一个字母，形成AB、CD等配对。
                letter++;
            }
            numberTurn = true;
            notifyAll(); // 通知其他线程字母已打印
        }
    }
}

class NumberPrinter implements Runnable {
    private CommunicationObject commObj;

    public NumberPrinter(CommunicationObject commObj) {
        this.commObj = commObj;
    }

    @Override
    public void run() {
        try {
            commObj.printNumber();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

class LetterPrinter implements Runnable {
    private CommunicationObject commObj;

    public LetterPrinter(CommunicationObject commObj) {
        this.commObj = commObj;
    }

    @Override
    public void run() {
        try {
            commObj.printLetters();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

