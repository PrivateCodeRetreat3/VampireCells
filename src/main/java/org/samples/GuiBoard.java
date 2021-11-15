package org.samples;

import com.spun.swing.Paintable;
import com.spun.swing.SwingUtils;
import org.lambda.actions.Action0;

import java.awt.*;

public class GuiBoard implements Paintable {
    private Board board;

    public GuiBoard(Board board) {
        this.board = board;
    }

    private static final int CELL_SIZE = 20;
    private static final int WIDTH = 10;

    @Override
    public Dimension getSize() {
        return new Dimension(CELL_SIZE * WIDTH + 1, CELL_SIZE * WIDTH + 1);
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, CELL_SIZE * WIDTH, CELL_SIZE * WIDTH);

        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < WIDTH; y++) {
                paintCell(x, y, g);
            }
        }
    }

    private void paintCell(int x, int y, Graphics g) {
        var cell = board.getCell(x, y);
        drawBorder(x, y, g);
        switch (cell.getType()) {
            case Alive -> {
                drawCell(x, y, g, Color.CYAN, ((AliveCell) cell).getAge() + "");
            }
            case Vampire -> {
                drawCell(x, y, g, Color.RED, "V");
            }
        }
    }

    private void drawCell(int x, int y, Graphics g, Color color, String text) {
        g.setColor(color);
        g.fillRect(x * CELL_SIZE + 1, y * CELL_SIZE + 1, CELL_SIZE - 1, CELL_SIZE - 1);
        g.setColor(Color.BLACK);
        SwingUtils.drawCenteredString(g, text, x * CELL_SIZE + CELL_SIZE / 2, y * CELL_SIZE + CELL_SIZE / 2);
    }

    private void drawBorder(int x, int y, Graphics g) {
        g.setColor(Color.WHITE);
        g.drawRect(x * CELL_SIZE, y * CELL_SIZE, CELL_SIZE, CELL_SIZE);
    }

    @Override
    public void registerRepaint(Action0 action0) {
    }
}
