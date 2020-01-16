package questions;

import java.util.ArrayDeque;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class Q05Combination {

    private static int totalCount = 0;
    /**
     * Combination (組合わせ)<br>
     * Question:
     * <pre>
     *     バスに設置されている両替機の話。
     *     この機械では、10円玉、50円玉、100円玉、500円玉の組み合わせに両顎可能。
     *     最大で15枚になるように両替する。
     *     例) 1000円を両替するときに「10円玉を100枚」にするのはできない。
     *
     *     1000円札を入れたときに出てくる効果の組み合わせは何通りあるかを求めよ。
     *     硬貨の順番を区別しないものとします。
     *
     *     ヒント
     * </pre>
     *
     * @param args
     */
    public static void main(String[] args) {
        AtomicInteger count = new AtomicInteger();
        // 500円は0~2枚
        IntStream.rangeClosed(0, 2).forEach(coin500 -> {
            // 100円は0~10枚
            IntStream.rangeClosed(0, 10).forEach(coin100 -> {
                // 50円玉は0~15枚
                IntStream.rangeClosed(0, 15).forEach(coin50 -> {
                    // 10円は0~15枚
                    IntStream.rangeClosed(0, 15).forEach(coin10 -> {
                        if ((coin500 + coin100 + coin50 + coin10) <= 15) {
                            if ((coin500 * 500) + (coin100 * 100) + (coin50 * 50) + (coin10 * 10) == 1000) {
                                count.getAndIncrement();
                            }
                        }
                    });
                });
            });
        });
        System.out.println(count);

        // ------------------------------------------------------
        // Recursive way
        ArrayDeque<Integer> coins = new ArrayDeque<>() {{
            this.add(500);
            this.add(100);
            this.add(50);
            this.add(10);
        }};
        calcRecursively(1_000, coins, 15);
        System.out.println(totalCount);
    }

    private static void calcRecursively(int targetAmount, ArrayDeque<Integer> coins, int limitCount) {
        Integer coin = coins.pollFirst();
        if (coins.size() == 0) {
            if ((targetAmount / coin) <= limitCount) {
                totalCount += 1;
            }
        } else {
            // (targetAmount / coin) => 1000 / 500 = 2
            IntStream.rangeClosed(0, (targetAmount / coin)).forEach(coinCount ->
                calcRecursively(targetAmount - coin * coinCount, coins.clone(), limitCount - coinCount)
            );
        }
    }
}
