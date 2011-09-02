package org.hyperdata.scute.rdf;

import java.io.InputStream;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.util.FileManager;

/**
 * output format as described in http://lists.w3.org/Archives/Public/public-linked-json/2011Sep/0035.html
 */
public class ModelToJSON {

	private ModelToJSON(Model model) {
	}

	public static String getJSON(Model model) {
		StringBuffer json = new StringBuffer("[");

		StmtIterator iterator = model.listStatements();
		boolean first = true;
		while (iterator.hasNext()) { // neater way of doing this?
			if(first){
				first = false;
			} else {
				json.append("\n,");
			}
			doStatement(json, iterator.nextStatement());
		}
		return json + "]";
	}

	private static void doStatement(StringBuffer json, Statement statement) {
		json.append("{\n    \"s\": ");
		doNode(json, statement.getSubject());
		json.append(",\n   \"p\": ");
		doNode(json, statement.getPredicate());
		json.append(",\n   \"o\": ");
		doNode(json, statement.getObject());
		json.append("}");
	}

	private static void doNode(StringBuffer json, RDFNode node) {
		// "s": { "type": "uri" , "value": "http://hyperdata.org/seki/Hello" }
		json.append("{ \"type\": \"");
		doTypeString(json, node);
		json.append("\" , \"value\": \"");
		doValueString(json, node);
		json.append("\" } ");

	}

	private static void doValueString(StringBuffer json, RDFNode node) {
		if (node.isURIResource()) {
			json.append(node.asResource().getURI());
			return;
		}
		if (node.isAnon()) {
			json.append(node.asResource().getId());
			return;
		}
		if (node.isLiteral()) {
			json.append(node.asLiteral().getString());
			doLang(json, node.asLiteral());
			doDatatype(json, node.asLiteral());
		}
	}

	private static void doTypeString(StringBuffer json, RDFNode node) {
		if (node.isURIResource()) {
			json.append("uri");
			return;
		}
		if (node.isAnon()) {
			json.append("bnode");
			return;
		}
		if (node.isLiteral()) {
			json.append("literal");
		}
	}

	private static void doDatatype(StringBuffer json, Literal literal) {
		String datatype = literal.getDatatypeURI();
		if (datatype != null) {
			json.append(", \"datatype\": \"" + datatype + "\"");
		}
	}

	private static void doLang(StringBuffer json, Literal literal) {
		String language = literal.getLanguage();
		if (!language.equals("")) {
			json.append(", \"xml:lang\": \"" + language + "\"");
		}
	}

	public static void main(String args[]) {

		Model model = ModelFactory.createDefaultModel();
		InputStream in = FileManager.get().open(args[0]);
		try {
			model.read(in, null);
			in.close();
			System.out.println(getJSON(model));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}