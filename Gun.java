import org.jsfml.graphics.CircleShape;

import static java.awt.SystemColor.window;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class Gun extends Actor {

    private float mouseX;
    private float mouseY;
    private float playerX;
    private float playerY;
    private float gunX;
    private float gunY;

    public Gun(float mouseX, float mouseY, float playerX, float playerY) {
        this.mouseX = mouseX;
        this.mouseY = mouseY;
        this.playerX = playerX;
        this.playerY = playerY;
        character = new CircleShape(10);

        double opposite = mouseY - playerY - 25;
        double adjacent = mouseX - playerX - 25;
        double angle = Math.toDegrees(Math.atan2(opposite, adjacent));
        float gunX = playerX + 15f + 40*(float)cos(angle*Math.PI/180);
        float gunY = playerY + 15f + 40*(float)sin(angle*Math.PI/180);
        character.setPosition(gunX , gunY);
    }

    public void update(float dt) {
        gunX = character.getPosition().x;
        gunY = character.getPosition().y;
    }

    public float getGunX() {
        return gunX;
    }

    public float getGunY() {
        return gunY;
    }
}
