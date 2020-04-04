package book_warm;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        StringBuilder startWord = new StringBuilder(scanner.nextLine());
        int n = Integer.parseInt(scanner.nextLine());

        String[][] matrix = new String[n][n];

        for (int i = 0; i < matrix.length; i++) {
            matrix[i] = scanner.nextLine().split("");
        }

        int pRow = -1;
        int pCol = -1;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if ("P".equalsIgnoreCase(matrix[i][j])) {
                    pRow = i;
                    pCol = j;
                }
            }
        }

        String command;
        while (!"End".equalsIgnoreCase(command = scanner.nextLine())) {
            switch (command) {
                case "up":
                    boolean moveUp = canMoveUp(pRow);
                    if (moveUp) {
                        if (Character.isAlphabetic(matrix[pRow - 1][pCol].charAt(0)) && !"P".equals(matrix[pRow - 1][pCol])) {
                            startWord.append(matrix[pRow - 1][pCol]);
                            matrix[pRow][pCol] = "-";
                            pRow -= 1;
                            matrix[pRow][pCol] = "P";
                        } else {
                            matrix[pRow][pCol] = "-";
                            pRow -= 1;
                            matrix[pRow][pCol] = "P";
                        }
                    } else {
                        removeLastLetter(startWord);
                    }
                    break;
                case "down":
                    boolean moveDown = canMoveDown(matrix, pRow);
                    if (moveDown) {
                        if (Character.isAlphabetic(matrix[pRow + 1][pCol].charAt(0)) && !"P".equals(matrix[pRow + 1][pCol])) {
                            startWord.append(matrix[pRow + 1][pCol]);
                            matrix[pRow][pCol] = "-";
                            pRow += 1;
                            matrix[pRow][pCol] = "P";
                        } else {
                            matrix[pRow][pCol] = "-";
                            pRow += 1;
                            matrix[pRow][pCol] = "P";
                        }
                    } else {
                        removeLastLetter(startWord);
                    }
                    break;
                case "left":
                    boolean moveLeft = canMoveLeft(pCol);
                    if (moveLeft) {
                        if (Character.isAlphabetic(matrix[pRow][pCol - 1].charAt(0)) && !"P".equals(matrix[pRow][pCol - 1])) {
                            startWord.append(matrix[pRow][pCol - 1].charAt(0));
                            matrix[pRow][pCol] = "-";
                            pCol -= 1;
                            matrix[pRow][pCol] = "P";
                        } else {
                            matrix[pRow][pCol] = "-";
                            pCol -= 1;
                            matrix[pRow][pCol] = "P";
                        }
                    } else {
                        removeLastLetter(startWord);
                    }
                    break;
                case "right":
                    boolean moveRight = canMoveRight(matrix, pRow, pCol);
                    if (moveRight){
                        if (Character.isAlphabetic(matrix[pRow][pCol + 1].charAt(0)) && !"P".equals(matrix[pRow][pCol + 1])){
                            startWord.append(matrix[pRow][pCol + 1]);
                            matrix[pRow][pCol] = "-";
                            pCol += 1;
                            matrix[pRow][pCol] = "P";
                        } else {
                            matrix[pRow][pCol] = "-";
                            pCol += 1;
                            matrix[pRow][pCol] = "P";
                        }
                    } else {
                        removeLastLetter(startWord);
                    }
                    break;
            }
        }

        System.out.println(startWord);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j]);
            }
            System.out.println();
        }
    }

    private static boolean canMoveRight(String[][] matrix, int row, int col) {
        return col + 1 < matrix[row].length;
    }

    private static boolean canMoveLeft(int col) {
        return col - 1 >= 0;
    }

    private static void removeLastLetter(StringBuilder startWord) {
        if (startWord.length() > 0) {
            startWord.replace(startWord.length() - 1, startWord.length(), "");
        }
    }

    private static Boolean canMoveDown(String[][] matrix, int row) {
        return row + 1 < matrix.length;
    }

    private static Boolean canMoveUp(int row) {
        return row - 1 >= 0;
    }
}
