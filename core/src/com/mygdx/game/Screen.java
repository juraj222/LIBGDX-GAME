package com.mygdx.game;

import com.mygdx.game.screens.CreditsScreen;
import com.mygdx.game.screens.GameScreen;
import com.mygdx.game.screens.IntroScreen;
import com.mygdx.game.screens.MainMenuScreen;

/**
 * Created by juraj on 5/31/2017.
 */
public enum Screen {
    INTRO {
        @Override
        protected com.badlogic.gdx.Screen getScreenInstance() {
            return new IntroScreen();
        }
    },

    MAIN_MENU {
        @Override
        protected com.badlogic.gdx.Screen getScreenInstance() {
            return new MainMenuScreen();
        }
    },

    GAME {
        @Override
        protected com.badlogic.gdx.Screen getScreenInstance() {
            return new GameScreen();
        }
    },

    CREDITS {
        @Override
        protected com.badlogic.gdx.Screen getScreenInstance() {
            return new CreditsScreen();
        }
    };

    protected abstract com.badlogic.gdx.Screen getScreenInstance();
}
