package io.sunyi.cases.pongohero;

import java.util.*;

/**
 * 题目详情 给了A、B两个单词和一个单词集合Dict，每个的长度都相同。我们希望通过若干次操作把单词A变成单词B，每次操作可以改变单词中的一个字母，同时，
 * 新产生的单词必须是在给定的单词集合Dict中。求所有行得通步数最少的修改方法。
 * 
 * 举个例子如下： Given: A = "hit" B = "cog" Dict = ["hot","dot","dog","lot","log"]
 * Return [ ["hit","hot","dot","dog","cog"], ["hit","hot","lot","log","cog"] ]
 * 
 * 即把字符串A = "hit"转变成字符串B = "cog"，有以下两种可能： "hit" -> "hot" -> "dot" -> "dog" ->
 * "cog"； "hit" -> "hot" -> "lot" -> "log" ->"cog"。
 * 
 * 
 */
public class 最小操作数 {

	public static class Node {
		public String content;
		public Vector<String> path = new Vector<String>();
	}

	public static Vector<Vector<String>> findLadders(String start, String end, Set<String> dict) {

		Vector<Vector<String>> result = new Vector<Vector<String>>();

		if (start == null ? end == null : start.equals(end)) {
			return result;
		}

		ThreadLocal<Boolean> over = new ThreadLocal<Boolean>();
		over.set(false);

		Node fromNode = new Node();
		fromNode.content = start;
		fromNode.path.add(start);

		List<Node> nodeList = new Vector<Node>();
		nodeList.add(fromNode);

		List<Node> nextNodeList = new Vector<Node>();

		while (!over.get()) {
			for (Node node : nodeList) {
				nextNodeList.addAll(getNode(node, end, dict, over, result));
			}
			nodeList.clear();
			nodeList.addAll(nextNodeList);
			nextNodeList.clear();
		}

		return result;
	}

	public static boolean canConvert(String from, String to) {
		int diff = 0;

		for (int i = 0; i < from.length(); i++) {
			if (from.charAt(i) != to.charAt(i)) {
				if (diff == 0) {
					diff++;
				} else {
					return false;
				}
			}
		}
		return true;
	}

	public static List<Node> getNode(Node node, String to, Set<String> dict, ThreadLocal<Boolean> over, Vector<Vector<String>> result) {
		if (canConvert(node.content, to)) {
			over.set(true);
			node.path.add(to);
			result.add(node.path);
			return Collections.emptyList();
		} else {
			List<Node> nextNodes = new Vector<Node>();
			for (String d : dict) {
				if (canConvert(node.content, d)) {
					Node nextNode = new Node();
					nextNode.content = d;
					nextNode.path.addAll(node.path);
					nextNode.path.add(d);
					nextNodes.add(nextNode);
				}
			}

			for (Node nextNode : nextNodes) {
				dict.remove(nextNode.content);
			}

			return nextNodes;
		}
	}

	public static void main(String[] args) {
		String[] dictArray = new String[] { "hot", "dot","hid","doa","hhh","doi","loa", "hoa","coa","dog", "lot", "log" };
		Set<String> dict = new HashSet<String>();
		Collections.addAll(dict, dictArray);

		Vector<Vector<String>> findLadders = findLadders("hit", "cog", dict);
		for(Vector<String> e : findLadders){
			System.out.println(e);
		}
	}

}
