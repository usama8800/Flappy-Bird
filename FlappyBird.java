import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class FlappyBird extends JPanel implements Runnable {
	private static final long serialVersionUID = -8395759457708163217L;

	public static final Dimension size = new Dimension(500, 500);

	private Bird bird;
	private Map map;
	private Thread thread;
	private Mouse mouse;
	private GameOver gameOver;
	public static boolean running;
	public static Point mousePoint;
	public static int score;

	public static void main(String[] args) {
		new FlappyBird();
	}

	public FlappyBird() {
		JFrame frame = new JFrame("Flappy Bird");
		frame.add(this);
		setPreferredSize(size);
		setValues();
		setProperties();
		thread.start();
		frame.setResizable(false);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	private void setValues() {
		bird = new Bird();
		map = new Map();
		thread = new Thread(this);
		mouse = new Mouse();
		gameOver = new GameOver();
		running = false;
		mousePoint = new Point();
		score = 0;
	}

	private void setProperties() {
		setDoubleBuffered(true);
		setFocusable(true);
		addMouseListener(mouse);
		addMouseMotionListener(mouse);
	}

	public void update() {
		if (running && !GameOver.gameOver) {
			bird.update();
			map.update(bird);
		}
	}

	public void paint(Graphics g1) {
		super.paint(g1);
		Graphics2D g = (Graphics2D) g1;
		g.setColor(Color.GREEN);
		g.fillRect(0, 0, size.width, size.height);
		bird.paint(g);
		map.paint(g);
		if (GameOver.gameOver)
			gameOver.paint(g);
		else
			paintScore(g);
		g.setColor(new Color(200, 255, 20));
		g.setFont(new Font("Arial", Font.PLAIN, 30));
		if (!running && !GameOver.gameOver)
			g.drawString("Click to Start", 180, 200);
	}

	private void paintScore(Graphics2D g) {
		g.setFont(new Font("Arial", Font.PLAIN, 20));
		g.setColor(Color.RED);
		g.drawString(String.valueOf(score), size.width - 30, 30);
	}

	public void run() {
		while (true) {
			try {
				Thread.sleep(17);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			update();
			repaint();
		}
	}

	private class Mouse extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {
			if (!running && !GameOver.gameOver)
				running = true;
			if (!GameOver.gameOver)
				bird.mouseClick();
			else {
				String returnValue = gameOver.mouseClick();
				if (returnValue.equals("Try Again")) {
					running = false;
					bird = new Bird();
					map = new Map();
					score = 0;
					GameOver.gameOver = false;
				}
			}
		}

		public void mouseMoved(MouseEvent e) {
			mousePoint = e.getPoint();
		}
	}

}
