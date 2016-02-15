package io.sunyi.cases.zk.zkclient;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.IZkStateListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.exception.ZkMarshallingError;
import org.I0Itec.zkclient.serialize.ZkSerializer;
import org.apache.zookeeper.Watcher.Event.KeeperState;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class ZkclientTest {

	public static void main(String[] args) throws IOException {

		ZkClient client = new ZkClient("192.168.1.107:2181");
		client.setZkSerializer(new ZkSerializer() {

			@Override
			public byte[] serialize(Object data) throws ZkMarshallingError {
				if (data == null) {
					return new byte[] {};
				} else {
					try {
						return data.toString().getBytes("UTF-8");
					} catch (UnsupportedEncodingException e) {
						throw new RuntimeException(e.getMessage(), e);
					}
				}

			}

			@Override
			public Object deserialize(byte[] bytes) throws ZkMarshallingError {
				try {
					if (bytes == null || bytes.length == 0) {
						return null;
					} else {
						return new String(bytes, "UTF-8");
					}
				} catch (UnsupportedEncodingException e) {
					throw new RuntimeException(e.getMessage(), e);
				}
			}
		});

		client.subscribeStateChanges(new IZkStateListener() {
			public void handleStateChanged(KeeperState state) throws Exception {
				if (state == KeeperState.Disconnected) {
					System.out.println("KeeperState --------------> Disconnected");
				} else if (state == KeeperState.SyncConnected) {
					System.out.println("KeeperState --------------> SyncConnected");
				}
			}

			public void handleNewSession() throws Exception {
				System.out.println("KeeperState --------------> Reonnected");
			}
		});

		client.subscribeDataChanges("/tt", new IZkDataListener() {

			@Override
			public void handleDataDeleted(String dataPath) throws Exception {
				System.out.println("handleDataDeleted, dataPath" + dataPath);

			}

			@Override
			public void handleDataChange(String dataPath, Object data) throws Exception {
				System.out.println("handleDataDeleted, dataPath" + dataPath + ",data" + data);
				throw new RuntimeException("aaaaaaa");
			}
		});

		System.in.read();
	}
}
