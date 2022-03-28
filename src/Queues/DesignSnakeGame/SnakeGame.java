package Queues.DesignSnakeGame;
import java.util.Deque;
import java.util.LinkedList;

// https://www.youtube.com/watch?v=aKuivabiOns&list=RDsGIm0-dQd8M&index=6
// https://wentao-shao.gitbook.io/leetcode/data-structure/353.design-snake-game

public class SnakeGame {
    private final int width;
    private final int height;
    private final int[][] food;
    private int score;
    private final Deque<Integer> snakeBody;
    private int currentFoodIndex;

    public SnakeGame(int width, int height, int[][] food){
        this.food = food;
        this.height = height;
        this.width = width;
        this.score = 0;
        this.snakeBody = new LinkedList<>();
        this.snakeBody.addLast(0);      // Store positions of Grids occupied by snake's body
        this.currentFoodIndex = 0;
    }

    public int move(char direction){
        if (this.score == -1)       // Game already finished
            return this.score;

        // Gets the position of head of the snake in the grid (Game's grid)
        int headRow = this.snakeBody.peekLast() / this.width;
        int headColumn = this.snakeBody.peekLast() % this.width;

        // Updates for new snake head position
        switch (direction){
            case 'U' -> headRow--;
            case 'D' -> headRow++;
            case 'L' -> headColumn--;
            case 'R' -> headColumn++;
        }

        // Checking whether snake's new head is out of boundary or not
        if (headRow < 0 || headRow >= this.height || headColumn < 0 || headColumn >= this.width)
            return  this.score = -1;

        // If Snake's new head has a food, give it food
        if (this.currentFoodIndex < this.food.length  &&  headRow==this.food[this.currentFoodIndex][0] && headColumn==this.food[this.currentFoodIndex][1]){
            int newSnakeHeadPosition = headRow * this.width + headColumn;
            this.snakeBody.addLast(newSnakeHeadPosition);
            this.currentFoodIndex++;
            this.score++;
        }
        // else just move the positions of tail and head of the Snake
        else {
            int snakeTail = this.snakeBody.removeFirst();
            int newSnakeHeadPosition = headRow * this.width + headColumn;

            if (this.snakeBody.contains(newSnakeHeadPosition))      // snake loop over his body
                return this.score = -1;
            else
                this.snakeBody.addLast(newSnakeHeadPosition);       // valid snake move
        }
        return this.score;
    }


    public static void main(String[] args) {
        int[][] food = {{2,2}, {1,0}, {1,2}, {0,3}};
        int height = 3;
        int width = 4;

        SnakeGame snakeGame = new SnakeGame(width, height, food);

        System.out.println(snakeGame.move('R'));
        System.out.println(snakeGame.move('D'));
        System.out.println(snakeGame.move('R'));
        System.out.println(snakeGame.move('D'));
        System.out.println(snakeGame.move('L'));
        System.out.println(snakeGame.move('U'));
        System.out.println(snakeGame.move('L'));
        System.out.println(snakeGame.move('U'));


        System.out.println();
        System.out.println(snakeGame.move('D'));
        System.out.println(snakeGame.move('U'));

    }
}
