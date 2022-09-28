import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {
    static int[] rowOne = new int[4];
    static int[] rowTwo = new int[4];
    static int[] rowThree = new int[4];
    static int[] rowFour = new int[4];

    public static void main(String[] args) {
        randomizeRows();

        printBoard();

        Scanner input = new Scanner(System.in);
        while (true) {
            String dir = input.next();

            switch (dir) {
                case "left" -> {
                    mergeLeft(rowOne);
                    mergeLeft(rowTwo);
                    mergeLeft(rowThree);
                    mergeLeft(rowFour);
                }
                case "right" -> {
                    mergeRight(rowOne);
                    mergeRight(rowTwo);
                    mergeRight(rowThree);
                    mergeRight(rowFour);
                }
                case "up" -> {
                    mergeUp(0);
                    mergeUp(1);
                    mergeUp(2);
                    mergeUp(3);
                }
                case "down" -> {
                    mergeDown(0);
                    mergeDown(1);
                    mergeDown(2);
                    mergeDown(3);
                }
            }

            var endGame = randomizeRows();

            if (dir.equals("exit") || endGame) {
                System.out.println("Game Lost!");
                break;
            }

            printBoard();
        }
    }

    static void printBoard() {
        System.out.println(Arrays.toString(rowOne));
        System.out.println(Arrays.toString(rowTwo));
        System.out.println(Arrays.toString(rowThree));
        System.out.println(Arrays.toString(rowFour));
    }

    static boolean randomizeRows() {
        var numAmount = new Random().nextInt(4) + 1;
        System.out.println("numAmount: " + numAmount);

        var randomsInRowOne = 0;
        var randomsInRowTwo = 0;
        var randomsInRowThree = 0;
        var randomsInRowFour = 0;
        for (int i = 0; i < numAmount; i++) {
            var randomRow = new Random().nextInt(4);

            switch (randomRow) {
                case 0 -> randomsInRowOne++;
                case 1 -> randomsInRowTwo++;
                case 2 -> randomsInRowThree++;
                case 3 -> randomsInRowFour++;
            }
        }

        for (int i = 0; i < randomsInRowOne; i++) {
            if (Arrays.stream(rowOne).anyMatch(value -> value == 0)) {
                if (rowOne[i] == 0) {
                    var random = new Random().nextInt(2) + 1;
                    rowOne[i] = random;
                } else if (rowOne[i + 1] == 0) {
                    var random = new Random().nextInt(2) + 1;
                    rowOne[i + 1] = random;
                } else if (rowOne[i + 2] == 0) {
                    var random = new Random().nextInt(2) + 1;
                    rowOne[i + 2] = random;
                } else if (rowOne[i + 3] == 0) {
                    var random = new Random().nextInt(2) + 1;
                    rowOne[i + 3] = random;
                }
            }
        }
        for (int i = 0; i < randomsInRowTwo; i++) {
            if (Arrays.stream(rowTwo).anyMatch(value -> value == 0)) {
                if (rowTwo[i] == 0) {
                    var random = new Random().nextInt(2) + 1;
                    rowTwo[i] = random;
                } else if (rowTwo[i + 1] == 0) {
                    var random = new Random().nextInt(2) + 1;
                    rowTwo[i + 1] = random;
                } else if (rowTwo[i + 2] == 0) {
                    var random = new Random().nextInt(2) + 1;
                    rowTwo[i + 2] = random;
                } else if (rowTwo[i + 3] == 0) {
                    var random = new Random().nextInt(2) + 1;
                    rowTwo[i + 3] = random;
                }
            }
        }
        for (int i = 0; i < randomsInRowThree; i++) {
            if (Arrays.stream(rowThree).anyMatch(value -> value == 0)) {
                if (rowThree[i] == 0) {
                    var random = new Random().nextInt(2) + 1;
                    rowThree[i] = random;
                } else if (rowThree[i + 1] == 0) {
                    var random = new Random().nextInt(2) + 1;
                    rowThree[i + 1] = random;
                } else if (rowThree[i + 2] == 0) {
                    var random = new Random().nextInt(2) + 1;
                    rowThree[i + 2] = random;
                } else if (rowThree[i + 3] == 0) {
                    var random = new Random().nextInt(2) + 1;
                    rowThree[i + 3] = random;
                }
            }
        }
        for (int i = 0; i < randomsInRowFour; i++) {
            if (Arrays.stream(rowFour).anyMatch(value -> value == 0)) {
                if (rowFour[i] == 0) {
                    var random = new Random().nextInt(2) + 1;
                    rowFour[i] = random;
                } else if (rowFour[i + 1] == 0) {
                    var random = new Random().nextInt(2) + 1;
                    rowFour[i + 1] = random;
                } else if (rowFour[i + 2] == 0) {
                    var random = new Random().nextInt(2) + 1;
                    rowFour[i + 2] = random;
                } else if (rowFour[i + 3] == 0) {
                    var random = new Random().nextInt(2) + 1;
                    rowFour[i + 3] = random;
                }
            }
        }

        return Arrays.stream(rowOne).noneMatch(value -> value == 0)
                && Arrays.stream(rowTwo).noneMatch(value -> value == 0)
                && Arrays.stream(rowThree).noneMatch(value -> value == 0)
                && Arrays.stream(rowFour).noneMatch(value -> value == 0);
    }

    static void mergeUp(int column) {
        int[] line = new int[4];
        line[0] = rowFour[column];
        line[1] = rowThree[column];
        line[2] = rowTwo[column];
        line[3] = rowOne[column];

        mergeRight(line);

        rowFour[column] = line[0];
        rowThree[column] = line[1];
        rowTwo[column] = line[2];
        rowOne[column] = line[3];

    }

    static void mergeDown(int column) {
        int[] line = new int[4];
        line[0] = rowFour[column];
        line[1] = rowThree[column];
        line[2] = rowTwo[column];
        line[3] = rowOne[column];

        mergeLeft(line);

        rowFour[column] = line[0];
        rowThree[column] = line[1];
        rowTwo[column] = line[2];
        rowOne[column] = line[3];

    }

    static void mergeLeft(int[] line) {
        int count = 0;
        for (int i = 0; i < line.length; i++) {
            if (line[i] != 0) {
                line[count++] = line[i];
            }
        }
        while (count < line.length) {
            line[count++] = 0;
        }

        for (int i = 0; i < line.length - 1; i++) {
            if (line[i] == line[i + 1]) {
                line[i] = line[i] * 2;
                line[i + 1] = 0;
            }
        }

        count = 0;
        for (int i = 0; i < line.length; i++) {
            if (line[i] != 0) {
                line[count++] = line[i];
            }
        }
        while (count < line.length) {
            line[count++] = 0;
        }

    }

    static void mergeRight(int[] line) {
        int count = line.length - 1;
        for (int i = line.length - 1; i >= 0; i--) {
            if (line[i] != 0) {
                line[count--] = line[i];
            }
        }
        while (count >= 0) {
            line[count--] = 0;
        }

        for (int i = 0; i < line.length - 1; i++) {
            if (line[i] == line[i + 1]) {
                line[i + 1] = line[i] * 2;
                line[i] = 0;
            }
        }

        count = line.length - 1;
        for (int i = line.length - 1; i >= 0; i--) {
            if (line[i] != 0) {
                line[count--] = line[i];
            }
        }
        while (count >= 0) {
            line[count--] = 0;
        }

    }
}
