package org.samples;

import org.lambda.query.Query;

import java.util.List;

public class VampireCell extends NormalCell {
    public VampireCell() {
        super(100);
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
    public NormalCell next(List<NormalCell> neighbours) {
        var count = Query.where(neighbours, n -> n.isVampire()).size();
        if (2 <= count) {
            return new NormalCell(0);
        }
        return new VampireCell();
    }
}
