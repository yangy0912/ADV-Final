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
  public JButton helpButton;
  public JButton backButton;

  private static boolean on = false;

  public Menu() {
    on = true;
  }

  public void startUp() {

    frame = new JFrame();
    panel = new JPanel();
    panel.setBackground(new Color(48, 144, 13));
    panel.setVisible(true);

    frame.getContentPane().setBackground(Color.GREEN);
    frame.setSize(500, 500);
    frame.setLayout(null);
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setResizable(false);

    JLabel label = new JLabel("Tower Defense");
    label.setFont(new Font("Serif", Font.BOLD, 44));
    label.setBounds(115, 75, 320, 50);

    playButton = new JButton("Play");
    playButton.setBounds(150, 150, 220, 50);
    playButton.addActionListener(this);
    quitButton = new JButton("Quit");
    quitButton.setBounds(150, 325, 220, 50);
    quitButton.addActionListener(this);
    helpButton = new JButton("Help");
    helpButton.setBounds(150, 230, 220, 50);
    helpButton.addActionListener(this);

    frame.add(label);
    frame.add(playButton);
    frame.add(quitButton);
    frame.add(helpButton);
  }
  
  
  public void help() {
	    frame = new JFrame();
	    panel = new JPanel();
	    frame.getContentPane().setBackground(Color.WHITE);
	    
	    frame.setSize(500, 500);
	    frame.setLayout(null);
	    frame.setVisible(true);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setResizable(false);

	    JLabel Label2 = new JLabel("How to play");
	    Label2.setFont(new Font("Serif", Font.BOLD, 44));
	    Label2.setBounds(145, 75, 240, 50);

	    JLabel instructions = new JLabel("<html>" + "Defend your base from endless waves of zombies. Bolster your defenses with the money earned by defeating zombies!" + "<html>");
	    instructions.setBounds(125, 100, 300, 175);

	    backButton = new JButton("Back");
	    backButton.setBounds(150, 300, 220, 50);
	    backButton.addActionListener(this);

	    frame.add(Label2);
	    frame.add(instructions);
	    frame.add(backButton);
	  }


  public void actionPerformed(ActionEvent e) {
	  Object buttonClicked = e.getSource();
	    if (buttonClicked == playButton) {
	      frame.setVisible(false);
	      GameFrame temp = new GameFrame();
	    } else if (buttonClicked == helpButton) {
	        frame.setVisible(false);
	        help();
	    } else if (buttonClicked == quitButton) {
	        frame.setVisible(false);
	        System.exit(0);
	    } else if (buttonClicked == backButton) {
	        frame.setVisible(false);
	        startUp();
	      }
  }
}