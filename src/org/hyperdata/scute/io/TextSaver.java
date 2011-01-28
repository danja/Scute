package org.hyperdata.scute.io;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.TimerTask;

import org.hyperdata.scute.main.Config;
import org.hyperdata.scute.source.TextContainer;

public class TextSaver extends TimerTask {

	private final TextContainer container;

	public TextSaver(TextContainer container) {
		this.container = container;
	}

	@Override
	public void run() {
		save();
	}

	public void save() {
		File file = new File(Config.TEXT_FILENAME);

		byte[] bytes = (Config.self
				.getIdentifyingComment(container.getSyntax()) + container
				.getText()).getBytes();
		try {
			OutputStream fos = new FileOutputStream(file);
			fos.write(bytes);
			fos.close();
			System.out.println("saving : " + container.getSyntax());
		} catch (IOException e) {
			// TODO popup warning
			e.printStackTrace();
		}
	}
}