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
        var neighbours = Queryable.as(listOfNeighbours);
        var count = neighbours.where(c -> c.getType() == CellType.Alive).size();
        var isAlive = 2 <= count && count <= 3;
        if (isAlive) {
            return ageOneYear();
        } else {
            boolean isVampireNearby = neighbours.any(c -> c.getType() == CellType.Vampire);
            return isVampireNearby ? new VampireCell() : new DeadCell();
        }
    }

    private Cell ageOneYear() {
        if (99 <= age ) {
            return new VampireCell();
        }
        return new AliveCell(age + 1);
    }

    @Override
    public CellType getType() {
            return CellType.Alive;
    }
}
