import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Bird {
	private int x, y, radius, diameter;
	private double dy;
	private boolean jump;
	private Rectangle rectangle;

	public Bird() {
		x = 50;
		y = FlappyBird.size.height / 2 - radius;
		radius = 15;
		diameter = radius * 2;
		dy = 0;
		jump = false;
		rectangle = new Rectangle(x - radius, y - radius, diameter, diameter);
	}

	public void update() {
		if (jump) {
			dy *= 0.9;
			if (dy > -1) {
				dy = 0;
				jump = false;
			}
		} else
			dy += 0.3;
		if (dy > 10)
			dy = 10;
		y += dy;
		if (y < 0) {
			y = 0;
			dy = 0;
		}
		if (y > FlappyBird.size.height - radius)
			GameOver.gameOver = true;
		rectangle = new Rectangle(x - radius, y - radius, diameter, diameter);
	}

	public void paint(Graphics2D g) {
		g.setColor(Color.BLACK);
		g.drawLine(x, y, x, y);
		g.setColor(Color.yellow);
		g.fillOval(x - radius, y - radius, diameter, diameter);
	}

	public void mouseClick() {
		dy = -10;
		jump = true;
	}

	public Rectangle getRectangle() {
		return rectangle;
	}
}
