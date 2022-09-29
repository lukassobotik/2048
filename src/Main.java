import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;
import java.util.Random;

public class Main {
    static int[] rowOne = new int[4];
    static int[] rowTwo = new int[4];
    static int[] rowThree = new int[4];
    static int[] rowFour = new int[4];
    static int score = 0;
    static GUI gui;
    static boolean endGame = false;

    public static void main(String[] args) {
        randomizeRows();

        gui = new GUI();

        printBoard();

        addKeyListener();
    }

    static void restart() {
        endGame = false;
        for (int i = 0; i < 4; i++) {
            rowOne[i] = 0;
            rowTwo[i] = 0;
            rowThree[i] = 0;
            rowFour[i] = 0;
        }

        gui.frame.setVisible(false);
        score = 0;
        gui = new GUI();

        randomizeRows();
        printBoard();
        addKeyListener();
    }

    static void addKeyListener() {
        gui.frame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                var keycode = e.getKeyCode();

                switch (keycode) {
                    case KeyEvent.VK_UP -> {
                        mergeUp(0, true);
                        mergeUp(1, true);
                        mergeUp(2, true);
                        mergeUp(3, true);
                    }
                    case KeyEvent.VK_DOWN -> {
                        mergeDown(0);
                        mergeDown(1);
                        mergeDown(2);
                        mergeDown(3);
                    }
                    case KeyEvent.VK_LEFT -> {
                        mergeLeft(rowOne);
                        mergeLeft(rowTwo);
                        mergeLeft(rowThree);
                        mergeLeft(rowFour);
                    }
                    case KeyEvent.VK_RIGHT -> {
                        mergeRight(rowOne);
                        mergeRight(rowTwo);
                        mergeRight(rowThree);
                        mergeRight(rowFour);
                    }
                }

                var areAllCellsFull = randomizeRows();

                if (areAllCellsFull) {
                    int[] rowOneCopy = new int[4];
                    int[] rowTwoCopy = new int[4];
                    int[] rowThreeCopy = new int[4];
                    int[] rowFourCopy = new int[4];
                    System.arraycopy(rowOne, 0, rowOneCopy, 0, 4);
                    System.arraycopy(rowTwo, 0, rowTwoCopy, 0, 4);
                    System.arraycopy(rowThree, 0, rowThreeCopy, 0, 4);
                    System.arraycopy(rowFour, 0, rowFourCopy, 0, 4);

                    var canMergeVertically = true;
                    var canMergeHorizontally = true;

                    if (mergeUp(0, false)
                            && mergeUp(1, false)
                            && mergeUp(2, false)
                            && mergeUp(3, false)) {
                        canMergeVertically = false;

                        mergeLeft(rowOneCopy);
                        mergeLeft(rowTwoCopy);
                        mergeLeft(rowThreeCopy);
                        mergeLeft(rowFourCopy);

                        if (Arrays.equals(rowOneCopy, rowOne)
                                && Arrays.equals(rowTwoCopy, rowTwo)
                                && Arrays.equals(rowThreeCopy, rowThree)
                                && Arrays.equals(rowFourCopy, rowFour)) {
                            canMergeHorizontally = false;

                        }
                    }

                    if (!canMergeVertically && !canMergeHorizontally) {
                        endGame = true;
                    }
                }

                if (endGame) {
                    System.out.println("Game Over!");
                    GUI.gameOver.setVisible(true);
                    gui.frame.removeKeyListener(this);
                }

                printBoard();
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }

    static void printBoard() {
        System.out.println("Score: " + score);
        System.out.println(Arrays.toString(rowOne));
        System.out.println(Arrays.toString(rowTwo));
        System.out.println(Arrays.toString(rowThree));
        System.out.println(Arrays.toString(rowFour));

        gui.score.setText("Score: " + score);

        if (rowOne[0] != 0) gui.label1.setText(String.valueOf(rowOne[0])); else gui.label1.setText("");
        if (rowOne[1] != 0) gui.label2.setText(String.valueOf(rowOne[1])); else gui.label2.setText("");
        if (rowOne[2] != 0) gui.label3.setText(String.valueOf(rowOne[2])); else gui.label3.setText("");
        if (rowOne[3] != 0) gui.label4.setText(String.valueOf(rowOne[3])); else gui.label4.setText("");

        if (rowTwo[0] != 0) gui.label5.setText(String.valueOf(rowTwo[0])); else gui.label5.setText("");
        if (rowTwo[1] != 0) gui.label6.setText(String.valueOf(rowTwo[1])); else gui.label6.setText("");
        if (rowTwo[2] != 0) gui.label7.setText(String.valueOf(rowTwo[2])); else gui.label7.setText("");
        if (rowTwo[3] != 0) gui.label8.setText(String.valueOf(rowTwo[3])); else gui.label8.setText("");

        if (rowThree[0] != 0) gui.label9.setText(String.valueOf(rowThree[0])); else gui.label9.setText("");
        if (rowThree[1] != 0) gui.label10.setText(String.valueOf(rowThree[1])); else gui.label10.setText("");
        if (rowThree[2] != 0) gui.label11.setText(String.valueOf(rowThree[2])); else gui.label11.setText("");
        if (rowThree[3] != 0) gui.label12.setText(String.valueOf(rowThree[3])); else gui.label12.setText("");

        if (rowFour[0] != 0) gui.label13.setText(String.valueOf(rowFour[0])); else gui.label13.setText("");
        if (rowFour[1] != 0)  gui.label14.setText(String.valueOf(rowFour[1])); else gui.label14.setText("");
        if (rowFour[2] != 0)  gui.label15.setText(String.valueOf(rowFour[2])); else gui.label15.setText("");
        if (rowFour[3] != 0)  gui.label16.setText(String.valueOf(rowFour[3])); else gui.label16.setText("");
    }

    static boolean randomizeRows() {
        var numAmount = new Random().nextInt(3) + 1;
        System.out.println("numAmount: " + numAmount);

        var randomsInRowOne = 0;
        var randomsInRowTwo = 0;
        var randomsInRowThree = 0;
        var randomsInRowFour = 0;
        for (int i = 0; i < numAmount; i++) {
            var randomRow = new Random().nextInt(4);

            switch (randomRow) {
                case 0 -> {
                    if (Arrays.stream(rowOne).noneMatch(value -> value == 0)) {
                        randomsInRowTwo++;
                    } else {
                        randomsInRowOne++;
                    }
                }
                case 1 -> {
                    if (Arrays.stream(rowTwo).noneMatch(value -> value == 0)) {
                        randomsInRowThree++;
                    } else {
                        randomsInRowTwo++;
                    }
                }
                case 2 -> {
                    if (Arrays.stream(rowThree).noneMatch(value -> value == 0)) {
                        randomsInRowFour++;
                    } else {
                        randomsInRowThree++;
                    }
                }
                case 3 ->  {
                    if (Arrays.stream(rowFour).noneMatch(value -> value == 0)) {
                        randomsInRowOne++;
                    } else {
                        randomsInRowFour++;
                    }
                }
            }
        }

        for (int i = 0; i < randomsInRowOne; i++) {
            if (Arrays.stream(rowOne).anyMatch(value -> value == 0)) {
                if (rowOne[i] == 0) {
                    var random = new Random().nextInt(2) + 1;
                    if (random == 1) {
                        rowOne[i] = 2;
                    } else {
                        rowOne[i] = 4;
                    }
                } else if (rowOne[i + 1] == 0) {
                    var random = new Random().nextInt(2) + 1;
                    if (random == 1) {
                        rowOne[i + 1] = 2;
                    } else {
                        rowOne[i + 1] = 4;
                    }
                } else if (rowOne[i + 2] == 0) {
                    var random = new Random().nextInt(2) + 1;
                    if (random == 1) {
                        rowOne[i + 2] = 2;
                    } else {
                        rowOne[i + 2] = 4;
                    }
                } else if (rowOne[i + 3] == 0) {
                    var random = new Random().nextInt(2) + 1;
                    if (random == 1) {
                        rowOne[i + 3] = 2;
                    } else {
                        rowOne[i + 3] = 4;
                    }
                }
            }
        }
        for (int i = 0; i < randomsInRowTwo; i++) {
            if (Arrays.stream(rowTwo).anyMatch(value -> value == 0)) {
                if (rowTwo[i] == 0) {
                    var random = new Random().nextInt(2) + 1;
                    if (random == 1) {
                        rowTwo[i] = 2;
                    } else {
                        rowTwo[i] = 4;
                    }
                } else if (rowTwo[i + 1] == 0) {
                    var random = new Random().nextInt(2) + 1;
                    if (random == 1) {
                        rowTwo[i + 1] = 2;
                    } else {
                        rowTwo[i + 1] = 4;
                    }
                } else if (rowTwo[i + 2] == 0) {
                    var random = new Random().nextInt(2) + 1;
                    if (random == 1) {
                        rowTwo[i + 2] = 2;
                    } else {
                        rowTwo[i + 2] = 4;
                    }
                } else if (rowTwo[i + 3] == 0) {
                    var random = new Random().nextInt(2) + 1;
                    if (random == 1) {
                        rowTwo[i + 3] = 2;
                    } else {
                        rowTwo[i + 3] = 4;
                    }
                }
            }
        }
        for (int i = 0; i < randomsInRowThree; i++) {
            if (Arrays.stream(rowThree).anyMatch(value -> value == 0)) {
                if (rowThree[i] == 0) {
                    var random = new Random().nextInt(2) + 1;
                    if (random == 1) {
                        rowThree[i] = 2;
                    } else {
                        rowThree[i] = 4;
                    }
                } else if (rowThree[i + 1] == 0) {
                    var random = new Random().nextInt(2) + 1;
                    if (random == 1) {
                        rowThree[i + 1] = 2;
                    } else {
                        rowThree[i + 1] = 4;
                    }
                } else if (rowThree[i + 2] == 0) {
                    var random = new Random().nextInt(2) + 1;
                    if (random == 1) {
                        rowThree[i + 2] = 2;
                    } else {
                        rowThree[i + 2] = 4;
                    }
                } else if (rowThree[i + 3] == 0) {
                    var random = new Random().nextInt(2) + 1;
                    if (random == 1) {
                        rowThree[i + 3] = 2;
                    } else {
                        rowThree[i + 3] = 4;
                    }
                }
            }
        }
        for (int i = 0; i < randomsInRowFour; i++) {
            if (Arrays.stream(rowFour).anyMatch(value -> value == 0)) {
                if (rowFour[i] == 0) {
                    var random = new Random().nextInt(2) + 1;
                    if (random == 1) {
                        rowFour[i] = 2;
                    } else {
                        rowFour[i] = 4;
                    }
                } else if (rowFour[i + 1] == 0) {
                    var random = new Random().nextInt(2) + 1;
                    if (random == 1) {
                        rowFour[i + 1] = 2;
                    } else {
                        rowFour[i + 1] = 4;
                    }
                } else if (rowFour[i + 2] == 0) {
                    var random = new Random().nextInt(2) + 1;
                    if (random == 1) {
                        rowFour[i + 2] = 2;
                    } else {
                        rowFour[i + 2] = 4;
                    }
                } else if (rowFour[i + 3] == 0) {
                    var random = new Random().nextInt(2) + 1;
                    if (random == 1) {
                        rowFour[i + 3] = 2;
                    } else {
                        rowFour[i + 3] = 4;
                    }
                }
            }
        }

        return Arrays.stream(rowOne).noneMatch(value -> value == 0)
                && Arrays.stream(rowTwo).noneMatch(value -> value == 0)
                && Arrays.stream(rowThree).noneMatch(value -> value == 0)
                && Arrays.stream(rowFour).noneMatch(value -> value == 0);
    }

    static boolean mergeUp(int column, boolean replace) {
        if (replace) {
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
        } else {
            int[] line = new int[4];
            line[0] = rowFour[column];
            line[1] = rowThree[column];
            line[2] = rowTwo[column];
            line[3] = rowOne[column];

            mergeRight(line);

            return line[0] == rowFour[column]
                    && line[1] == rowThree[column]
                    && line[2] == rowTwo[column]
                    && line[3] == rowOne[column];
        }
        return false;
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
                score = score + (line[i] * 2);
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
                score = score + (line[i] * 2);
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
