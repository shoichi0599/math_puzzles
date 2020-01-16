package questions;

public class Q04WoodCut {

    /**
     * Wood Cut (棒の切り分け)<br>
     * Question:
     * <pre>
     *     長さn[cm]の1本の棒を1[cm]単位に切り分けるとする。
     *     ただし、1本の棒を一度に切ることができるのは1人だけ。
     *     切り分けられた棒が3本あれば、同時に3人で切ることができる。
     *     最大m人の人がいるとき、最短何回で切り分けられるか求めよ。
     *     例) n = 8, m = 3の場合は、4回で切り分けられる
     *     8cmの棒を3人で1cm単位に切り分ける
     *     1回目 => People 1: 4cm - 4cm
     *     2回目 => People 2: 2cm - 2cm - 2cm - 2cm
     *     3回目 => People 3: 1cm - 1cm - 1cm - 1cm - 1cm - 1cm - 2cm
     *     4回目 => People 1: 1cm - 1cm - 1cm - 1cm - 1cm - 1cm - 1cm - 1cm
     *
     *     #1
     *     n = 20, m = 3 のとき最短の回数を求めよ
     *     20cmの棒を3人で切り分ける
     *
     *     #2
     *     n = 100, m = 5 のとき最短の回数を求めよ
     *     100cmの棒を5人で切り分ける
     *
     *     ヒント
     *     再帰を使う。
     *     切った棒のそれぞれに対してさらに切手いくイメージ。
     * </pre>
     *
     * @param args
     */
    public static void main(String[] args) {
        int length = 20;
        int peopleCount = 3;
        int woodCount = 1;
        System.out.println(cutWoodRecursively(length, peopleCount, woodCount));

        length = 100;
        peopleCount = 5;
        woodCount = 1;
        System.out.println(cutWoodRecursively(length, peopleCount, woodCount));

        // Different solution
        System.out.println(assembleWood(length, peopleCount));
    }

    private static int cutWoodRecursively(int woodLength, int peopleCount, int woodCount) {
        // First condition
        // - woodLength = 20
        // - peopleCount = 3
        // - woodCount = 1

        // 1st: 1 wood >= 20cm
        // 2nd: 2 wood >= 20cm
        if (woodCount >= woodLength) {
            return 0;
        }
        // 1st: 1 wood < 3 people
        // 2nd: 2 wood < 3 people
        if (woodCount < peopleCount) {
            // 1st: woodCount(2) = woodCount(1) * 2
            // 2nd: woodCount(4) = woodCount(2) * 2
            return 1 + cutWoodRecursively(woodLength, peopleCount, woodCount * 2);
        }
        // 3rd: 4 wood < 3 people
        //      woodCount(7) = woodCount(4) + 3
        // 4th: 7 wood < 3 people
        //      woodCount(10) = woodCount(7) + 3
        return 1 + cutWoodRecursively(woodLength, peopleCount, woodCount + peopleCount);
    }

    private static int assembleWood(int woodLength, int peopleCount) {
        int count = 0;
        int currentLength = 1;
        while (woodLength > currentLength) {
            if (currentLength < peopleCount) {
                currentLength += currentLength;
            } else {
                currentLength += peopleCount;
            }
            count++;
        }
        return count;
    }
}
