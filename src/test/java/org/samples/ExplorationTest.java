package org.samples;


import com.spun.util.NumberUtils;
import com.spun.util.Tuple;
import com.spun.util.logger.SimpleLogger;
import org.approvaltests.awt.AwtApprovals;
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

  public static final int NUMBER_OF_VAMPIRES = 23;

  //@Test
  @UseReporter(ImageWebReporter.class)
  void testExploration() {
    GuiBoard guiBoard;
    var board = generateInterestingBoard();
    SimpleLogger.variable(String.format("static GuiBoard NAME = ExplorationTest.createBoard(1,1,%s).withWidth(34).withHeight(34);",print(board)));
    guiBoard = new GuiBoard(board).withWidth(34).withHeight(34);
    AwtApprovals.verifySequence(guiBoard, 200, Duration.ofMillis(100), n -> guiBoard.advance());
  }
  @Test
  @UseReporter(ImageWebReporter.class)
  void testInteresting() {
    GuiBoard guiBoard = InterestingBoards.VAMPIRE_GENERATOR;
    AwtApprovals.verifySequence(guiBoard, 65, Duration.ofMillis(100), n -> guiBoard.advance());
  }

  public static GuiBoard createBoard(int xOffset, int yOffset, Tuple<Point,Cell>... cells){
    Board board = new Board();
    for (Tuple<Point, Cell> cell : cells) {
      board.put(cell.getFirst().x+xOffset, cell.getFirst().y+yOffset, cell.getSecond());
    }
    return new GuiBoard(board);
  }

  public static Tuple<Point, Cell> __(int x, int y, Cell cell) {
    return new Tuple<>(new Point(x, y), cell);
  }

  public static String print(Board board) {
    return print(board, ExplorationTest::printCell);
  }
  public static String print(Board board, Function1<Cell, String> printCell) {
    var entries = toList(board.board.entrySet());
    var cells = Query.orderBy(entries, e -> e.getKey().toString()).select(e -> String.format("__(%s, %s, %s)", e.getKey().x, e.getKey().y, printCell.call(e.getValue())));
    return cells.join(", ");
  }

  public static String printCell(Cell cell) {
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

  public static <T> List<T> toList(Collection<T> entrySet) {
    return new ArrayList<T>(entrySet);
  }


  public static Board generateInterestingBoard() {
    for (int i = 0; i < 100_000; i++) {
      var board = new Board();
      var numberOfCells = NumberUtils.getRandomInt(5, 10);
      for (int j = 0; j < numberOfCells; j++) {
        board.put(NumberUtils.getRandomInt(0, 10), NumberUtils.getRandomInt(0, 10), getRandomCell());
      }
      Board interesting = isInterestingBecauseLotOfVampires(board);
      if (interesting != null){
        return interesting;
      }
      if (i % 100 == 0){
        SimpleLogger.hourGlass();
      }
    }

    return null;
  }

  public static Board isInterestingBecauseLotOfVampires(Board board) {
    var b1 = board;
    var b2 = b1.advance();
    for (int j = 0; j < 150; j++) {
      b1 = b2;
      b2 = b1.advance();
    }
    if (NUMBER_OF_VAMPIRES < Query.where(b2.board.values(), c -> c.getType() == CellType.Vampire).size()) {
      return board;
    }
    return null;
  }public static Board isInteresting(Board board) {
    var b1 = board;
    var b2 = b1.advance();
    for (int j = 0; j < 900; j++) {
      b1 = b2;
      b2 = b1.advance();
    }
    if (!print(b1,c -> c.getType().toString()).equals(print(b2, c -> c.getType().toString()))) {
      return board;
    }
    return null;
  }

  public static Cell getRandomCell() {
    if (NumberUtils.doRandomPercentage(80)){
      if (NumberUtils.doRandomPercentage(50)) {
        return new AliveCell(1);
      }
      return new AliveCell(NumberUtils.getRandomInt(90,99));
    }
    return new VampireCell();
  }


}
