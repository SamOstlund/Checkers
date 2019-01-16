//Name: Sam Ostlund, Tyler Gurley
import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JInternalFrame;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import java.awt.event.KeyEvent;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.JLabel;

public class Checkers extends JFrame
{
    private static int board[][]; //0 for empty spot, 1 for player 1, 2 for player 2, 3 for king player 1, 4 for king player 2
    private BlTile blacks[];
    private WhTile whites[];
    private static int turn; //holds what player's turn it is
    private static int firstclick;
    private static int secondclick;
    private static int hopped;

    public static void main(String[] args)
    {
        Checkers game = new Checkers();
        game.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        game.setSize( 800, 600 ); // set frame size
        game.setVisible( true ); // display frame
        game.game();
    }

    public Checkers()
    {
        super("Checkers");
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        JMenu changeturn = new JMenu("Switch Turns");
        menuBar.add(changeturn);
        JMenuItem changet = new JMenuItem("Switch"); //allows players to switch turns after a jump
        changet.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));
        changeturn.add(changet);
        changet.addActionListener(
          new ActionListener()
          {
            @Override
            public void actionPerformed(ActionEvent did)
            {
              switchTurn();
            }
          }
        );
        JMenu options = new JMenu("Options");
        options.setMnemonic(KeyEvent.VK_O);
        menuBar.add(options);
        JMenu quit = new JMenu("Quit");
        quit.setMnemonic(KeyEvent.VK_Q);
        menuBar.add(quit);
        JMenu forfeit = new JMenu("Forfeit");
        JMenuItem p1forfeit = new JMenuItem("Player 1 Forfeit");
        p1forfeit.addActionListener(
          new ActionListener()
          {
            @Override
            public void actionPerformed(ActionEvent did)
            {
              JLabel victory2 = new JLabel("Player 2 Wins by Forfeit", JLabel.CENTER);
              JOptionPane.showMessageDialog(null, victory2, "And the winner is...", JOptionPane.PLAIN_MESSAGE);
              System.exit(0);
            }
          }
        );
        JMenuItem p2forfeit = new JMenuItem("Player 2 Forfeit");
        p2forfeit.addActionListener(
          new ActionListener()
          {
            @Override
            public void actionPerformed(ActionEvent did)
            {
              JLabel victory1 = new JLabel("Player 1 Wins by Forfeit", JLabel.CENTER);
              JOptionPane.showMessageDialog(null, victory1, "And the winner is...", JOptionPane.PLAIN_MESSAGE);
              System.exit(0);
            }
          }
        );
        forfeit.add(p1forfeit);
        forfeit.add(p2forfeit);
        options.add(forfeit);

        JMenu player1color = new JMenu("Player 1 Color"); //Menu that will allow for changing of player 1's color
        JMenuItem colorblue = new JMenuItem("Blue");
        colorblue.addActionListener(
          new ActionListener()
          {
            @Override
            public void actionPerformed(ActionEvent did)
            {
              if(BlTile.player2co != Color.BLUE)
              {
                BlTile.player1co = Color.BLUE;
                rePaint();
              }
            }
          }
        );
        JMenuItem colorgreen = new JMenuItem("Green");
        colorgreen.addActionListener(
          new ActionListener()
          {
            @Override
            public void actionPerformed(ActionEvent did)
            {
              if(BlTile.player2co != Color.GREEN)
              {
                  BlTile.player1co = Color.GREEN;
                  rePaint();
              }
            }
          }
        );
        JMenuItem colorcyan = new JMenuItem("Cyan");
        colorcyan.addActionListener(
          new ActionListener()
          {
            @Override
            public void actionPerformed(ActionEvent did)
            {
              if(BlTile.player2co != Color.CYAN)
              {
                BlTile.player1co = Color.CYAN;
                rePaint();
              }
            }
          }
        );
        JMenuItem colorred = new JMenuItem("Red");
        colorred.addActionListener(
          new ActionListener()
          {
            @Override
            public void actionPerformed(ActionEvent did)
            {
              if(BlTile.player2co != Color.RED)
              {
                BlTile.player1co = Color.RED;
                rePaint();
              }
            }
          }
        );
        JMenuItem colormagenta = new JMenuItem("Magenta");
        colormagenta.addActionListener(
          new ActionListener()
          {
            @Override
            public void actionPerformed(ActionEvent did)
            {
              if(BlTile.player2co != Color.MAGENTA)
              {
                BlTile.player1co = Color.MAGENTA;
                rePaint();
              }
            }
          }
        );
        player1color.add(colorblue);
        player1color.add(colorgreen);
        player1color.add(colorcyan);
        player1color.add(colorred);
        player1color.add(colormagenta);
        options.add(player1color);

        JMenu player2color = new JMenu("Player 2 Color"); //Menu that will allow for changing of player 2's color
        JMenuItem colorblue2 = new JMenuItem("Blue");
        colorblue2.addActionListener(
          new ActionListener()
          {
            @Override
            public void actionPerformed(ActionEvent did)
            {
              if(BlTile.player1co != Color.BLUE)
              {
                BlTile.player2co = Color.BLUE;
                rePaint();
              }
            }
          }
        );
        JMenuItem colorgreen2 = new JMenuItem("Green");
        colorgreen2.addActionListener(
          new ActionListener()
          {
            @Override
            public void actionPerformed(ActionEvent did)
            {
              if(BlTile.player1co != Color.GREEN)
              {
                BlTile.player2co = Color.GREEN;
                rePaint();
              }
            }
          }
        );
        JMenuItem colorcyan2 = new JMenuItem("Cyan");
        colorcyan2.addActionListener(
          new ActionListener()
          {
            @Override
            public void actionPerformed(ActionEvent did)
            {
              if(BlTile.player1co != Color.CYAN)
              {
                BlTile.player2co = Color.CYAN;
                rePaint();
              }
            }
          }
        );
        JMenuItem colorred2 = new JMenuItem("Red");
        colorred2.addActionListener(
          new ActionListener()
          {
            @Override
            public void actionPerformed(ActionEvent did)
            {
              if(BlTile.player1co != Color.RED)
              {
                BlTile.player2co = Color.RED;
                rePaint();
              }
            }
          }
        );
        JMenuItem colormagenta2 = new JMenuItem("Magenta");
        colormagenta2.addActionListener(
          new ActionListener()
          {
            @Override
            public void actionPerformed(ActionEvent did)
            {
              if(BlTile.player1co != Color.MAGENTA)
              {
                BlTile.player2co = Color.MAGENTA;
                rePaint();
              }
            }
          }
        );
        player2color.add(colorblue2);
        player2color.add(colorgreen2);
        player2color.add(colorcyan2);
        player2color.add(colorred2);
        player2color.add(colormagenta2);
        options.add(player2color);

        JMenuItem restartgame = new JMenuItem("Restart Game", 'r'); //Menu item that will allow the game pieces to be reset
        restartgame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, KeyEvent.CTRL_DOWN_MASK)); //CTRL + R will be used as a shortcut
        options.add(restartgame);
        restartgame.addActionListener(
          new ActionListener()
          {
            @Override
            public void actionPerformed(ActionEvent did)
            {
              turn = 1;
              resetBoard();
              rePaint();
            }
          }
        );

        JMenuItem exitprogram = new JMenuItem("Exit Program", 'x');   //Menu Item to exit the game
        exitprogram.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, KeyEvent.CTRL_DOWN_MASK)); //exit game has a CTRL + X shortcut
        quit.add(exitprogram);
        exitprogram.addActionListener(
          new ActionListener()
          {
            @Override
            public void actionPerformed(ActionEvent did)
            {
              System.exit(0);
            }
          }
        );

        board = new int[8][8]; //8 x 8 int array containing all 0's
        resetBoard(); //sets up the default board with all pieces in their starting spots
        setLayout(new GridLayout(8,8,0,0)); //creates a grid layout
        blacks = new BlTile[32];
        whites = new WhTile[32];
        int counter = 1;
        for (int i = 0; i < 8; i++) //creates 32 white tiles and 32 black tiles
        {
            for (int j = 0; j < 4; j++)
            {
                if (i % 2 == 0)
                {
                    blacks[(i * 4) + j] = new BlTile(counter);
                    counter++;
                    whites[(i * 4) + j] = new WhTile();
                    counter++;
                }
                if (i % 2 == 1)
                {
                    whites[(i * 4) + j] = new WhTile();
                    counter++;
                    blacks[(i * 4) + j] = new BlTile(counter);
                    counter++;
                }
            }
        }
        for (int i = 0; i < 8; i++) //adds the tiles to the frame creating the board
        {
            for (int j = 0; j < 4; j++)
            {
                if (i % 2 == 0)
                {
                    add(blacks[(i * 4) + j]);
                    add(whites[(i * 4) + j]);
                }
                if (i % 2 == 1)
                {
                    add(whites[(i * 4) + j]);
                    add(blacks[(i * 4) + j]);
                }
            }
        }
        firstclick = 0;
        secondclick = 0;
        turn = 1;

    }

    private void resetBoard() //resets the board to their starting spots
    {
        for (int i = 0; i < 8; i = i+2) //sets up the original board
        {
            board[0][i] = 1;
            board[1][i+1] = 1;
            board[2][i] = 1;
            board[5][i+1] = 2;
            board[6][i] = 2;
            board[7][i+1] = 2;
        }
        for(int j = 0; j < 8; j++)
        {
            board[3][j] = 0;
            board[4][j] = 0;
        }
        turn = 1;
        hopped = 0;
    }

    public void game() //runs the actual game in the window
    {
        while (player1Win() == false && player2Win() == false) //goes until someone wins
        {
            int clicked = 0;
            hopped = 0;
            while (turn == 1)
            {
                int didmove = 0;
                while (firstClicked() == false || secondClicked() == false && hopped == 0) //while neither tiles are picked, it waits
                {
                    try {
                        wait();
                    }
                    catch(Exception ex){}
                }
                while (hopped >= 1 && secondClicked() == false)
                {
                   try {
                        wait();
                    }
                   catch (Exception ex){}
                }
                if (hopped >= 1 && firstclick == secondclick) //clicking the same piece after a hop switches turns
                {
                    switchTurn();
                    break;
                }
                if (firstclick == secondclick)
                    resetClicks();
                if (hopped >= 1 && firstClicked() != false && secondClicked() != false && clicked == firstclick) //when both clicks are completed
                {
                        if (isP1Hop(clicked,secondclick) == false)
                           switchTurn();
                        if (isP1Hop(clicked,secondclick) == true)
                        {
                            p1Move(firstclick,secondclick);
                            clicked = secondclick;
                            didmove = 1;
                            hopped = 1;
                        }
                        kingCheck();
                        rePaint();
                }
                if (isP1Move(firstclick, secondclick) == false && hopped == 0 && didmove == 0) //bad move, restart move
                {
                    firstclick = 0;
                    secondclick = 0;
                    rePaint();
                }

                if (isP1Move(firstclick, secondclick) == true && hopped == 0 && didmove == 0) //good move, does it
                {
                    int first = firstclick;
                    clicked = secondclick;
                    int second = secondclick;
                    resetClicks();
                    if (isP1Hop(first,second) == true)
                        hopped++;
                    p1Move(first,second);
                    kingCheck();
                    repaint();
                }
            }
            hopped = 0;
            if(player1Win() == true || player2Win() == true) //if a player wins in the middle of the while loop it will break
            {
                break;
            }
            while (turn == 2)
            {
                int didmove = 0;
                while (firstClicked() == false || secondClicked() == false && hopped == 0) //while neither tiles are picked, it waits
                {
                    try {
                        wait();
                    }
                    catch(Exception ex){}
                }
                while (hopped >= 1 && secondClicked() == false)
                {
                   try {
                        wait();
                    }
                   catch (Exception ex){}
                }
                if (hopped >= 1 && firstclick == secondclick) //clicking the same piece after jump switches turns
                {
                    switchTurn();
                    break;
                }
                if (firstclick == secondclick)
                    resetClicks();
                if (hopped >= 1 && firstClicked() != false && secondClicked() != false && clicked == firstclick) // both clicks are completed
                {
                        if (isP2Hop(clicked,secondclick) == false)
                           switchTurn();
                        if (isP2Hop(clicked,secondclick) == true)
                        {
                            p2Move(firstclick,secondclick);
                            clicked = secondclick;
                            didmove = 1;
                            hopped = 1;
                        }
                        kingCheck();
                        rePaint();
                }
                if (isP2Move(firstclick, secondclick) == false && hopped == 0 && didmove == 0) //bad move, restart move
                {
                    firstclick = 0;
                    secondclick = 0;
                    rePaint();
                }

                if (isP2Move(firstclick, secondclick) == true && hopped == 0 && didmove == 0) //good move, does it
                {
                    int first = firstclick;
                    clicked = secondclick;
                    int second = secondclick;
                    resetClicks();
                    if (isP2Hop(first,second) == true)
                        hopped++;
                    p2Move(first,second);
                    kingCheck();
                    repaint();
                }
            }
        }
        if(player1Win() == true)
        {
          JLabel victory = new JLabel("Player 1 Wins", JLabel.CENTER);
          JOptionPane.showMessageDialog(this, victory, "And the winner is...", JOptionPane.PLAIN_MESSAGE); //Popup for player 1 winning
        }
        else if(player2Win() == true)
        {
          JLabel victory = new JLabel("Player 2 Wins", JLabel.CENTER);
          JOptionPane.showMessageDialog(this, victory, "And the winner is...", JOptionPane.PLAIN_MESSAGE);  //Popup for player 2 winning
        }
        System.exit(0);
    }

    private boolean isP1Hop(int start, int end) //goes through all possible hop options and determines if the move is a valid hop for p1
    {
        if(start == end)
            return false;
        if (occupied(start) != 1 && occupied(start) != 3) //if the starting spot is not a player 1 piece, returns false
            return false;
        if (occupied(end) != 0) //if the ending piece is not empty, returns false
            return false;
        int strow = 0; //variables for the starting row/column and ending row/column
        int stcolumn = 0;
        int enrow = 0;
        int encolumn = 0;
        if (occupied(start) == 1)  //regular move
        {
            while (start > 8) //finds row and column in array for start
            {
                strow++;
                start = start - 8;
            }
            stcolumn = start - 1;
            while (end > 8) //finds row an column in array for end
            {
                enrow++;
                end = end - 8;
            }
            encolumn = end - 1;
            if (stcolumn == 0) //if in the first slot of a row
            {
                if (enrow == strow + 2 && encolumn == stcolumn + 2) //can only attack to the right 2 and down 2
                {
                   int tarrow = strow + 1;
                   int tarcolumn = stcolumn + 1;
                   int target = (tarrow * 8) + tarcolumn + 1;
                   if (occupied(target) == 2 || occupied(target) == 4)
                       return true;
               }
            }
            if (stcolumn == 1)
            {
                if (enrow == strow + 2 && encolumn == stcolumn + 2)
                {
                   int tarrow = strow + 1;
                   int tarcolumn = stcolumn + 1;
                   int target = (tarrow * 8) + tarcolumn + 1;
                   if (occupied(target) == 2 || occupied(target) == 4)
                       return true;
                }
            }
            if (stcolumn > 1 && stcolumn < 6)
            {
                if (enrow == strow + 2 && encolumn == stcolumn + 2)
                {
                   int tarrow = strow + 1;
                   int tarcolumn = stcolumn + 1;
                   int target = (tarrow * 8) + tarcolumn + 1;
                   if (occupied(target) == 2 || occupied(target) == 4)
                       return true;
                }
                if (enrow == strow + 2 && encolumn == stcolumn - 2)
                {
                   int tarrow = strow + 1;
                   int tarcolumn = stcolumn - 1;
                   int target = (tarrow * 8) + tarcolumn + 1;
                   if (occupied(target) == 2 || occupied(target) == 4)
                       return true;
                }
            }
            if (stcolumn == 6)
            {
                if (enrow == strow + 2 && encolumn == stcolumn - 2)
                {
                   int tarrow = strow + 1;
                   int tarcolumn = stcolumn - 1;
                   int target = (tarrow * 8) + tarcolumn + 1;
                   if (occupied(target) == 2 || occupied(target) == 4)
                       return true;
                }
            }
            if (stcolumn == 7)
            {
                if (enrow == strow + 2 && encolumn == stcolumn - 2)
                {
                   int tarrow = strow + 1;
                   int tarcolumn = stcolumn - 1;
                   int target = (tarrow * 8) + tarcolumn + 1;
                   if (occupied(target) == 2 || occupied(target) == 4)
                       return true;
               }
            }
        }
        if (occupied(start) == 3) //goes through possible king hops
        {
            while (start > 8) //finds row and column in array for start
            {
                strow++;
                start = start - 8;
            }
            stcolumn = start - 1;
            while (end > 8) //finds row and column in array for end
            {
                enrow++;
                end = end - 8;
            }
            encolumn = end - 1;
            if (stcolumn == 0) //if in the first slot of a row
            {
                if (enrow == strow + 2 && encolumn == stcolumn + 2) //can only attack to the right 2 and down 2
                {
                   int tarrow = strow + 1;
                   int tarcolumn = stcolumn + 1;
                   int target = (tarrow * 8) + tarcolumn + 1;
                   if (occupied(target) == 2 || occupied(target) == 4)
                       return true;
               }
            }
            if (stcolumn == 1)
            {
                if (enrow == strow + 2 && encolumn == stcolumn + 2)
                {
                   int tarrow = strow + 1;
                   int tarcolumn = stcolumn + 1;
                   int target = (tarrow * 8) + tarcolumn + 1;
                   if (occupied(target) == 2 || occupied(target) == 4)
                       return true;
                }
            }
            if (stcolumn > 1 && stcolumn < 6)
            {
                if (enrow == strow + 2 && encolumn == stcolumn + 2)
                {
                   int tarrow = strow + 1;
                   int tarcolumn = stcolumn + 1;
                   int target = (tarrow * 8) + tarcolumn + 1;
                   if (occupied(target) == 2 || occupied(target) == 4)
                       return true;
                }
                if (enrow == strow + 2 && encolumn == stcolumn - 2)
                {
                   int tarrow = strow + 1;
                   int tarcolumn = stcolumn - 1;
                   int target = (tarrow * 8) + tarcolumn + 1;
                   if (occupied(target) == 2 || occupied(target) == 4)
                       return true;
                }
            }
            if (stcolumn == 6)
            {
                if (enrow == strow + 2 && encolumn == stcolumn - 2)
                {
                   int tarrow = strow + 1;
                   int tarcolumn = stcolumn - 1;
                   int target = (tarrow * 8) + tarcolumn + 1;
                   if (occupied(target) == 2 || occupied(target) == 4)
                       return true;
                }
            }
            if (stcolumn == 7)
            {
                if (enrow == strow + 2 && encolumn == stcolumn - 2)
                {
                   int tarrow = strow + 1;
                   int tarcolumn = stcolumn - 1;
                   int target = (tarrow * 8) + tarcolumn + 1;
                   if (occupied(target) == 2 || occupied(target) == 4)
                       return true;
               }
            }
            if (stcolumn == 0) //if in the first slot of a row
            {
               if (enrow == strow - 2 && encolumn == stcolumn + 2) //can only attack to the right 2 and down 2
               {
                   int tarrow = strow - 1;
                   int tarcolumn = stcolumn + 1;
                   int target = (tarrow * 8) + tarcolumn + 1;
                   if (occupied(target) == 2 || occupied(target) == 4)
                       return true;
               }
            }
            if (stcolumn == 1) //if in the second slot, can move either way but only attack right
            {
                if (enrow == strow - 2 && encolumn == stcolumn + 2)
                {
                   int tarrow = strow - 1;
                   int tarcolumn = stcolumn + 1;
                   int target = (tarrow * 8) + tarcolumn + 1;
                   if (occupied(target) == 2 || occupied(target) == 4)
                       return true;
                }
            }
            if (stcolumn > 1 && stcolumn < 6) //can move to right or left and can attack both ways as well
            {
               if (enrow == strow - 2 && encolumn == stcolumn + 2)
               {
                   int tarrow = strow - 1;
                   int tarcolumn = stcolumn + 1;
                   int target = (tarrow * 8) + tarcolumn + 1;
                   if (occupied(target) == 2 || occupied(target) == 4)
                        return true;
               }
               if (enrow == strow - 2 && encolumn == stcolumn - 2)
               {
                   int tarrow = strow - 1;
                   int tarcolumn = stcolumn - 1;
                   int target = (tarrow * 8) + tarcolumn + 1;
                   if (occupied(target) == 2 || occupied(target) == 4)
                       return true;
               }
            }
            if (stcolumn == 6) //if in the second to last spot, can go both ways but only attack left
            {
                if (enrow == strow - 2 && encolumn == stcolumn - 2)
                {
                   int tarrow = strow - 1;
                   int tarcolumn = stcolumn - 1;
                   int target = (tarrow * 8) + tarcolumn + 1;
                   if (occupied(target) == 2 || occupied(target) == 4)
                       return true;
                }
            }
            if (stcolumn == 7) //if in the right slot you can only go left and attack left
            {
               if (enrow == strow - 2 && encolumn == stcolumn - 2)
               {
                   int tarrow = strow - 1;
                   int tarcolumn = stcolumn - 1;
                   int target = (tarrow * 8) + tarcolumn + 1;
                   if (occupied(target) == 2 || occupied(target) == 4)
                       return true;
               }
            }

        }
        return false;
    }

    private void kingCheck() //Checks for king pieces and makes the king pieces if available
    {
        for (int i = 0; i < 8; i++)
        {
            if (board[0][i] == 2)
                board[0][i] = 4;
            if (board[7][i] == 1)
                board[7][i] = 3;
        }
    }

    private boolean isP1Move(int start, int end)
    {
        if(start == end)
            return false;
        if (occupied(start) != 1 && occupied(start) != 3) //if the starting spot is not a player 1 piece, returns false
            return false;
        if (occupied(end) != 0) //if the ending piece is not empty, returns false
            return false;
        int strow = 0; //variables for the starting row/column and ending row/column
        int stcolumn = 0;
        int enrow = 0;
        int encolumn = 0;
        if (occupied(start) == 1)  //regular move, not king
        {
            while (start > 8) //finds row and column in array for start
            {
                strow++;
                start = start - 8;
            }
            stcolumn = start - 1;
            while (end > 8) //finds row an column in array for end
            {
                enrow++;
                end = end - 8;
            }
            encolumn = end - 1;
            if (stcolumn == 0) //if in the first slot of a row
            {
                if (enrow == strow + 1 && encolumn == stcolumn + 1) //can only move to the right one and down 1
                {
                    return true;
                }
               if (enrow == strow + 2 && encolumn == stcolumn + 2) //can only attack to the right 2 and down 2
               {
                   int tarrow = strow + 1;
                   int tarcolumn = stcolumn + 1;
                   int target = (tarrow * 8) + tarcolumn + 1;
                   if (occupied(target) == 2 || occupied(target) == 4)
                   {
                       return true;
                   }
               }
            }
            if (stcolumn == 1) //if in the second slot, can move either way but only attack one way
            {
                if ((enrow == strow + 1 && encolumn == stcolumn + 1) || (enrow == strow + 1 && encolumn == stcolumn - 1))
                {
                    return true;
                }
                if (enrow == strow + 2 && encolumn == stcolumn + 2)
                {
                   int tarrow = strow + 1;
                   int tarcolumn = stcolumn + 1;
                   int target = (tarrow * 8) + tarcolumn + 1;
                   if (occupied(target) == 2 || occupied(target) == 4)
                   {
                       return true;
                   }
                }
            }
            if (stcolumn > 1 && stcolumn < 6) //can move to right or left and can attack both ways as well
            {
                if ((enrow == strow + 1 && encolumn == stcolumn + 1) || (enrow == strow + 1 && encolumn == stcolumn - 1))
                {
                    return true;
                }
               if (enrow == strow + 2 && encolumn == stcolumn + 2)
               {
                   int tarrow = strow + 1;
                   int tarcolumn = stcolumn + 1;
                   int target = (tarrow * 8) + tarcolumn + 1;
                   if (occupied(target) == 2 || occupied(target) == 4)
                   {
                       return true;
                   }
               }
               if (enrow == strow + 2 && encolumn == stcolumn - 2)
               {
                   int tarrow = strow + 1;
                   int tarcolumn = stcolumn - 1;
                   int target = (tarrow * 8) + tarcolumn + 1;
                   if (occupied(target) == 2 || occupied(target) == 4)
                   {
                       return true;
                   }
               }
            }
            if (stcolumn == 6) //if in the second to last spot, can go both ways but only attack left
            {
                if ((enrow == strow + 1 && encolumn == stcolumn + 1) || (enrow == strow + 1 && encolumn == stcolumn - 1))
                {
                    return true;
                }
                if (enrow == strow + 2 && encolumn == stcolumn - 2)
                {
                   int tarrow = strow + 1;
                   int tarcolumn = stcolumn - 1;
                   int target = (tarrow * 8) + tarcolumn + 1;
                   if (occupied(target) == 2 || occupied(target) == 4)
                   {
                       return true;
                   }
                }
            }
            if (stcolumn == 7) //if in the right slot you can only go left and attack left
            {
                if (enrow == strow + 1 && encolumn == stcolumn - 1)
                {
                    return true;
                }
               if (enrow == strow + 2 && encolumn == stcolumn - 2)
               {
                   int tarrow = strow + 1;
                   int tarcolumn = stcolumn - 1;
                   int target = (tarrow * 8) + tarcolumn + 1;
                   if (occupied(target) == 2 || occupied(target) == 4)
                   {
                       return true;
                   }
               }
            }

        }
        if (occupied(start) == 3) //goes through possible king hops
        {
            while (start > 8) //finds row and column in array for start
            {
                strow++;
                start = start - 8;
            }
            stcolumn = start - 1;
            while (end > 8) //finds row and column in array for end
            {
                enrow++;
                end = end - 8;
            }
            encolumn = end - 1;
            if (stcolumn == 0) //if in the first slot of a row
            {
                if (enrow == strow + 1 && encolumn == stcolumn + 1) //can only move to the right one and down 1
                {
                    return true;
                }
               if (enrow == strow + 2 && encolumn == stcolumn + 2) //can only attack to the right 2 and down 2
               {
                   int tarrow = strow + 1;
                   int tarcolumn = stcolumn + 1;
                   int target = (tarrow * 8) + tarcolumn + 1;
                   if (occupied(target) == 2 || occupied(target) == 4)
                   {
                       return true;
                   }
               }
            }
            if (stcolumn == 1) //if in the second slot, can move either way but only attack one way
            {
                if ((enrow == strow + 1 && encolumn == stcolumn + 1) || (enrow == strow + 1 && encolumn == stcolumn - 1))
                {
                    return true;
                }
                if (enrow == strow + 2 && encolumn == stcolumn + 2)
                {
                   int tarrow = strow + 1;
                   int tarcolumn = stcolumn + 1;
                   int target = (tarrow * 8) + tarcolumn + 1;
                   if (occupied(target) == 2 || occupied(target) == 4)
                   {
                       return true;
                   }
                }
            }
            if (stcolumn > 1 && stcolumn < 6) //can move to right or left and can attack both ways as well
            {
                if ((enrow == strow + 1 && encolumn == stcolumn + 1) || (enrow == strow + 1 && encolumn == stcolumn - 1))
                {
                    return true;
                }
               if (enrow == strow + 2 && encolumn == stcolumn + 2)
               {
                   int tarrow = strow + 1;
                   int tarcolumn = stcolumn + 1;
                   int target = (tarrow * 8) + tarcolumn + 1;
                   if (occupied(target) == 2 || occupied(target) == 4)
                   {
                       return true;
                   }
               }
               if (enrow == strow + 2 && encolumn == stcolumn - 2)
               {
                   int tarrow = strow + 1;
                   int tarcolumn = stcolumn - 1;
                   int target = (tarrow * 8) + tarcolumn + 1;
                   if (occupied(target) == 2 || occupied(target) == 4)
                   {
                       return true;
                   }
               }
            }
            if (stcolumn == 6) //if in the second to last spot, can go both ways but only attack left
            {
                if ((enrow == strow + 1 && encolumn == stcolumn + 1) || (enrow == strow + 1 && encolumn == stcolumn - 1))
                {
                    return true;
                }
                if (enrow == strow + 2 && encolumn == stcolumn - 2)
                {
                   int tarrow = strow + 1;
                   int tarcolumn = stcolumn - 1;
                   int target = (tarrow * 8) + tarcolumn + 1;
                   if (occupied(target) == 2 || occupied(target) == 4)
                   {
                       return true;
                   }
                }
            }
            if (stcolumn == 7) //if in the right slot you can only go left and attack left
            {
                if (enrow == strow + 1 && encolumn == stcolumn - 1)
                {
                    return true;
                }
               if (enrow == strow + 2 && encolumn == stcolumn - 2)
               {
                   int tarrow = strow + 1;
                   int tarcolumn = stcolumn - 1;
                   int target = (tarrow * 8) + tarcolumn + 1;
                   if (occupied(target) == 2 || occupied(target) == 4)
                   {
                       return true;
                   }
               }
            }
            if (stcolumn == 0) //if in the first slot of a row
            {
                if (enrow == strow - 1 && encolumn == stcolumn + 1) //can only move to the right one and down 1
                {
                    return true;
                }
               if (enrow == strow - 2 && encolumn == stcolumn + 2) //can only attack to the right 2 and down 2
               {
                   int tarrow = strow - 1;
                   int tarcolumn = stcolumn + 1;
                   int target = (tarrow * 8) + tarcolumn + 1;
                   if (occupied(target) == 2 || occupied(target) == 4)
                   {
                       return true;
                   }
               }
            }
            if (stcolumn == 1) //if in the second slot, can move either way but only attack right
            {
                if ((enrow == strow - 1 && encolumn == stcolumn - 1) || (enrow == strow - 1 && encolumn == stcolumn + 1))
                {
                    return true;
                }
                if (enrow == strow - 2 && encolumn == stcolumn + 2)
                {
                   int tarrow = strow - 1;
                   int tarcolumn = stcolumn + 1;
                   int target = (tarrow * 8) + tarcolumn + 1;
                   if (occupied(target) == 2 || occupied(target) == 4)
                   {
                       return true;
                   }
                }
            }
            if (stcolumn > 1 && stcolumn < 6) //can move to right or left and can attack both ways as well
            {
                if ((enrow == strow - 1 && encolumn == stcolumn + 1) || (enrow == strow - 1 && encolumn == stcolumn - 1))
                {
                    return true;
                }
               if (enrow == strow - 2 && encolumn == stcolumn + 2)
               {
                   int tarrow = strow - 1;
                   int tarcolumn = stcolumn + 1;
                   int target = (tarrow * 8) + tarcolumn + 1;
                   if (occupied(target) == 2 || occupied(target) == 4)
                   {
                       return true;
                   }
               }
               if (enrow == strow - 2 && encolumn == stcolumn - 2)
               {
                   int tarrow = strow - 1;
                   int tarcolumn = stcolumn - 1;
                   int target = (tarrow * 8) + tarcolumn + 1;
                   if (occupied(target) == 2 || occupied(target) == 4)
                   {
                       return true;
                   }
               }
            }
            if (stcolumn == 6) //if in the second to last spot, can go both ways but only attack left
            {
                if ((enrow == strow - 1 && encolumn == stcolumn + 1) || (enrow == strow - 1 && encolumn == stcolumn - 1))
                {
                    return true;
                }
                if (enrow == strow - 2 && encolumn == stcolumn - 2)
                {
                   int tarrow = strow - 1;
                   int tarcolumn = stcolumn - 1;
                   int target = (tarrow * 8) + tarcolumn + 1;
                   if (occupied(target) == 2 || occupied(target) == 4)
                   {
                       return true;
                   }
                }
            }
            if (stcolumn == 7) //if in the right slot you can only go left and attack left
            {
                if (enrow == strow - 1 && encolumn == stcolumn - 1)
                {
                    return true;
                }
               if (enrow == strow - 2 && encolumn == stcolumn - 2)
               {
                   int tarrow = strow - 1;
                   int tarcolumn = stcolumn - 1;
                   int target = (tarrow * 8) + tarcolumn + 1;
                   if (occupied(target) == 2 || occupied(target) == 4)
                   {
                       return true;
                   }
               }
            }
        }
        return false; //if it makes it through to here then it isn't a valid move
    }

    private boolean isP2Hop(int start, int end) //goes through all possible hop options and determines if the move is a valid hop for p2
    {
        if (start == end)
            return false;
        if (start == 0 || end == 0)
            return false;
        if (occupied(start) != 2 && occupied(start) != 4) //if the starting spot is not a player 1 piece, returns false
            return false;
        if (occupied(end) != 0) //if the ending piece is not empty, returns false
            return false;
        int strow = 0; //variables for the starting row/column and ending row/column
        int stcolumn = 0;
        int enrow = 0;
        int encolumn = 0;
        if (occupied(start) == 2)  //regular move, not king
        {
            while (start > 8) //finds row and column in array for start
            {
                strow++;
                start = start - 8;
            }
            stcolumn = start - 1;
            while (end > 8) //finds row an column in array for end
            {
                enrow++;
                end = end - 8;
            }
            encolumn = end - 1;
            if (stcolumn == 0) //if in the first slot of a row
            {
               if (enrow == strow - 2 && encolumn == stcolumn + 2) //can only attack to the right 2 and down 2
               {
                   int tarrow = strow - 1;
                   int tarcolumn = stcolumn + 1;
                   int target = (tarrow * 8) + tarcolumn + 1;
                   if (occupied(target) == 1 || occupied(target) == 3)
                       return true;
               }
            }
            if (stcolumn == 1) //if in the second slot, can move either way but only attack right
            {
                if (enrow == strow - 2 && encolumn == stcolumn + 2)
                {
                   int tarrow = strow - 1;
                   int tarcolumn = stcolumn + 1;
                   int target = (tarrow * 8) + tarcolumn + 1;
                   if (occupied(target) == 1 || occupied(target) == 3)
                       return true;
                }
            }
            if (stcolumn > 1 && stcolumn < 6) //can move to right or left and can attack both ways as well
            {
               if (enrow == strow - 2 && encolumn == stcolumn + 2)
               {
                   int tarrow = strow - 1;
                   int tarcolumn = stcolumn + 1;
                   int target = (tarrow * 8) + tarcolumn + 1;
                   if (occupied(target) == 1 || occupied(target) == 3)
                        return true;
               }
               if (enrow == strow - 2 && encolumn == stcolumn - 2)
               {
                   int tarrow = strow - 1;
                   int tarcolumn = stcolumn - 1;
                   int target = (tarrow * 8) + tarcolumn + 1;
                   if (occupied(target) == 1 || occupied(target) == 3)
                       return true;
               }
            }
            if (stcolumn == 6) //if in the second to last spot, can go both ways but only attack left
            {
                if (enrow == strow - 2 && encolumn == stcolumn - 2)
                {
                   int tarrow = strow - 1;
                   int tarcolumn = stcolumn - 1;
                   int target = (tarrow * 8) + tarcolumn + 1;
                   if (occupied(target) == 1 || occupied(target) == 3)
                       return true;
                }
            }
            if (stcolumn == 7) //if in the right slot you can only go left and attack left
            {
               if (enrow == strow - 2 && encolumn == stcolumn - 2)
               {
                   int tarrow = strow - 1;
                   int tarcolumn = stcolumn - 1;
                   int target = (tarrow * 8) + tarcolumn + 1;
                   if (occupied(target) == 1 || occupied(target) == 3)
                       return true;
               }
            }
        }
        if (occupied(start) == 4)
        {
            while (start > 8) //finds row and column in array for start
            {
                strow++;
                start = start - 8;
            }
            stcolumn = start - 1;
            while (end > 8) //finds row and column in array for end
            {
                enrow++;
                end = end - 8;
            }
            encolumn = end - 1;
            if (stcolumn == 0) //if in the first slot of a row
            {
               if (enrow == strow - 2 && encolumn == stcolumn + 2) //can only attack to the right 2 and down 2
               {
                   int tarrow = strow - 1;
                   int tarcolumn = stcolumn + 1;
                   int target = (tarrow * 8) + tarcolumn + 1;
                   if (occupied(target) == 1 || occupied(target) == 3)
                       return true;
               }
            }
            if (stcolumn == 1) //if in the second slot, can move either way but only attack right
            {
                if (enrow == strow - 2 && encolumn == stcolumn + 2)
                {
                   int tarrow = strow - 1;
                   int tarcolumn = stcolumn + 1;
                   int target = (tarrow * 8) + tarcolumn + 1;
                   if (occupied(target) == 1 || occupied(target) == 3)
                       return true;
                }
            }
            if (stcolumn > 1 && stcolumn < 6) //can move to right or left and can attack both ways as well
            {
               if (enrow == strow - 2 && encolumn == stcolumn + 2)
               {
                   int tarrow = strow - 1;
                   int tarcolumn = stcolumn + 1;
                   int target = (tarrow * 8) + tarcolumn + 1;
                   if (occupied(target) == 1 || occupied(target) == 3)
                        return true;
               }
               if (enrow == strow - 2 && encolumn == stcolumn - 2)
               {
                   int tarrow = strow - 1;
                   int tarcolumn = stcolumn - 1;
                   int target = (tarrow * 8) + tarcolumn + 1;
                   if (occupied(target) == 1 || occupied(target) == 3)
                       return true;
               }
            }
            if (stcolumn == 6) //if in the second to last spot, can go both ways but only attack left
            {
                if (enrow == strow - 2 && encolumn == stcolumn - 2)
                {
                   int tarrow = strow - 1;
                   int tarcolumn = stcolumn - 1;
                   int target = (tarrow * 8) + tarcolumn + 1;
                   if (occupied(target) == 1 || occupied(target) == 3)
                       return true;
                }
            }
            if (stcolumn == 7) //if in the right slot you can only go left and attack left
            {
               if (enrow == strow - 2 && encolumn == stcolumn - 2)
               {
                   int tarrow = strow - 1;
                   int tarcolumn = stcolumn - 1;
                   int target = (tarrow * 8) + tarcolumn + 1;
                   if (occupied(target) == 1 || occupied(target) == 3)
                       return true;
               }
            }
            if (stcolumn == 0) //if in the first slot of a row
            {
                if (enrow == strow + 2 && encolumn == stcolumn + 2) //can only attack to the right 2 and down 2
                {
                   int tarrow = strow + 1;
                   int tarcolumn = stcolumn + 1;
                   int target = (tarrow * 8) + tarcolumn + 1;
                   if (occupied(target) == 1 || occupied(target) == 3)
                       return true;
               }
            }
            if (stcolumn == 1)
            {
                if (enrow == strow + 2 && encolumn == stcolumn + 2)
                {
                   int tarrow = strow + 1;
                   int tarcolumn = stcolumn + 1;
                   int target = (tarrow * 8) + tarcolumn + 1;
                   if (occupied(target) == 1 || occupied(target) == 3)
                       return true;
                }
            }
            if (stcolumn > 1 && stcolumn < 6)
            {
                if (enrow == strow + 2 && encolumn == stcolumn + 2)
                {
                   int tarrow = strow + 1;
                   int tarcolumn = stcolumn + 1;
                   int target = (tarrow * 8) + tarcolumn + 1;
                   if (occupied(target) == 1 || occupied(target) == 3)
                       return true;
                }
                if (enrow == strow + 2 && encolumn == stcolumn - 2)
                {
                   int tarrow = strow + 1;
                   int tarcolumn = stcolumn - 1;
                   int target = (tarrow * 8) + tarcolumn + 1;
                   if (occupied(target) == 1 || occupied(target) == 3)
                       return true;
                }
            }
            if (stcolumn == 6)
            {
                if (enrow == strow + 2 && encolumn == stcolumn - 2)
                {
                   int tarrow = strow + 1;
                   int tarcolumn = stcolumn - 1;
                   int target = (tarrow * 8) + tarcolumn + 1;
                   if (occupied(target) == 1 || occupied(target) == 3)
                       return true;
                }
            }
            if (stcolumn == 7)
            {
                if (enrow == strow + 2 && encolumn == stcolumn - 2)
                {
                   int tarrow = strow + 1;
                   int tarcolumn = stcolumn - 1;
                   int target = (tarrow * 8) + tarcolumn + 1;
                   if (occupied(target) == 1 || occupied(target) == 3)
                       return true;
               }
            }
        }
        return false; //if it makes it through then its not valid
    }

    private boolean isP2Move(int start, int end)
    {
        if(start == end)
            return false;
        if (occupied(start) != 2 && occupied(start) != 4) //if the starting spot is not a player 1 piece, returns false
            return false;
        if (occupied(end) != 0) //if the ending piece is not empty, returns false
            return false;
        int strow = 0; //variables for the starting row/column and ending row/column
        int stcolumn = 0;
        int enrow = 0;
        int encolumn = 0;
        if (occupied(start) == 2)  //regular move, not king
        {
            while (start > 8) //finds row and column in array for start
            {
                strow++;
                start = start - 8;
            }
            stcolumn = start - 1;
            while (end > 8) //finds row an column in array for end
            {
                enrow++;
                end = end - 8;
            }
            encolumn = end - 1;
            if (stcolumn == 0) //if in the first slot of a row
            {
                if (enrow == strow - 1 && encolumn == stcolumn + 1) //can only move to the right one and down 1
                {
                    return true;
                }
               if (enrow == strow - 2 && encolumn == stcolumn + 2) //can only attack to the right 2 and down 2
               {
                   int tarrow = strow - 1;
                   int tarcolumn = stcolumn + 1;
                   int target = (tarrow * 8) + tarcolumn + 1;
                   if (occupied(target) == 1 || occupied(target) == 3)
                   {
                       return true;
                   }
               }
            }
            if (stcolumn == 1) //if in the second slot, can move either way but only attack right
            {
                if ((enrow == strow - 1 && encolumn == stcolumn - 1) || (enrow == strow - 1 && encolumn == stcolumn + 1))
                {
                    return true;
                }
                if (enrow == strow - 2 && encolumn == stcolumn + 2)
                {
                   int tarrow = strow - 1;
                   int tarcolumn = stcolumn + 1;
                   int target = (tarrow * 8) + tarcolumn + 1;
                   if (occupied(target) == 1 || occupied(target) == 3)
                   {
                       return true;
                   }
                }
            }
            if (stcolumn > 1 && stcolumn < 6) //can move to right or left and can attack both ways as well
            {
                if ((enrow == strow - 1 && encolumn == stcolumn + 1) || (enrow == strow - 1 && encolumn == stcolumn - 1))
                {
                    return true;
                }
               if (enrow == strow - 2 && encolumn == stcolumn + 2)
               {
                   int tarrow = strow - 1;
                   int tarcolumn = stcolumn + 1;
                   int target = (tarrow * 8) + tarcolumn + 1;
                   if (occupied(target) == 1 || occupied(target) == 3)
                   {
                       return true;
                   }
               }
               if (enrow == strow - 2 && encolumn == stcolumn - 2)
               {
                   int tarrow = strow - 1;
                   int tarcolumn = stcolumn - 1;
                   int target = (tarrow * 8) + tarcolumn + 1;
                   if (occupied(target) == 1 || occupied(target) == 3)
                   {
                       return true;
                   }
               }
            }
            if (stcolumn == 6) //if in the second to last spot, can go both ways but only attack left
            {
                if ((enrow == strow - 1 && encolumn == stcolumn + 1) || (enrow == strow - 1 && encolumn == stcolumn - 1))
                {
                    return true;
                }
                if (enrow == strow - 2 && encolumn == stcolumn - 2)
                {
                   int tarrow = strow - 1;
                   int tarcolumn = stcolumn - 1;
                   int target = (tarrow * 8) + tarcolumn + 1;
                   if (occupied(target) == 1 || occupied(target) == 3)
                   {
                       return true;
                   }
                }
            }
            if (stcolumn == 7) //if in the right slot you can only go left and attack left
            {
                if (enrow == strow - 1 && encolumn == stcolumn - 1)
                {
                    return true;
                }
               if (enrow == strow - 2 && encolumn == stcolumn - 2)
               {
                   int tarrow = strow - 1;
                   int tarcolumn = stcolumn - 1;
                   int target = (tarrow * 8) + tarcolumn + 1;
                   if (occupied(target) == 1 || occupied(target) == 3)
                   {
                       return true;
                   }
               }
            }
        }
        if (occupied(start) == 4)
        {
            while (start > 8) //finds row and column in array for start
            {
                strow++;
                start = start - 8;
            }
            stcolumn = start - 1;
            while (end > 8) //finds row and column in array for end
            {
                enrow++;
                end = end - 8;
            }
            encolumn = end - 1;
            if (stcolumn == 0) //if in the first slot of a row
            {
                if (enrow == strow - 1 && encolumn == stcolumn + 1) //can only move to the right one and down 1
                {
                    return true;
                }
               if (enrow == strow - 2 && encolumn == stcolumn + 2) //can only attack to the right 2 and down 2
               {
                   int tarrow = strow - 1;
                   int tarcolumn = stcolumn + 1;
                   int target = (tarrow * 8) + tarcolumn + 1;
                   if (occupied(target) == 1 || occupied(target) == 3)
                   {
                       return true;
                   }
               }
            }
            if (stcolumn == 1) //if in the second slot, can move either way but only attack right
            {
                if ((enrow == strow - 1 && encolumn == stcolumn - 1) || (enrow == strow - 1 && encolumn == stcolumn + 1))
                {
                    return true;
                }
                if (enrow == strow - 2 && encolumn == stcolumn + 2)
                {
                   int tarrow = strow - 1;
                   int tarcolumn = stcolumn + 1;
                   int target = (tarrow * 8) + tarcolumn + 1;
                   if (occupied(target) == 1 || occupied(target) == 3)
                   {
                       return true;
                   }
                }
            }
            if (stcolumn > 1 && stcolumn < 6) //can move to right or left and can attack both ways as well
            {
                if ((enrow == strow - 1 && encolumn == stcolumn + 1) || (enrow == strow - 1 && encolumn == stcolumn - 1))
                {
                    return true;
                }
               if (enrow == strow - 2 && encolumn == stcolumn + 2)
               {
                   int tarrow = strow - 1;
                   int tarcolumn = stcolumn + 1;
                   int target = (tarrow * 8) + tarcolumn + 1;
                   if (occupied(target) == 1 || occupied(target) == 3)
                   {
                       return true;
                   }
               }
               if (enrow == strow - 2 && encolumn == stcolumn - 2)
               {
                   int tarrow = strow - 1;
                   int tarcolumn = stcolumn - 1;
                   int target = (tarrow * 8) + tarcolumn + 1;
                   if (occupied(target) == 1 || occupied(target) == 3)
                   {
                       return true;
                   }
               }
            }
            if (stcolumn == 6) //if in the second to last spot, can go both ways but only attack left
            {
                if ((enrow == strow - 1 && encolumn == stcolumn + 1) || (enrow == strow - 1 && encolumn == stcolumn - 1))
                {
                    return true;
                }
                if (enrow == strow - 2 && encolumn == stcolumn - 2)
                {
                   int tarrow = strow - 1;
                   int tarcolumn = stcolumn - 1;
                   int target = (tarrow * 8) + tarcolumn + 1;
                   if (occupied(target) == 1 || occupied(target) == 3)
                   {
                       return true;
                   }
                }
            }
            if (stcolumn == 7) //if in the right slot you can only go left and attack left
            {
                if (enrow == strow - 1 && encolumn == stcolumn - 1)
                {
                    return true;
                }
               if (enrow == strow - 2 && encolumn == stcolumn - 2)
               {
                   int tarrow = strow - 1;
                   int tarcolumn = stcolumn - 1;
                   int target = (tarrow * 8) + tarcolumn + 1;
                   if (occupied(target) == 1 || occupied(target) == 3)
                   {
                       return true;
                   }
               }
            }
            if (stcolumn == 0) //if in the first slot of a row
            {
                if (enrow == strow + 1 && encolumn == stcolumn + 1) //can only move to the right one and down 1
                {
                    return true;
                }
               if (enrow == strow + 2 && encolumn == stcolumn + 2) //can only attack to the right 2 and down 2
               {
                   int tarrow = strow + 1;
                   int tarcolumn = stcolumn + 1;
                   int target = (tarrow * 8) + tarcolumn + 1;
                   if (occupied(target) == 1 || occupied(target) == 3)
                   {
                       return true;
                   }
               }
            }
            if (stcolumn == 1) //if in the second slot, can move either way but only attack one way
            {
                if ((enrow == strow + 1 && encolumn == stcolumn + 1) || (enrow == strow + 1 && encolumn == stcolumn - 1))
                {
                    return true;
                }
                if (enrow == strow + 2 && encolumn == stcolumn + 2)
                {
                   int tarrow = strow + 1;
                   int tarcolumn = stcolumn + 1;
                   int target = (tarrow * 8) + tarcolumn + 1;
                   if (occupied(target) == 1 || occupied(target) == 3)
                   {
                       return true;
                   }
                }
            }
            if (stcolumn > 1 && stcolumn < 6) //can move to right or left and can attack both ways as well
            {
                if ((enrow == strow + 1 && encolumn == stcolumn + 1) || (enrow == strow + 1 && encolumn == stcolumn - 1))
                {
                    return true;
                }
               if (enrow == strow + 2 && encolumn == stcolumn + 2)
               {
                   int tarrow = strow + 1;
                   int tarcolumn = stcolumn + 1;
                   int target = (tarrow * 8) + tarcolumn + 1;
                   if (occupied(target) == 1 || occupied(target) == 3)
                   {
                       return true;
                   }
               }
               if (enrow == strow + 2 && encolumn == stcolumn - 2)
               {
                   int tarrow = strow + 1;
                   int tarcolumn = stcolumn - 1;
                   int target = (tarrow * 8) + tarcolumn + 1;
                   if (occupied(target) == 1 || occupied(target) == 3)
                   {
                       return true;
                   }
               }
            }
            if (stcolumn == 6) //if in the second to last spot, can go both ways but only attack left
            {
                if ((enrow == strow + 1 && encolumn == stcolumn + 1) || (enrow == strow + 1 && encolumn == stcolumn - 1))
                {
                    return true;
                }
                if (enrow == strow + 2 && encolumn == stcolumn - 2)
                {
                   int tarrow = strow + 1;
                   int tarcolumn = stcolumn - 1;
                   int target = (tarrow * 8) + tarcolumn + 1;
                   if (occupied(target) == 1 || occupied(target) == 3)
                   {
                       return true;
                   }
                }
            }
            if (stcolumn == 7) //if in the right slot you can only go left and attack left
            {
                if (enrow == strow + 1 && encolumn == stcolumn - 1)
                {
                    return true;
                }
               if (enrow == strow + 2 && encolumn == stcolumn - 2)
               {
                   int tarrow = strow + 1;
                   int tarcolumn = stcolumn - 1;
                   int target = (tarrow * 8) + tarcolumn + 1;
                   if (occupied(target) == 1 || occupied(target) == 3)
                   {
                       return true;
                   }
               }
            }

        }
        return false; //if it makes it through to here then it isn't a valid move
    }

    public static void switchTurn() //switches the turn
    {
        if (turn == 2)
            turn = 1;
        else
            turn = 2;
    }

    private void resetClicks() //resets the clicks to 0
    {
        firstclick = 0;
        secondclick = 0;
    }
    private void rePaint() //function that repaints the board
    {
        for (int i = 0; i < 32; i++)
           blacks[i].repaint();
    }
    public static void setFirstClick(int i) //sets the first location
    {
        firstclick = i;
    }

    public static void setSecondClick(int i) //sets the second location
    {
        secondclick = i;
    }

    public static boolean firstClicked() //returns if the first tile is clicked
    {
        if (firstclick == 0)
        {
            return false;
        }
        return true;
    }

    public static boolean secondClicked() //returns if the second tile is clicked
    {
        if (secondclick == 0)
        {
            return false;
        }
        return true;
    }


    private boolean player1Win() //function that checks if player 1 won
    {
        for (int i = 0; i < 8; i++) //checks if player 1 won
        {
            for (int j = 0; j < 8; j++)
            {
                if (board[i][j] == 2 || board[i][j] == 4) //if player 2 has a piece on the board, 1 did not win
                {
                    return false;
                }
            }
         }
        return true; //if it made it all the way through then there are no pieces for player 2 and player 1 wins
    }

    private boolean player2Win()
    {
        for (int i = 0; i < 8; i++) //checks if player 2 won
        {
            for (int j = 0; j < 8; j++)
            {
                if (board[i][j] == 1 || board[i][j] == 3) //if player 1 has a piece on the board, 2 did not win
                {
                    return false;
                }
            }
         }
        return true; //if it made it all the way through then there are no pieces for player 1 and player 2 wins

    }

    public static int occupied(int spot)
    {
        int counter = spot;
        int row = 0;
        int column;
        while (counter > 8)
        {
            row++;
            counter = counter - 8;
        }
        column = counter - 1;
        if (board[row][column] == 1)
            return 1;
        if (board[row][column] == 2)
            return 2;
        if (board[row][column] == 3)
            return 3;
        if (board[row][column] == 4)
            return 4;
        return 0;
    }

    public static boolean p1Move(int start, int end) //boolean that is for player 1 moves, returns true if valid move, false if invalid
    {
        int piece = 0;
        if (occupied(start) == 1)
            piece = 1;
        if (occupied(start) == 3)
            piece = 3;
        if (start == end)
            return false;
        if (start == 0 || end == 0)
            return false;
        if (occupied(start) != 1 && occupied(start) != 3) //if the starting spot is not a player 1 piece, returns false
            return false;
        if (occupied(end) != 0) //if the ending piece is not empty, returns false
            return false;
        int strow = 0; //variables for the starting row/column and ending row/column
        int stcolumn = 0;
        int enrow = 0;
        int encolumn = 0;
        if (occupied(start) == 1)  //regular moves
        {
            while (start > 8) //finds row and column in array for start
            {
                strow++;
                start = start - 8;
            }
            stcolumn = start - 1;
            while (end > 8) //finds row an column in array for end
            {
                enrow++;
                end = end - 8;
            }
            encolumn = end - 1;
            if (stcolumn == 0) //if in the first slot of a row
            {
                if (enrow == strow + 1 && encolumn == stcolumn + 1) //can only move to the right one and down 1
                {
                    board[strow][stcolumn] = 0;
                    board[enrow][encolumn] = piece;
                    turn = 2;
                    return true;
                }
               if (enrow == strow + 2 && encolumn == stcolumn + 2) //can only attack to the right 2 and down 2
               {
                   int tarrow = strow + 1;
                   int tarcolumn = stcolumn + 1;
                   int target = (tarrow * 8) + tarcolumn + 1;
                   if (occupied(target) == 2 || occupied(target) == 4)
                   {
                       board[strow][stcolumn] = 0;
                       board[enrow][encolumn] = piece;
                       board[tarrow][tarcolumn] = 0;
                       return true;
                   }
               }
            }
            if (stcolumn == 1) //if in the second slot, can move either way but only attack one way
            {
                if ((enrow == strow + 1 && encolumn == stcolumn + 1) || (enrow == strow + 1 && encolumn == stcolumn - 1))
                {
                    board[strow][stcolumn] = 0;
                    board[enrow][encolumn] = piece;
                    turn = 2;
                    return true;
                }
                if (enrow == strow + 2 && encolumn == stcolumn + 2)
                {
                   int tarrow = strow + 1;
                   int tarcolumn = stcolumn + 1;
                   int target = (tarrow * 8) + tarcolumn + 1;
                   if (occupied(target) == 2 || occupied(target) == 4)
                   {
                       board[strow][stcolumn] = 0;
                       board[enrow][encolumn] = piece;
                       board[tarrow][tarcolumn] = 0;
                       return true;
                   }
                }
            }
            if (stcolumn > 1 && stcolumn < 6) //can move to right or left and can attack both ways as well
            {
                if ((enrow == strow + 1 && encolumn == stcolumn + 1) || (enrow == strow + 1 && encolumn == stcolumn - 1))
                {
                    board[strow][stcolumn] = 0;
                    board[enrow][encolumn] = piece;
                    turn = 2;
                    return true;
                }
               if (enrow == strow + 2 && encolumn == stcolumn + 2)
               {
                   int tarrow = strow + 1;
                   int tarcolumn = stcolumn + 1;
                   int target = (tarrow * 8) + tarcolumn + 1;
                   if (occupied(target) == 2 || occupied(target) == 4)
                   {
                       board[strow][stcolumn] = 0;
                       board[enrow][encolumn] = piece;
                       board[tarrow][tarcolumn] = 0;
                       return true;
                   }
               }
               if (enrow == strow + 2 && encolumn == stcolumn - 2)
               {
                   int tarrow = strow + 1;
                   int tarcolumn = stcolumn - 1;
                   int target = (tarrow * 8) + tarcolumn + 1;
                   if (occupied(target) == 2 || occupied(target) == 4)
                   {
                       board[strow][stcolumn] = 0;
                       board[enrow][encolumn] = piece;
                       board[tarrow][tarcolumn] = 0;
                       return true;
                   }
               }
            }
            if (stcolumn == 6) //if in the second to last spot, can go both ways but only attack left
            {
                if ((enrow == strow + 1 && encolumn == stcolumn + 1) || (enrow == strow + 1 && encolumn == stcolumn - 1))
                {
                    board[strow][stcolumn] = 0;
                    board[enrow][encolumn] = piece;
                    turn = 2;
                    return true;
                }
                if (enrow == strow + 2 && encolumn == stcolumn - 2)
                {
                   int tarrow = strow + 1;
                   int tarcolumn = stcolumn - 1;
                   int target = (tarrow * 8) + tarcolumn + 1;
                   if (occupied(target) == 2 || occupied(target) == 4)
                   {
                       board[strow][stcolumn] = 0;
                       board[enrow][encolumn] = piece;
                       board[tarrow][tarcolumn] = 0;
                       return true;
                   }
                }
            }
            if (stcolumn == 7) //if in the right slot you can only go left and attack left
            {
                if (enrow == strow + 1 && encolumn == stcolumn - 1)
                {
                    board[strow][stcolumn] = 0;
                    board[enrow][encolumn] = piece;
                    turn = 2;
                    return true;
                }
               if (enrow == strow + 2 && encolumn == stcolumn - 2)
               {
                   int tarrow = strow + 1;
                   int tarcolumn = stcolumn - 1;
                   int target = (tarrow * 8) + tarcolumn + 1;
                   if (occupied(target) == 2 || occupied(target) == 4)
                   {
                       board[strow][stcolumn] = 0;
                       board[enrow][encolumn] = piece;
                       board[tarrow][tarcolumn] = 0;
                       return true;
                   }
               }
            }

        }
        if (occupied(start) == 3) //if the piece is a king
        {
            while (start > 8) //finds row and column in array for start
            {
                strow++;
                start = start - 8;
            }
            stcolumn = start - 1;
            while (end > 8) //finds row and column in array for end
            {
                enrow++;
                end = end - 8;
            }
            encolumn = end - 1;
            if (stcolumn == 0) //if in the first slot of a row
            {
                if (enrow == strow + 1 && encolumn == stcolumn + 1) //can only move to the right one and down 1
                {
                    board[strow][stcolumn] = 0;
                    board[enrow][encolumn] = piece;
                    turn = 2;
                    return true;
                }
               if (enrow == strow + 2 && encolumn == stcolumn + 2) //can only attack to the right 2 and down 2
               {
                   int tarrow = strow + 1;
                   int tarcolumn = stcolumn + 1;
                   int target = (tarrow * 8) + tarcolumn + 1;
                   if (occupied(target) == 2 || occupied(target) == 4)
                   {
                       board[strow][stcolumn] = 0;
                       board[enrow][encolumn] = piece;
                       board[tarrow][tarcolumn] = 0;
                       return true;
                   }
               }
            }
            if (stcolumn == 1) //if in the second slot, can move either way but only attack one way
            {
                if ((enrow == strow + 1 && encolumn == stcolumn + 1) || (enrow == strow + 1 && encolumn == stcolumn - 1))
                {
                    board[strow][stcolumn] = 0;
                    board[enrow][encolumn] = piece;
                    turn = 2;
                    return true;
                }
                if (enrow == strow + 2 && encolumn == stcolumn + 2)
                {
                   int tarrow = strow + 1;
                   int tarcolumn = stcolumn + 1;
                   int target = (tarrow * 8) + tarcolumn + 1;
                   if (occupied(target) == 2 || occupied(target) == 4)
                   {
                       board[strow][stcolumn] = 0;
                       board[enrow][encolumn] = piece;
                       board[tarrow][tarcolumn] = 0;
                       return true;
                   }
                }
            }
            if (stcolumn > 1 && stcolumn < 6) //can move to right or left and can attack both ways as well
            {
                if ((enrow == strow + 1 && encolumn == stcolumn + 1) || (enrow == strow + 1 && encolumn == stcolumn - 1))
                {
                    board[strow][stcolumn] = 0;
                    board[enrow][encolumn] = piece;
                    turn = 2;
                    return true;
                }
               if (enrow == strow + 2 && encolumn == stcolumn + 2)
               {
                   int tarrow = strow + 1;
                   int tarcolumn = stcolumn + 1;
                   int target = (tarrow * 8) + tarcolumn + 1;
                   if (occupied(target) == 2 || occupied(target) == 4)
                   {
                       board[strow][stcolumn] = 0;
                       board[enrow][encolumn] = piece;
                       board[tarrow][tarcolumn] = 0;
                       return true;
                   }
               }
               if (enrow == strow + 2 && encolumn == stcolumn - 2)
               {
                   int tarrow = strow + 1;
                   int tarcolumn = stcolumn - 1;
                   int target = (tarrow * 8) + tarcolumn + 1;
                   if (occupied(target) == 2 || occupied(target) == 4)
                   {
                       board[strow][stcolumn] = 0;
                       board[enrow][encolumn] = piece;
                       board[tarrow][tarcolumn] = 0;
                       return true;
                   }
               }
            }
            if (stcolumn == 6) //if in the second to last spot, can go both ways but only attack left
            {
                if ((enrow == strow + 1 && encolumn == stcolumn + 1) || (enrow == strow + 1 && encolumn == stcolumn - 1))
                {
                    board[strow][stcolumn] = 0;
                    board[enrow][encolumn] = piece;
                    turn = 2;
                    return true;
                }
                if (enrow == strow + 2 && encolumn == stcolumn - 2)
                {
                   int tarrow = strow + 1;
                   int tarcolumn = stcolumn - 1;
                   int target = (tarrow * 8) + tarcolumn + 1;
                   if (occupied(target) == 2 || occupied(target) == 4)
                   {
                       board[strow][stcolumn] = 0;
                       board[enrow][encolumn] = piece;
                       board[tarrow][tarcolumn] = 0;
                       return true;
                   }
                }
            }
            if (stcolumn == 7) //if in the right slot you can only go left and attack left
            {
                if (enrow == strow + 1 && encolumn == stcolumn - 1)
                {
                    board[strow][stcolumn] = 0;
                    board[enrow][encolumn] = piece;
                    turn = 2;
                    return true;
                }
               if (enrow == strow + 2 && encolumn == stcolumn - 2)
               {
                   int tarrow = strow + 1;
                   int tarcolumn = stcolumn - 1;
                   int target = (tarrow * 8) + tarcolumn + 1;
                   if (occupied(target) == 2 || occupied(target) == 4)
                   {
                       board[strow][stcolumn] = 0;
                       board[enrow][encolumn] = piece;
                       board[tarrow][tarcolumn] = 0;
                       return true;
                   }
               }
            }

            if (stcolumn == 0) //if in the first slot of a row
            {
                if (enrow == strow - 1 && encolumn == stcolumn + 1) //can only move to the right one and down 1
                {
                    board[strow][stcolumn] = 0;
                    board[enrow][encolumn] = piece;
                    turn = 2;
                    return true;
                }
               if (enrow == strow - 2 && encolumn == stcolumn + 2) //can only attack to the right 2 and down 2
               {
                   int tarrow = strow - 1;
                   int tarcolumn = stcolumn + 1;
                   int target = (tarrow * 8) + tarcolumn + 1;
                   if (occupied(target) == 2 || occupied(target) == 4)
                   {
                       board[strow][stcolumn] = 0;
                       board[enrow][encolumn] = piece;
                       board[tarrow][tarcolumn] = 0;
                       return true;
                   }
               }
            }
            if (stcolumn == 1) //if in the second slot, can move either way but only attack right
            {
                if ((enrow == strow - 1 && encolumn == stcolumn - 1) || (enrow == strow - 1 && encolumn == stcolumn + 1))
                {
                    board[strow][stcolumn] = 0;
                    board[enrow][encolumn] = piece;
                    turn = 2;
                    return true;
                }
                if (enrow == strow - 2 && encolumn == stcolumn + 2)
                {
                   int tarrow = strow - 1;
                   int tarcolumn = stcolumn + 1;
                   int target = (tarrow * 8) + tarcolumn + 1;
                   if (occupied(target) == 2 || occupied(target) == 4)
                   {
                       board[strow][stcolumn] = 0;
                       board[enrow][encolumn] = piece;
                       board[tarrow][tarcolumn] = 0;
                       return true;
                   }
                }
            }
            if (stcolumn > 1 && stcolumn < 6) //can move to right or left and can attack both ways as well
            {
                if ((enrow == strow - 1 && encolumn == stcolumn + 1) || (enrow == strow - 1 && encolumn == stcolumn - 1))
                {
                    board[strow][stcolumn] = 0;
                    board[enrow][encolumn] = piece;
                    turn = 2;
                    return true;
                }
               if (enrow == strow - 2 && encolumn == stcolumn + 2)
               {
                   int tarrow = strow - 1;
                   int tarcolumn = stcolumn + 1;
                   int target = (tarrow * 8) + tarcolumn + 1;
                   if (occupied(target) == 2 || occupied(target) == 4)
                   {
                       board[strow][stcolumn] = 0;
                       board[enrow][encolumn] = piece;
                       board[tarrow][tarcolumn] = 0;
                       return true;
                   }
               }
               if (enrow == strow - 2 && encolumn == stcolumn - 2)
               {
                   int tarrow = strow - 1;
                   int tarcolumn = stcolumn - 1;
                   int target = (tarrow * 8) + tarcolumn + 1;
                   if (occupied(target) == 2 || occupied(target) == 4)
                   {
                       board[strow][stcolumn] = 0;
                       board[enrow][encolumn] = piece;
                       board[tarrow][tarcolumn] = 0;
                       return true;
                   }
               }
            }
            if (stcolumn == 6) //if in the second to last spot, can go both ways but only attack left
            {
                if ((enrow == strow - 1 && encolumn == stcolumn + 1) || (enrow == strow - 1 && encolumn == stcolumn - 1))
                {
                    board[strow][stcolumn] = 0;
                    board[enrow][encolumn] = piece;
                    turn = 2;
                    return true;
                }
                if (enrow == strow - 2 && encolumn == stcolumn - 2)
                {
                   int tarrow = strow - 1;
                   int tarcolumn = stcolumn - 1;
                   int target = (tarrow * 8) + tarcolumn + 1;
                   if (occupied(target) == 2 || occupied(target) == 4)
                   {
                       board[strow][stcolumn] = 0;
                       board[enrow][encolumn] = piece;
                       board[tarrow][tarcolumn] = 0;
                       return true;
                   }
                }
            }
            if (stcolumn == 7) //if in the right slot you can only go left and attack left
            {
                if (enrow == strow - 1 && encolumn == stcolumn - 1)
                {
                    board[strow][stcolumn] = 0;
                    board[enrow][encolumn] = piece;
                    turn = 2;
                    return true;
                }
               if (enrow == strow - 2 && encolumn == stcolumn - 2)
               {
                   int tarrow = strow - 1;
                   int tarcolumn = stcolumn - 1;
                   int target = (tarrow * 8) + tarcolumn + 1;
                   if (occupied(target) == 2 || occupied(target) == 4)
                   {
                       board[strow][stcolumn] = 0;
                       board[enrow][encolumn] = piece;
                       board[tarrow][tarcolumn] = 0;
                       return true;
                   }
               }
            }
        }
        return false; //if it makes it through to here then it isn't a valid move
    }

        public static boolean p2Move(int start, int end) //boolean that is for player 2 moves, returns true if valid move, false if invalid
    {
        int piece = 0;
        if (occupied(start) == 2)
            piece = 2;
        if (occupied(start) == 4)
            piece = 4;
        if (start == end)
            return false;
        if (start == 0 || end == 0)
            return false;
        if (occupied(start) != 2 && occupied(start) != 4) //if the starting spot is not a player 2 piece, returns false
            return false;
        if (occupied(end) != 0) //if the ending piece is not empty, returns false
            return false;
        int strow = 0; //variables for the starting row/column and ending row/column
        int stcolumn = 0;
        int enrow = 0;
        int encolumn = 0;
        if (occupied(start) == 2)  //goes through the regular move options, not king yet
        {
            while (start > 8) //finds row and column in array for start
            {
                strow++;
                start = start - 8;
            }
            stcolumn = start - 1;
            while (end > 8) //finds row and column in array for end
            {
                enrow++;
                end = end - 8;
            }
            encolumn = end - 1;
            if (stcolumn == 0) //if in the first slot of a row
            {
                if (enrow == strow - 1 && encolumn == stcolumn + 1) //can only move to the right one and down 1
                {
                    board[strow][stcolumn] = 0;
                    board[enrow][encolumn] = piece;
                    turn = 1;
                    return true;
                }
               if (enrow == strow - 2 && encolumn == stcolumn + 2) //can only attack to the right 2 and down 2
               {
                   int tarrow = strow - 1;
                   int tarcolumn = stcolumn + 1;
                   int target = (tarrow * 8) + tarcolumn + 1;
                   if (occupied(target) == 1 || occupied(target) == 3)
                   {
                       board[strow][stcolumn] = 0;
                       board[enrow][encolumn] = piece;
                       board[tarrow][tarcolumn] = 0;
                       return true;
                   }
               }
            }
            if (stcolumn == 1) //if in the second slot, can move either way but only attack right
            {
                if ((enrow == strow - 1 && encolumn == stcolumn - 1) || (enrow == strow - 1 && encolumn == stcolumn + 1))
                {
                    board[strow][stcolumn] = 0;
                    board[enrow][encolumn] = piece;
                    turn = 1;
                    return true;
                }
                if (enrow == strow - 2 && encolumn == stcolumn + 2)
                {
                   int tarrow = strow - 1;
                   int tarcolumn = stcolumn + 1;
                   int target = (tarrow * 8) + tarcolumn + 1;
                   if (occupied(target) == 1 || occupied(target) == 3)
                   {
                       board[strow][stcolumn] = 0;
                       board[enrow][encolumn] = piece;
                       board[tarrow][tarcolumn] = 0;
                       return true;
                   }
                }
            }
            if (stcolumn > 1 && stcolumn < 6) //can move to right or left and can attack both ways as well
            {
                if ((enrow == strow - 1 && encolumn == stcolumn + 1) || (enrow == strow - 1 && encolumn == stcolumn - 1))
                {
                    board[strow][stcolumn] = 0;
                    board[enrow][encolumn] = piece;
                    turn = 1;
                    return true;
                }
               if (enrow == strow - 2 && encolumn == stcolumn + 2)
               {
                   int tarrow = strow - 1;
                   int tarcolumn = stcolumn + 1;
                   int target = (tarrow * 8) + tarcolumn + 1;
                   if (occupied(target) == 1 || occupied(target) == 3)
                   {
                       board[strow][stcolumn] = 0;
                       board[enrow][encolumn] = piece;
                       board[tarrow][tarcolumn] = 0;
                       return true;
                   }
               }
               if (enrow == strow - 2 && encolumn == stcolumn - 2)
               {
                   int tarrow = strow - 1;
                   int tarcolumn = stcolumn - 1;
                   int target = (tarrow * 8) + tarcolumn + 1;
                   if (occupied(target) == 1 || occupied(target) == 3)
                   {
                       board[strow][stcolumn] = 0;
                       board[enrow][encolumn] = piece;
                       board[tarrow][tarcolumn] = 0;
                       return true;
                   }
               }
            }
            if (stcolumn == 6) //if in the second to last spot, can go both ways but only attack left
            {
                if ((enrow == strow - 1 && encolumn == stcolumn + 1) || (enrow == strow - 1 && encolumn == stcolumn - 1))
                {
                    board[strow][stcolumn] = 0;
                    board[enrow][encolumn] = piece;
                    turn = 1;
                    return true;
                }
                if (enrow == strow - 2 && encolumn == stcolumn - 2)
                {
                   int tarrow = strow - 1;
                   int tarcolumn = stcolumn - 1;
                   int target = (tarrow * 8) + tarcolumn + 1;
                   if (occupied(target) == 1 || occupied(target) == 3)
                   {
                       board[strow][stcolumn] = 0;
                       board[enrow][encolumn] = piece;
                       board[tarrow][tarcolumn] = 0;
                       return true;
                   }
                }
            }
            if (stcolumn == 7) //if in the right slot you can only go left and attack left
            {
                if (enrow == strow - 1 && encolumn == stcolumn - 1)
                {
                    board[strow][stcolumn] = 0;
                    board[enrow][encolumn] = piece;
                    turn = 1;
                    return true;
                }
               if (enrow == strow - 2 && encolumn == stcolumn - 2)
               {
                   int tarrow = strow - 1;
                   int tarcolumn = stcolumn - 1;
                   int target = (tarrow * 8) + tarcolumn + 1;
                   if (occupied(target) == 1 || occupied(target) == 3)
                   {
                       board[strow][stcolumn] = 0;
                       board[enrow][encolumn] = piece;
                       board[tarrow][tarcolumn] = 0;
                       return true;
                   }
               }
            }
        }
        if (occupied(start) == 4) //goes through all the possible king moves
        {
            while (start > 8) //finds row and column in array for start
            {
                strow++;
                start = start - 8;
            }
            stcolumn = start - 1;
            while (end > 8) //finds row and column in array for end
            {
                enrow++;
                end = end - 8;
            }
            encolumn = end - 1;
            if (stcolumn == 0) //if in the first slot of a row
            {
                if (enrow == strow - 1 && encolumn == stcolumn + 1) //can only move to the right one and down 1
                {
                    board[strow][stcolumn] = 0;
                    board[enrow][encolumn] = piece;
                    turn = 1;
                    return true;
                }
               if (enrow == strow - 2 && encolumn == stcolumn + 2) //can only attack to the right 2 and down 2
               {
                   int tarrow = strow - 1;
                   int tarcolumn = stcolumn + 1;
                   int target = (tarrow * 8) + tarcolumn + 1;
                   if (occupied(target) == 1 || occupied(target) == 3)
                   {
                       board[strow][stcolumn] = 0;
                       board[enrow][encolumn] = piece;
                       board[tarrow][tarcolumn] = 0;
                       return true;
                   }
               }
            }
            if (stcolumn == 1) //if in the second slot, can move either way but only attack right
            {
                if ((enrow == strow - 1 && encolumn == stcolumn - 1) || (enrow == strow - 1 && encolumn == stcolumn + 1))
                {
                    board[strow][stcolumn] = 0;
                    board[enrow][encolumn] = piece;
                    turn = 1;
                    return true;
                }
                if (enrow == strow - 2 && encolumn == stcolumn + 2)
                {
                   int tarrow = strow - 1;
                   int tarcolumn = stcolumn + 1;
                   int target = (tarrow * 8) + tarcolumn + 1;
                   if (occupied(target) == 1 || occupied(target) == 3)
                   {
                       board[strow][stcolumn] = 0;
                       board[enrow][encolumn] = piece;
                       board[tarrow][tarcolumn] = 0;
                       return true;
                   }
                }
            }
            if (stcolumn > 1 && stcolumn < 6) //can move to right or left and can attack both ways as well
            {
                if ((enrow == strow - 1 && encolumn == stcolumn + 1) || (enrow == strow - 1 && encolumn == stcolumn - 1))
                {
                    board[strow][stcolumn] = 0;
                    board[enrow][encolumn] = piece;
                    turn = 1;
                    return true;
                }
               if (enrow == strow - 2 && encolumn == stcolumn + 2)
               {
                   int tarrow = strow - 1;
                   int tarcolumn = stcolumn + 1;
                   int target = (tarrow * 8) + tarcolumn + 1;
                   if (occupied(target) == 1 || occupied(target) == 3)
                   {
                       board[strow][stcolumn] = 0;
                       board[enrow][encolumn] = piece;
                       board[tarrow][tarcolumn] = 0;
                       return true;
                   }
               }
               if (enrow == strow - 2 && encolumn == stcolumn - 2)
               {
                   int tarrow = strow - 1;
                   int tarcolumn = stcolumn - 1;
                   int target = (tarrow * 8) + tarcolumn + 1;
                   if (occupied(target) == 1 || occupied(target) == 3)
                   {
                       board[strow][stcolumn] = 0;
                       board[enrow][encolumn] = piece;
                       board[tarrow][tarcolumn] = 0;
                       return true;
                   }
               }
            }
            if (stcolumn == 6) //if in the second to last spot, can go both ways but only attack left
            {
                if ((enrow == strow - 1 && encolumn == stcolumn + 1) || (enrow == strow - 1 && encolumn == stcolumn - 1))
                {
                    board[strow][stcolumn] = 0;
                    board[enrow][encolumn] = piece;
                    turn = 1;
                    return true;
                }
                if (enrow == strow - 2 && encolumn == stcolumn - 2)
                {
                   int tarrow = strow - 1;
                   int tarcolumn = stcolumn - 1;
                   int target = (tarrow * 8) + tarcolumn + 1;
                   if (occupied(target) == 1 || occupied(target) == 3)
                   {
                       board[strow][stcolumn] = 0;
                       board[enrow][encolumn] = piece;
                       board[tarrow][tarcolumn] = 0;
                       return true;
                   }
                }
            }
            if (stcolumn == 7) //if in the right slot you can only go left and attack left
            {
                if (enrow == strow - 1 && encolumn == stcolumn - 1)
                {
                    board[strow][stcolumn] = 0;
                    board[enrow][encolumn] = piece;
                    turn = 1;
                    return true;
                }
               if (enrow == strow - 2 && encolumn == stcolumn - 2)
               {
                   int tarrow = strow - 1;
                   int tarcolumn = stcolumn - 1;
                   int target = (tarrow * 8) + tarcolumn + 1;
                   if (occupied(target) == 1 || occupied(target) == 3)
                   {
                       board[strow][stcolumn] = 0;
                       board[enrow][encolumn] = piece;
                       board[tarrow][tarcolumn] = 0;
                       return true;
                   }
               }
            }
            if (stcolumn == 0) //if in the first slot of a row
            {
                if (enrow == strow + 1 && encolumn == stcolumn + 1) //can move to the right 1 and down 1
                {
                    board[strow][stcolumn] = 0;
                    board[enrow][encolumn] = piece;
                    turn = 1;
                    return true;
                }
               if (enrow == strow + 2 && encolumn == stcolumn + 2) //can attack to the right 2 and down 2
               {
                   int tarrow = strow + 1;
                   int tarcolumn = stcolumn + 1;
                   int target = (tarrow * 8) + tarcolumn + 1;
                   if (occupied(target) == 1 || occupied(target) == 3)
                   {
                       board[strow][stcolumn] = 0;
                       board[enrow][encolumn] = piece;
                       board[tarrow][tarcolumn] = 0;
                       return true;
                   }
               }
            }
            if (stcolumn == 1) //if in the second slot, can move either way but only attack one way
            {
                if ((enrow == strow + 1 && encolumn == stcolumn + 1) || (enrow == strow + 1 && encolumn == stcolumn - 1))
                {
                    board[strow][stcolumn] = 0;
                    board[enrow][encolumn] = piece;
                    turn = 1;
                    return true;
                }
                if (enrow == strow + 2 && encolumn == stcolumn + 2)
                {
                   int tarrow = strow + 1;
                   int tarcolumn = stcolumn + 1;
                   int target = (tarrow * 8) + tarcolumn + 1;
                   if (occupied(target) == 1 || occupied(target) == 3)
                   {
                       board[strow][stcolumn] = 0;
                       board[enrow][encolumn] = piece;
                       board[tarrow][tarcolumn] = 0;
                       return true;
                   }
                }
            }
            if (stcolumn > 1 && stcolumn < 6) //can move to right or left and can attack both ways as well
            {
                if ((enrow == strow + 1 && encolumn == stcolumn + 1) || (enrow == strow + 1 && encolumn == stcolumn - 1))
                {
                    board[strow][stcolumn] = 0;
                    board[enrow][encolumn] = piece;
                    turn = 1;
                    return true;
                }
               if (enrow == strow + 2 && encolumn == stcolumn + 2)
               {
                   int tarrow = strow + 1;
                   int tarcolumn = stcolumn + 1;
                   int target = (tarrow * 8) + tarcolumn + 1;
                   if (occupied(target) == 1 || occupied(target) == 3)
                   {
                       board[strow][stcolumn] = 0;
                       board[enrow][encolumn] = piece;
                       board[tarrow][tarcolumn] = 0;
                       return true;
                   }
               }
               if (enrow == strow + 2 && encolumn == stcolumn - 2)
               {
                   int tarrow = strow + 1;
                   int tarcolumn = stcolumn - 1;
                   int target = (tarrow * 8) + tarcolumn + 1;
                   if (occupied(target) == 1 || occupied(target) == 3)
                   {
                       board[strow][stcolumn] = 0;
                       board[enrow][encolumn] = piece;
                       board[tarrow][tarcolumn] = 0;
                       return true;
                   }
               }
            }
            if (stcolumn == 6) //if in the second to last spot, can go both ways but only attack left
            {
                if ((enrow == strow + 1 && encolumn == stcolumn + 1) || (enrow == strow + 1 && encolumn == stcolumn - 1))
                {
                    board[strow][stcolumn] = 0;
                    board[enrow][encolumn] = piece;
                    turn = 1;
                    return true;
                }
                if (enrow == strow + 2 && encolumn == stcolumn - 2)
                {
                   int tarrow = strow + 1;
                   int tarcolumn = stcolumn - 1;
                   int target = (tarrow * 8) + tarcolumn + 1;
                   if (occupied(target) == 1 || occupied(target) == 3)
                   {
                       board[strow][stcolumn] = 0;
                       board[enrow][encolumn] = piece;
                       board[tarrow][tarcolumn] = 0;
                       return true;
                   }
                }
            }
            if (stcolumn == 7) //if in the right slot you can only go left and attack left
            {
                if (enrow == strow + 1 && encolumn == stcolumn - 1)
                {
                    board[strow][stcolumn] = 0;
                    board[enrow][encolumn] = piece;
                    turn = 1;
                    return true;
                }
               if (enrow == strow + 2 && encolumn == stcolumn - 2)
               {
                   int tarrow = strow + 1;
                   int tarcolumn = stcolumn - 1;
                   int target = (tarrow * 8) + tarcolumn + 1;
                   if (occupied(target) == 1 || occupied(target) == 3)
                   {
                       board[strow][stcolumn] = 0;
                       board[enrow][encolumn] = piece;
                       board[tarrow][tarcolumn] = 0;
                       return true;
                   }
               }
            }

        }
        return false; //if it makes it through to here then it isn't a valid move
    }
}
