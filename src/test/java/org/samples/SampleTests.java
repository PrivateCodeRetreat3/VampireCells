package org.samples;


import com.spun.util.ArrayUtils;
import com.spun.util.NumberUtils;
import org.approvaltests.Approvals;
import org.approvaltests.combinations.CombinationApprovals;
import org.junit.jupiter.api.Test;
import org.lambda.utils.Grid;

import java.util.ArrayList;
import java.util.List;

public class SampleTests
{
//  @Test
//  public void testNormalCellDiesWhenLonely()
//  {
//    final NormalCell[] possible = new NormalCell[] {new NormalCell(1), new NormalCell(0), new NormalCell(99), new VampireCell() };
//    CombinationApprovals.verifyAllCombinations(this::getNext, possible, possible, possible, possible, possible, possible, possible, possible, possible);
//  }

  @Test
  public void testNeighborScenarios()
  {
    final NormalCell[] possible = new NormalCell[] {new NormalCell(1), new NormalCell(0), new NormalCell(99), new VampireCell() };
    CombinationApprovals.verifyAllCombinations(this::getNext, possible, new Integer[]{0, 1, 2, 3, 4}, new Integer[]{0, 1, 2, 3});
  }
  private String getNext(NormalCell center, NormalCell topLeft, NormalCell top, NormalCell topRight, NormalCell left,
                         NormalCell right, NormalCell bottomLeft, NormalCell bottom, NormalCell bottomRight) {
    final NormalCell next = center.next(List.of(topLeft, topRight, left, top, right, bottomLeft, bottom, bottomRight));
    return next.toString();
  }
  private String getNext(NormalCell center, Integer aliveNeighborCount, Integer vampireNeihborCount) {
    int deadNeighborCount = 8 - aliveNeighborCount - vampireNeihborCount;
    List<NormalCell> neighbors = new ArrayList<>();
    for (int i = 0; i < aliveNeighborCount; i++) {
      neighbors.add(new NormalCell(1));
    }
    for (int i = 0; i < vampireNeihborCount; i++) {
      neighbors.add(new VampireCell());
    }
    for (int i = 0; i < deadNeighborCount; i++) {
      neighbors.add(new NormalCell(0));
    }
    neighbors = List.of(NumberUtils.getShuffled(neighbors.toArray(new NormalCell[8]), 8));
    return center.next(neighbors).toString();
  }

}
