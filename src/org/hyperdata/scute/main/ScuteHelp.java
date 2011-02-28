/**
 * 
 */
package org.hyperdata.scute.main;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import oracle.help.Help;
import oracle.help.library.helpset.*;

/**
 * @author danny
 *
 *
 *
 *possible values in Scute.hs :
 *
 oracle.help.engine.SearchEngine
<data engine="com.sun.java.help.search.DefaultSearchEngine">JavaHelpSearch</data>
javax.help.search.MergingSearchEngine.class
javax.help.search.SearchEngine.class

 */
public class ScuteHelp  {

	/**
	 * @param args
	 */
	public static void main(String[] args) { 
		// TODO need to add close action for standalone
	new ScuteHelp();
	}

	private Help help;
	
	public ScuteHelp(){
		help = new Help();
		File file = new File("doc/www/Scute.hs");
		URL url = null;
		try {
			url = file.toURI().toURL();
		} catch (MalformedURLException exception1) {
			exception1.printStackTrace();
		}
		HelpSet helpSet = null;
		try {
			helpSet = new HelpSet(url);
		} catch (HelpSetParseException exception) {
			exception.printStackTrace();
		}
		help.addBook(helpSet);
	}
	
	public void show(){
		 help.showNavigatorWindow();
	}
}
