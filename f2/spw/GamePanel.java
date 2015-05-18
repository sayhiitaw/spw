package f2.spw;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.awt.Font;
import java.awt.Dimension;

import javax.swing.JPanel;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class GamePanel extends JPanel {
	
	private BufferedImage bi;	
	Graphics2D big;
	ArrayList<Sprite> sprites = new ArrayList<Sprite>();

	BufferedImage BG;
	BufferedImage HR;
	BufferedImage BN;
	BufferedImage OV;
	BufferedImage FI;

	int fontSize1 = 25;
	int fontSize2 = 20;

	public GamePanel() {
		
		bi = new BufferedImage(425, 700, BufferedImage.TYPE_INT_ARGB);
		big = (Graphics2D) bi.getGraphics();
		try{
			BG = ImageIO.read(new File("f2/image/bg.jpg"));
		}
		catch(IOException e){

		}

		try{
			HR = ImageIO.read(new File("f2/image/heart.png"));
		}
		catch(IOException e){

		}

		try{
			BN = ImageIO.read(new File("f2/image/bo.png"));
		}
		catch(IOException e){

		}

		try{
			OV = ImageIO.read(new File("f2/image/over.png"));
		}
		catch(IOException e){

		}

		try{
			FI = ImageIO.read(new File("f2/image/final.png"));
		}
		catch(IOException e){

		}
	}

	public void updateGameUI(GameReporter reporter){
		big.clearRect(0, 0, 425, 700);
		
		big.setColor(Color.BLACK);
		big.setFont(new Font("Franklin Gothic Demi Cond", Font.PLAIN, fontSize2));	
		
		big.drawImage(BG, 0, 0, 425, 750, null);	
		big.drawString(String.format("score: %08d", reporter.getScore()), 280,30);
		
		big.drawImage(HR, 10, 15, 25, 25, null);
		big.drawString(String.format(" %d", reporter.getHeart()), 40,30);

		if( reporter.getHeart() == 1){
			big.drawImage(OV, 60, 300, 300, 60, null);
			big.drawImage(FI, 65, 275, 80, 30, null);
			big.setColor(Color.BLACK);
			big.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, fontSize1));
			big.drawString(String.format("Your Score: %08d", reporter.getScore()), 105,380);
		}	
		
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
