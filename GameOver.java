import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class GameOver {

	public static boolean gameOver;
	private Rectangle tryAgain;
	private String[] menu = { "Try Again" };

	public GameOver() {
		gameOver = false;
	}

	public void paint(Graphics2D g) {
		g.setFont(new Font("Arial", Font.PLAIN, 30));
		if (tryAgain == null)
			setTextBounds(g);

		g.setColor(new Color(200, 200, 200, 150));
		g.fillRect(0, 0, FlappyBird.size.width, FlappyBird.size.height);

		g.setColor(Color.BLUE);
		g.drawString("Score: " + FlappyBird.score, 190, 200);
		if (tryAgain.contains(FlappyBird.mousePoint))
			g.setColor(Color.RED);
		g.drawString(menu[0], tryAgain.x, tryAgain.y + tryAgain.height);
	}

	public String mouseClick() {
		if (tryAgain.contains(FlappyBird.mousePoint))
			return menu[0];
		return "";
	}

	private void setTextBounds(Graphics2D g) {
		tryAgain = new Rectangle(180, 250, g.getFontMetrics().charWidth('a') * menu[0].length(),
				g.getFontMetrics().getHeight());
	}

}
