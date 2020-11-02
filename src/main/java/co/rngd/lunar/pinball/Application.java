package co.rngd.lunar.pinball;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Application extends ApplicationAdapter {
	private SpriteBatch batch;
	private Texture pinball;

  private float w, h, x, y;
  private float vx = 0, vy = 0;
  private float g = 500f;

	@Override
	public void create () {
		batch = new SpriteBatch();
		pinball = new Texture("pinball.png");
	}

  public void resize(int width, int height) {
    w = (float) width;
    h = (float) height;
    x = (w - 100) / 2;
    y = (h - 100) / 2;
    vx = 100f; 
    vy = 150f;
  }

	@Override
	public void render () {
    float dt = Gdx.graphics.getDeltaTime();

    x += vx * dt;
    y += vy * dt;
    vy -= g * dt;
    if (x < 0) { x = 0; vx = pos(vx); }
    if (y < 0) { y = 0; vy = pos(vy); }
    if (x > w - 100) { x = w - 100; vx = neg(vx); }
    if (y > h - 100) { y = h - 100; vy = neg(vy); }

		Gdx.gl.glClearColor(0.3f, 0.4f, 0.1f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(pinball, x, y);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		pinball.dispose();
	}

  private static float pos(float v) { return v < 0 ? -v : v; }
  private static float neg(float v) { return v < 0 ? v : -v; }
}

