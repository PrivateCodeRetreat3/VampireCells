package org.samples;

import org.lambda.query.Query;

import java.util.List;

public class DeadCell implements Cell {
    @Override
    public boolean isAlive() {
        return false;
    }

    @Override
    public Boolean isVampire() {
        return false;
    }

    @Override
    public Cell next(List<Cell> neighbours) {
        var count = Query.where(neighbours, n -> n.isAlive()).size();
        return count == 3 ? new NormalCell(1): new DeadCell();
    }

    @Override
    public String toString() {
        return "__";
    }
}
