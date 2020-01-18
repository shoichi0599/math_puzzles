package questions;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import utils.Strings;

public class Q07DateToBinaryNumber {

    private static DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");
    private static DateTimeFormatter DATE_FORMATTER_JP = DateTimeFormatter.ofPattern("yyyy年MM月dd日");

    /**
     * Date to Binary Number (日付の2進数変換)<br>
     *
     * Question:
     * <pre>
     *     年月日をYYYYMMDDの8桁の整数で表したとき、
     *     これを2進数に変換し逆から並べ、
     *     さらに10進数にしたとき、元の日付と同じ日付になるものを探してください。
     *     期間は"1964年10月10日 ~ 2020年7月24日"とする
     *
     *     例) 1966年7月13日の場合
     *     # 1: YYYYMMDD -> 19660713
     *     # 2: 2進数に変換      -> 1001100001001001110001001
     *     # 3: 2進数を逆に並べる -> 1001010111111111110101001
     *     # 4: 逆に並べた2進数を10進数に戻す -> 19660713
     *
     *
     *     ヒント
     * </pre>
     *
     * @param args
     */
    public static void main(String[] args) {

        var startDate = LocalDate.of(1964, 10, 10);
        var endDate = LocalDate.of(2020, 7, 24);

        startDate.datesUntil(endDate).forEach(date -> {
            // 1. Format to yyyyMMdd
            var dateString = DATE_FORMATTER.format(date);

            // 2. Convert to binary number
            String binary = Integer.toBinaryString(Integer.parseInt(dateString));

            // 3. Reverse the number
            String binaryReversed = Strings.reverse(binary);

            if (binary.equals(binaryReversed)) {
                System.out.println(date.format(DATE_FORMATTER_JP));
            }
        });

        System.out.println("\n関数型プログラミング. *遅い、ケースbyケースだが一般的に宣言的でわかりやすいとされる");
        startDate.datesUntil(endDate)
                .map(date -> DATE_FORMATTER.format(date))
                .map(dateString -> Integer.toBinaryString(Integer.parseInt(dateString)))
                .filter(binaryString -> binaryString.equals(Strings.reverse(binaryString)))
                .map(binaryString -> LocalDate.parse(Integer.toString(Integer.parseInt(binaryString, 2)), DATE_FORMATTER).format(DATE_FORMATTER_JP))
                .forEach(System.out::println);
    }
}
