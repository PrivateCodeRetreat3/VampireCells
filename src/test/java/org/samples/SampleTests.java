package org.samples;


import org.approvaltests.Approvals;
import org.approvaltests.combinations.CombinationApprovals;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SampleTests
{
  @Test
  public void testNormalCellDiesWhenLonely()
  {
    final NormalCell[] possible = new NormalCell[] {new NormalCell(1), new NormalCell(0), new VampireCell() };
    CombinationApprovals.verifyAllCombinations(this::getNext, possible, possible, possible, possible, possible, possible, possible, possible, possible);
  }

  private String getNext(NormalCell center, NormalCell topLeft, NormalCell top, NormalCell topRight, NormalCell left,
                         NormalCell right, NormalCell bottomLeft, NormalCell bottom, NormalCell bottomRight) {
    final NormalCell next = center.next(List.of(topLeft, topRight, left, top, right, bottomLeft, bottom, bottomRight));
    return next.display();
  }

  @Test
  public void testNormalCellStaysAlive()
  {
    final NormalCell normalCell = new NormalCell(1);
    var neighbours = List.of(
            new NormalCell(1),
            new NormalCell(1));
    var next = normalCell.next(neighbours);
    Approvals.verify(next.display());
  }

  @Test
  public void testNormalCellBecomesAlive()
  {
    final NormalCell normalCell = new NormalCell(0);
    var neighbours = List.of(
            new NormalCell(1),
            new NormalCell(1),
            new NormalCell(1));
    var next = normalCell.next(neighbours);
    Approvals.verify(next.display());
  }
}
