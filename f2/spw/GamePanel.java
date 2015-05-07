package f2.spw;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JPanel;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class GamePanel extends JPanel {
	
	private BufferedImage bi;	
	Graphics2D big;
	ArrayList<Sprite> sprites = new ArrayList<Sprite>();

	BufferedImage BG;

	public GamePanel() {
		bi = new BufferedImage(425, 700, BufferedImage.TYPE_INT_ARGB);
		big = (Graphics2D) bi.getGraphics();
		try{
			BG = ImageIO.read(new File("f2/image/bg.jpg"));
		}
		catch(IOException e){

		}
	}

	public void updateGameUI(GameReporter reporter){
		big.clearRect(0, 0, 425, 700);
		
		big.setColor(Color.GRAY);	
		
		big.drawImage(BG, 0, 0, 425, 750, null);	
		big.drawString(String.format("%08d", reporter.getScore()), 330,20);
		
		for(Sprite s : sprites){
			s.draw(big);
		}
		
		repaint();
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(bi, null, 0, 0);
	}

}
