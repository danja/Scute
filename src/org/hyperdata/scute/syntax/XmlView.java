/*
 * Copyright 2006-2008 Kees de Kooter
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
 * Thanks: http://groups.google.com/group/de.comp.lang.java/msg/2bbeb016abad270
 * 
 * IMPORTANT NOTE: regex should contain 1 group.
 * 
 * Using PlainView here because we don't want line wrapping to occur.
 * 
 * @author kees
 * @date 13-jan-2006
 * 
 */
public class XmlView extends HighlighterView implements PatternMap {

	/** The GENERI c_ xm l_ name. */
	private static String GENERIC_XML_NAME = "[A-Za-z\\-_]+(:[A-Za-z\\-_]+)?";

	/** The pattern colors. */
	private static HashMap<Pattern, Color> patternMap;

	/** The TA g_ attribut e_ pattern. */
	private static String TAG_ATTRIBUTE_PATTERN = "(" + GENERIC_XML_NAME
			+ ")\\w*\\=";
	
	/** The TA g_ attribut e_ value. */
	private static String TAG_ATTRIBUTE_VALUE = "\\w*\\=\\w*(\"[^\"]*\")";
	
	/** The TA g_ cdata. */
	private static String TAG_CDATA = "(<\\!\\[CDATA\\[.*\\]\\]>)";
	
	/** The TA g_ comment. */
	private static String TAG_COMMENT = "(<\\!--[\\w ]*-->)";
	
	/** The TA g_ en d_ pattern. */
	private static String TAG_END_PATTERN = "(/>)";
	
	/** The TA g_ pattern. */
	private static String TAG_PATTERN = "(</?" + GENERIC_XML_NAME + ")\\s?>?";

	static {
		// NOTE: the order is important!
		patternMap = new LinkedHashMap<Pattern, Color>();
		patternMap
				.put(Pattern.compile(TAG_PATTERN), new Color(63, 127, 127));
		patternMap.put(Pattern.compile(TAG_CDATA), Color.GRAY);
		patternMap.put(Pattern.compile(TAG_ATTRIBUTE_PATTERN), new Color(
				127, 0, 127));
		patternMap.put(Pattern.compile(TAG_END_PATTERN), new Color(63, 127,
				127));
		patternMap.put(Pattern.compile(TAG_ATTRIBUTE_VALUE), new Color(42,
				0, 255));
		patternMap.put(Pattern.compile(TAG_COMMENT), Color.BLUE);
	}

	/**
	 * Instantiates a new xml view.
	 * 
	 * @param element
	 *            the element
	 */
	public XmlView(Element element) {

		super(element);

		// Set tabsize to 4 (instead of the default 8)
		getDocument().putProperty(PlainDocument.tabSizeAttribute, 4);
	}
	
	public HashMap<Pattern, Color> getPatternMap() {
		return patternMap;
	}
}
