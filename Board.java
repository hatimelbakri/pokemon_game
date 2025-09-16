import java.awt.*;
import MG2D.Couleur;

public class Board {
    private Pokemon[][] grid = new Pokemon[9][9];
    private boolean[][] highlights = new boolean[9][9];
    private boolean[][] attackHighlights = new boolean[9][9];
    private int cellSize = 75;

    public void draw(Graphics g) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {

                int x = col * cellSize;
                int y = row * cellSize + 30;

                // Draw cell background
                if ((row + col) % 2 == 0) g.setColor(Couleur.LIGHT_GRAY);
                else g.setColor(Couleur.GRAY);
                g.fillRect(x, y, cellSize, cellSize);

                // Highlight for possible moves
                if (highlights[row][col]) {
                    g.setColor(Couleur.BLACK);
                    g.drawOval(x + 5, y + 5, cellSize - 10, cellSize - 10);
                }

                // Highlight for possible attacks
                if (attackHighlights[row][col]) {
                    g.setColor(Couleur.RED);
                    g.drawOval(x + 5, y + 5, cellSize - 10, cellSize - 10);
                }

                // Draw Pokémon if present
                Pokemon p = grid[row][col];
                if (p != null) {
                    // Automatically remove Pokémon if HP <= 0
                    if (p.getHP() <= 0) {
                        grid[row][col] = null;
                        continue;
                    }

                    g.drawImage(p.getImage(), x + 7, y + 7, 60, 60, null);

                    // Set HP text color based on player
                    if (p.getPlayerId() == 1) {
                        g.setColor(Couleur.RED);  // Player Red
                    } else if (p.getPlayerId() == 2) {
                        g.setColor(Couleur.VERT); // Player Blue
                    }

                    g.setFont(new Font("Arial", Font.BOLD, 12));
                    g.drawString("" + p.getHP(), x + 5, y + cellSize - 5);
                }
            }
        }
    }

    public void placePokemon(Pokemon p, int row, int col) {
        grid[row][col] = p;
    }

    public Pokemon getPokemon(int row, int col) {
        return grid[row][col];
    }

    public void removePokemon(int row, int col) {
        grid[row][col] = null;
    }

    public void movePokemon(Pokemon p, int newRow, int newCol) {
        grid[p.getRow()][p.getCol()] = null;
        p.setPosition(newRow, newCol);
        grid[newRow][newCol] = p;
    }

    public void highlightMoves(Pokemon p) {
        clearHighlights();
        int row = p.getRow();
        int col = p.getCol();
        int playerId = p.getPlayerId();

        int[][] directions = {
            {-1,  0}, {1,  0}, {0, -1}, {0,  1},
            {-1, -1}, {-1, 1}, {1, -1}, {1, 1}
        };

        for (int[] dir : directions) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];
            if (newRow >= 0 && newRow < 9 && newCol >= 0 && newCol < 9) {
                Pokemon target = grid[newRow][newCol];
                if (target == null) {
                    highlights[newRow][newCol] = true;
                } else if (target.getPlayerId() != playerId) {
                    attackHighlights[newRow][newCol] = true;
                }
            }
        }
    }

    public void clearHighlights() {
        highlights = new boolean[9][9];
        attackHighlights = new boolean[9][9];
    }
}
