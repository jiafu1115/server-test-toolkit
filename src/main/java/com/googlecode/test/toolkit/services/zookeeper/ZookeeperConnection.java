package com.googlecode.test.toolkit.services.zookeeper;

import java.io.IOException;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

public interface ZookeeperConnection {

	long getSessionId();

	boolean isAvalable();

	String getConnectString();

	void process(WatchedEvent event);

	ZooKeeper createZookeeper(String connectString, int sessionTimeout, Watcher watcher) throws IOException;

	int getSessionTimeout();

	ZooKeeper getZookeeper();

}
