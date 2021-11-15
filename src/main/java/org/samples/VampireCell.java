package org.samples;

import org.lambda.query.Query;

import java.util.List;

public class VampireCell implements Cell {
    public VampireCell() {

    }

    @Override
    public String toString() {
        return "V ";
    }
    @Override
    public CellType getType() {
        return CellType.Vampire;
    }

    @Override
    public Cell next(List<Cell> neighbours) {
        var count = Query.where(neighbours, n -> n.getType() == CellType.Vampire).size();
        if (2 <= count) {
            return new DeadCell();
        }
        return new VampireCell();
    }
}
