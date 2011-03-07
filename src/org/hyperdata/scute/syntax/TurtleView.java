/*
 * Scute
 * 
 * Homepage: http://hyperdata.org/scute
 * 
 * License : http://www.apache.org/licenses/LICENSE-2.0
 * See also license.txt or http://hyperdata.org/wiki/Scute:License
 * 
 * Danny Ayers 2011
 */
package org.hyperdata.scute.syntax;

import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Element;
import javax.swing.text.PlainDocument;
import javax.swing.text.PlainView;
import javax.swing.text.Segment;
import javax.swing.text.Utilities;

/**
 * The Class TurtleView.
 */
public class TurtleView extends HighlighterView {

	public static HashMap<Pattern, Color> commonPatternMap;
	private static HashMap<Pattern, Color> patternMap;
	
	public static String BNODE_PATTERN = "(_:\\w+)";
	
	public static String KW_BASE_PATTERN = "(@base)";
	
	public static String KW_PREFIX_PATTERN = "(@prefix)";
	
	public static String LITERAL_PATTERN = "(\".+\")";
	
	public static String LONG_LITERAL_PATTERN = "(\"\"\".+\"\"\")";
	
	public static String NODE_PATTERN = "(\\w*:\\w+)"; 
	
	public static String SQUARE_BRACKETS_PATTERN = "(\\[|\\])";

	public static String CURLY_BRACKETS_PATTERN = "(\\{|\\})";
	
	/** The UR i_ pattern. */
	public static String URI_PATTERN = "(<.+>)";

	static {
		commonPatternMap = new LinkedHashMap<Pattern, Color>();
		
		// order is important!
		commonPatternMap.put(Pattern.compile(URI_PATTERN), Color.BLUE);
		commonPatternMap.put(Pattern.compile(LITERAL_PATTERN), Color.GRAY);
		commonPatternMap.put(Pattern.compile(LONG_LITERAL_PATTERN), Color.GRAY);
		commonPatternMap.put(Pattern.compile(BNODE_PATTERN), Color.CYAN);
		commonPatternMap.put(Pattern.compile(NODE_PATTERN), Color.BLUE);
		commonPatternMap.put(Pattern.compile(SQUARE_BRACKETS_PATTERN), Color.BLUE);
		commonPatternMap.put(Pattern.compile(CURLY_BRACKETS_PATTERN), Color.BLUE);
		
		patternMap = new LinkedHashMap<Pattern, Color>();
		
		patternMap.putAll(commonPatternMap);
		patternMap.put(Pattern.compile(KW_PREFIX_PATTERN, Pattern.CASE_INSENSITIVE), Color.YELLOW);
		patternMap.put(Pattern.compile(KW_BASE_PATTERN, Pattern.CASE_INSENSITIVE), Color.GREEN);
	}

	/**
	 * Instantiates a new turtle view.
	 * 
	 * @param element
	 *            the element
	 */
	public TurtleView(Element element) {
		super(element);
		// Set tabsize to 4 (instead of the default 8)
		getDocument().putProperty(PlainDocument.tabSizeAttribute, 4);
	}
	
	/**
	 * @return
	 */
	public HashMap<Pattern, Color> getPatternMap() {
		return patternMap;
	}
}