package org.samples;

import com.spun.util.StringUtils;
import org.lambda.query.Query;
import org.lambda.query.Queryable;

import java.util.List;

public class AliveCell implements Cell {
    private final int age;

    public AliveCell(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return StringUtils.padNumber(this.age, 2);
    }

    public Cell next(List<Cell> listOfNeighbours) {
        var neighbours = new Queryable<Cell>(Cell.class);
        neighbours.addAll(listOfNeighbours);
        var count = neighbours.where(c -> c.isAlive()).size();
        var isAlive = 2 <= count && count <= 3;
        if (isAlive) {
            return ageOneYear();
        } else {
            boolean isVampireNearby = neighbours.any(c -> c.isVampire());
            return isVampireNearby ? new VampireCell() : new DeadCell();
        }
    }

    private Cell ageOneYear() {
        if (99 <= age ) {
            return new VampireCell();
        }
        return new AliveCell(age + 1);
    }

    public Boolean isVampire() {
        return false;
    }

    public boolean isAlive() {
       return true;
    }

}
