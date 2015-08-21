package com.googlecode.test.toolkit.server.ssh;

import com.googlecode.test.toolkit.util.ValidationUtil;

public class SshTaskResult<K, V> {

	private K host;
	private V result;

	/**
	 * not check result parameter: due to  result allowed to be <code>null</code>
	 *
	 * @param host
	 * @param result
	 */
	SshTaskResult(K host, V result) {
		ValidationUtil.checkNull(host);

		this.host = host;
		this.result = result;
	}

	K getHost() {
		return host;
	}

	void setHost(K host) {
		this.host = host;
	}

	V getResult() {
		return result;
	}

	void setResult(V result) {
		this.result = result;
	}

}
