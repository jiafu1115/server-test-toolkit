package com.googlecode.test.toolkit.services.zookeeper;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import com.googlecode.test.toolkit.services.exception.ServiceExecuteException;
import com.googlecode.test.toolkit.services.zookeeper.operations.ZookeeperOperation;

/**
 * @author fu.jian date Aug 15, 2012
 */
public class DefaultZookeeperService extends AbstractZookeeperService implements Watcher {

	private static final Logger LOGGER = Logger.getLogger(DefaultZookeeperService.class);

	/**
	 * 
	 * @param connectString
	 *            comma separated host:port pairs, each corresponding to a zk
	 *            server. e.g. "127.0.0.1:3000,127.0.0.1:3001,127.0.0.1:3002" If
	 *            the optional chroot suffix is used the example would look
	 *            like: "127.0.0.1:3000,127.0.0.1:3001,127.0.0.1:3002/app/a"
	 *            where the client would be rooted at "/app/a" and all paths
	 *            would be relative to this root - ie getting/setting/etc...
	 *            "/foo/bar" would result in operations being run on
	 *            "/app/a/foo/bar" (from the server perspective).
	 * @param sessionTimeout
	 *            session timeout in milliseconds
	 * @return DefaultZookeeperService instance
	 */
	public static ZookeeperOperations getInstance(String connectString, int sessionTimeout) {
		return new DefaultZookeeperService(connectString, sessionTimeout);
	}

	private DefaultZookeeperService(String connectString, int sessionTimeout) {
 		super(connectString, sessionTimeout);
	}

	public ZooKeeper createZookeeper(String connectString, int sessionTimeout, Watcher watcher)
			throws IOException {
		return new ZooKeeper(connectString, sessionTimeout, watcher);
	}

	@Override
	public void process(WatchedEvent event) {
		LOGGER.info("proccess default wather event: " + event);
	}

	@Override
	public <E> E executeZookeeperOperate(ZookeeperOperation<E> operation) {
		try {
			LOGGER.info("[zookeeper] execute:"+operation.getOperationName());
			return operation.execute();
		} catch (Exception e) {
			throw new ServiceExecuteException(e.getMessage(), e);
		}
	}

}
