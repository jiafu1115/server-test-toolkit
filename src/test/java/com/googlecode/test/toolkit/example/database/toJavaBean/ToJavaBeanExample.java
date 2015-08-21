package com.googlecode.test.toolkit.example.database.toJavaBean;

import java.util.List;

import com.googlecode.test.toolkit.database.DefaultDbClient;
import com.googlecode.test.toolkit.database.config.OracleConfig;
import com.googlecode.test.toolkit.database.resultset.handler.ToJavabeanListHandler;

public class ToJavaBeanExample {

	public static void main(String[] args) {

		String url = "jdbc:oracle:thin:@10.224.210.121:3001:pbop";
		String username = "test";
		String password = "pass";

		DefaultDbClient defaultDbClient = new DefaultDbClient(new OracleConfig(url, username, password));

		String sql = "select * from TEST.TABLE order by createtime desc";
		ToJavabeanListHandler<TABLENAME> toJavabeanListHandler = new ToJavabeanListHandler<TABLENAME>(
				TABLENAME.class);
		List<TABLENAME> result = defaultDbClient.query(sql, toJavabeanListHandler);

		System.out.println(result.size());
		System.out.println(result.get(0));

	}

}
