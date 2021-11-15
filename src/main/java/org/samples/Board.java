package org.samples;

import org.lambda.query.Query;
import org.lambda.query.Queryable;
import org.lambda.utils.Grid;

import java.awt.*;
import java.util.*;
import java.util.List;

public class Board {
    private HashMap<Point, Cell> board = new HashMap<>();

    @Override
    public String toString() {
        return Grid.print(5, 5, this::printCell);
    }

    private String printCell(Integer x, Integer y) {
        return getCell(x, y).toString();
    }

    public Cell getCell(Integer x, Integer y) {
        var point = new Point(x, y);
        return board.getOrDefault(point, new DeadCell());
    }

    public void put(int x, int y, Cell cell) {
             board.put(new Point(x, y), cell);
    }

    public Board advance() {
        // create a new board
        Board next = new Board();
        // loop over all cells that are currently relevant
        var pointsOnBoard = Queryable.as(board.keySet().toArray(new Point[0]));
        Queryable<Point> distinct = Query.selectMany(pointsOnBoard, k -> getNeighborsAndSelf(k)).distinct();

        for (Point point : distinct) {
            var neighbours = getNeighborsAndSelf(point).where(p -> !p.equals(point)).select(p -> getCell(p));
            next.put(point, getCell(point).next(neighbours));
        }
        // advance the cells and put it onto the new board

        // return the new board
        return next;
    }

    private void put(Point point, Cell next) {
        if (next.getType() != CellType.Dead){
            put(point.x, point.y,next);
        }
    }

    private Cell getCell(Point point) {
        return getCell(point.x, point.y);
    }

    private Queryable<Point> getNeighborsAndSelf(Point p) {
        return Queryable.as(
                new Point(p.x - 1, p.y - 1),
                new Point(p.x - 1, p.y - 0),
                new Point(p.x - 1, p.y + 1),
                new Point(p.x - 0, p.y - 1),
                new Point(p.x - 0, p.y - 0),
                new Point(p.x - 0, p.y + 1),
                new Point(p.x + 1, p.y - 1),
                new Point(p.x + 1, p.y - 0),
                new Point(p.x + 1, p.y + 1)
        );
    }
}
