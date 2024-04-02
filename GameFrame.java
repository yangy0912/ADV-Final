import java.util.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameFrame extends JFrame {
  
  public GamePanel panel;
  
    public GameFrame(){

      panel = new GamePanel();
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.add(panel);
      this.setBackground(Color.WHITE);
      this.setVisible(true);
      this.pack();
    }

}