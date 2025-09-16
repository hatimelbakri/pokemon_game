import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import MG2D.Couleur;

public class Game extends JPanel implements MouseListener {
    private Board board;
    private Player player1, player2;
    private Player currentPlayer;
    private Pokemon selectedPokemon;
    private int cellSize = 75;

    public Game() {
        this.board = new Board();
        this.player1 = new Player("Red");
        this.player2 = new Player("Blue");
        this.currentPlayer = player1;
        addMouseListener(this);
        loadPokemons();
    }

    private void loadPokemons() {
        // Positions for Red player (bottom)
        int[][] redPositions = {
            {8, 0}, {8, 1}, {8, 2}, {8, 3}, {8, 4}, {8, 5}, {8, 6}, {8, 7}, {8, 8},
            {7, 0}, {7, 1}, {7, 2}, {7, 3}, {7, 4}, {7, 5}, {7, 6}, {7, 7}, {7, 8},
            {6, 3}, {6, 4}, {6, 5}
        };

        // Positions for Blue player (top)
        int[][] bluePositions = {
            {0, 0}, {0, 1}, {0, 2}, {0, 3}, {0, 4}, {0, 5}, {0, 6}, {0, 7}, {0, 8},
            {1, 0}, {1, 1}, {1, 2}, {1, 3}, {1, 4}, {1, 5}, {1, 6}, {1, 7}, {1, 8},
            {2, 3}, {2, 4}, {2, 5}
        };

        // Pokédex numbers for Red team
        int[] redPokedex = {
            15, 10, 89, 46, 129, 141, 33, 25, 149,
            28, 32, 135, 140, 16, 13, 111, 137, 134,
            19, 22, 18
        };

        // Pokédex numbers for Blue team
        int[] bluePokedex = {
            14, 17, 11, 150, 145, 83, 5, 59, 8,
            2, 6, 131, 130, 1, 7, 4, 3, 9,
            69, 12, 36
        };

        // Create Red Pokémon
        for (int i = 0; i < redPositions.length; i++) {
            int row = redPositions[i][0];
            int col = redPositions[i][1];
            Pokemon p = new Pokemon(redPokedex[i], row, col, 1); // Team 1: Red
            board.placePokemon(p, row, col);
            player1.addPokemon(p);
        }

        // Create Blue Pokémon
        for (int i = 0; i < bluePositions.length; i++) {
            int row = bluePositions[i][0];
            int col = bluePositions[i][1];
            Pokemon p = new Pokemon(bluePokedex[i], row, col, 2); // Team 2: Blue
            board.placePokemon(p, row, col);
            player2.addPokemon(p);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        board.draw(g);
        g.setColor(Couleur.RED);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Au joueur " + (currentPlayer == player1 ? "rouge" : "bleu") + " de jouer", 20, 20);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int row = e.getY() / cellSize;
        int col = e.getX() / cellSize;

        if (selectedPokemon == null) {
            Pokemon clicked = board.getPokemon(row, col);
            if (clicked != null && currentPlayer.hasPokemon(clicked)) {
                selectedPokemon = clicked;
                board.highlightMoves(clicked);
            }
        } else {
            if (selectedPokemon.isValidMove(row, col)) {
                Pokemon target = board.getPokemon(row, col);

                if (target == null) {
                    board.movePokemon(selectedPokemon, row, col);
                    selectedPokemon = null;
                    board.clearHighlights();
                    switchPlayer();
                } else if (!currentPlayer.hasPokemon(target)) {
                    selectedPokemon.attack(target);
                    if (target.getHP() <= 0) {
                        board.removePokemon(row, col);
                        getOpponent().removePokemon(target);
                        if (target.getNumPokedex() == 150) {
                            JOptionPane.showMessageDialog(this,
                                currentPlayer.getName() + " wins! Mewtwo defeated.");
                            System.exit(0);
                        }
                    }
                    selectedPokemon = null;
                    board.clearHighlights();
                    switchPlayer();
                }
                selectedPokemon = null;
                board.clearHighlights();
            } else {
                selectedPokemon = null;
                board.clearHighlights();
            }
        }
        repaint();
    }

    private void switchPlayer() {
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
    }

    private Player getOpponent() {
        return (currentPlayer == player1) ? player2 : player1;
    }

    // Unused mouse events
    public void mouseReleased(MouseEvent e) {}
    public void mouseClicked(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
}
