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
    public boolean isAlive() {
        return false;
    }

    @Override
    public Boolean isVampire() {
        return true;
    }

    @Override
    public Cell next(List<Cell> neighbours) {
        var count = Query.where(neighbours, n -> n.isVampire()).size();
        if (2 <= count) {
            return new DeadCell();
        }
        return new VampireCell();
    }
}
