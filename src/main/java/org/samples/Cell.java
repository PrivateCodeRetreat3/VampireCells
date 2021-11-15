package org.samples;

import java.util.List;

public interface Cell {
    @Override
    String toString();

    boolean isAlive();

    Boolean isVampire();

    Cell next(List<Cell> neighbours);
}
