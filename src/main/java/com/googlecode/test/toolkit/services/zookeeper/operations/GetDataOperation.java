package com.googlecode.test.toolkit.services.zookeeper.operations;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import com.googlecode.test.toolkit.util.ValidationUtil;

/**
 * @author fu.jian
 * date Aug 15, 2012
 */
public class GetDataOperation extends AbstractZookeeperOperationWithWatcher<byte[]> {

	private final String path;
	private final Stat stat;

	public GetDataOperation(ZooKeeper zookeeper, String path, boolean watch, Stat stat) {
		super(zookeeper, watch);
		ValidationUtil.checkString(path);
 		this.path = path;
		this.stat = stat;
	}

	public GetDataOperation(ZooKeeper zookeeper, String path, Watcher watcher, Stat stat) {
		super(zookeeper, watcher);
		this.path = path;
		this.stat = stat;
	}

	@Override
	public byte[] execute() throws KeeperException, InterruptedException {
		if (hasExplicitWatcher)
			return zookeeper.getData(path, watcher, stat);

		return zookeeper.getData(path, watch, stat);
	}

	@Override
	public String getOperationName() {
		return "get data path: " + path;
	}
}