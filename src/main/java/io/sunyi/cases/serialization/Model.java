package io.sunyi.cases.serialization;

public class Model implements java.io.Serializable {
	private String a;
	private int b;
	private int c;


	public static Model getObject() {
		Model simple = new Model();
		simple.a = "ttt";
		simple.b = 10;
		return simple;
	}

	@Override
	public String toString() {
		return "Model{" +
				"a='" + a + '\'' +
				", b=" + b +
				'}';
	}
}