package org.samples;


import org.approvaltests.Approvals;
import org.approvaltests.combinations.CombinationApprovals;
import org.junit.jupiter.api.Test;

import java.util.List;

public class SampleTests
{
  @Test
  public void testNormalCellDiesWhenLonely()
  {
    final NormalCell[] possible = new NormalCell[] {new NormalCell(1), new NormalCell(0), new NormalCell(99), new VampireCell() };
    CombinationApprovals.verifyAllCombinations(this::getNext, possible, possible, possible, possible, possible, possible, possible, possible, possible);
  }

  private String getNext(NormalCell center, NormalCell topLeft, NormalCell top, NormalCell topRight, NormalCell left,
                         NormalCell right, NormalCell bottomLeft, NormalCell bottom, NormalCell bottomRight) {
    final NormalCell next = center.next(List.of(topLeft, topRight, left, top, right, bottomLeft, bottom, bottomRight));
    return next.toString();
  }


}
