package io.sunyi.cases.serialization.kyro;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.sun.tools.doclets.internal.toolkit.util.DocFinder;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.objenesis.strategy.StdInstantiatorStrategy;
import sun.java2d.pipe.SpanShapeRenderer;
import io.sunyi.cases.serialization.Model;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;

/**
 * @author sunyi
 *         Created on 16/1/4
 */
public class KyroTest {

	public static void main(String args[]) throws DecoderException {
		Kryo kryo = new Kryo();


//		Model model = Model.getObject();
//
//
//		ByteArrayOutputStream baos = new ByteArrayOutputStream();
//		Output output = new Output(baos);
//
//		kryo.writeObject(output, model);
//
//		output.flush();
//
//		String o = new String(Hex.encodeHex(baos.toByteArray()));
//		System.out.println(o);


		String o = "01017474f414";

		byte[] bytes = Hex.decodeHex(o.toCharArray());
		ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
		Input input = new Input(bais);
		Model model1 = kryo.readObject(input, Model.class);

		System.out.println(model1);


	}
}
