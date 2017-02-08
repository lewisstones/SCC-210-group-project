import org.jsfml.graphics.Font;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Text;
import org.jsfml.system.Clock;
import org.jsfml.window.ContextSettings;
import org.jsfml.window.Mouse;
import org.jsfml.window.VideoMode;
import org.jsfml.window.WindowStyle;
import org.jsfml.window.event.Event;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;

import static java.awt.SystemColor.window;
import static java.lang.Math.sqrt;
import static java.lang.Thread.sleep;

public class Testing {

    private RenderWindow window;

    private Player player;
    private Gun gun;
    private ArrayList<Enemy> actors;
    private ArrayList<Bullet> bullets;
    int maxEnemies = 1;
    int enemiesAlive = 1;
    int bulletsUsed = 0;

    /*Font font = new Font();
    try {
        font.loadFromFile(Paths.get("resources/OpenSans-Italic.ttf"));
    } catch (IOException e) {
        e.printStackTrace();
    }*/

    public Testing() {

        window = new RenderWindow(new VideoMode(640, 360), "Testing",
                WindowStyle.CLOSE | WindowStyle.TITLEBAR, new ContextSettings(8));

        player = new Player(1);
        actors = new ArrayList<>();
        bullets = new ArrayList<>();
        actors.add(new Enemy(1, player, 0, 0));

    }

    public void run() {
        

        Clock clock = new Clock();

        //Text bulletCount = new Text("Bullets remaining: " + (10 - bulletsUsed), font, 20);
        //bulletCount.setPosition(10, 10);

        while (window.isOpen()) {
            float dt = clock.restart().asSeconds();

            for(Event event : window.pollEvents()) {
                switch (event.type) {
                    case CLOSED:
                        window.close();
                        break;
                }
            }

            window.clear();
            float mouseX = Mouse.getPosition(window).x;
            float mouseY = Mouse.getPosition(window).y;

            if (Mouse.isButtonPressed(Mouse.Button.LEFT)) {
                bullets.add(new Bullet(gun.getGunX() + 5, gun.getGunY() + 5, player.Trajectory(mouseX, mouseY)));
                try {
                    sleep(50);
                }
                catch(Exception e){ //this wont work, maybe mouse listener>?
                    System.out.println("Error!");
                }
            }

            player.update(dt);
            window.draw(player.getCharacter());
            for(int i = 0; i < actors.size(); i++) {
                actors.get(i).update(dt);
                window.draw(actors.get(i).getCharacter());
            }

            for (int j = 0; j < bullets.size(); j++ ) {
                bullets.get(j).update(dt);
                window.draw(bullets.get(j).getCharacter());
            }

            gun = new Gun(mouseX, mouseY, player.getxCord(), player.getYCord());
            window.draw(gun.getCharacter());
            gun.update(dt);

            for(int counter = 0; counter < bullets.size(); counter++) {
                float bulletX = bullets.get(counter).getXCord();
                float bulletY = bullets.get(counter).getYCord();
                for (int counter2 = 0; counter2 < player.getEnemiesAlive(); counter2++) {
                    float detectX = actors.get(counter2).getXCord();
                    float detectY = actors.get(counter2).getYCord();
                    System.out.println(+bulletX);
                    System.out.println(+detectX);
                    if (sqrt((detectX - bulletX) * (detectX - bulletX) + (detectY - bulletY) * (detectY - bulletY)) < 15) {
                        actors.get(counter2).setAlive(false);
                        actors.remove(counter2);
                        System.out.println("Hit!");
                        enemiesAlive = player.getEnemiesAlive() - 1;
                        player.setEnemiesAlive(enemiesAlive);
                        System.out.println(+enemiesAlive);
                        //aliveEnemies[counter2] = new CircleShape(10);
                        //aliveEnemies[counter2].setPosition(200,10); (this works if isDead{counter2) = true is not done
                    }
                }
            }

            window.display();

        }
    }
}
