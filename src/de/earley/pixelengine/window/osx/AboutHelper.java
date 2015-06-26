package de.earley.pixelengine.window.osx;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.apple.eawt.AboutHandler;
import com.apple.eawt.AppEvent.AboutEvent;

public class AboutHelper implements AboutHandler {

	private final String msg;

	public AboutHelper(String msg) {
		this.msg = msg;
	}

	@Override
	public void handleAbout(AboutEvent arg0) {
		final JPanel panel = new JPanel();
		JOptionPane.showMessageDialog(panel, msg, "About", JOptionPane.INFORMATION_MESSAGE);
	}

}
