package org.hyperdata.scute.source;

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

public class TurtleView extends PlainView {

	private static String BNODE_PATTERN = "(_:\\w+)";
	private static String KW_BASE_PATTERN = "(@base)";
	private static String KW_PREFIX_PATTERN = "(@prefix)";
	private static String LITERAL_PATTERN = "(\"\")";
	private static String NODE_PATTERN = "(:\\w+)";
	private static HashMap<Pattern, Color> patternColors;
	private static String SEPERATORS_PATTERN = "(\\[|\\])";
	private static String URI_PATTERN = "(<http://.*>)";

	static {
		// NOTE: the order is important!
		patternColors = new LinkedHashMap<Pattern, Color>();
		patternColors.put(Pattern.compile(URI_PATTERN), Color.RED);
		patternColors.put(Pattern.compile(LITERAL_PATTERN), Color.GRAY);
		patternColors.put(Pattern.compile(BNODE_PATTERN), Color.CYAN);
		patternColors.put(Pattern.compile(NODE_PATTERN), Color.RED);
		patternColors.put(Pattern.compile(SEPERATORS_PATTERN), Color.BLUE);

		patternColors.put(Pattern.compile(KW_PREFIX_PATTERN), Color.YELLOW);
		patternColors.put(Pattern.compile(KW_BASE_PATTERN), Color.GREEN);
	}

	public TurtleView(Element element) {

		super(element);

		// Set tabsize to 4 (instead of the default 8)
		getDocument().putProperty(PlainDocument.tabSizeAttribute, 4);
	}

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