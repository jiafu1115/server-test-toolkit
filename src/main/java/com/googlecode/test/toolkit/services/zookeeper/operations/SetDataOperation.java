package com.googlecode.test.toolkit.services.zookeeper.operations;

import org.apache.log4j.Logger;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import com.googlecode.test.toolkit.util.ValidationUtil;

public class SetDataOperation extends AbstractZookeeperOperation<Stat> {

	private static final Logger LOGGER=Logger.getLogger(SetDataOperation.class);

	private final byte[] data;
	private final int version;
	private final String path;

	public SetDataOperation(ZooKeeper zookeeper,String path, byte[] data, int version) {
		super(zookeeper);
		ValidationUtil.checkString(path);
		ValidationUtil.checkPositive(version);

		this.path = path;
 		this.data = data;
		this.version = version;
	}

	@Override
	public Stat execute() throws KeeperException, InterruptedException {
		if (data != null && data.length > 1024 * 1024) {
			LOGGER.warn("setData is very large. path=" + path);
		}
		return zookeeper.setData(path, data, version);
	}

	@Override
	public String getOperationName() {
		return "set data path: " + path;
	}
}