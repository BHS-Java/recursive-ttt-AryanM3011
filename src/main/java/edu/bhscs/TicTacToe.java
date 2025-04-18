import java.util.Scanner;

public class TicTacToe {
  private char[][] board;
  private char currentPlayer;

  public TicTacToe() {
      board = new char[3][3];
      currentPlayer = 'X';
      for (int i = 0; i < 3; i++) {
          for (int j = 0; j < 3; j++) {
              board[i][j] = ' ';
          }
      }
  }

  public void play() {
    Scanner scanner = new Scanner(System.in);
    printBoard();

    while (true) {
        System.out.println("Player " + currentPlayer + ", enter your move (row[1-3] column[1-3]): ");
        int row = scanner.nextInt() - 1;
        int col = scanner.nextInt() - 1;

        if (isValidMove(row, col)) {
            board[row][col] = currentPlayer;
            printBoard();

            if (checkWin(currentPlayer)) {
                System.out.println("Player " + currentPlayer + " wins!");
                break;
            } else if (isBoardFull()) {
                System.out.println("It's a draw!");
                break;
            } else {
                switchPlayer();
            }
        } else {
            System.out.println("Invalid move. Try again.");
        }
    }
    scanner.close();
}

  private void printBoard() {
    System.out.println("-------------");
    for (int i = 0; i < 3; i++) {
      System.out.print("| ");
      for (int j = 0; j < 3; j++) {
        System.out.print(board[i][j] + " | ");
      }
        System.out.println();
        System.out.println("-------------");
    }
}

private boolean isValidMove(int row, int col) {
    return row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == ' ';
}

private boolean checkWin(char player) {
    return checkRows(player, 0) || checkCols(player, 0) || checkDiagonals(player);
}

private boolean checkRows(char player, int row) {
    if (row >= 3) {
        return false;
    }
    if (board[row][0] == player && board[row][1] == player && board[row][2] == player) {
        return true;
    }
    return checkRows(player, row + 1);
}

private boolean checkCols(char player, int col) {
    if (col >= 3) {
        return false;
    }
    if (board[0][col] == player && board[1][col] == player && board[2][col] == player) {
        return true;
    }
    return checkCols(player, col + 1);
}

private boolean checkDiagonals(char player) {
    return (board[0][0] == player && board[1][1] == player && board[2][2] == player) ||
           (board[0][2] == player && board[1][1] == player && board[2][0] == player);
}

private boolean isBoardFull() {
    for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++) {
            if (board[i][j] == ' ') {
                return false;
            }
        }
    }
    return true;
}


private void switchPlayer() {
    char PLAYER_X = 'X';
    char PLAYER_O = 'O';
    currentPlayer = (currentPlayer == PLAYER_X) ? PLAYER_O : PLAYER_X;
}

public static void main(String[] args) {
    TicTacToe game = new TicTacToe();
    game.play();
    
}
}