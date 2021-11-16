package org.samples;


import com.spun.util.NumberUtils;
import com.spun.util.Tuple;
import com.spun.util.logger.SimpleLogger;
import org.approvaltests.Approvals;
import org.approvaltests.StoryBoard;
import org.approvaltests.awt.AwtApprovals;
import org.approvaltests.combinations.CombinationApprovals;
import org.approvaltests.reporters.ImageWebReporter;
import org.approvaltests.reporters.UseReporter;
import org.junit.jupiter.api.Test;
import java.time.Duration;
import java.util.*;
import java.util.List;

public class SampleTests
{

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

  @Test
  void testBoard() {
    var storyBoard = new StoryBoard();
    var board = new Board[]{new Board()};
    board[0].put(1, 1, new AliveCell(97));
    board[0].put(1, 2, new AliveCell(96));
    board[0].put(1, 3, new AliveCell(95));
    board[0].put(3, 3, new VampireCell());
    storyBoard.add(board[0]);
    storyBoard.addFrames(6, () -> {
      board[0] = board[0].advance();
      return board[0];
    });
    Approvals.verify(storyBoard);
  }

  @Test
  @UseReporter(ImageWebReporter.class)
  void testGuiBoard() {
    var board = new Board();
    board.put(1, 1, new AliveCell(97));
    board.put(1, 2, new AliveCell(96));
    board.put(1, 3, new AliveCell(95));
    board.put(3, 3, new VampireCell());
    verifyGui(board, 6);
  }

  @Test
  @UseReporter(ImageWebReporter.class)
  void testGuiToad() {
    var board = new Board();
    board.put(4,4, new AliveCell(92));
    board.put(5,4, new AliveCell(1));
    board.put(6, 4, new AliveCell(1));
    board.put(5,5, new AliveCell(1));
    board.put(6,5, new AliveCell(1));
    board.put(7, 5, new AliveCell(95));
    var guiBoard = new GuiBoard(board);

    AwtApprovals.verifySequenceWithTimings(111, n ->  {
      if (n == 0){
        return new Tuple<>(guiBoard, Duration.ofSeconds(1));
      }
      long t = (9 < n && n < 100) ? 100: 1000;
      return new Tuple<>(guiBoard.advance(), Duration.ofMillis(t));
    });
  }

  private void verifyGui(Board board, int numberOfFrames) {
    var guiBoard = new GuiBoard(board);

    AwtApprovals.verifySequence(numberOfFrames, Duration.ofSeconds(1), n -> {
      if (n == 0){
        return guiBoard;
      }
      return guiBoard.advance();
    });
  }



}
