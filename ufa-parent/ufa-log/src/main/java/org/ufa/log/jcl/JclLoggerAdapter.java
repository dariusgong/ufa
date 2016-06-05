package org.ufa.log.jcl;

import java.io.File;

import org.apache.commons.logging.LogFactory;
import org.ufa.log.AbstractMdcLoggerAdapter;
import org.ufa.log.Level;
import org.ufa.log.Logger;
import org.ufa.log.LoggerAdapter;


public class JclLoggerAdapter extends AbstractMdcLoggerAdapter implements LoggerAdapter {

	@Override
	public Logger getLogger(String key) {
		return new JclLogger(LogFactory.getLog(key));
	}

	@Override
	public Logger getLogger(Class<?> key) {
		return new JclLogger(LogFactory.getLog(key));
	}

	private Level level;

	private File file;

	@Override
	public void setLevel(Level level) {
		this.level = level;
	}

	@Override
	public Level getLevel() {
		return level;
	}

	@Override
	public File getFile() {
		return file;
	}

	@Override
	public void setFile(File file) {
		this.file = file;
	}

}
