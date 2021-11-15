package org.samples;


import com.spun.util.NumberUtils;
import org.approvaltests.combinations.CombinationApprovals;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class SampleTests
{
//  @Test
//  public void testCell DiesWhenLonely()
//  {
//    final Cell [] possible = new Cell [] {new Cell (1), new Cell (0), new Cell (99), new VampireCell() };
//    CombinationApprovals.verifyAllCombinations(this::getNext, possible, possible, possible, possible, possible, possible, possible, possible, possible);
//  }

  @Test
  public void testNeighborScenarios()
  {
    final Cell [] possible = new Cell [] {new AliveCell(1), new DeadCell (), new AliveCell(99), new VampireCell() };
    CombinationApprovals.verifyAllCombinations(this::getNext, possible, new Integer[]{0, 1, 2, 3, 4}, new Integer[]{0, 1, 2, 3});
  }
  private String getNext(Cell center, Cell topLeft, Cell top, Cell topRight, Cell left,
                         Cell right, Cell bottomLeft, Cell bottom, Cell bottomRight) {
    final Cell next = center.next(List.of(topLeft, topRight, left, top, right, bottomLeft, bottom, bottomRight));
    return next.toString();
  }

  @Test
  void name() {
    getNext(new AliveCell(1), 0,1);
  }

  private String getNext(Cell center, Integer aliveNeighborCount, Integer vampireNeihborCount) {
    int deadNeighborCount = 8 - aliveNeighborCount - vampireNeihborCount;
    List<Cell> neighbors = new ArrayList<>();
    for (int i = 0; i < aliveNeighborCount; i++) {
      neighbors.add(new AliveCell(1));
    }
    for (int i = 0; i < vampireNeihborCount; i++) {
      neighbors.add(new VampireCell());
    }
    for (int i = 0; i < deadNeighborCount; i++) {
      neighbors.add(new DeadCell ());
    }
    neighbors = List.of(NumberUtils.getShuffled(neighbors.toArray(new Cell [8]), 8));
    return center.next(neighbors).toString();
  }

}
