package questions;

public class Q01Palindrome {

    /**
     * Palindrome (回文)<br>
     * Question:
     * <pre>
     *     10進数、2進数、8進数のいずれで表現しても回文数となる数のうち
     *     10進数の10以上で最小の値を求めてください。
     *     例) 9 (10進数) = 1001 (2進数) = 11 (8進数)
     *
     *     ヒント
     *     2進数で0で始まる数字はないので、左・右端は1となる。
     *     2進数で右端が1の場合必ず奇数となる。
     *     つまり奇数だけを探せば良い。
     * </pre>
     *
     * @param args
     */
    public static void main(String[] args) {
        int num = 11;
        while (true) {
            String s = Integer.toString(num);
            String bs = Integer.toBinaryString(num);
            String os = Integer.toOctalString(num);
            System.out.println(String.format("10進数: %s, 2進数: %s, 8進数: %s", s, bs, os));
            System.out.println();
            if (s.equals(reverse(s))
                    && bs.equals(reverse(bs))
                    && os.equals(reverse(os))
            ) {
                System.out.println("-----------------------------------------------------------------");
                System.out.println("Result: " + num);
                System.out.println("-----------------------------------------------------------------");
                break;
            }
            num += 2;
        }
    }
    private static String reverse(String s) {
        return new StringBuffer(s).reverse().toString();
    }
}
