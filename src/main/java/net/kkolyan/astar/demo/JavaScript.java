package net.kkolyan.astar.demo;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;

public class JavaScript {

	public static void evaluate(String... scripts) throws IOException {
		try {
            ScriptEngineManager sem = new ScriptEngineManager();
            ScriptEngine engine = sem.getEngineByName("javascript");
			for (String script: scripts) {
                URL url = JavaScript.class.getClassLoader().getResource(script);
                if (url == null) {
                    throw new FileNotFoundException("classpath:"+script);
                }
				Reader reader = new InputStreamReader(url.openStream());
                engine.eval(reader);
				reader.close();
			}
		} catch (ScriptException e) {
            e.printStackTrace();
		}
	}
}
