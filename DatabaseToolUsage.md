
#how to use database tool


# DB Support #
mysql/oracle

# All methods support #

```
	Connection getConnnection();

	boolean execute(String sql);

	int update(String sql);

	int update(String sql, Object[] params);

	boolean execute(String sql, Object[] params);

	<T> T query(String sql, Object[] params, ResultSetHandler<T> resultSetHandler);

	<T> T query(String sql, ResultSetHandler<T> resultSetHandler);
```

# How to handle query result #

There are two methods to handle the result:

## 1. convert to map list ##

one DB record is one map for column and column's value. map's list is for multi DB records.


```
OracleConfig oracleConfig = new OracleConfig("jdbc:oracle:thin:@2.2.6.30:1523:taweb", "test", "pass");
		DefaultDbClient defaultDbClient = new DefaultDbClient(oracleConfig);
defaultDbClient.execute("select count(*) from mtgconference");
ResultSetHandler<List<HashMap<String, ?>>> resultSetHandler=new ToMapListHandler();
List<HashMap<String, ?>> query = defaultDbClient.query("select * from mtgconference where rownum=1", resultSetHandler);

```

## 2. convert to java bean list ##

one DB record is one java bean object

create one java bean class such as TABLE\_MQ to represent db table:
then we can write code as followed:
```
String sql = "select * from TEST.TABLE_MQ order by createtime desc";
ToJavabeanListHandler<TABLE_MQ> toJavabeanListHandler = new ToJavabeanListHandler<TABLE_MQ>(
TABLE_MQ.class);
List<TABLE_MQ> result = defaultDbClient.query(sql, toJavabeanListHandler);
```
