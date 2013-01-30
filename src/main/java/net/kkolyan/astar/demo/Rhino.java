package net.kkolyan.astar.demo;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.RhinoException;
import org.mozilla.javascript.Scriptable;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;

public class Rhino {

	public static void evaluate(String... scripts) throws IOException {
		Context context = Context.enter();
		try {
			Scriptable scope = context.initStandardObjects();

			for (String script: scripts) {
                URL url = Rhino.class.getClassLoader().getResource(script);
                if (url == null) {
                    throw new FileNotFoundException("classpath:"+script);
                }
				Reader reader = new InputStreamReader(url.openStream());
				context.evaluateReader(scope, reader, url.toString(), 1, null);
				reader.close();
			}
		} catch (RhinoException e) {
			System.out.println(e.getMessage());
			System.out.println(e.getScriptStackTrace());
		}
		finally {
			Context.exit();
		}
	}
}
