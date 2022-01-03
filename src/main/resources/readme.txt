Instructions :
The goal of the game is to test your skills in solving mazes

Controls :
- Enter : to start the game.
- Arrows : Move in the maze.
- V : Toggle lighting ON/OFF
            This gives you the option to see the whole maze
            or to see only with a light at your position
- T : toggle tracking ON/OFF
            This give you the option to see where the places you have visited
            so you can use it to solve the maze using backtracking or other algorithms.
- R : to reset the maze.
- H : help (opens this window).

Algorithms :
There is a plenty of algorithms to help you solve this maze
- Random mouse algorithm :
        This is a trivial method that can be implemented by a very unintelligent robot or perhaps a mouse.
         It is simply to proceed following the current passage until a junction is reached,
          and then to make a random decision about the next direction to follow.
           Although such a method would always eventually find the right solution,
            this algorithm can be extremely slow.
- wall follower :
            The best-known rule for traversing mazes is the wall follower,
             also known as either the left-hand rule or the right-hand rule.
              If the maze is simply connected, that is, all its walls are connected together
               or to the maze's outer boundary, then by keeping one hand in contact with one
               wall of the maze the solver is guaranteed not to get lost and will reach a different
                exit if there is one; otherwise, the algorithm will return to the entrance having
                traversed every corridor next to that connected section of walls at least once.
                 The algorithm is a depth-first in-order tree traversal.

visit
https://en.wikipedia.org/wiki/Maze-solving_algorithm#Random_mouse_algorithm
to get more Algorithms