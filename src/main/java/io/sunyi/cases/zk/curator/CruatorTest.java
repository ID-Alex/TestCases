package io.sunyi.cases.zk.curator;

import com.netflix.curator.framework.CuratorFramework;
import com.netflix.curator.framework.CuratorFrameworkFactory;
import com.netflix.curator.framework.CuratorFrameworkFactory.Builder;
import com.netflix.curator.framework.api.CuratorWatcher;
import com.netflix.curator.framework.state.ConnectionState;
import com.netflix.curator.framework.state.ConnectionStateListener;
import com.netflix.curator.retry.RetryNTimes;
import org.apache.zookeeper.WatchedEvent;

public class CruatorTest {

	public static void main(String[] args) throws Exception {

		Builder builder = CuratorFrameworkFactory.builder().connectString("192.168.1.107:2181")
				.retryPolicy(new RetryNTimes(Integer.MAX_VALUE, 1000)).connectionTimeoutMs(5000);
		final CuratorFramework client = builder.build();
		client.getConnectionStateListenable().addListener(new ConnectionStateListener() {
			public void stateChanged(CuratorFramework client, ConnectionState state) {
				if (state == ConnectionState.LOST) {
				} else if (state == ConnectionState.CONNECTED) {
				} else if (state == ConnectionState.RECONNECTED) {
				}
			}
		});
		client.start();

		client.getData().usingWatcher(new CuratorWatcher() {

			@Override
			public void process(WatchedEvent event) throws Exception {
				String path = event.getPath();
				byte[] data = client.getData().usingWatcher(this).forPath(path);
				System.out.println(new String(data));

			}
		}).forPath("/test");

		System.in.read();

	}

}
