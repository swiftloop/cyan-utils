package com.framework.utils;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Sorata
 * @date 2018/6/21 17:51
 */
public class XFThreadFactory implements ThreadFactory {

	private static AtomicInteger threadNumber = new AtomicInteger(1);

	private String prefix;

	private boolean isDaemon;

	public XFThreadFactory(String prefix, boolean isDaemon) {
		this.prefix = prefix;
		this.isDaemon = isDaemon;
	}

	public XFThreadFactory(String prefix) {
		this(prefix,false);
	}

	@Override
	public Thread newThread(Runnable r) {
		final Thread thread = new Thread(r,prefix+"-xf-thread-"+threadNumber );
		thread.setDaemon(isDaemon);
		return thread;
	}
}
