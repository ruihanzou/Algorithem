package problems_2017_07_20;

import java.util.Scanner;
import java.util.Stack;

/**
 * Created by ruihan on 7/23/17.
 */
public class MountainsAndFlame {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {
            int size = in.nextInt();
            int[] arr = new int[size];
            for (int i = 0; i < size; i++) {
                arr[i] = in.nextInt();
            }
            System.out.println(communications(arr));
        }
    }


    public static int nextIndex(int size, int i) {
        return i < (size - 1) ? (i + 1) : 0;
    }
    //for example C7^2, the total possible pairs in the 7 same height mountains
    public static long getInternalSum(int n) {
        return n == 1L ? 0L :(long) n * (long) (n - 1) / 2L;
    }

    public static class Pair {
        public int value;
        public int times;

        public Pair(int value) {
            this.value = value;
            this.times = 1;
        }
    }

    public static long communications(int[] arr) {
        // only one mountain can not be pared.
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int size = arr.length;
        int maxIndex = 0;
        //first find the largest number from array and put it into a stack
        for (int i = 0; i < size; i++) {
            maxIndex = arr[maxIndex] < arr[i] ? i : maxIndex;
        }
        int value = arr[maxIndex];
        int index = nextIndex(size, maxIndex);
        long res = 0L;
        Stack<Pair> stack = new Stack<>();
        stack.push(new Pair(value));
        while (index != maxIndex) {
            value = arr[index];
            while (!stack.isEmpty() && stack.peek().value < value) {
                int times = stack.pop().times;
                res += getInternalSum(times) + times; //from right(the neighbourhood mountain at right hand side) to mid (the mountain at top of stack)
                res += stack.isEmpty() ? 0 : times; // from mid to left
            }
            if (!stack.isEmpty() && stack.peek().value == value) {
                stack.peek().times++;
            } else {
                stack.push(new Pair(value));
            }
            index = nextIndex(size, index);
        }
        //post processing the remaining mountains in the stack
        while (!stack.isEmpty()) {
            int times = stack.pop().times;
            res += getInternalSum(times); //internal times
            if (!stack.isEmpty()) {
                res += times; //from left to mid
                if (stack.size() > 1) {
                    res += times; // from right(next left) to next mid
                } else {
                    res += stack.peek().times > 1 ? times : 0; // the maximum height mountain doesn't have any pair
                }
            }
        }
        return res;
    }
}
