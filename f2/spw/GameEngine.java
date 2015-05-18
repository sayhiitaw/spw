package f2.spw;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.Timer;


public class GameEngine implements KeyListener, GameReporter{
	GamePanel gp;

	private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	private ArrayList<Silver> silcoin = new ArrayList<Silver>();	
	private ArrayList<Cash> cashmoney = new ArrayList<Cash>();	
	private ArrayList<Bonus> bonusmoney = new ArrayList<Bonus>();
	private ArrayList<Bomb> boom = new ArrayList<Bomb>();	

	private SpaceShip v;	
	
	private Timer timer;

	private long score = 0;
	private double difficulty = 0.1;
	private int heart = 5;

	public GameEngine(GamePanel gp, SpaceShip v) {
		this.gp = gp;
		this.v = v;		
		
		gp.sprites.add(v);
		
		timer = new Timer(100, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				process();
			}
		});
		timer.setRepeats(true);
		
	}
	
	public void start(){
		timer.start();
	}

	private void generateEnemy(){
		Enemy e = new Enemy((int)(Math.random()*390), 30);
		gp.sprites.add(e);
		enemies.add(e);
	}
	
	private void generateSilver(){
		Silver s = new Silver((int)(Math.random()*390), 30);
		gp.sprites.add(s);
		silcoin.add(s);
	}

	private void generateCash(){
		Cash c = new Cash((int)(Math.random()*390), 30);
		gp.sprites.add(c);
		cashmoney.add(c);
	}

	private void generateBonus(){
		Bonus b = new Bonus((int)(Math.random()*390), 30);
		gp.sprites.add(b);
		bonusmoney.add(b);
	}

	private void generateBomb(){
		Bomb bb = new Bomb((int)(Math.random()*390), 30);
		gp.sprites.add(bb);
		boom.add(bb);
	}

	private void process(){

		if(Math.random() < difficulty + 0.05){
			generateBomb();
		}

		if(Math.random() < (difficulty - 0.01)){
			generateEnemy();
		}

		if(Math.random() < difficulty){
			generateSilver();
		}

		if(Math.random() < 0.08){
			generateCash();
		}
		
		if(Math.random() < 0.008){
			generateBonus();
		}
		
		Iterator<Enemy> e_iter = enemies.iterator();
		while(e_iter.hasNext()){
			Enemy e = e_iter.next();
			e.proceed();
			
			if(!e.isAlive()){
				e_iter.remove();
				gp.sprites.remove(e);
				score += 1000;
			}
		}

		Iterator<Silver> s_iter = silcoin.iterator();
		while(s_iter.hasNext()){
			Silver s = s_iter.next();
			s.proceed();
			
			if(!s.isAlive()){
				s_iter.remove();
				gp.sprites.remove(s);
				score += 500;
			}
		}

		Iterator<Cash> c_iter = cashmoney.iterator();
		while(c_iter.hasNext()){
			Cash c = c_iter.next();
			c.proceed();
			
			if(!c.isAlive()){
				c_iter.remove();
				gp.sprites.remove(c);
				score += 2000;
			}
		}

		Iterator<Bonus> b_iter = bonusmoney.iterator();
		while(b_iter.hasNext()){
			Bonus b = b_iter.next();
			b.proceed();
			
			if(!b.isAlive()){
				b_iter.remove();
				gp.sprites.remove(b);
				score += 10000;
     			}
     		}

     		Iterator<Bomb> m_iter = boom.iterator();
     		while(m_iter.hasNext()){
     			Bomb m = m_iter.next();
     			m.proceed();

     			if(!m.isAlive()){
     				m_iter.remove();
     				gp.sprites.remove(m);
     				score -= 100;
     			}
     		}

     		gp.updateGameUI(this);

     		Rectangle2D.Double vr = v.getRectangle();
     		Rectangle2D.Double er;
     		for(Enemy e : enemies){
     			er = e.getRectangle();
     			if(er.intersects(vr)){
     				e.noAlive();
     				score -=100;
     				return;
     			}
     		}

     		Rectangle2D.Double sr;
     		for(Silver s : silcoin){
     			sr = s.getRectangle();
     			if(sr.intersects(vr)){
     				s.noAlive();
     				score -=50;
     				return;
     			}
     		}

     		Rectangle2D.Double cr;
     		for(Cash c : cashmoney){
     			cr = c.getRectangle();
     			if(cr.intersects(vr)){
     				c.noAlive();
     				score -=200;
     				return;
     			}
     		}

     		Rectangle2D.Double br;
     		for(Bonus b : bonusmoney){
     			br = b.getRectangle();
     			if(br.intersects(vr)){
     				b.noAlive();
     				score += 100;
     				return;
     			}
     		}

     		Rectangle2D.Double mr;
     		for(Bomb m : boom){
     			mr = m.getRectangle();
     			if(mr.intersects(vr)){
     				if(heart > 0){
     					m.noAlive();
     					score -= 1000;
     					heart -=1;
     					if(heart == 0){
       						die();
      					}
       				}
       				return;
     			}
     		}
     	}

     	public void die(){
     		timer.stop();
    	}

     	void controlVehicle(KeyEvent e) {
     		switch (e.getKeyCode()) {
     			case KeyEvent.VK_LEFT:
     			v.move(-1);
     			break;
     			case KeyEvent.VK_RIGHT:
     			v.move(1);
     			break;
     			case KeyEvent.VK_D:
     			difficulty += 0.1;
     			break;
     		}
     	}

     	public long getScore(){
     		return score;
     	}

     	public int getHeart(){
     		return heart;
     	}

  	@Override
	public void keyPressed(KeyEvent e) {
		controlVehicle(e);
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		//do nothing
	}

	@Override
	public void keyTyped(KeyEvent e) {
		//do nothing		
	}
}
