import org.jsfml.graphics.CircleShape;
import org.jsfml.window.Keyboard;
import org.jsfml.window.Mouse;

public class Player extends Actor {

    private int health;
    private int enemiesAlive = 1;
    private float xCord = 320;
    private float yCord = 300;

    public Player(int heath) {
        character = new CircleShape(25);
        character.setPosition(xCord, yCord);
        this.health = heath;
    }

    public void update(float dt) {
        if (Keyboard.isKeyPressed(Keyboard.Key.A)) {
            character.move(-dt * 500, 0); // Move left at a rate of 500 (lower is slower)
            xCord = character.getPosition().x;
        }
        if (Keyboard.isKeyPressed(Keyboard.Key.D)) {
            character.move(dt * 500, 0);
            xCord = character.getPosition().x;
        }

        if(character.getPosition().x < 0) {
            character.setPosition(0, character.getPosition().y);
        }
        else if(character.getPosition().x > 640 - (character.getRadius() * 2)) {
            character.setPosition(640 - (character.getRadius() * 2), character.getPosition().y);
        }
    }

    public float Trajectory(float targetX, float targetY){
        float projectileX = character.getPosition().x + 15;
        float projectileY = character.getPosition().y + 15;

        float ratio2 = ((projectileX - targetX)/(projectileY - targetY));
        return ratio2;
    }

    public float getxCord() {
        return xCord;
    }

    public float getYCord() {
        return yCord;
    }

    public int getEnemiesAlive() {
        return enemiesAlive;
    }

    public void setEnemiesAlive(int enemiesAlive) {
        this.enemiesAlive = enemiesAlive;
    }
}

