import java.util.Scanner;

class Player {
    private final String name;
    private final char symbol;

    Player(String name, char symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    String getName() {
        return name;
    }

    char getSymbol() {
        return symbol;
    }
}

class Board {
    private final char[][] cells = new char[3][3];

    Board() {
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 3; column++) {
                cells[row][column] = ' ';
            }
        }
    }

    boolean place(int row, int column, char symbol) {
        if (row < 0 || row >= 3 || column < 0 || column >= 3
                || cells[row][column] != ' ') {
            return false;
        }

        cells[row][column] = symbol;
        return true;
    }

    boolean hasWon(char symbol) {
        for (int i = 0; i < 3; i++) {
            if (cells[i][0] == symbol && cells[i][1] == symbol && cells[i][2] == symbol) {
                return true;
            }
            if (cells[0][i] == symbol && cells[1][i] == symbol && cells[2][i] == symbol) {
                return true;
            }
        }

        return (cells[0][0] == symbol && cells[1][1] == symbol && cells[2][2] == symbol)
                || (cells[0][2] == symbol && cells[1][1] == symbol && cells[2][0] == symbol);
    }

    boolean isFull() {
        for (char[] row : cells) {
            for (char cell : row) {
                if (cell == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    void print() {
        System.out.println();
        System.out.println("    1   2   3");
        for (int row = 0; row < 3; row++) {
            System.out.printf("%d | %c | %c | %c |%n",
                    row + 1, cells[row][0], cells[row][1], cells[row][2]);
            if (row < 2) {
                System.out.println("  |---+---+---|");
            }
        }
        System.out.println();
    }
}

class Game {
    private final Scanner scanner = new Scanner(System.in);
    private final Board board = new Board();
    private final Player[] players = {
            new Player("Jogador 1", 'X'),
            new Player("Jogador 2", 'O')
    };
    private int currentPlayer = 0;

    void run() {
        System.out.println("=== JOGO DA VELHA ===");

        while (true) {
            board.print();
            Player player = players[currentPlayer];

            System.out.printf("%s (%c), sua vez.%n", player.getName(), player.getSymbol());

            Integer row = readCoordinate("Linha (1-3): ");
            if (row == null) {
                System.out.println("Entrada encerrada. Partida finalizada.");
                return;
            }

            Integer column = readCoordinate("Coluna (1-3): ");
            if (column == null) {
                System.out.println("Entrada encerrada. Partida finalizada.");
                return;
            }

            if (!board.place(row - 1, column - 1, player.getSymbol())) {
                System.out.println("Essa posição já está ocupada. Escolha outra.");
                continue;
            }

            if (board.hasWon(player.getSymbol())) {
                board.print();
                System.out.printf("%s (%c) venceu!%n", player.getName(), player.getSymbol());
                return;
            }

            if (board.isFull()) {
                board.print();
                System.out.println("Empate!");
                return;
            }

            currentPlayer = (currentPlayer + 1) % players.length;
        }
    }

    private Integer readCoordinate(String prompt) {
        while (true) {
            System.out.print(prompt);

            if (!scanner.hasNextLine()) {
                return null;
            }

            String input = scanner.nextLine().trim();

            try {
                int value = Integer.parseInt(input);
                if (value >= 1 && value <= 3) {
                    return value;
                }
                System.out.println("Digite um número entre 1 e 3.");
            } catch (NumberFormatException exception) {
                System.out.println("Entrada inválida. Digite um número entre 1 e 3.");
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        new Game().run();
    }
}

