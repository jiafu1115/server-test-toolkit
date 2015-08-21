package com.googlecode.test.toolkit.services.zookeeper.operations;

import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

/**
 * @author fu.jian
 * date Aug 15, 2012
 * @param <T>
 */
public abstract class AbstractZookeeperOperationWithWatcher<T> extends AbstractZookeeperOperation<T> {

	protected boolean hasExplicitWatcher = false;
	protected boolean watch;
	protected Watcher watcher;

	protected AbstractZookeeperOperationWithWatcher(ZooKeeper zookeeper, boolean watch) {
		super(zookeeper);
		this.watch = watch;
	}

	protected AbstractZookeeperOperationWithWatcher(ZooKeeper zookeeper, Watcher watcher) {
		super(zookeeper);
		this.watcher = watcher;
		this.hasExplicitWatcher = true;
	}
	
}
