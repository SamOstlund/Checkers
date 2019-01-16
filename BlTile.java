//Name:Sam Ostlund, Tyler Gurley

import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Graphics;

public class BlTile extends JButton implements ActionListener
{
    private int placement;
    private int clicked;
    protected static Color player1co;
    protected static Color player2co;

    public BlTile(int num) //creates the button and makes it visible, also adds the spot on the board that it is as member data
    {
        super();
        placement = num;
        addActionListener(this);
        clicked = 0;
        player1co = new Color(Color.RED.getRGB());
        player2co = new Color(Color.GREEN.getRGB());
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        int height = this.getSize().height; //gets the height and width
        int width = this.getSize().width;
        g.setColor(Color.BLACK); //makes the tile black
        if (clicked == 1 && Checkers.firstClicked() == true)
            g.setColor(Color.ORANGE);
        else
            clicked = 0;
        g.drawRect(0,0, width, height);
        g.fillRect(0,0, width, height);
        double eightyheightdoub = height / 10 * 8;
        double eightywidthdoub = width / 10 * 8;
        double tenpercheight = height / 10;
        double tenpercwidth = width / 10;
        int eightheightint = (int) (eightyheightdoub + .5);
        int eightwidthint = (int) (eightywidthdoub + .5);
        int tenperch = (int) (tenpercheight + .5);
        int tenpercw = (int) (tenpercwidth + .5);
        if (Checkers.occupied(placement) == 1 || Checkers.occupied(placement) == 3) //draws a player 1 piece
        {
            g.setColor(player1co);
            g.drawArc(tenpercw, tenperch, eightwidthint, eightheightint, 0, 360);
            g.fillArc(tenpercw, tenperch, eightwidthint, eightheightint, 0, 360);
        }
        if (Checkers.occupied(placement) == 2 || Checkers.occupied(placement) == 4) //draws a player 2 piece
        {
            g.setColor(player2co);
            g.drawArc(tenpercw, tenperch, eightwidthint, eightheightint, 0, 360);
            g.fillArc(tenpercw, tenperch, eightwidthint, eightheightint, 0, 360);
        }
        if (Checkers.occupied(placement) == 3) //draws a player 1 king
        {
            g.setColor(Color.YELLOW);
            g.drawArc(tenpercw * 3, tenperch * 3, eightwidthint / 2, eightheightint / 2, 0, 360);
            g.fillArc(tenpercw * 3, tenperch * 3, eightwidthint / 2, eightheightint / 2, 0, 360);
        }
        if (Checkers.occupied(placement) == 4) //draws a player 2 king
        {
            g.setColor(Color.YELLOW);
            g.drawArc(tenpercw * 3, tenperch * 3, eightwidthint / 2, eightheightint / 2, 0, 360);
            g.fillArc(tenpercw * 3, tenperch * 3, eightwidthint / 2, eightheightint / 2, 0, 360);
        }

    }

    public void actionPerformed(ActionEvent e)
    {
        if (Checkers.firstClicked() == false)
        {
          Checkers.setFirstClick(placement);
          clicked = 1;
        }
        else
        {
            Checkers.setSecondClick(placement);
        }
    }


}
