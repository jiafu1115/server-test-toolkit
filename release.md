Version 2.2.6(April 24, 2014)

<pre>
•   Fix bug issued:  When multithread run the ssh tool by >10 threads will popup exception: the root cause is linux’s SSHD only allow 10 channel for every session, so fix it:  if found the opened session using channel >10, will new one new session.<br>
Add log:<br>
<br>
08:48:50,474  INFO SshServerOperations:136 - [Server] [Dynamic Update IP-Session Map]<br>
<br>
<br>
<br>
•   Add remote common pool client into it<br>
com.googlecode.common.remote.pool.client.CommonRemotePoolClient.CommonRemotePoolClient(String)<br>
com.googlecode.common.remote.pool.client.CommonRemotePoolClient.borrowObject(Class)<br>
com.googlecode.common.remote.pool.client.CommonRemotePoolClient.returnObject(Object)<br>
com.googlecode.common.remote.pool.client.CommonRemotePoolClient.addObject(Object...)<br>
<br>
</pre>
