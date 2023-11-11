
//Your input must be in this format e.g. {1, 2, 3} , and in case of empty set { } not {}

import java.util.Scanner;

class problem1Recursive {
    // funtion for getting the bit value getBit()
    public static int getBit(int number, int position) {
        number = number >> position; // shifting the required bit to be the least significant bit
        if (number % 2 == 0) { // even; last bit is 0
            return 0;
        } else { // odd; last bit is 1
            return 1;
        }
    }

    // function for counting number of 1's in the set
    public static int count1s(int number, int size) {
        int count = 0;
        for (int t = 0; t < size; t++) {
            if (getBit(number, t) == 1)
                count++;
        }
        return count;
    }

    // funtion for getting the element in set correspondting to the 1 bit value
    public static void getSubSet(String set[], int num, int start, int numOfElements) {
        int size = set.length;
        // base case
        if (start == size)
            return;

        if (getBit(num, start) == 1) {
            System.out.print(set[size - start - 1]);
            ++numOfElements;
            if (numOfElements != count1s(num, size))
                System.out.print(", ");
        }
        getSubSet(set, num, ++start, numOfElements);

    }

    public static void powerSetPrinting(String[] set, int num) {
        // base case
        if (num > 0 && (int) Math.pow(2, set.length) == num)
            return;
        System.out.print("{");
        getSubSet(set, num, 0, 0);
        System.out.print("}");
        if ((int) Math.pow(2, set.length) - num != 1)
            System.out.print(", ");
        powerSetPrinting(set, ++num);

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // reading set
        System.out.print("Enter your set: ");
        String input = scanner.nextLine().replace("{", "");
        input = input.replace("}", "");
        input = input.replace(",", "");
        if (input.length() == 0) {
            System.out.println("Wrong Input");
            return;
        }
        String[] inputArr = input.split(" ");

        System.out.print("Power set is : {");
        powerSetPrinting(inputArr, 0);
        System.out.print("}");

    }
}
