package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.entities.Hero;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by juraj on 5/31/2017.
 */
public class GameScreen implements com.badlogic.gdx.Screen, InputProcessor {
    private Texture img;
    private TiledMap tiledMap;
    private OrthographicCamera camera;
    private TiledMapRenderer tiledMapRenderer;
    private Stage stage;
    private Hero hero;
    private int x_move = 0;
    private int y_move = 0;
    private List<Rectangle> blocked= new ArrayList<Rectangle>();

    public GameScreen() {
        Gdx.app.log("INFO","Game started");
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        stage = new Stage();
        camera = new OrthographicCamera();
        camera.setToOrtho(false,w,h);
        camera.position.set(w/2,h/2,0);
        camera.update();
        tiledMap = new TmxMapLoader().load("map/iceAdventureMap2.tmx");
        for ( MapLayer mapLayer  :tiledMap.getLayers() ) {
            if(mapLayer.getProperties().containsKey("blocked")){
                TiledMapTileLayer mapTiledLayer = (TiledMapTileLayer) mapLayer;
                for (int i = 0; i < mapTiledLayer.getWidth(); i++) {
                    for (int j = 0; j < mapTiledLayer.getHeight(); j++) {
                        TiledMapTileLayer.Cell cell = mapTiledLayer.getCell(i, j);
                        if(cell != null){
                            blocked.add(new Rectangle(i*mapTiledLayer.getTileWidth()  ,j*mapTiledLayer.getTileHeight() ,mapTiledLayer.getTileWidth(),mapTiledLayer.getTileHeight()));
                        }
                    }
                }
            }
        }
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);

        hero = new Hero(0,w/2,h/2,"a");
        stage.addActor(hero);
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        Rectangle heroMove = new Rectangle(hero.getX() + x_move,hero.getY() + y_move, hero.getWidth(), hero.getHeight());
        if(!checkCollisionWithMap(blocked, heroMove)) {
            hero.moveBy(x_move,y_move);
            Gdx.app.log("DEBUG","CAM---- " + camera.position.x + " " + camera.position.y);
        }

        camera.position.set(hero.getX(), hero.getY(), 0);
        tiledMapRenderer.render();
        camera.update();
        hero.render();

        tiledMapRenderer.setView(camera);
        stage.act(delta);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    @Override
    public boolean keyDown(int keycode) {
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            x_move--;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            x_move++;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
            y_move--;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            y_move++;
        }
        if(keycode == Input.Keys.NUM_1)
            tiledMap.getLayers().get(0).setVisible(!tiledMap.getLayers().get(0).isVisible());
        if(keycode == Input.Keys.NUM_2)
            tiledMap.getLayers().get(1).setVisible(!tiledMap.getLayers().get(1).isVisible());

        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if(keycode ==(Input.Keys.LEFT)) {
            x_move = 0;
        }
        if(keycode == (Input.Keys.RIGHT)) {
            x_move = 0;
        }
        if(keycode == (Input.Keys.UP)) {
            y_move = 0;
        }
        if(keycode == (Input.Keys.DOWN)) {
            y_move = 0;
        }
        return false;
    }

    @Override
    public boolean keyTyped(char character) { return false;}

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    public boolean checkCollisionEntity(){
        return true;
    }

    public boolean checkCollisionWithMap(List<Rectangle> objects,Rectangle playerRectangle){

        Gdx.app.log("DEBUG","Hero--- " + playerRectangle.getX() + " " + playerRectangle.getY());
        for (Rectangle rectangle : objects) {
//            Gdx.app.log("DEBUG","Blocked " + rectangle.getX() + " " + rectangle.getY());
            if (Intersector.overlaps(rectangle, playerRectangle)){
                 return true;
            }
        }
        return false;
    }
}
