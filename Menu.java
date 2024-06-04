import java.util.*;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.Timer;
public class Menu implements ActionListener {

  public JFrame frame;
  public JPanel panel;
  public JButton playButton;
  public JButton quitButton;

  private static boolean on = false;

  public Menu() {
    on = true;
  }

  public void startUp() {

    frame = new JFrame();
    panel = new JPanel();
    panel.setBackground(new Color(48, 144, 13));

    frame.getContentPane().setBackground(Color.WHITE);
    frame.setSize(500, 500);
    frame.setLayout(null);
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setResizable(false);

    JLabel snakeLabel = new JLabel("Tower Defense");
    snakeLabel.setBounds(235, 75, 220, 50);

    playButton = new JButton("Play");
    playButton.setBounds(150, 125, 220, 50);
    playButton.addActionListener(this);
    quitButton = new JButton("Quit");
    quitButton.setBounds(150, 325, 220, 50);
    quitButton.addActionListener(this);

    frame.add(snakeLabel);
    frame.add(playButton);
    frame.add(quitButton);
  }
  


  public void actionPerformed(ActionEvent e) {
	  Object buttonClicked = e.getSource();
	    if (buttonClicked == playButton) {
	      frame.setVisible(false);
	      GameFrame temp = new GameFrame();

	    }
  }
}