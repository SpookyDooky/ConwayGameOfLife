import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;

public class Game extends JFrame {

    private int heightAmount;
    private int widthAmount;

    private int windowHeight;
    private int windowWidth;

    private int[][] populationArray;
    public Game(int heightAmount, int widthAmount){
        super("Game of Life");
        this.heightAmount = heightAmount;
        this.widthAmount = widthAmount;

        this.populationArray = new int[heightAmount][widthAmount];
        this.windowHeight = 1000;
        this.windowWidth = 1800;
    }

    public void startGame(){
        initWindow();
        initPopulation();
        this.update();
        this.render();
    }

    public void initPopulation(){
        Random rand = new Random();
        for(int x = 1; x < widthAmount - 1; x++){
            for(int y = 1; y < heightAmount - 1; y++){
                int random = ((int)Math.floor(rand.nextDouble() * 2) + 1) - 1;
                populationArray[y][x] = random;
            }
        }
    }

    public void initWindow(){
        this.setSize(windowWidth,windowHeight);
        this.setVisible(true);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(1);
            }
        });
    }

    public void updatePopulation(){
        //We start at x = 1 and y = 1 and stop at xMax - 1, yMax -1, because edges are dead by default.
        int[][] newPopulation = new int[heightAmount][widthAmount];
        for(int x = 1; x < widthAmount - 1; x++){
            for(int y = 1; y < heightAmount - 1; y++){
                int neighbours = checkNeigbours(x,y);

                if((populationArray[y][x] == 1) && ((neighbours < 2) || (neighbours > 3))){
                    newPopulation[y][x] = 0;
                } else if((populationArray[y][x] == 0) && (neighbours == 3)){
                    newPopulation[y][x] = 1;
                } else {
                    newPopulation[y][x] = populationArray[y][x];
                }
            }
        }
        this.populationArray = newPopulation;
    }

    public int checkNeigbours(int xValue, int yValue){
        int result = 0;
        for(int x = -1; x <= 1; x++){
            for(int y = -1; y <= 1; y++){
                result += populationArray[yValue + y][xValue + x];
            }
        }
        result -= populationArray[yValue][xValue];
        return result;
    }

    public void update(){
        this.add(new JPanel(){
            @Override
            public void paint(Graphics g){
                int blockHeight = windowHeight/heightAmount;
                int blockWidth = windowWidth/widthAmount;

                for(int x = 0; x < widthAmount;x++){
                    for(int y = 0; y < heightAmount;y++){
                        int xValue = x * blockWidth;
                        int yValue = y * blockHeight;
                        if(populationArray[y][x] == 1){
                            g.setColor(Color.BLACK);
                        } else {
                            g.setColor(Color.WHITE);
                        }
                        g.fillRect(xValue,yValue,blockWidth,blockHeight);
                    }
                }
            }
        });
    }

    //Game loop
    private void render(){
        boolean keepgoing = true;
        int generation = 0;
        while(keepgoing){
            this.updatePopulation();
            this.update();
            this.repaint();
            generation++;
            System.out.println("GENERATION: " + generation);
            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args){
        Game game = new Game(200,360);
        game.startGame();
    }
}
