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

import javax.swing.text.*;

/**
 * The Class HighlighterView.
 * 
 * contains methods - subclasses contain pattern constants
 * 
 */
public abstract class HighlighterView extends ScalableView implements PatternMap {

	private static HashMap<Pattern, Color> patternMap;
	
	/**
	 * Instantiates a new turtle view.
	 * 
	 * @param element
	 *            the element
	 */
	public HighlighterView(Element element) {

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

		final SortedMap<Integer, Integer> blockMap = new TreeMap<Integer, Integer>();
		final SortedMap<Integer, Color> colorMap = new TreeMap<Integer, Color>();

		
		// Match all regexes on this snippet, store positions
		for (final Map.Entry<Pattern, Color> entry : getPatternMap().entrySet()) {
			// System.out.println();
			final Matcher matcher = entry.getKey().matcher(text);
			MatcherLoop:
			while (matcher.find()) {
//				System.out.println(matcher.start(1) + " -> " +matcher.end());
//				System.out.println(matcher.group());

				// remove blocks nested inside - redundant?
				for(int i = matcher.start(1);i<matcher.end();i++){
					if(blockMap.get(i) != null){
						if(blockMap.get(i) < matcher.end()){
							blockMap.remove(i);
						}
					}
				}
				// don't insert if this will be inside a larger match
				for(int i = 0;i<matcher.start(1);i++){
					if(blockMap.get(i) != null){
						if(blockMap.get(i) > matcher.end()){
//							System.out.println("don't include inner "+i+" -> "+matcher.end());
//							System.out.println("-----------------------");
							break MatcherLoop;
						}
					}
				}
				blockMap.put(matcher.start(1), matcher.end());
				colorMap.put(matcher.start(1), entry.getValue());
			}
		}

		// Colour the parts
		int i = 0;
		for (final Map.Entry<Integer, Integer> entry : blockMap.entrySet()) {
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
		return x; // x is end of block
	}
}