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
public class TurtleView extends ScalableView {

	private static HashMap<Pattern, Color> patternColors;
	
	public static String BNODE_PATTERN = "(_:\\w+)";
	
	public static String KW_BASE_PATTERN = "(@base)";
	
	public static String KW_PREFIX_PATTERN = "(@prefix)";
	
	public static String LITERAL_PATTERN = "(\".+\")";
	
	public static String LONG_LITERAL_PATTERN = "(\"\"\".+\"\"\")";
	
	public static String NODE_PATTERN = "(\\w*:\\w+)"; 
	
	// PROBLEM:
	// http://dbpedia.org/resource/Category:Furniture-making
	
	public static String SQUARE_BRACKETS_PATTERN = "(\\[|\\])";
	
	/** The UR i_ pattern. */
	public static String URI_PATTERN = "(<.+>)";

	static {
		patternColors = new LinkedHashMap<Pattern, Color>();
		
		// order is important!
		patternColors.put(Pattern.compile(URI_PATTERN), Color.RED);
		patternColors.put(Pattern.compile(LITERAL_PATTERN), Color.GRAY);
		patternColors.put(Pattern.compile(BNODE_PATTERN), Color.CYAN);
		patternColors.put(Pattern.compile(NODE_PATTERN), Color.RED);
		patternColors.put(Pattern.compile(SQUARE_BRACKETS_PATTERN), Color.BLUE);

		patternColors.put(Pattern.compile(KW_PREFIX_PATTERN, Pattern.CASE_INSENSITIVE), Color.YELLOW);
		patternColors.put(Pattern.compile(KW_BASE_PATTERN, Pattern.CASE_INSENSITIVE), Color.GREEN);
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

	/* (non-Javadoc)
	 * @see javax.swing.text.PlainView#drawUnselectedText(java.awt.Graphics, int, int, int, int)
	 */
	@Override
	protected int drawUnselectedText(Graphics graphics, int x, int y, int p0,
			int p1) throws BadLocationException {

		final Document doc = getDocument();
		final String text = doc.getText(p0, p1 - p0);

		final Segment segment = getLineBuffer();

		final SortedMap<Integer, Integer> startMap = new TreeMap<Integer, Integer>();
		final SortedMap<Integer, Color> colorMap = new TreeMap<Integer, Color>();

		// Match all regexes on this snippet, store positions
		for (final Map.Entry<Pattern, Color> entry : patternColors.entrySet()) {

			final Matcher matcher = entry.getKey().matcher(text);

			while (matcher.find()) {
				startMap.put(matcher.start(1), matcher.end());
				colorMap.put(matcher.start(1), entry.getValue());
			}
		}

		// TODO: check the map for overlapping parts

		int i = 0;

		// Colour the parts
		for (final Map.Entry<Integer, Integer> entry : startMap.entrySet()) {
			final int start = entry.getKey();
			final int end = entry.getValue();

			if (i < start) {
				graphics.setColor(Color.black);
				doc.getText(p0 + i, start - i, segment);
				x = Utilities.drawTabbedText(segment, x, y, graphics, this, i);
			}

			graphics.setColor(colorMap.get(start));
			i = end;
			doc.getText(p0 + start, i - start, segment);
			x = Utilities.drawTabbedText(segment, x, y, graphics, this, start);
		}

		// Paint possible remaining text black
		if (i < text.length()) {
			graphics.setColor(Color.black);
			doc.getText(p0 + i, text.length() - i, segment);
			x = Utilities.drawTabbedText(segment, x, y, graphics, this, i);
		}

		return x;
	}

}