package org.samples;

import static org.samples.ExplorationTest.__;

public class InterestingBoards {
    //    board = createBoard(__(0, 4, new AliveCell(1)), __(1, 4, new AliveCell(1)), __(1, 5, new AliveCell(92)), __(2, 5, new AliveCell(1)), __(5, 0, new AliveCell(1)), __(5, 2, new AliveCell(1)), __(5, 5, new AliveCell(1)), __(6, 5, new AliveCell(1)), __(8, 5, new AliveCell(1)));
    // massive glider
    //var board = createBoard(30, __(0, 6, new AliveCell(1)), __(2, 2, new AliveCell(96)), __(4, 2, new AliveCell(98)), __(6, 0, new AliveCell(1)), __(6, 2, new AliveCell(1)), __(7, 0, new AliveCell(1)), __(7, 1, new AliveCell(1)), __(8, 1, new AliveCell(1)), __(8, 9, new AliveCell(90)));
    //
    // var board = createBoard(20,__(1, 1, new AliveCell(1)), __(2, 1, new AliveCell(1)), __(3, 0, new AliveCell(96)), __(3, 1, new AliveCell(97)), __(5, 2, new AliveCell(97)), __(7, 1, new AliveCell(91)), __(8, 1, new AliveCell(1)), __(8, 8, new VampireCell()), __(9, 2, new VampireCell()));


    static GuiBoard LOTS_OF_VAMPIRES = ExplorationTest.createBoard(1, 1, __(0, 6, new AliveCell(1)), __(2, 1, new VampireCell()), __(3, 8, new VampireCell()), __(5, 8, new AliveCell(97)), __(6, 7, new AliveCell(97)), __(6, 9, new AliveCell(1)), __(7, 7, new AliveCell(1)), __(8, 6, new AliveCell(90)), __(9, 9, new AliveCell(1))).withWidth(34).withHeight(15);
    static GuiBoard VAMPIRES_20 = ExplorationTest.createBoard(1,1, __(0, 5, new VampireCell()), __(2, 7, new AliveCell(1)), __(2, 9, new VampireCell()), __(3, 2, new AliveCell(1)), __(4, 2, new AliveCell(1)), __(4, 3, new AliveCell(95)), __(5, 1, new AliveCell(95)), __(5, 2, new AliveCell(1)), __(9, 1, new AliveCell(1))).withWidth(34).withHeight(34);
    static GuiBoard THE_VAMPIRE = ExplorationTest.createBoard(26,2,__(0, 6, new AliveCell(1)), __(2, 1, new AliveCell(94)), __(2, 4, new AliveCell(1)), __(3, 0, new VampireCell()), __(3, 1, new AliveCell(93)), __(3, 4, new AliveCell(94)), __(4, 1, new AliveCell(95)), __(4, 4, new AliveCell(1)), __(4, 7, new AliveCell(1))).withWidth(60).withHeight(50);
    static GuiBoard VAMPIRE_GENERATOR = ExplorationTest.createBoard(1,1,__(0, 2, new VampireCell()), __(1, 1, new AliveCell(1)), __(1, 3, new AliveCell(1)), __(2, 4, new AliveCell(1)), __(3, 4, new AliveCell(1)), __(4, 1, new AliveCell(1)), __(4, 4, new AliveCell(1)), __(5, 2, new AliveCell(1)), __(5, 3, new AliveCell(1)), __(5, 4, new AliveCell(1))).withWidth(50).withHeight(9);
}
