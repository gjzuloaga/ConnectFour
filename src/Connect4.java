import java.util.Scanner;

public class Connect4 {
    private static final int ROWS = 6;
    private static final int COLUMNS = 7;
    private static String[][] board = new String[ROWS][COLUMNS];
    private static String currentPlayer = "\u001B[34mX\u001B[0m"; // Blue for Player 1
    private static String tableColor = "\u001B[0m"; // Default color

    public static void main(String[] args) {
        askTableColor();
        displayConnectFourArt();
        initializeBoard();
        boolean gameWon = false;

        while (!gameWon) {
            displayBoard();
            int column = getPlayerMove();
            dropDisc(column);

            if (checkWin()) {
                displayBoard();
                announceWinner();
                gameWon = true;
            } else if (isBoardFull()) {
                displayBoard();
                System.out.println("It's a draw!");
                gameWon = true;
            } else {
                currentPlayer = (currentPlayer.equals("\u001B[34mX\u001B[0m")) ? "\u001B[31mO\u001B[0m" : "\u001B[34mX\u001B[0m";
            }
        }
    }

    private static void askTableColor() {
        System.out.println("Choose the table color:");
        System.out.println("1. Yellow");
        System.out.println("2. Green");
        System.out.println("3. Red");
        System.out.println("4. Blue");
        System.out.println("5. Purple");
        
        Scanner scanner = new Scanner(System.in);
        int choice;
        
        do {
            System.out.print("Enter your choice (1-5): ");
            choice = scanner.nextInt();
        } while (choice < 1 || choice > 5);
        
        switch (choice) {
            case 1:
                tableColor = "\u001B[33m"; // Yellow
                break;
            case 2:
                tableColor = "\u001B[32m"; // Green
                break;
            case 3:
                tableColor = "\u001B[31m"; // Red
                break;
            case 4:
                tableColor = "\u001B[34m"; // Blue
                break;
            case 5:
                tableColor = "\u001B[35m"; // Purple
                break;
        }
    }

    private static void initializeBoard() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS; col++) {
                board[row][col] = " ";
            }
        }
    }

    private static void displayBoard() {
        System.out.println(" 1 2 3 4 5 6 7");
        for (int row = 0; row < ROWS; row++) {
            System.out.print(tableColor + "|");
            for (int col = 0; col < COLUMNS; col++) {
                System.out.print(board[row][col] + tableColor + "|");
            }
            System.out.println("\u001B[0m"); // Reset color to default
        }
    }

    private static int getPlayerMove() {
        Scanner scanner = new Scanner(System.in);
        int column;
        while (true) {
            System.out.print("Player " + (currentPlayer.equals("\u001B[34mX\u001B[0m") ? "\u001B[34mBlue\u001B[0m" : "\u001B[31mRed\u001B[0m") + ", enter a column (1-7): ");
            column = scanner.nextInt();
            if (column >= 1 && column <= COLUMNS && board[0][column - 1].equals(" ")) {
                break;
            }
            System.out.println("Invalid move. Please try again.");
        }
        return column - 1;
    }

    private static void dropDisc(int column) {
        for (int row = ROWS - 1; row >= 0; row--) {
            if (board[row][column].equals(" ")) {
                board[row][column] = currentPlayer;
                break;
            }
        }
    }

    private static boolean checkWin() {
          if (checkHorizontal() || checkVertical() || checkDiagonal()) {
           
            return true;
        }
        return false;
       
    }

    private static boolean checkHorizontal() {
        // Check for a win horizontally
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col <= COLUMNS - 4; col++) {
                if (board[row][col].equals(currentPlayer) &&
                    board[row][col + 1].equals(currentPlayer) &&
                    board[row][col + 2].equals(currentPlayer) &&
                    board[row][col + 3].equals(currentPlayer)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean checkVertical() {
        // Check for a win vertically
        for (int row = 0; row <= ROWS - 4; row++) {
            for (int col = 0; col < COLUMNS; col++) {
                if (board[row][col].equals(currentPlayer) &&
                    board[row + 1][col].equals(currentPlayer) &&
                    board[row + 2][col].equals(currentPlayer) &&
                    board[row + 3][col].equals(currentPlayer)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean checkDiagonal() {
        // Check for a win diagonally
        for (int row = 0; row <= ROWS - 4; row++) {
            for (int col = 0; col <= COLUMNS - 4; col++) {
                if (board[row][col].equals(currentPlayer) &&
                    board[row + 1][col + 1].equals(currentPlayer) &&
                    board[row + 2][col + 2].equals(currentPlayer) &&
                    board[row + 3][col + 3].equals(currentPlayer)) {
                    return true;
                }
            }
        }
        for (int row = 0; row <= ROWS - 4; row++) {
            for (int col = 3; col < COLUMNS; col++) {
                if (board[row][col].equals(currentPlayer) &&
                    board[row + 1][col - 1].equals(currentPlayer) &&
                    board[row + 2][col - 2].equals(currentPlayer) &&
                    board[row + 3][col - 3].equals(currentPlayer)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static void displayConnectFourArt() {
        System.out.println("Welcome to Connect Four!");
   
    }


    private static boolean isBoardFull() {
        for (int col = 0; col < COLUMNS; col++) {
            if (board[0][col].equals(" ")) {
                return false;
            }
        }
        return true;
    }


private static void announceWinner() {
        String winnerColor = tableColor.equals("\u001B[0m") ? "\u001B[37m" : tableColor; // Default to white text on the table color
        System.out.println(winnerColor + "Player " + (currentPlayer.equals("\u001B[34mX\u001B[0m") ? "Blue" : "Red") + " wins!" + "\u001B[0m");
    }

}

