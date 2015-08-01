/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testPackage;

import de.earley.pixelengine.game.Game;
import de.earley.pixelengine.sprite.Sprite;
import de.earley.pixelengine.window.Window;
import de.earley.pixelengine.window.render.GraphicsHelper;
import de.earley.pixelengine.window.render.RenderAction;
import de.earley.pixelengine.window.render.Screen;
import de.earley.pixelengine.window.render.Viewport;

import java.awt.Color;

/**
 *
 * @author timmy
 */
public class GameTest extends Game {
        
    public static void test() {
        Screen screen = new Screen(1000, 1000, 0, 0, (viewport) -> { render((Screen) viewport); });
        Window window = new Window("Game", 500, 500);
        window.addViewport(screen);
        GameTest gameTest = new GameTest(window);
        gameTest.start();
    }

    private static void render(Screen screen) {
        screen.setColour(Color.RED);
        screen.fillRect(0, 0, 500, 500);
    }

    public GameTest(Window window) {
        super(window);
    }

    @Override
    protected void init() {
    }
    
    

    @Override
    protected void update(int delta, Window window) {
    }
    
}
