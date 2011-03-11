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
package org.hyperdata.scute.rdf;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import org.hyperdata.scute.main.Config;
import org.hyperdata.scute.system.Log;

import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.RDFWriter;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.vocabulary.RDF;
import com.hp.hpl.jena.vocabulary.RSS;

/**
 * Taken from com.idea.io.RdfUtils, modified for Jena 2
 */
public class RdfUtils {

	/**
	 * Save.
	 * 
	 * @param model
	 *            the model
	 * @param filename
	 *            the filename
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static void save(Model model, String filename) throws IOException {
		setPrefixes(model);
		// System.out.println("FILENAME="+filename);
		OutputStream os = new FileOutputStream(filename);
		model.write(os, Config.self.getDefaultFileFormat());
		os.close();
	}

	// public static void save(Model model, String filename, String lang,
	// String base) {
	// FileOutputStream fos = null;
	// try {
	// fos = new FileOutputStream(filename);
	// model.write(fos, lang, base);
	// fos.close();
	// } catch (FileNotFoundException e) {
	// Log.exception(exception);;
	// } catch (IOException e) {
	// Log.exception(exception);;
	// }
	// }

	/** The iso date. */
	public static SimpleDateFormat isoDate = new SimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:ssz");
	
	private static HashMap<String, String> prefixes = null;
	private static HashMap<String, String> commonPrefixes = null;
	private static HashMap<String, String> notSoCommonPrefixes = null;

	/*
	 * ?? Z - 1.4 only??
	 * 
	 * Complete date plus hours, minutes and seconds: YYYY-MM-DDThh:mm:ssTZD (eg
	 * 1997-07-16T19:20:30+01:00)
	 */

	// public static final String RDF_FORMAT = "RDF/XML-ABBREV";

	// 2003-10-29T10:05:35-05:00
	/**
	 * From iso date.
	 * 
	 * @param string
	 *            the string
	 * @return the date
	 */
	public static Date fromIsoDate(String string) {
		Date date = null;
		System.out.println(string);
		string = string.substring(0, 19) + "GMT" + string.substring(19);
		System.out.println(string);
		try {
			date = isoDate.parse(string);
		} catch (final ParseException exception) {
			Log.exception(exception);
		}
		return date;
	}

	// can use model.getProperty() directly now?
	/**
	 * Gets the first property value.
	 * 
	 * @param resource
	 *            the resource
	 * @param property
	 *            the property
	 * @return the first property value
	 */
	public static RDFNode getFirstPropertyValue(Resource resource,
			Property property) {
		/*
		 * try {
		 * 
		 * StmtIterator test = resource.listProperties(); if
		 * (resource.hasProperty(property)) {
		 * 
		 * StmtIterator iterator = resource.listProperties(property); Statement
		 * statement = (Statement) iterator.next(); return
		 * statement.getObject(); // changed for Jena 2 } } catch (Exception
		 * exception) { Log.exception(exception); }
		 */
		final Statement statement = resource.getProperty(property);
		if (statement == null)
			return null;
		return statement.getObject();
	}

	/*
	 * public static Resource updateProperties(Resource resource, GraphVertex
	 * vertex){ setProperty(resource, RSS.title, vertex.getTitle()); return
	 * resource; }
	 */

	// approximate : returns first match
	/**
	 * Gets the parent.
	 * 
	 * @param model
	 *            the model
	 * @param rdfNode
	 *            the rdf node
	 * @return the parent
	 */
	public static Resource getParent(Model model, RDFNode rdfNode) {
		/*
		 * Statement statement; // Resource parent; try { StmtIterator iterator
		 * = model.listStatements(); while (iterator.hasNext()) { statement =
		 * iterator.next(); if (rdfNode.equals(statement.getObject())) {
		 * //parent = statement.getSubject(); if
		 * (!(RDF.type).equals(statement.getPredicate())) { return
		 * statement.getSubject(); } } } } catch (Exception exception) {
		 * Log.exception(exception); } return null;
		 */
		if (rdfNode instanceof Property)
			return getParentResource(model, (Property) rdfNode);

		return getParentProperty(model, rdfNode);
	}

	// approximate : returns predicate of first statement with matching object
	/**
	 * Gets the parent property.
	 * 
	 * @param model
	 *            the model
	 * @param rdfNode
	 *            the rdf node
	 * @return the parent property
	 */
	public static Property getParentProperty(Model model, RDFNode rdfNode) {
		final Statement statement = getParentStatement(model, rdfNode);
		if (statement == null)
			return null;

		return statement.getPredicate();
		/*
		 * Statement statement;
		 * 
		 * try { StmtIterator iterator = model.listStatements();
		 * 
		 * while(iterator.hasNext()) { statement = iterator.next();
		 * 
		 * if(rdfNode.equals(statement.getObject())) { //parent =
		 * statement.getSubject();
		 * if(!(RDF.type).equals(statement.getPredicate())) { return
		 * statement.getPredicate(); } } } } catch(Exception exception) {
		 * Log.exception(exception); }
		 * 
		 * return null;
		 */
	}

	// approximate : returns object of first statement with matching predicate
	/**
	 * Gets the parent resource.
	 * 
	 * @param model
	 *            the model
	 * @param property
	 *            the property
	 * @return the parent resource
	 */
	public static Resource getParentResource(Model model, Property property) {
		Statement statement;

		try {
			final StmtIterator iterator = model.listStatements();

			while (iterator.hasNext()) {
				statement = iterator.next();
				// changed for Jena 2

				if (property.equals(statement.getPredicate()))
					return statement.getSubject();
			}
		} catch (final Exception exception) {
			Log.exception(exception);
		}

		return null;
	}

	// approximate : returns first statement with matching object
	/**
	 * Gets the parent statement.
	 * 
	 * @param model
	 *            the model
	 * @param rdfNode
	 *            the rdf node
	 * @return the parent statement
	 */
	public static Statement getParentStatement(Model model, RDFNode rdfNode) {
		Statement statement;

		try {
			final StmtIterator iterator = model.listStatements();

			while (iterator.hasNext()) {
				statement = iterator.next();

				if (rdfNode.equals(statement.getObject())) {
					// parent = statement.getSubject();
					if (!(RDF.type).equals(statement.getPredicate()))
						return statement;
				}
			}
		} catch (final Exception exception) {
			Log.exception(exception);
		}
		return null;
	}

	/*
	 * public static void setPropertyObject( Resource resource, Property
	 * property, Resource object) { try { StmtIterator iterator =
	 * resource.listProperties(property);
	 * 
	 * while (iterator.hasNext()) { iterator.next(); iterator.remove(); }
	 * 
	 * resource.addProperty(property, object); } catch (Exception exception) {
	 * Log.exception(exception); } }
	 */
	/**
	 * Gets the property.
	 * 
	 * @param resource
	 *            the resource
	 * @param property
	 *            the property
	 * @return the property
	 */
	public static String getProperty(Resource resource, Property property) {
		// System.out.println("����");
		// show(resource.getModel());
		// show(resource);
		// System.out.println("resource = "+resource);
		// System.out.println("property = "+property);
		// show(property);
		final RDFNode node = getFirstPropertyValue(resource, property);
		// System.out.println("node = "+node);
		if (node == null)
			return null;
		return node.toString();
	}

	/**
	 * Gets the rdf type.
	 * 
	 * @param resource
	 *            the resource
	 * @return the rdf type
	 */
	public static String getRdfType(Resource resource) {
		if (resource.isAnon())
			// @TODO this whole lot needs improving
			return "anon";
		// System.out.println("resource.toSting())"+resource);
		// show(resource);
		final RDFNode type = getFirstPropertyValue(resource, RDF.type);
		if (type == null)
			return "untyped";
		return type.toString();
	}

	// approximate : gets first match (predicate and object)
	/**
	 * Gets the statement.
	 * 
	 * @param model
	 *            the model
	 * @param property
	 *            the property
	 * @param object
	 *            the object
	 * @return the statement
	 */
	public static Statement getStatement(Model model, Property property,
			RDFNode object) {
		Statement statement;

		try {
			final StmtIterator iterator = model.listStatements();

			while (iterator.hasNext()) {
				statement = iterator.next();

				if (property.equals(statement.getPredicate())
						&& object.equals(statement.getObject()))
					return statement;
			}
		} catch (final Exception exception) {
			Log.exception(exception);
		}
		return null;
	}

	// approximate : gets first match (predicate and object)
	/**
	 * Gets the subject.
	 * 
	 * @param model
	 *            the model
	 * @param property
	 *            the property
	 * @param object
	 *            the object
	 * @return the subject
	 */
	public static Resource getSubject(Model model, Property property,
			RDFNode object) {
		final Statement statement = getStatement(model, property, object);
		if (statement == null)
			return null;
		return statement.getSubject();
	}

	/**
	 * Load.
	 * 
	 * @param model
	 *            the model
	 * @param filename
	 *            the filename
	 * @param format
	 *            the format
	 * @return the model
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static Model load(Model model, String filename, String format)
			throws IOException {

		final InputStream inputStream = new FileInputStream(filename);
		// model.read(new FileReader(filename), "");
		model.read(inputStream, "", format);
		setPrefixes(model);
		// System.out.println(modelToString(model));
		return model;
	}

	/**
	 * Load.
	 * 
	 * @param filename
	 *            the filename
	 * @param format
	 *            the format
	 * @return the model
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static Model load(String filename, String format) throws IOException {
		return load(ModelFactory.createDefaultModel(), filename, format);
	}

	/**
	 * The main method.
	 * 
	 * @param srgs
	 *            the arguments
	 */
	public static void main(String[] srgs) {
		// 2003-10-29T10:05:35-05:00
		// Date test = new Date();

		final Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, 2003);
		calendar.set(Calendar.MONTH, 10);
		calendar.set(Calendar.DATE, 28);
		calendar.set(Calendar.HOUR_OF_DAY, 10);

		calendar.set(Calendar.MINUTE, 5);
		calendar.set(Calendar.SECOND, 35);

		calendar.set(Calendar.ZONE_OFFSET, -18000000);

		final Date date = calendar.getTime();

		final String iso = toIsoDate(date);
		System.out.println("iso = " + iso);
		final Date date2 = fromIsoDate(iso);
		System.out.println("old: " + date + "   new:" + date2);
	}

	/*
	 * public static RDFWriter getPrettyWriter() { RDFWriter rdfWriter = null;
	 * RDFWriterF rdfWriterF = new RDFWriterFImpl(); try { // rdfWriter =
	 * rdfWriterF.getWriter("RDF/XML-ABBREV"); // rdfWriter.setNsPrefix("dc",
	 * DC_10.getURI()); // rdfWriter.setNsPrefix("rss", RSS.getURI()); //
	 * rdfWriter.setNsPrefix("idea", IDEA.getURI()); //
	 * rdfWriter.setNsPrefix("graphic", GRAPHIC.getURI()); //
	 * rdfWriter.setNsPrefix("fs", FILESYSTEM.getURI()); //
	 * rdfWriter.setNsPrefix("prj", PROJECT.getURI()); //
	 * rdfWriter.setNsPrefix("foaf", "http://xmlns.com/foaf/0.1/"); //
	 * rdfWriter.setNsPrefix("owl", "http://www.w3.org/2002/07/owl#"); //
	 * rdfWriter.setNsPrefix("ibis", "http://purl.org/ibis#"); //
	 * rdfWriter.setNsPrefix("fs", //
	 * "http://ideagraph.org/xmlns/idea/filesystem#"); // the encoding was
	 * screwing up, so declaration removed rdfWriter.setProperty(
	 * "showXmlDeclaration", Boolean.FALSE); } catch (Exception exception) {
	 * Log.exception(exception); }
	 * 
	 * return rdfWriter; }
	 */

	/**
	 * Model to string.
	 * 
	 * @param model
	 *            the model
	 * @return the string
	 */
	public static String modelToString(Model model) {
		// System.out.println("MODEL TO STRPING");
		if (model == null)
			return "Null Model.";
		final StringWriter stringOut = new StringWriter();
		try {
			setPrefixes(model);
			model.write(stringOut, "RDF/XML-ABBREV", RSS.getURI());
			// http://base
			stringOut.flush();
			stringOut.close();
		} catch (final Exception exception) {
			Log.exception(exception);
		}
		return stringOut.toString();
	}

	/**
	 * Replace literal value.
	 * 
	 * @param model
	 *            the model
	 * @param literal
	 *            the literal
	 * @param value
	 *            the value
	 */
	public static void replaceLiteralValue(Model model, Literal literal,
			String value) {
		final Literal newLiteral = model.createLiteral(value);
		// ((Literal)getRdfNode()).
		final Set<Statement> statements = new HashSet<Statement>();
		final StmtIterator iterator = model.listStatements(null, null, literal);

		while (iterator.hasNext()) {
			statements.add(iterator.next());
		}

		final Iterator<Statement> setIterator = statements.iterator();
		Statement statement;

		while (setIterator.hasNext()) {
			statement = setIterator.next();
			model.add(statement.getSubject(), statement.getPredicate(),
					newLiteral);
			model.remove(statement);
		}
	}

	/**
	 * Replace object resource.
	 * 
	 * @param statement
	 *            the statement
	 * @param newObject
	 *            the new object
	 */
	public static void replaceObjectResource(Statement statement,
			Resource newObject) {
		Statement newStatement;
		try {
			final Model model = statement.getModel();
			newStatement = model.createStatement(statement.getSubject(),
					statement.getPredicate(), newObject);
			model.remove(statement);
			model.add(newStatement);
		} catch (final Exception exception) {
			Log.exception(exception);
		}
	}

	/**
	 * Replace resource.
	 * 
	 * @param model
	 *            the model
	 * @param oldResource
	 *            the old resource
	 * @param newResource
	 *            the new resource
	 * @return the model
	 */
	public static Model replaceResource(Model model, Resource oldResource,
			Resource newResource) {
		try {
			final StmtIterator statements = model.listStatements();
			Statement statement;
			Resource subject;
			RDFNode object = null;
			
			// buffer in List to avoid concurrent modification exception
			final List<Statement> statementList = new ArrayList<Statement>();
			while (statements.hasNext()) {
				statementList.add(statements.next());
			}

			for (int i = 0; i < statementList.size(); i++) {
				statement = statementList.get(i);
				subject = statement.getSubject();
				object = statement.getObject();
				if (subject.equals(oldResource)) {
					replaceSubjectResource(statement, newResource);
				}
				if ((object instanceof Resource)
						&& (oldResource.equals(object))) {
					replaceObjectResource(statement, newResource);
				}
			}

		} catch (final Exception exception) {
			Log.exception(exception);
		}
		return model;
	}

	/**
	 * Replace subject resource.
	 * 
	 * @param statement
	 *            the statement
	 * @param newSubject
	 *            the new subject
	 */
	public static void replaceSubjectResource(Statement statement,
			Resource newSubject) {
		Statement newStatement;
		try {
			final Model model = statement.getModel();
			newStatement = model.createStatement(newSubject, statement
					.getPredicate(), statement.getObject());
			model.remove(statement);
			model.add(newStatement);
		} catch (final Exception exception) {
			Log.exception(exception);
		}

	}

	/**
	 * Copies all properties across to new resource, just replaces type.
	 * 
	 * @param resource
	 *            the resource
	 * @param newType
	 *            the new type
	 */
	public static void replaceType(Resource resource, Resource newType) {

		try {
			final StmtIterator iterator = resource.listProperties();
			Property property = null;
			Statement statement = null;

			while (iterator.hasNext()) {
				statement = iterator.next();
				property = statement.getPredicate();
				// System.out.println("property = "+property);
				if (property.equals(RDF.type)) {
					break; // to stop concurrent mod exc
				}

			}
			if (property.equals(RDF.type)) {
				resource.getModel().remove(statement);
				resource.addProperty(RDF.type, newType);
			}
		} catch (final Exception exception) {
			Log.exception(exception);
		}
	}

	/**
	 * Sets the common prefixes.
	 * 
	 * @param model
	 *            the new common prefixes
	 */
	public static void setPrefixes(Model model) {
		model.setNsPrefixes(getAllPrefixes());
	}
	
	public static Map getAllPrefixes(){
		if(prefixes == null){
			prefixes = new HashMap<String, String>();
			prefixes.putAll(getCommonPrefixes());
			prefixes.putAll(getNotSoCommonPrefixes());
		}
		return prefixes;
	}

	public static Map<String, String> getCommonPrefixes(){
		if(commonPrefixes == null){
commonPrefixes = new HashMap<String, String>();
		commonPrefixes.put("rdf", "http://www.w3.org/1999/02/22-rdf-syntax-ns#");
		commonPrefixes.put("rdfs", "http://www.w3.org/2000/01/rdf-schema#");
		commonPrefixes.put("owl", "http://www.w3.org/2002/07/owl#");
		commonPrefixes.put("xsd","http://www.w3.org/2001/XMLSchema#");
		commonPrefixes.put("dc", "http://purl.org/dc/elements/1.1/");
		commonPrefixes.put("dcterms", "http://purl.org/dc/terms/");
		commonPrefixes.put("skos", "http://www.w3.org/2004/02/skos/core#");
		commonPrefixes.put("foaf", "http://xmlns.com/foaf/0.1/");
		commonPrefixes.put("x", "http://purl.org/stuff/");
		commonPrefixes.put("void", "http://rdfs.org/ns/void#");
		commonPrefixes.put("mo", "http://purl.org/ontology/mo/");
		commonPrefixes.put("rel", "http://purl.org/vocab/relationship/");
		commonPrefixes.put("rev", "http://purl.org/stuff/rev#");
		}
		// prefixes.put("dbpo", "http://dbpedia.org/ontology/");
		// prefixes.put("dbpr", "http://dbpedia.org/resource/");
		// prefixes.put("dbpp", "http://dbpedia.org/property/");
		return commonPrefixes;
	}
	
	public static Map<String, String> getNotSoCommonPrefixes(){
		if(notSoCommonPrefixes == null){
		notSoCommonPrefixes = new HashMap<String, String>();
		notSoCommonPrefixes.put("tdb", "http://jena.hpl.hp.com/2008/tdb#");
		notSoCommonPrefixes.put("ja", "http://jena.hpl.hp.com/2005/11/Assembler#");
		
		notSoCommonPrefixes.put("dbpo", "http://dbpedia.org/ontology/");
		notSoCommonPrefixes.put("dbpr", "http://dbpedia.org/resource/");
		notSoCommonPrefixes.put("dbpp", "http://dbpedia.org/property/");
		}
		return notSoCommonPrefixes;
	}

	// yuck
	/**
	 * Sets the property.
	 * 
	 * @param resource
	 *            the resource
	 * @param property
	 *            the property
	 * @param value
	 *            the value
	 */
	public static void setProperty(Resource resource, Property property,
			Object value) {
		try {
			/*
			 * StmtIterator iterator = resource.listProperties(property); while
			 * (iterator.hasNext()) { iterator.next(); iterator.remove(); }
			 */
			resource.removeAll(property);
			resource.addProperty(property, (String) value);
		} catch (final Exception exception) {
			Log.exception(exception);
		}
	}

	// //////////////
	/**
	 * Sets the property object.
	 * 
	 * @param resource
	 *            the resource
	 * @param property
	 *            the property
	 * @param object
	 *            the object
	 */
	public static void setPropertyObject(Resource resource, Property property,
			Resource object) {
		try {
			final StmtIterator iterator = resource.listProperties(property);

			while (iterator.hasNext()) {
				iterator.next();
				iterator.remove();
			}

			resource.addProperty(property, object);
		} catch (final Exception exception) {
			Log.exception(exception);
		}
	}

	/**
	 * Sets the uri.
	 * 
	 * @param resource
	 *            the resource
	 * @param uri
	 *            the uri
	 */
	public static void setUri(Resource resource, URI uri) {
		try {
			final Model model = resource.getModel();

			// create a new resource
			final Resource newResource = model.createResource(uri.toString());

			final StmtIterator iterator = resource.listProperties();

			// copy properties from old resource
			// buffer used to avoid concurrent modification
			final Set<Statement> statements = new HashSet<Statement>();
			while (iterator.hasNext()) {
				final Statement stmt = iterator.next();
				statements.add(stmt);
				// changed for Jena 2
				newResource.addProperty(stmt.getPredicate(), stmt.getObject());
				// model.remove(stmt);
			}
			final Iterator<Statement> setIterator = statements.iterator();
			Statement statement;
			while (setIterator.hasNext()) {
				statement = setIterator.next();
				if (model.contains(statement)) {
					model.remove(statement);
				}
			}
		} catch (final Exception exception) {
			Log.exception(exception);
		}
	}

	/**
	 * Show.
	 * 
	 * @param model
	 *            the model
	 */
	public static void show(Model model) {
		System.out.println(modelToString(model));
	}

	/**
	 * Show.
	 * 
	 * @param resource
	 *            the resource
	 */
	public static void show(Resource resource) {
		try {
			final StmtIterator iterator = resource.listProperties();
			show(iterator);
		} catch (final Exception exception) {
			Log.exception(exception);
		}
	}

	/**
	 * Show.
	 * 
	 * @param statement
	 *            the statement
	 */
	public static void show(Statement statement) {
		show(statement.getSubject());
		show(statement.getPredicate());
		if (statement.getObject() instanceof Resource) {
			show((Resource) statement.getObject());
		} else {
			System.out.println(statement.getObject());
		}
	}

	/**
	 * Show.
	 * 
	 * @param iterator
	 *            the iterator
	 */
	public static void show(StmtIterator iterator) {
		final StringBuffer buffer = new StringBuffer("\n--v--");
		try { // StmtIterator iterator = resource.listProperties();
			while (iterator.hasNext()) {
				buffer.append("\n" + iterator.next().toString());
			}
		} catch (final Exception exception) {
			Log.exception(exception);
		}
		buffer.append("\n--^--");
		System.out.println(buffer);
	}

	/**
	 * Title to id.
	 * 
	 * @param title
	 *            the title
	 * @return the string
	 */
	public static String titleToID(String title) {
		return title.replace(' ', '_');
	}

	// /////////////// beware of the GMT!
	/**
	 * To iso date.
	 * 
	 * @param date
	 *            the date
	 * @return the string
	 */
	public static String toIsoDate(Date date) {
		return isoDate.format(date);
	}

	/**
	 * To iso date.
	 * 
	 * @param date
	 *            the date
	 * @return the string
	 */
	public static String toISODate(Date date) {
		return isoDate.format(date);
	}

	/**
	 * Instantiates a new rdf utils.
	 */
	private RdfUtils() {
	}

	/*
	 * public static RDFWriter getPrettyWriter() {
	 * 
	 * return RdfUtils.getPrettyWriter(); }
	 */
	/**
	 * Model to string.
	 * 
	 * @param model
	 *            the model
	 * @param lang
	 *            the lang
	 * @return the string
	 */
	public static String modelToString(Model model, String lang) {
		// System.out.println("MODEL TO STRPING");
		final StringWriter stringOut = new StringWriter();
		try {
			final RDFWriter rdfWriter = model.getWriter(lang);
			/*
			 * rdfWriter.setNsPrefix("features", FEATURES.getURI());
			 * rdfWriter.setNsPrefix("dc", DCES.getURI());
			 * rdfWriter.setNsPrefix("rss", RSS.getURI());
			 * rdfWriter.setNsPrefix("idea", IDEA.getURI());
			 * rdfWriter.setNsPrefix("graphic", GRAPHIC.getURI());
			 * rdfWriter.setNsPrefix("fs", FILESYSTEM.getURI());
			 * rdfWriter.setNsPrefix("prj", PROJECT.getURI());
			 */
			// the encoding was screwing up, so declaration removed
			// rdfWriter.setProperty("showXmlDeclaration", Boolean.FALSE);
			// rdfWriter.write(model, System.out, RSS.getURI());
			rdfWriter.write(model, stringOut, RSS.getURI());
			// model.write(stringOut, lang, RSS.getURI());
			// http://base
			stringOut.flush();
			stringOut.close();
		} catch (final Exception exception) {
			// Log.exception(exception);
			// System.exit(1);
			throw new RuntimeException(exception);
		}
		return stringOut.toString();
	}

	/**
	 * String to model.
	 * 
	 * @param string
	 *            the string
	 * @param base
	 *            the base
	 * @param lang
	 *            the lang
	 * @return the model
	 * @throws Exception
	 *             the exception
	 */
	public static Model stringToModel(String string, String base, String lang)
			throws Exception {
		final Model model = ModelFactory.createDefaultModel();
		final StringReader reader = new StringReader(string);
		model.read(reader, base, lang); // base
		return model;
	}

	// @TODO this needs pushing out
	/**
	 * String to model exception caught.
	 * 
	 * @param string
	 *            the string
	 * @param base
	 *            the base
	 * @return the model
	 */
	public static Model stringToModelExceptionCaught(String string, String base) {
		Model model = null;

		try {
			model = stringToModel(string, base, null);
		} catch (final Exception exception) {
			Log.exception(exception);
		}

		return model;
	}

}