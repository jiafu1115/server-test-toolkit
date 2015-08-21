package com.googlecode.test.toolkit.server.ssh.performance;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

/**
 * <pre>
 * procs -----------memory----------    ---swap--    -----io----   --system--    -----cpu------
 *  r  b   swpd   free   buff  cache     si   so       bi    bo     in   cs      us sy id wa st
 *  1  0    112 1670000 236252 2652656    0    0       0     6       0    1       0  0 100  0  0
 * </pre>
 *
 * @author fu.jian
 * date Aug 6, 2012
 */
public class PerformanceData {

	private int r;
	private int b;
	private int cache;
	private int so;
	private int us;
	private int in;
	private int si;
	private int cs;
	private int id;
	private int bo;
	private int free;
	private int swpd;
	private int buff;
	private int wa;
	private int bi;
	private int sy;
	private int st;

	public static PerformanceData parsePerformanceData(String str) {
		int startIndexForKeys = str.indexOf(" r");
		int endIndexForKeys = str.indexOf(" st") + 3;
		String[] keys = subStrAndSplitWithSpace(str, startIndexForKeys, endIndexForKeys);

		int startIndexForValues = endIndexForKeys;
		int endIndexForValues = str.length();
		String[] values = subStrAndSplitWithSpace(str, startIndexForValues, endIndexForValues);

		HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
		for (int i = 0; i < keys.length; i++) {
			hashMap.put(keys[i], Integer.valueOf(values[i]));
		}

		return fromMap(hashMap);
	}

	private static String[] subStrAndSplitWithSpace(String str, int startIndex, int endIndex) {
		String rawKeys = str.substring(startIndex, endIndex);
		String[] keys = rawKeys.trim().split("(\\s)+");
		return keys;
	}

	public static PerformanceData fromMap(Map<String, Integer> map) {
		PerformanceData performanceData = new PerformanceData();
		try {
			BeanUtils.copyProperties(performanceData, map);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
 		}

		return performanceData;
	}

	public PerformanceData() {
		super();
	}

	public int getB() {
		return b;
	}

	public void setB(int b) {
		this.b = b;
	}

	public int getCache() {
		return cache;
	}

	public void setCache(int cache) {
		this.cache = cache;
	}

	public int getSo() {
		return so;
	}

	public void setSo(int so) {
		this.so = so;
	}

	public int getUs() {
		return us;
	}

	public void setUs(int us) {
		this.us = us;
	}

	public int getIn() {
		return in;
	}

	public void setIn(int in) {
		this.in = in;
	}

	public int getSi() {
		return si;
	}

	public void setSi(int si) {
		this.si = si;
	}

	public int getCs() {
		return cs;
	}

	public void setCs(int cs) {
		this.cs = cs;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBo() {
		return bo;
	}

	public void setBo(int bo) {
		this.bo = bo;
	}

	public int getFree() {
		return free;
	}

	public void setFree(int free) {
		this.free = free;
	}

	public int getR() {
		return r;
	}

	public void setR(int r) {
		this.r = r;
	}

	public int getSwpd() {
		return swpd;
	}

	public void setSwpd(int swpd) {
		this.swpd = swpd;
	}

	public int getBuff() {
		return buff;
	}

	public void setBuff(int buff) {
		this.buff = buff;
	}

	public int getWa() {
		return wa;
	}

	public void setWa(int wa) {
		this.wa = wa;
	}

	public int getBi() {
		return bi;
	}

	public void setBi(int bi) {
		this.bi = bi;
	}

	public int getSy() {
		return sy;
	}

	public void setSy(int sy) {
		this.sy = sy;
	}

	public MemoryData getMemoryData() {
		return new MemoryData(swpd, free, buff, cache);
	}

	public IoData getIoData() {
		return new IoData(bi, bo);
	}

	public SystemData getSystemData() {
		return new SystemData(in, cs);
	}

	public CpuData getCpuData() {
		return new CpuData(us, sy, id, wa, st);
	}

	public SwapData getSwapData() {
		return new SwapData(si, so);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PerformanceData [r=");
		builder.append(r);
		builder.append(", b=");
		builder.append(b);
		builder.append(", cache=");
		builder.append(cache);
		builder.append(", so=");
		builder.append(so);
		builder.append(", us=");
		builder.append(us);
		builder.append(", in=");
		builder.append(in);
		builder.append(", si=");
		builder.append(si);
		builder.append(", cs=");
		builder.append(cs);
		builder.append(", id=");
		builder.append(id);
		builder.append(", bo=");
		builder.append(bo);
		builder.append(", free=");
		builder.append(free);
		builder.append(", swpd=");
		builder.append(swpd);
		builder.append(", buff=");
		builder.append(buff);
		builder.append(", wa=");
		builder.append(wa);
		builder.append(", bi=");
		builder.append(bi);
		builder.append(", sy=");
		builder.append(sy);
		builder.append(", st=");
		builder.append(st);
		builder.append("]");

		return builder.toString();
	}

}
