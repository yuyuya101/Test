/**
 * 2022150203 俞炜林
 */
public class CollatzConjecture {

    public static void main(String[] args) {
        for (int i = 1; i <= 1024; i++) {
            if (!collatz(i)) {
                System.out.println("Number " + i + " does not satisfy the Collatz Conjecture.");
            }
        }
    }

    private static boolean collatz(int n) {
        while (n != 1) {
            if (n % 2 == 0) {
                n /= 2;
            } else {
                n = 3 * n + 1;
            }
            // 如果n超过了1024，说明这个数不满足卡拉兹猜想  1234567
            if (n > 1024) {
                return false;
            }
        }
        return true;
    }
}