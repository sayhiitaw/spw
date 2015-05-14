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
	}

	public void updateGameUI(GameReporter reporter){
		big.clearRect(0, 0, 425, 700);
		
		big.setColor(Color.BLACK);
		big.setFont(new Font("Franklin Gothic Demi Cond", Font.PLAIN, fontSize2));	
		
		big.drawImage(BG, 0, 0, 425, 750, null);	
		big.drawString(String.format("score: %08d", reporter.getScore()), 280,30);
		
		if(reporter.getHeart() == 5){
			big.drawImage(HR, 10, 15, 25, 25, null);
			big.drawImage(HR, 40, 15, 25, 25, null);
			big.drawImage(HR, 70, 15, 25, 25, null);
			big.drawImage(HR, 100, 15, 25, 25, null);
			big.drawImage(HR, 130, 15, 25, 25, null);
		}else if(reporter.getHeart() == 4){
			big.drawImage(HR, 10, 15, 25, 25, null);
			big.drawImage(HR, 40, 15, 25, 25, null);
			big.drawImage(HR, 70, 15, 25, 25, null);
			big.drawImage(HR, 100, 15, 25, 25, null);
		}else if(reporter.getHeart() == 3){
			big.drawImage(HR, 10, 15, 25, 25, null);
			big.drawImage(HR, 40, 15, 25, 25, null);
			big.drawImage(HR, 70, 15, 25, 25, null);
		}else if(reporter.getHeart() == 2){
			big.drawImage(HR, 10, 15, 25, 25, null);
			big.drawImage(HR, 40, 15, 25, 25, null);
		}else if(reporter.getHeart() == 1){
			big.drawImage(HR, 10, 15, 25, 25, null);
			big.drawImage(OV, 60, 300, 300, 60, null);
			big.setColor(Color.BLACK);
			big.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, fontSize1));
			big.drawString(String.format("Your Score: %08d", reporter.getScore()), 105,380);
		}		
		
		/*if(reporter.getBonus() == 1){
			big.drawImage(BN, 0, 350, 350, 60, null);
			}*/

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
