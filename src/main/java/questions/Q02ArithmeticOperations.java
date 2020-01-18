package questions;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import utils.Strings;

public class Q02ArithmeticOperations {
    /**
     * Arithmetic Operations (四則演算)<br>
     * Question:
     * <pre>
     *     並んでいる数字の各桁の間に四則演算の演算子を入れて計算する。
     *     最低でも1つは演算子を入れる。
     *     例)
     *     1234 => 1 + 2 x 3 - 4 = 3
     *     9876 => 9 x 87 + 6 = 789
     *
     *     出来上がった式を計算した結果、「元の数の桁から逆に並べた数字」と
     *     同じになるものを考える。
     *     式の計算は数学の順序とする。(乗除が先、加減は後)
     *     例)
     *     351 => 3 x 51 = 153
     *     621 => 6 x 21 = 126
     *     886 => 8 x 86 = 688
     *
     *     1000 ~ 9999のうち、上記の条件を満たす数を求めよ。
     *
     *     ヒント
     *     逆ポーランド記法を使う。
     *     「逆ポーランド記法とは」by Wikipedia
     *     -------------------------------------------------------------
     *     逆ポーランド記法を使えば、式の計算をする（評価）には、
     *     先頭からひとつずつ順番に記号を読み込み、
     *     その記号が演算子以外であればスタックに値を積み、
     *     演算子であればスタックから値を取り出して演算し結果をスタックに積む、
     *     という簡単な操作の繰り返しだけでよい
     *     -------------------------------------------------------------
     * </pre>
     *
     * @param args
     */
    public static void main(String[] args) {
        // 1000 ~ 9999
        String[] ops = { "*", "" };
        for (int i = 1_000; i < 10_000; i++) {
            String s = Integer.toString(i);
            for (String op : ops) {
                for (String op1 : ops) {
                    for (String op2 : ops) {
                        String formula = s.charAt(0) + op + s.charAt(1) + op1 + s.charAt(2) + op2 + s.charAt(3);
                        if (formula.length() > 4) {
                            int result = evalRPN(convertToRPN(formula));
                            if (s.equals(Strings.reverse(Integer.toString(result)))) {
                                System.out.println(String.format("%s = %s", i, formula));
                            }
                        }
                    }
                }
            }
        }
    }

    private static String[] convertToRPN(String formula) {
        int operationCount = 0;
        for (char c : formula.toCharArray()) {
            if (c == '*' || c == '/' || c == '+' || c == '-') {
                operationCount++;
            }
        }
        List<String> list = new ArrayList<>(Arrays.asList(formula.split("\\*")));
        for (int i = 0; i < operationCount; i++) {
            list.add("*");
        }
        return list.toArray(String[]::new);
    }

    /**
     * Evaluates the value of an arithmetic expression in Reverse Polish Notation.
     *
     * @param tokens includes numeric and arithmetic
     * @return result of evaluation
     */
    private static int evalRPN(String[] tokens) {
        Deque<Integer> stack = new ArrayDeque<>();
        Integer a = 0;
        Integer b = 0;
        for (String token : tokens) {
            switch (token) {
                case "+":
                    a = stack.pollFirst();
                    b = stack.pollFirst();
                    stack.addFirst(b + a);
                    break;
                case "-":
                    a = stack.pollFirst();
                    b = stack.pollFirst();
                    stack.addFirst(b - a);
                    break;
                case "/":
                    a = stack.pollFirst();
                    b = stack.pollFirst();
                    stack.addFirst(b / a);
                    break;
                case "*":
                    a = stack.pollFirst();
                    b = stack.pollFirst();
                    stack.addFirst(b * a);
                    break;
                default:
                    stack.addFirst(Integer.parseInt(token));
            }
        }
        return stack.pop();
    }
}
