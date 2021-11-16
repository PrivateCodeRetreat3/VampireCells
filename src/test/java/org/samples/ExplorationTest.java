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
import org.lambda.functions.Function1;
import org.lambda.query.Query;

import java.awt.*;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ExplorationTest
{

  @Test
  @UseReporter(ImageWebReporter.class)
  void testExploration() {
    var board = generateInterestingBoard();
//    board = createBoard(__(0, 4, new AliveCell(1)), __(1, 4, new AliveCell(1)), __(1, 5, new AliveCell(92)), __(2, 5, new AliveCell(1)), __(5, 0, new AliveCell(1)), __(5, 2, new AliveCell(1)), __(5, 5, new AliveCell(1)), __(6, 5, new AliveCell(1)), __(8, 5, new AliveCell(1)));
    SimpleLogger.variable(print(board));
    var guiBoard = new GuiBoard(board);

    AwtApprovals.verifySequence(guiBoard, 200, Duration.ofMillis(100), n -> guiBoard.advance());
  }

  private Board createBoard(Tuple<Point,Cell>... cells){
    Board board = new Board();
    for (Tuple<Point, Cell> cell : cells) {
      board.put(cell.getFirst().x, cell.getFirst().y, cell.getSecond());
    }
    return board;
  }

  private Tuple<Point, Cell> __(int x, int y, Cell cell) {
    return new Tuple<>(new Point(x, y), cell);
  }

  private String print(Board board) {
    return print(board, this::printCell);
  }
  private String print(Board board, Function1<Cell, String> printCell) {
    var entries = toList(board.board.entrySet());
    var cells = Query.orderBy(entries, e -> e.getKey().toString()).select(e -> String.format("__(%s, %s, %s)", e.getKey().x, e.getKey().y, printCell.call(e.getValue())));
    return cells.join(", ");
  }

  private String printCell(Cell cell) {
    switch (cell.getType()) {
      case Alive:
        return String.format("new AliveCell(%s)", ((AliveCell) cell).getAge());
      case Dead:
        return "new DeadCell()";
      case Vampire:
        return "new VampireCell()";
    }
    return null;
  }

  private <T> List<T> toList(Collection<T> entrySet) {
    return new ArrayList<T>(entrySet);
  }


  private Board generateInterestingBoard() {
    for (int i = 0; i < 1000; i++) {
      var board = new Board();
      var numberOfCells = NumberUtils.getRandomInt(5, 10);
      for (int j = 0; j < numberOfCells; j++) {
        board.put(NumberUtils.getRandomInt(0, 10), NumberUtils.getRandomInt(0, 10), getRandomCell());
      }
      Board interesting = isInteresting(board);
      if (interesting != null){
        return interesting;
      }
    }

    return null;
  }

  private Board isInteresting(Board board) {
    var b1 = board;
    var b2 = b1.advance();
    for (int j = 0; j < 100; j++) {
      b1 = b2;
      b2 = b1.advance();
    }
    if (!print(b1,c -> c.getType().toString()).equals(print(b2, c -> c.getType().toString()))) {
      return board;
    }
    return null;
  }

  private Cell getRandomCell() {
    if (NumberUtils.doRandomPercentage(80)){
      if (NumberUtils.doRandomPercentage(50)) {
        return new AliveCell(1);
      }
      return new AliveCell(NumberUtils.getRandomInt(90,99));
    }
    return new VampireCell();
  }


}
