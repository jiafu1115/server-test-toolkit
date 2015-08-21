package com.googlecode.test.toolkit.services.zookeeper;

import java.util.List;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.data.Stat;

import com.googlecode.test.toolkit.services.zookeeper.operations.ZookeeperOperation;

public interface ZookeeperOperations {

	String createSequenceNode(String parentPath, String path, byte[] data) throws KeeperException,
			InterruptedException;

	String createEphemeralNode(String path, byte[] data);

	String createPersistentNode(String path, byte data[]);

	List<String> getChildren(String path, boolean watch);

	List<String> getChildren(String path, Watcher watch);

	Stat exists(String path, Watcher watcher);

	Stat exists(String path, boolean watcher);

	void delete(String path, int version) throws InterruptedException, KeeperException;

	Stat setData(String path, byte data[], int version);

	void ensurePathExist(String path);

	byte[] getData(String siblePath, boolean watch, Stat stat);

	byte[] getData(String path, Watcher watcher, Stat stat);

	<T> T executeZookeeperOperate(ZookeeperOperation<T> operation) throws KeeperException,
			InterruptedException;

}