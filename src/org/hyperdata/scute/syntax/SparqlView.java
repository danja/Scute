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

	private static String[] KEYWORD_PATTERNS = {"(PREFIX)", "(SELECT)", "(DISTINCT)", "(WHERE)", "(LIMIT)"
		, "(FILTER)", "(REGEX)", "(OPTIONAL)", "(UNION)", "(OFFSET)", "(ORDER BY)"};

	private static String VARIABLE_PATTERN = "(\\?\\w++)";

	private static HashMap<Pattern, Color> patternMap;

	static {
		patternMap = new LinkedHashMap<Pattern, Color>();

		patternMap.putAll(TurtleView.commonPatternMap);
		
		patternMap.put(Pattern.compile(VARIABLE_PATTERN), Color.RED);
		// order is important!

		for(int i =0;i<KEYWORD_PATTERNS.length; i++){
			patternMap.put(
					Pattern.compile(KEYWORD_PATTERNS[i], Pattern.CASE_INSENSITIVE),
					Color.GREEN);
		}
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