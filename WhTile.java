//Name: Sam Ostlund, Tyler Gurley

import java.awt.Color;
import javax.swing.JButton;
import java.awt.Graphics;

public class WhTile extends JButton //basic white tile that does nothing but occupy a space on the board, not used in the game.
{
    
    public WhTile()
    {
        super();
    }
    
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        int height = this.getSize().height; //gets the height and width
        int width = this.getSize().width;
        g.setColor(Color.WHITE); //makes the tile white
        g.drawRect(0,0, width, height);
        g.fillRect(0,0, width, height);
        
    }
}
