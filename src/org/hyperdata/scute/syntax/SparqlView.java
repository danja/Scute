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
import java.awt.Graphics2D;
import java.awt.Shape;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.text.*;

/**
 * The Class TurtleView.
 */
public class SparqlView extends HighlighterView {

	// private static String KW_BASE_PATTERN = "(@base)";

	private static String KW_PREFIX_PATTERN = "(PREFIX)";

	private static String KW_SELECT_PATTERN = "(SELECT)";
	private static String KW_WHERE_PATTERN = "(WHERE)";
	private static String KW_LIMIT_PATTERN = "(LIMIT)";

	private static String VARIABLE_PATTERN = "(\\?\\w++)";

	private static HashMap<Pattern, Color> patternMap;

	static {
		patternMap = new LinkedHashMap<Pattern, Color>();

		// order is important!

		// SPARQL extra
		patternMap.put(
				Pattern.compile(KW_WHERE_PATTERN, Pattern.CASE_INSENSITIVE),
				Color.GREEN);
		patternMap.put(
				Pattern.compile(KW_SELECT_PATTERN, Pattern.CASE_INSENSITIVE),
				Color.GREEN);
		patternMap.put(
				Pattern.compile(KW_LIMIT_PATTERN, Pattern.CASE_INSENSITIVE),
				Color.GREEN);

		// SPARQL extra
		patternMap.put(Pattern.compile(VARIABLE_PATTERN), Color.BLUE);

		patternMap.put(Pattern.compile(TurtleView.URI_PATTERN), Color.RED);
		patternMap.put(Pattern.compile(TurtleView.LITERAL_PATTERN),
				Color.GRAY);
		patternMap
				.put(Pattern.compile(TurtleView.BNODE_PATTERN), Color.CYAN);
		patternMap.put(Pattern.compile(TurtleView.NODE_PATTERN), Color.RED);
		patternMap.put(Pattern.compile(TurtleView.SQUARE_BRACKETS_PATTERN),
				Color.BLUE);
		patternMap.put(Pattern.compile(KW_PREFIX_PATTERN), Color.YELLOW);
	}

	private boolean scaled = false;

	/**
	 * Instantiates a new turtle view.
	 * 
	 * @param element
	 *            the element
	 */
	public SparqlView(Element element) {
		super(element);
	}

	public HashMap<Pattern, Color> getPatternMap() {
		return patternMap;
	}
}