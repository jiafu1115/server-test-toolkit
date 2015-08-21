package com.googlecode.test.toolkit.services.zookeeper.operations;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import com.googlecode.test.toolkit.util.ValidationUtil;

public class ExistOperation extends AbstractZookeeperOperationWithWatcher<Stat> {

	private final String path;

	public ExistOperation(ZooKeeper zookeeper, String path, Watcher watcher) {
		super(zookeeper, watcher);
		ValidationUtil.checkString(path);
		this.path = path;
	}

	public ExistOperation(ZooKeeper zookeeper, String path, boolean watch) {
		super(zookeeper, watch);
		this.path = path;
	}

	@Override
	public Stat execute() throws KeeperException, InterruptedException {
		if (hasExplicitWatcher)
			return zookeeper.exists(path, watcher);

		return zookeeper.exists(path, watch);
	}

	@Override
	public String getOperationName() {
		return "exist path: " + path;
	}
}