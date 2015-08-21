package com.googlecode.test.toolkit.services.zookeeper.operations;

import org.apache.zookeeper.KeeperException;

public interface ZookeeperOperation<T> {

	T execute() throws KeeperException, InterruptedException;

	String getOperationName();

}