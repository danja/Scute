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
public class SparqlView extends ScalableView {

	// private static String KW_BASE_PATTERN = "(@base)";

	private static String KW_PREFIX_PATTERN = "(PREFIX)";

	private static String KW_SELECT_PATTERN = "(SELECT)";
	private static String KW_WHERE_PATTERN = "(WHERE)";
	private static String KW_LIMIT_PATTERN = "(LIMIT)";

	private static String VARIABLE_PATTERN = "(\\?\\w++)";

	private static HashMap<Pattern, Color> patternColors;

	static {
		patternColors = new LinkedHashMap<Pattern, Color>();

		// order is important!

		// SPARQL extra
		patternColors.put(
				Pattern.compile(KW_WHERE_PATTERN, Pattern.CASE_INSENSITIVE),
				Color.GREEN);
		patternColors.put(
				Pattern.compile(KW_SELECT_PATTERN, Pattern.CASE_INSENSITIVE),
				Color.GREEN);
		patternColors.put(
				Pattern.compile(KW_LIMIT_PATTERN, Pattern.CASE_INSENSITIVE),
				Color.GREEN);

		// SPARQL extra
		patternColors.put(Pattern.compile(VARIABLE_PATTERN), Color.BLUE);

		patternColors.put(Pattern.compile(TurtleView.URI_PATTERN), Color.RED);
		patternColors.put(Pattern.compile(TurtleView.LITERAL_PATTERN),
				Color.GRAY);
		patternColors
				.put(Pattern.compile(TurtleView.BNODE_PATTERN), Color.CYAN);
		patternColors.put(Pattern.compile(TurtleView.NODE_PATTERN), Color.RED);
		patternColors.put(Pattern.compile(TurtleView.SQUARE_BRACKETS_PATTERN),
				Color.BLUE);
		patternColors.put(Pattern.compile(KW_PREFIX_PATTERN), Color.YELLOW);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.text.PlainView#drawUnselectedText(java.awt.Graphics,
	 * int, int, int, int)
	 */
	@Override
	protected int drawUnselectedText(Graphics graphics, int x, int y, int p0,
			int p1) throws BadLocationException {

		// float xSpan = getPreferredSpan(View.X_AXIS);
		// float ySpan = getPreferredSpan(View.Y_AXIS);
		// setSize(xSpan*2, ySpan*2);

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

//	public void paint(Graphics g, Shape a) {
//		((Graphics2D) g).scale(2, 2);
//		super.paint(g, a);
//	}
}