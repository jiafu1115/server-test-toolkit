package com.googlecode.test.toolkit.server.ssh;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.Callable;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import com.googlecode.test.toolkit.server.common.exception.CommandExecuteException;
import com.googlecode.test.toolkit.server.common.exception.ContentOverSizeException;
import com.googlecode.test.toolkit.server.common.exception.UncheckedServerOperationException;
import com.googlecode.test.toolkit.server.common.util.JSchUtil.JSchChannelUtil;
import com.googlecode.test.toolkit.util.MemoryUtil;
import com.googlecode.test.toolkit.util.ValidationUtil;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSchException;

/**
 * @author fu.jian
 * date Jul 25, 2012
 */
public class SshTask implements Callable<SshTaskResult<String, String>> {

	private static final Logger LOGGER = Logger.getLogger(SshTask.class);

	private String command;
	private boolean isReturnResult;
	private boolean isHanged;
	private SessionWrapper session;

	SshTask(SessionWrapper sessionWrapper, String command, boolean returnResult, boolean isHanged) {
		ValidationUtil.checkString(command);
		ValidationUtil.checkNull(sessionWrapper);

		this.session = sessionWrapper;
		this.command = command;
		this.isReturnResult = returnResult;
		this.isHanged=isHanged;
	}

	@Override
	public SshTaskResult<String, String> call() throws Exception {
		LOGGER.info("[Server] ["+session.getHost()+"] [Execute command] [Begin] command is: (" + command + ")");

		InputStream inputStream = null;
		InputStream errStream = null;
		ChannelExec channelExec=null;
		try {
			channelExec=JSchChannelUtil.getExecChannel(session);
 			configChannelExec(channelExec);

 			channelExec.connect();

			inputStream = channelExec.getInputStream();
			errStream = channelExec.getErrStream();

			if(!isHanged)
				judgeIfCommandExecuteError(errStream);

			return getResult(inputStream);
		} catch (UncheckedServerOperationException e) {
			logError(e);
			throw e;
		} catch (Exception e) {
			logError(e);
 			throw new UncheckedServerOperationException(e.getMessage(),e);
		} finally {
			try{
				closeChannelAndStream(channelExec, inputStream, errStream);
 			}finally{
 				if(!isHanged)
 					session.decreaseUsingChannelNumber();
  			}
		}
	}

	private void configChannelExec(ChannelExec channelExec) throws JSchException {
 		channelExec.setCommand(command);
		channelExec.setErrStream(System.err);
		channelExec.setInputStream(null);

 	}

	private void judgeIfCommandExecuteError(InputStream errStream) throws IOException {
		String errorString = IOUtils.toString(errStream);
		if (!errorString.isEmpty()) {
			LOGGER.error("[Server] ["+session.getHost()+"] [Execute command] [End] [Fail] " + errorString);
			throw new CommandExecuteException(errorString);
		}
	}

	/**
	 * @param inputStream
	 * @return SshTaskResult: host:ResultString Pair
	 * @throws IOException
	 */
	private SshTaskResult<String, String> getResult(InputStream inputStream) throws IOException {
		String hostAsKey = session.getHost();
		SshTaskResult<String, String> operationResult = new SshTaskResult<String, String>(hostAsKey, null);
		if (isReturnResult) {
			judgeIfOverSize(inputStream);
			operationResult.setResult(IOUtils.toString(inputStream));
		}
		LOGGER.info("[Server] ["+session.getHost()+"] [Execute command] [End] [Success] command is: (" + command + ")");

		return operationResult;
	}

	private void logError(Exception e) {
		String errorMsg = String.format("[Server] ["+session.getHost()+"] [Execute command] [End] [Fail] command (%s) for (%s)",
				command, e);
 		LOGGER.error(errorMsg, e);
 	}

	private void judgeIfOverSize(InputStream inputStream) throws IOException {
		long availableMemory = MemoryUtil.getAvailableMemory();
		int available = inputStream.available();
		if (available > availableMemory) {
			String message = String.format("current available memory is [%d], but content is [%d]",
					availableMemory, available);
			throw new ContentOverSizeException(message);
		}
	}

	private void closeChannelAndStream(Channel channel, InputStream inputStream, InputStream errStream) {
		IOUtils.closeQuietly(inputStream);
		IOUtils.closeQuietly(errStream);
		JSchChannelUtil.disconnect(channel);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SshTask [host=");
		builder.append(session.getHost());
		builder.append(", command=");
		builder.append(command);
		builder.append(", isReturnResult=");
		builder.append(isReturnResult);
		builder.append("]");

		return builder.toString();
	}
}