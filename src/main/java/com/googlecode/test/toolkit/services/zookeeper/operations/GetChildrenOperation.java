package com.googlecode.test.toolkit.services.zookeeper.operations;

import java.util.List;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import com.googlecode.test.toolkit.util.ValidationUtil;

public class GetChildrenOperation extends AbstractZookeeperOperationWithWatcher<List<String>> {

	private final String path;

	public GetChildrenOperation(ZooKeeper zookeeper, String path, Watcher watcher) {
		super(zookeeper, watcher);
		ValidationUtil.checkString(path);
		this.path = path;
	}

	public GetChildrenOperation(ZooKeeper zookeeper, String path, boolean watch) {
		super(zookeeper, watch);
		this.path = path;
	}

	@Override
	public List<String> execute() throws KeeperException, InterruptedException {
		if (hasExplicitWatcher)
			return zookeeper.getChildren(path, watcher);

		return zookeeper.getChildren(path, watch);
	}

	@Override
	public String getOperationName() {
		return "getChildren path: " + path;
	}
}