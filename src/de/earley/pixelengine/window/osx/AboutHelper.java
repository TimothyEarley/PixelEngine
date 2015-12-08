package de.earley.pixelengine.window.osx;

import com.apple.eawt.AboutHandler;
import com.apple.eawt.AppEvent.AboutEvent;

import javax.swing.*;

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
