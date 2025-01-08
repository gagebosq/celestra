import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    // screen settings
    final int originaltileSize = 16; // 16 x 16
    final int scale = 3;

    final int tileSize = originaltileSize * scale;  // 48x 48 tile
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;
    
    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);

        
    }
}
