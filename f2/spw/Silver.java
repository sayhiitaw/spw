package f2.spw;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Silver extends Sprite{
	public static final int Y_TO_FADE = 390;
	public static final int Y_TO_DIE = 650;
	
	private int step = 20;
	private boolean alive = true;

	BufferedImage coin;
	
	public Silver(int x, int y) {
		super(x, y, 40, 40);

			try{
			coin = ImageIO.read(new File("f2/image/silver.png"));
		}
		catch(IOException e){

		}
	}

	@Override
	public void draw(Graphics2D g) {
		if(y < Y_TO_FADE)
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
		else{
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 
					(float)(Y_TO_DIE - y)/(Y_TO_DIE - Y_TO_FADE)));
		}

		g.drawImage(coin, x, y, width, height, null);
		//g.fillRect(x, y, width, height);
		
	}

	public void proceed(){
		y += step;
		if(y > Y_TO_DIE){
			alive = false;
		}
	}
	
	public boolean isAlive(){
		return alive;
	}

	public boolean noAlive(){
		return alive = false;
	}
}