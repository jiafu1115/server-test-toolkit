package com.googlecode.test.toolkit.server.common.util;

import java.lang.reflect.Constructor;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.googlecode.test.toolkit.server.common.exception.ServerConnectionException;
import com.googlecode.test.toolkit.server.common.exception.UncheckedServerOperationException;
import com.googlecode.test.toolkit.server.common.user.ServerUser;
import com.googlecode.test.toolkit.server.ssh.SessionWrapper;
import com.googlecode.test.toolkit.util.ValidationUtil;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public final class JSchUtil {

	private static final Logger LOGGER = Logger.getLogger(JSchUtil.class);

	public static class JSchExceptionUtil {

		public static <T extends UncheckedServerOperationException> T jSchExceptionToUncheckedServerConnectionException(
				String errorMsg, JSchException jSchException, Class<T> clazz) {
 			try {
				Constructor<T>  constructor = clazz.getConstructor(String.class, Throwable.class);
 				T uncheckedServerOperationException = constructor.newInstance(errorMsg, jSchException);
				LOGGER.error(errorMsg, uncheckedServerOperationException);

				return uncheckedServerOperationException;
			} catch (Exception e) {
				String msg = "exception convert fail for: " + e.getMessage();
				LOGGER.error(msg, e);
				throw new RuntimeException(msg, e);
			}

		}
	}

	public static class JSchSessionUtil {

 		public static SessionWrapper getSessionWrapper(ServerUser serverUser, int maxChannelNumber) {
 			return new SessionWrapper(getSession(serverUser),serverUser,maxChannelNumber);
		}

		public static SessionWrapper getSessionWrapper(ServerUser serverUser) {
 			return new SessionWrapper(getSession(serverUser),serverUser,10);
		}

		public static Session getSession(ServerUser serverUser) {
			ValidationUtil.checkNull(serverUser);
			LOGGER.info("[Server] [Create connnection] [begin] with: " + serverUser);

			Session session;
			JSch jSch = new JSch();
			try {
				session = jSch.getSession(serverUser.getUsername(), serverUser.getHost(),
						serverUser.getPort());
				if (serverUser.getPassword() != null) {
					session.setPassword(serverUser.getPassword());
				}
				JSchSessionUtil.setConfigForSession(session);
				session.connect();
				JSchUtil.LOGGER
						.info("[Server] [Create connnection] [End] [Success]: " + serverUser);
			} catch (JSchException e) {
				String errorMsg = String.format(
						"[Server] [Create connnection] [End] [Fail] with: [%s] for %s", serverUser,e.getMessage());
				ServerConnectionException serverConnnectionException = JSchExceptionUtil
						.jSchExceptionToUncheckedServerConnectionException(errorMsg, e,
								ServerConnectionException.class);
				throw serverConnnectionException;
			}
			return session;
		}

		private static void setConfigForSession(Session session) {
			Properties config = new Properties();
			config.setProperty("StrictHostKeyChecking", "no");
			session.setConfig(config);
		}

		public static void disconnect(Session session) {
			if (session != null && session.isConnected())
				session.disconnect();
		}

	}

	public static class JSchChannelUtil {

		public static ChannelExec getExecChannel(SessionWrapper sessionWrapper) {
			return (ChannelExec) getChannel(sessionWrapper.getSession(), "exec");
		}

		public static ChannelExec getExecChannel(Session session) {
			return (ChannelExec) getChannel(session, "exec");
		}

		public static Channel getChannel(Session session, String mode) {
			try {
				return session.openChannel(mode);
			} catch (JSchException e) {
				ServerConnectionException serverConnnectionException = JSchExceptionUtil
						.jSchExceptionToUncheckedServerConnectionException(
								"[Server] [Create channel] [Fail] for", e,
								ServerConnectionException.class);
				throw serverConnnectionException;
			}
		}

		public static ChannelSftp getSftpChannel(Session session) {
			return (ChannelSftp) getChannel(session, "sftp");
		}

		public static void disconnect(Channel channel) {
			if (channel != null)
				channel.disconnect();
		}

	}

 	private JSchUtil() {
	}
}
