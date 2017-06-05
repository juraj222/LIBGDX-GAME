package com.mygdx.game;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.TextureLoader;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by juraj on 6/1/2017.
 */
public class Resources {
    private static Resources ourInstance = new Resources();
    private static AssetManager manager;

    public static Resources getInstance() {
        return ourInstance;
    }

    private Resources() {
    }

    public void intialize() {
        manager = new AssetManager();
    }

    public void loadAssets(){
        manager.load("hero/professor.pack", TextureAtlas.class);
        while(true){
            if(manager.update() == true)
                break;
        }
    }

    public AssetManager getManager(){
        return manager;
    }
}
