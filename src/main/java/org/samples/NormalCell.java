package org.samples;

import org.lambda.query.Query;

import java.util.List;

public class NormalCell {
    private int age;

    public NormalCell(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return display();
    }

    public NormalCell next(List<NormalCell> neighbours) {
        var count = Query.where(neighbours, n -> n.isAlive()).size();
        var alive = (count == 2 && this.isAlive()) || count == 3;
        if (alive) {
            return new NormalCell(age + 1);
        } else {
            return new NormalCell(0);
        }
    }

    private boolean isAlive() {
        return 0 < age;
    }

    public String display() {
        return isAlive() ? "" + this.age : ".";
    }
}
