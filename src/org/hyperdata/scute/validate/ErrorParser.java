/**
 * 
 */
package org.hyperdata.scute.validate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author danny
 * 
 * not guaranteed ever to work...
 */
public class ErrorParser {
	 Pattern pattern = Pattern.compile("line (\\d+).+column (\\d+)");
private Matcher matcher;

/**
 * @param test
 */
public void parse(String string) {
	 matcher = pattern.matcher(string);
	 matcher.find();
}

/**
 * @return
 */
public int getColumn() {
	return Integer.parseInt(matcher.group(2));
}

public boolean found(){
	return matcher.groupCount() == 2;
}
/**
 * @return
 */
public int getLine() {
	return Integer.parseInt(matcher.group(1));
}

public static void main(String[] args){
	String test = "at line 3, column 44.";
	ErrorParser parser = new ErrorParser();
	parser.parse(test);
	System.out.println(parser.found());
	System.out.println("line = "+parser.getLine()+"   column = "+parser.getColumn());
}

}
