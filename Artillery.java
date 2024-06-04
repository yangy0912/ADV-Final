import java.util.*;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.io.*;
import java.lang.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.Timer;

public class Artillery {
	public int xPos;
	public int yPos;
	public int damage;
	public Image trebuchet;
	
	public Artillery(int x, int y) {
		this.xPos = x;
		this.yPos = y;
		try {
	    	trebuchet = ImageIO.read(new File("trebuchet.png"));
	    } catch (Exception e) {
	    	System.out.println("No Image Found");
	    }
	}
	
	public void draw(Graphics g) {
		g.drawImage(trebuchet, xPos, yPos, null);
	}
}
