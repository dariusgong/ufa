package org.ufa.data.nosql.mongodb;
import org.springframework.data.mongodb.core.MongoTemplate;


/**
 * <P> 获取不同特性的MongoTemplate的工厂方法 </P>
 */
public class MongoTemplateFactory {
	private final MongoTemplate fastest;
	private final MongoTemplate faster;
	private final MongoTemplate fast;
	private final MongoTemplate safest;

	public MongoTemplateFactory(MongoTemplate fastest, MongoTemplate faster, MongoTemplate fast, MongoTemplate safest) {
		this.fastest = fastest;
		this.faster = faster;
		this.fast = fast;
		this.safest = safest;
	}

	/**
	 * 性能最高,数据安全性最低的配置:<br>
	 * 1.不返回所有服务器端或网络异常<br>
	 * 2.不等待写入redo log就返回响应<br>
	 * 3.不会等待数据同步到集群其他节点后才返回响应<br>
	 * 4.批量操作时,其中某一条执行出错,继续执行
	 * 5.超时时间为3秒
	 */
	public MongoTemplate getFastest() {
		return fastest;
	}

	/**
	 * 性能非常高,数据安全性较低的配置:<br>
	 * 1.会返回网络异常,但不返回mongodb服务器端的异常<br>
	 * 2.不会等待写入redo log后返回响应<br>
	 * 3.不会等待数据同步到集群其他节点后返回响应<br>
	 * 4.批量操作时,其中某一条执行出错,则立刻返回异常,不再继续执行
	 * 5.超时时间为5秒
	 */
	public MongoTemplate getFaster() {
		return faster;
	}

	/**
	 * 性能普通,数据安全性一般的配置:<br>
	 * 1.会返回所有服务器端或网络异常<br>
	 * 2.会等待写入redo log后返回响应<br>
	 * 3.不会等待数据同步到集群其他节点后返回响应<br>
	 * 4.批量操作时,其中某一条执行出错,则立刻返回异常,不再继续执行
	 * 5.超时时间为10秒
	 */
	public MongoTemplate getFast() {
		return fast;
	}

	/**
	 * 数据一致性最高,最安全的配置:<br>
	 * 1.会返回所有服务器端或网络异常<br>
	 * 2.会等待写入redo log后返回响应<br>
	 * 3.配置了集群同步的情况下,会等待数据同步到集群其他节点后返回响应;单机情况下没有任何同步等待<br>
	 * 4.批量操作时,其中某一条执行出错,则立刻返回异常,不再继续执行
	 * 5.超时时间为30秒
	 */
	public MongoTemplate getSafest() {
		return safest;
	}

}
