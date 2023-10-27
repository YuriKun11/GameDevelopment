package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable{

	final int originalTileSize = 16;
	final int scale = 3;
	
	public final int houseSize = 48;
	
	public final int tileSize = originalTileSize * scale;
	public final int maxScreenCol = 16;
	public final int maxScreenRow = 12;
	public final int screenWidth = tileSize * maxScreenCol;
	public final int screenHeight = tileSize * maxScreenRow;
	
	int FPS = 60;
	
	Thread gameThread;
	KeyHandler keyH = new KeyHandler();
	Player player = new Player(this, keyH);
	TileManager tileM = new TileManager(this);
	
	
	public int playerX = 100;
	public int playerY = 100;
	public int playerSpeed = 4;
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.lightGray);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
		
	}
	
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}

//	@Override
//	public void run() {
//		// TODO Auto-generated method stub
//		
//		double drawInterval = 1000000000 / FPS;
//		double nextDrawTime = System.nanoTime() + drawInterval;
//
//		while(gameThread != null) {
//			
//
//			update();
//			
//			repaint();
//
//			try {
//				double remainingTime = nextDrawTime - System.nanoTime();
//				remainingTime = remainingTime / 1000000000;
//				
//				if(remainingTime < 0) {
//					remainingTime = 0;
//				}
//				
//				Thread.sleep((long) remainingTime);
//				
//				nextDrawTime += drawInterval;
//				
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//		}
//		
//	}
	
	public void run() {
		
		double drawInterval = 1000000000/FPS;
		double delta = 0;
		double lastTime = System.nanoTime();
		long currentTime;
		int timer = 0;
		int drawCount = 0;
		
		while(gameThread != null) {
			
			currentTime = System.nanoTime();
			
			delta += (currentTime - lastTime) / drawInterval;
			timer += (currentTime - lastTime);
			lastTime = currentTime;
			
			lastTime = currentTime;
			
			if(delta >=1) {
				update();
				repaint();
				delta--;
				drawCount++;
			}
			
			if(timer >= 1000000000) {
				System.out.println("FPS: "+drawCount);
				drawCount = 0;
				timer = 0;
			}
			
		
		}
	}
	
	public void update() {
		player.update();
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		
		tileM.draw(g2);
		player.draw(g2);
	
		g2.dispose();
	}

	
}
