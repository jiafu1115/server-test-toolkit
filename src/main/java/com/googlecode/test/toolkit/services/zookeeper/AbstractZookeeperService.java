package com.googlecode.test.toolkit.services.zookeeper;

import java.io.IOException;
import java.util.List;
import java.util.Stack;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import com.googlecode.test.toolkit.constants.MarkConstants;
import com.googlecode.test.toolkit.server.common.exception.ServerConnectionException;
import com.googlecode.test.toolkit.services.Service;
import com.googlecode.test.toolkit.services.zookeeper.operations.CreateNode;
import com.googlecode.test.toolkit.services.zookeeper.operations.CreateSequenceNodeOperation;
import com.googlecode.test.toolkit.services.zookeeper.operations.DeleteOperation;
import com.googlecode.test.toolkit.services.zookeeper.operations.ExistOperation;
import com.googlecode.test.toolkit.services.zookeeper.operations.GetChildrenOperation;
import com.googlecode.test.toolkit.services.zookeeper.operations.GetDataOperation;
import com.googlecode.test.toolkit.services.zookeeper.operations.SetDataOperation;
import com.googlecode.test.toolkit.services.zookeeper.operations.ZookeeperOperation;
import com.googlecode.test.toolkit.util.PathUtil;
import com.googlecode.test.toolkit.util.ValidationUtil;

public abstract class AbstractZookeeperService implements Service, ZookeeperOperations, ZookeeperConnection,
		Watcher {

	protected String connectString;
	protected long sessionId;
	protected int sessionTimeout;
	protected ZooKeeper zookeeper;

	protected AbstractZookeeperService(String connectString, int sessionTimeout) {
		ValidationUtil.checkNull(connectString, sessionTimeout);
		this.connectString = connectString;
		this.sessionTimeout = sessionTimeout;
		try {
			this.zookeeper = createZookeeper(connectString, sessionTimeout, this);
		} catch (IOException e) {
			throw new ServerConnectionException(e.getMessage(), e);
		}
		this.sessionId = zookeeper.getSessionId();
	}

	@Override
	public String createEphemeralNode(final String path, final byte[] data) {
		return executeZookeeperOperate(new CreateNode(zookeeper, path, data, CreateMode.EPHEMERAL));
	}

	@Override
	public String createPersistentNode(final String path, final byte data[]) {
		return executeZookeeperOperate(new CreateNode(zookeeper, path, data, CreateMode.PERSISTENT));
	}

	@Override
	public String createSequenceNode(final String parentPath, final String path, final byte[] data) {
		return executeZookeeperOperate(new CreateSequenceNodeOperation(zookeeper, path, parentPath, data));
	}

	@Override
	public void delete(final String path, final int version) {
		executeZookeeperOperate(new DeleteOperation(zookeeper, path, version));
	}

	@Override
	public void ensurePathExist(final String path) {
		Stat state = exists(path, false);
		if (state != null)
			return;

		String tmpPath = PathUtil.formatPath(path);
		Stack<String> unCreatedPathStack = getUncreatedPath(tmpPath);
		while (!unCreatedPathStack.empty()) {
			createPersistentNode(unCreatedPathStack.pop(), null);
		}
	}

	@Override
	public abstract <E> E executeZookeeperOperate(ZookeeperOperation<E> operation);

	@Override
	public Stat exists(final String path, final boolean watch) {
		return executeZookeeperOperate(new ExistOperation(zookeeper, path, watch));
	}

	@Override
	public Stat exists(final String path, final Watcher watcher) {
		return executeZookeeperOperate(new ExistOperation(zookeeper, path, watcher));
	}

	@Override
	public List<String> getChildren(final String path, final boolean watch) {
		return executeZookeeperOperate(new GetChildrenOperation(zookeeper, path, watch));
	}

	@Override
	public List<String> getChildren(final String path, final Watcher watcher) {
		return executeZookeeperOperate(new GetChildrenOperation(zookeeper, path, watcher));
	}

	@Override
	public String getConnectString() {
		return connectString;
	}

	@Override
	public byte[] getData(final String path, final boolean watch, final Stat stat) {
		return executeZookeeperOperate(new GetDataOperation(zookeeper, path, watch, stat));
	}

	@Override
	public byte[] getData(final String path, final Watcher watcher, final Stat stat) {
		return executeZookeeperOperate(new GetDataOperation(zookeeper, path, watcher, stat));
	}

	@Override
	public String getServiceName() {
		return "zookeeper";
	}

	@Override
	public long getSessionId() {
		return sessionId;
	}

	@Override
	public int getSessionTimeout() {
		return sessionTimeout;
	}

	private Stack<String> getUncreatedPath(String path) {
		Stat state;
		Stack<String> unCreatedPathStack = new Stack<String>();
		unCreatedPathStack.push(path);
		int lastSplitPos = path.lastIndexOf(MarkConstants.SPLIT);
		while (lastSplitPos != 0) {
			path = path.substring(0, lastSplitPos);
			state = exists(path, false);
			if (state != null) {
				break;
			}
			unCreatedPathStack.push(path);
			lastSplitPos = path.lastIndexOf(MarkConstants.SPLIT);
		}
		return unCreatedPathStack;
	}

	@Override
	public ZooKeeper getZookeeper() {
		return zookeeper;
	}

	@Override
	public boolean isAvalable() {
		return zookeeper.getState() == ZooKeeper.States.CONNECTED;
	}

	@Override
	public Stat setData(final String path, final byte data[], final int version) {
		return executeZookeeperOperate(new SetDataOperation(zookeeper, path, data, version));
	}

}
