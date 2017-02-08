import org.jsfml.graphics.CircleShape;

import java.util.ArrayList;

import static java.awt.SystemColor.window;

public class Bullet extends Actor {


    private boolean alive;
    private float ratio;
    private float xCord;
    private float yCord;

    public Bullet(float xCord, float yCord, float ratio) {
        this.xCord = xCord;
        this.yCord = yCord;
        this.ratio = ratio;
        character = new CircleShape(5);
        character.setPosition(xCord, yCord);
        alive = true;
    }

    public void update(float dt) {
        character.move(-ratio*10, -10);
        xCord = character.getPosition().x;
        yCord = character.getPosition().y;
    }

    public boolean isAlive() {
        return alive;
    }

    public float getXCord() {
        return xCord;
    }

    public float getYCord() {
        return yCord;
    }
}
