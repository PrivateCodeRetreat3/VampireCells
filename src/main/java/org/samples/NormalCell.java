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
        return isAlive() ? "" + this.age : ".";
    }

    public NormalCell next(List<NormalCell> neighbours) {
        var count = Query.where(neighbours, n -> n.isAlive()).size();
        var alive = (count == 2 && this.isAlive()) || count == 3;
        if (alive) {
            return ageOneYear();
        } else {
            boolean hasVampire = Query.any(neighbours, n -> n.isVampire());
            if (isAlive() && hasVampire) {
                return new VampireCell();
            }
            // if you are dying AND there is a neighbor that is a vampire
            // become a vampire cell
            // otherwise be a normal cell with age zero (dead)
            return new NormalCell(0);
        }
    }

    private NormalCell ageOneYear() {
        if (99 <= age ) {
            return new VampireCell();
        }
        return new NormalCell(age + 1);
    }

    public Boolean isVampire() {
        return false;
    }

    public boolean isAlive() {
        return 0 < age;
    }

}
