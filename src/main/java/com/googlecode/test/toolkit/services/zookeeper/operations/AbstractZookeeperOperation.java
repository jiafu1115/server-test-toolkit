package com.googlecode.test.toolkit.services.zookeeper.operations;

import org.apache.zookeeper.ZooKeeper;

import com.googlecode.test.toolkit.util.ValidationUtil;

public abstract class AbstractZookeeperOperation<T> implements ZookeeperOperation<T> {

	protected ZooKeeper zookeeper;

	protected AbstractZookeeperOperation(ZooKeeper zookeeper) {
		super();
		ValidationUtil.checkNull(zookeeper);
		this.zookeeper = zookeeper;
	}

	public ZooKeeper getZookeeper() {
		return zookeeper;
	}

	public void setZookeeper(ZooKeeper zookeeper) {
		this.zookeeper = zookeeper;
	}

}
