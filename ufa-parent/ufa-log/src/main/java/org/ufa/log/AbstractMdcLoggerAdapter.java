package org.ufa.log;

import java.util.HashMap;
import java.util.Map;


/**
 * 照搬org.jboss.logging.AbstractMdcLoggerProvider,为没有MDCC功能的logger提供者增加默认实现
 * 
 *  
 */
public abstract class AbstractMdcLoggerAdapter extends AbstractLoggerAdapter {
	private final InheritableThreadLocal<Map<String, Object>> mdcMap = new InheritableThreadLocal<Map<String, Object>>();

	public Object getMdc(String key) {
		return mdcMap.get() == null ? null : mdcMap.get().get(key);
	}

	public Map<String, Object> getMdcMap() {
		return mdcMap.get();
	}

	public Object putMdc(String key, Object value) {
		Map<String, Object> map = mdcMap.get();
		if (map == null) {
			map = new HashMap<String, Object>();
			mdcMap.set(map);
		}
		return map.put(key, value);
	}

	public void removeMdc(String key) {
		Map<String, Object> map = mdcMap.get();
		if (map == null)
			return;
		map.remove(key);
	}
}
