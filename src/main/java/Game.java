import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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
        this.windowWidth = 1000;
    }

    public void startGame(){
        initWindow();
        this.update();
        this.render();
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

    public void gameTick(){

    }

    public void update(){
        this.add(new JPanel(){
            @Override
            public void paint(Graphics g){
                g.setColor(Color.BLACK);
                int blockHeight = windowHeight/heightAmount;
                int blockWidth = windowWidth/widthAmount;

                for(int x = 0; x < widthAmount;x++){
                    for(int y = 0; y < heightAmount;y++){
                        int xValue = x * blockWidth;
                        int yValue = y * blockHeight;
                        g.drawRect(xValue,yValue,blockWidth,blockHeight);
                    }
                }
            }
        });
    }

    public void render(){

    }

    public static void main(String[] args){
        Game game = new Game(200,200);
        game.startGame();
    }
}
