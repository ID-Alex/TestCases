package io.sunyi.cases.netty.echo;

import com.sun.corba.se.impl.orbutil.ObjectWriter;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

/**
 * @author sunyi
 *         Created on 15/9/23
 */
public class EchoEncode extends MessageToMessageEncoder<Object> {

	@Override
	protected void encode(ChannelHandlerContext ctx, Object msg, List out) throws Exception {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(baos);
		oos.writeObject(msg);
		oos.flush();
		byte[] bytes = baos.toByteArray();
		ByteBuf bb = Unpooled.buffer(bytes.length);
		bb.writeBytes(bytes);
		out.add(bb);

	}
}
