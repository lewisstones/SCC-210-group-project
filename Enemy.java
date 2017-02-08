import org.jsfml.graphics.CircleShape;

public class Enemy extends Actor {

    private static final float SPEED = 0.8f;

    private Player player;
    private boolean alive;
    private int health;
    private float xCord;
    private float yCord;


    public Enemy(int health, Player player, float xCord, float yCord) {
        character = new CircleShape(10);
        this.health = health;
        this.player = player;
        this.xCord = xCord;
        this.yCord = yCord;
        alive = true;
    }

    public void update(float dt) {
        float enemyX = character.getPosition().x;
        float enemyY = character.getPosition().y;
        float playerX = player.character.getPosition().x;
        float playerY = player.character.getPosition().y;
        float ratio = ((playerX - enemyX) / (playerY - enemyY));
        if(ratio > 3){
            ratio = 3;
        }
        if(ratio < -3){
            ratio = -3;
        }
        if(enemyY < playerY){
            character.setPosition(enemyX + (SPEED * ratio), enemyY + SPEED);
            xCord = enemyX + (SPEED * ratio);
            yCord = enemyY + SPEED;
        }
        if(enemyY >= playerY){

            character.setPosition(enemyX - (SPEED * ratio), enemyY);
            xCord = enemyX + (SPEED * ratio);
            yCord = enemyY;
        }


        /*if(Math.sqrt((playerX - enemyX)*(playerX - enemyX) + (playerY - enemyY)*(playerY - enemyY)) < 15){
            System.out.println("Destroyed");
            window.close();
        }*/
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public float getXCord() {
        return xCord;
    }

    public float getYCord() {
        return yCord;
    }
}
