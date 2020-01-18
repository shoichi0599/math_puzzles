package questions;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class Q06CollatzProblemAlter {
    /**
     * Collatz Problem (コラッツの問題) 改造版<br>
     *
     * コラッツの問題とは
     * <pre>
     *     自然数nについて
     *     - nが偶数の場合、nを2で割る
     *     - nが奇数の場合、nに3をかけて1を足す
     *     という操作を繰り返すとき、初期値がどんな数であっても必ず1に到達する。
     *     例) n = 2
     *     1 -> 4 -> 2 -> 1
     * </pre>
     *
     * Question:
     * <pre>
     *     改造版
     *     初期値が偶数の場合、初回のみnに3をかけて1を足すことから始めることとし、
     *     「最初の数」に戻るパターンを考える
     *     例) n = 2
     *     2 -> 7 -> 22 -> 11 -> 34 -> 17 -> 52 -> 26 -> 13 -> 40 -> 20 -> 10 -> 5 -> 16 -> 8 -> 4 -> 2
     *
     *     例)  n = 6
     *     6 -> 19 -> 58 -> 29 -> 88 -> 44 -> 22 -> 11 -> 34 -> 17 -> 52 -> 26 -> 13 -> 40 -> 20 -> 10
     *     -> 5 -> 16 -> 8 -> 4 -> 2 -> 1 -> 4 -> ...
     *     6にならない
     *
     *
     *     10,000以下の偶数のうち、上記の2や4のような「最初の数に戻る数」が
     *     いくつあるか、その個数を求めよ
     *
     *
     *     ヒント
     * </pre>
     *
     * @param args
     */
    public static void main(String[] args) {
        AtomicInteger count = new AtomicInteger(0);
        IntStream.iterate(2, i -> i < 10_000, i -> i + 2).forEach(i -> {
            var original = i;
            // Since `i` is only even number, `i` times 3 plus 1 at first.
            i = (i * 3) + 1;
            while (true) {
                if (i % 2 == 0) {
                    i = i / 2;
                } else {
                    i = (i * 3) + 1;
                }
                if (i == original) {
                    count.getAndAdd(1);
                    break;
                }
                if (i == 1) {
                    break;
                }
            }
        });
        System.out.println(String.format("Total Count: %s", count));
    }
}
