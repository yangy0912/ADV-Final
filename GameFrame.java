import java.util.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameFrame extends JFrame implements KeyListener{
  
  public GamePanel panel;
  
    public GameFrame(){

      panel = new GamePanel();
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.setFocusable(true);
	  this.requestFocusInWindow();
      this.add(panel);
      this.setBackground(Color.WHITE);
      this.setVisible(true);
      this.setResizable(false);
      this.addKeyListener(this);
      this.pack();
    }

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == 27){
			   if (panel.getTimer().isRunning()) {
				   panel.getTimer().stop();
			   } else {
				   panel.getTimer().start();
			   }
		}
		if (e.getKeyCode() == 82){
			 this.remove(panel);
			 panel = new GamePanel();
			 this.add(panel);
			 this.revalidate();
	         this.repaint();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}