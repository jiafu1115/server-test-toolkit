package com.googlecode.test.toolkit.services.zookeeper.operations;

import java.util.List;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.ACL;

import com.googlecode.test.toolkit.util.ValidationUtil;

public class CreateNode extends AbstractZookeeperOperation<String> {

	private static final List<ACL> ACL = ZooDefs.Ids.OPEN_ACL_UNSAFE;

	private final String path;
	private final byte[] data;
	private final CreateMode createMode;

	public CreateNode(ZooKeeper zookeeper,String path, byte[] data, CreateMode createMode) {
		super(zookeeper);

 		ValidationUtil.checkString(path);
		ValidationUtil.checkNull(createMode);

		this.path = path;
		this.data = data;
		this.createMode=createMode;
	}

	@Override
	public String execute() throws KeeperException, InterruptedException {
		return zookeeper.create(path, data, ACL, createMode);
	}

	@Override
	public String getOperationName() {
		return "create path " + path;
	}
}