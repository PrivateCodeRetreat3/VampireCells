package org.samples;

import java.util.List;

public interface Cell {
    @Override
    String toString();

    CellType getType();

    Cell next(List<Cell> neighbours);
}
