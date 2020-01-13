package questions;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Q03CardFlipping {

    /**
     * Card Flipping (カードを裏返せ)<br>
     * Question:
     * <pre>
     *     1 ~ 100までの番号が書かれた100枚のカードが順番に並べられています。
     *     最初はすべて裏返し。
     *
     *     ある人が2番のカードから、1枚おきにカードを裏返す。
     *     => 2,4,6,...100番のカードが表向きになる。
     *
     *     別の人が3番のカードから2枚おきにカードを裏返す。
     *     ＊向きのカードは裏向き、裏向きのカードは表向きになる
     *     => 3番のカードは表向き、6番のカードは裏向きになる。
     *
     *     また、別の人が4番目のカードから3枚おきにカードを裏返す。
     *     => 4, 8番のカードは裏向きになる
     *
     *     このようにn番目のカードから(n - 1)枚おきにカードを裏返す操作を、
     *     どのカードの向きも変わらなくなるまで続けたとする。
     *
     *     カードの向きが変わらなくなったとき、裏向きになっているカードの番号を求めてください。
     *
     *     ヒント
     *     カードの状態を配列で表現する。
     *     表向きのカードを「true」,裏向きを「false」とする。
     * </pre>
     *
     * @param args
     */
    public static void main(String[] args) {
        // Set the default values to false as back side
        boolean[] cards = new boolean[100];
        Arrays.fill(cards, false);

        IntStream.rangeClosed(2, 100).forEach(i -> {
            for (int j = i - 1; j < cards.length; j+=i) {
                // j = 1 (2 - 1) => card number 2
                // Second value which is card number 2
                // Set the opposite value of the current value.
                // card number 2 = false => true
                cards[j] = !cards[j];
            }
        });

        // Output the card numbers which are flipped to back side
        IntStream.range(0, 100).forEach(i -> {
            if (!cards[i]) {
                System.out.println(i + 1);
            }
        });
    }

    // My first thought
//    public static void main(String[] args) {
//        HashMap<Integer, Boolean> cardAndSideMap = new HashMap<>() {{
//            IntStream.rangeClosed(1, 100).forEach(i -> put(i, false));
//        }};
//        for (int i = 2; i <= 100; i++) {
//            for (int j = i; j <= 100; j+=i) {
//                Boolean isFront = cardAndSideMap.get(j);
//                if (isFront) {
//                    cardAndSideMap.put(j, false);
//                } else {
//                    cardAndSideMap.put(j, true);
//                }
//                // the code above can be better
//                // reduce if-else
//                // => cardAndSideMap.put(j, !cardAndSideMap.get(j));
//            }
//        }
//        cardAndSideMap.entrySet()
//                .stream()
//                .filter(m -> !m.getValue())
//                .forEach(m -> System.out.println(m.getKey()));
//    }
}
