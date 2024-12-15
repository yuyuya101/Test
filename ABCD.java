/**
 * 2022150203 俞炜林
 */
public class Main {
    public static void main(String[] args) {
        CommunicationObject commObj = new CommunicationObject();

        Thread numberThread = new Thread(new NumberPrinter(commObj));
        Thread letterThread = new Thread(new LetterPrinter(commObj));

        numberThread.start();
        letterThread.start();
    }
}