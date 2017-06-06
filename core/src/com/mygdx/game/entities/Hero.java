package com.mygdx.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.Resources;
import com.sun.javafx.scene.traversal.Direction;


/**
 * Created by juraj on 6/1/2017.
 */
public class Hero extends Actor {
    private PlayerStates state = PlayerStates.STANDING;
    private Directions direction = Directions.RIGHT;
    private InputProcessor inputProcessor;
    private int numPlayer;
    private String name;

    //private ArrayList<Action> actions;
    private Animation standingAnim;
    private Animation movingAnim;
    private float stateTime;
    private SpriteBatch spriteBatch;
    private Rectangle rec;
    private Action currentAction;
    private Animation currentAnimation;

    public Hero( int numPlayer,float x, float y,String name) {
        super.setSize(29,34);
        super.setPosition(x,y);

        this.numPlayer = numPlayer;
        this.name = name;
        rec = new Rectangle(getX(),getY(),getWidth(),getHeight());
        TextureAtlas heroAtlas = Resources.getInstance().getManager().get("hero/professor.pack", TextureAtlas.class);

        standingAnim = new Animation<TextureAtlas.AtlasRegion>(0.06f, heroAtlas.findRegion("hero"), heroAtlas.findRegion("hero10"));
        movingAnim = new Animation<TextureAtlas.AtlasRegion>(0.06f, heroAtlas.findRegion("hero20"), heroAtlas.findRegion("hero30"));
        currentAnimation = standingAnim;
        spriteBatch = new SpriteBatch();
        stateTime = 0f;
    }

    public void update(float delta){
        stateTime += delta;
        switch(state) {
            case STANDING:
                if(currentAnimation != standingAnim)
                    currentAnimation = standingAnim;
                break;
            case MOVING:
                if(currentAnimation != movingAnim)
                    currentAnimation = movingAnim;
                break;
//            case ACTING:
//                Animation anim = new Animation(0.06f, Utils.loadTextureAtlas(currentAction.getName(), "textures/characters/animations/" + getName() + "/").getRegions());
//                if(currentAnimation != anim)
//                    currentAnimation = anim;
//                break;
        }
    }

    public void render() {
        TextureAtlas.AtlasRegion currentFrame = (TextureAtlas.AtlasRegion)currentAnimation.getKeyFrame(stateTime, true);

        if(getDirection() == Directions.LEFT) {
            currentFrame.flip(true, false);
        }

        spriteBatch.begin();
        spriteBatch.draw(currentFrame, getX(), getY(), getWidth(), getHeight());

        spriteBatch.end();
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        update(delta);
    }

    public Rectangle getRectangle() {
        rec.setPosition(super.getX(),super.getY());
        return rec;
    }

    public PlayerStates getState() {
        return state;
    }

    public void setState(PlayerStates state) {
        this.state = state;
    }

    public Directions getDirection() {
        return direction;
    }

    public void setDirection(Directions direction) {
        this.direction = direction;
    }
}
