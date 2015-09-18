package de.earley.pixelengine.window.ui;

/**
 *
 * @author timmy
 */
public abstract class UIButton extends UIElement {

    public abstract void doAction();
    
    public void entered() {};
    
    public void exited() {};
    
    public void pressed() {};
    
    public void released() {
	doAction();
    }
    
}
