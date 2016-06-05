package org.ufa.log.slf4j;

import java.io.File;
import java.util.Map;

import org.slf4j.MDC;
import org.ufa.log.AbstractLoggerAdapter;
import org.ufa.log.Level;
import org.ufa.log.Logger;
import org.ufa.log.LoggerAdapter;


public class Slf4jLoggerAdapter extends AbstractLoggerAdapter implements LoggerAdapter {

	@Override
	public Logger getLogger(String key) {
		return new Slf4jLogger(org.slf4j.LoggerFactory.getLogger(key));
	}

	@Override
	public Logger getLogger(Class<?> key) {
		return new Slf4jLogger(org.slf4j.LoggerFactory.getLogger(key));
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

	@Override
	public Object putMdc(final String key, final Object value) {
		try {
			return MDC.get(key);
		} finally {
			if (value == null) {
				MDC.remove(key);
			} else {
				MDC.put(key, String.valueOf(value));
			}
		}
	}

	@Override
	public Object getMdc(final String key) {
		return MDC.get(key);
	}

	@Override
	public void removeMdc(final String key) {
		MDC.remove(key);
	}

	@Override
	@SuppressWarnings({ "unchecked" })
	public Map<String, Object> getMdcMap() {
		return MDC.getCopyOfContextMap();
	}

}
