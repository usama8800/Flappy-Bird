import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

public class Map {

	private ArrayList<Rectangle> map;
	private Random rand;
	private int pipeWidth, dx, pipexGap, pipeyGap;

	public Map() {
		pipeWidth = 50;
		pipexGap = 250;
		pipeyGap = 150;
		map = new ArrayList<>();
		map.add(new Rectangle(FlappyBird.size.width + pipexGap, 0, pipeWidth, 200));
		map.add(new Rectangle(FlappyBird.size.width + pipexGap, 200 + pipeyGap, pipeWidth,
				FlappyBird.size.height - 200 - pipeyGap));
		rand = new Random();
		dx = 3;
	}

	public void update(Bird bird) {
		if (map.get(map.size() - 1).x < FlappyBird.size.width) {
			pipeyGap = rand.nextInt(70) + 130;
			pipexGap = rand.nextInt(100) + 200;
			Rectangle top = new Rectangle(FlappyBird.size.width + pipexGap, 0, pipeWidth,
					20 + rand.nextInt(FlappyBird.size.height - 300));
			map.add(top);
			map.add(new Rectangle(FlappyBird.size.width + pipexGap, top.height + pipeyGap, pipeWidth,
					FlappyBird.size.height - top.height - pipeyGap));
		}
		for (int i = 0; i < map.size(); i++) {
			Rectangle pipe = map.get(i);
			if (pipe.x + pipeWidth < 0) {
				map.remove(i);
				i--;
				FlappyBird.score++;
				continue;
			}
			if (pipe.intersects(bird.getRectangle()))
				GameOver.gameOver = true;
			pipe.x -= dx;
		}
	}

	public void paint(Graphics2D g) {
		g.setColor(Color.WHITE);
		for (int i = 0; i < map.size(); i++) {
			Rectangle pipe = map.get(i);
			g.fillRect(pipe.x, pipe.y, pipe.width, pipe.height);
		}
	}

}
