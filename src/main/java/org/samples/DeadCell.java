package org.samples;

import org.lambda.query.Query;

import java.util.List;

public class DeadCell implements Cell {
    @Override
    public CellType getType() {
        return CellType.Dead;
    }

    @Override
    public Cell next(List<Cell> neighbours) {
        var count = Query.where(neighbours, n -> n.getType() == CellType.Alive).size();
        return count == 3 ? new AliveCell(1): new DeadCell();
    }

    @Override
    public String toString() {
        return "__";
    }
}
