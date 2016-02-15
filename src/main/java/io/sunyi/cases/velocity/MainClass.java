package io.sunyi.cases.velocity;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;

import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class MainClass
{
	public static void main(String[] args)
	{
		String path = "src/main/resource/io/sunyi/cases/velocity/sample.vm";

		Velocity.init();

		// parameter
		VelocityContext context = new VelocityContext();

		List<String> list = new ArrayList<String>();
		list.add("1");
		list.add("2");
		list.add("3");
		
		boolean bl = false;
		
		Object condition = null;
		//__
		
		
		context.put("name", new String("VelocityTest"));
		context.put("list", list);
		context.put("bl",bl);
		context.put("condition",condition);

		Template template = null;

		try
		{
			template = Velocity.getTemplate(path);
			
			OutputStreamWriter writer = new OutputStreamWriter(System.out);

			template.merge(context, writer);
			
			writer.flush();


		} catch (ResourceNotFoundException rnfe)
		{
			// couldn't find the template
			rnfe.printStackTrace();
		} catch (ParseErrorException pee)
		{
			// syntax error: problem parsing the template
			pee.printStackTrace();
		} catch (MethodInvocationException mie)
		{
			// something invoked in the template
			mie.printStackTrace();
			// threw an exception
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
