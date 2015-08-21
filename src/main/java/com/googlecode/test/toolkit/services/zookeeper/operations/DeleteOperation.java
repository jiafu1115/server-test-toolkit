package com.googlecode.test.toolkit.services.zookeeper.operations;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;

import com.googlecode.test.toolkit.util.ValidationUtil;

public class DeleteOperation extends AbstractZookeeperOperation<Object> {
	private final String path;
	private final int version;

	public DeleteOperation(ZooKeeper zookeeper,String path, int version) {
		super(zookeeper);
		ValidationUtil.checkString(path);
		ValidationUtil.checkPositive(version);
		
		this.path = path;
		this.version = version;
	}

	@Override
	public Object execute() throws KeeperException, InterruptedException {
		zookeeper.delete(path, version);
		return null;
	}

	@Override
	public String getOperationName() {
		return "delete path: " + path;
	}
}