package f2.spw;

import java.awt.Color;
import java.awt.Graphics2D;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class SpaceShip extends Sprite{

	int step = 20;
	BufferedImage kapook;
	
	public SpaceShip(int x, int y, int width, int height) {
		super(x, y, width, height);
		
		try{
			kapook = ImageIO.read(new File("f2/image/kapook.png"));
		}
		catch(IOException e){

		}
	}

	@Override
	public void draw(Graphics2D g) {
		g.drawImage(kapook, x, y, width, height, null);
	}

	public void move(int direction){
		x += (step * direction);
		if(x < 0)
			x = 0;
		if(x > 425 - width)
			x =  425 - width;
	}

}
