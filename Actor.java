import org.jsfml.graphics.CircleShape;

public abstract class Actor {

    protected CircleShape character;

    public Actor() {

    }

    public abstract void update(float dt);

    public CircleShape getCharacter() {

        return character;
    }

}
