import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Game extends JFrame {

    private int height;
    private int width;

    public Game(int height, int width){
        super("Game of Life");
        this.height = height;
        this.width = width;
    }

    public void startGame(){
        initWindow();
    }

    public void initWindow(){
        this.setSize(1000,1000);
        this.setLayout(null);
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

    }

    public void render(){

    }

    public static void main(String[] args){
        Game game = new Game(200,200);
        game.startGame();
    }
}
