/**
 * 
 */
package edu.buffalo.cse.irf14.analysis;

import edu.buffalo.cse.irf14.analysis.Util.FieldNameAnalyser;
import edu.buffalo.cse.irf14.document.FieldNames;

/**
 * @author nikhillo
 * This factory class is responsible for instantiating "chained" {@link Analyzer} instances
 */
public class AnalyzerFactory {

	//Single Instance of Analyzer Factory.
	public static AnalyzerFactory analyserFactory = null;
	
	/**
	 * Static method to return an instance of the factory class.
	 * Usually factory classes are defined as singletons, i.e. 
	 * only one instance of the class exists at any instance.
	 * This is usually achieved by defining a private static instance
	 * that is initialized by the "private" constructor.
	 * On the method being called, you return the static instance.
	 * This allows you to reuse expensive objects that you may create
	 * during instantiation
	 * @return An instance of the factory
	 */
	
	public static AnalyzerFactory getInstance() {
		if(analyserFactory == null) {
			analyserFactory = new AnalyzerFactory();
		}
		return analyserFactory;
	}
	
	/**
	 * Returns a fully constructed and chained {@link Analyzer} instance
	 * for a given {@link FieldNames} field
	 * Note again that the singleton factory instance allows you to reuse
	 * {@link TokenFilter} instances if need be
	 * @param name: The {@link FieldNames} for which the {@link Analyzer}
	 * is requested
	 * @param TokenStream : Stream for which the Analyzer is requested
	 * @return The built {@link Analyzer} instance for an indexable {@link FieldNames}
	 * null otherwise
	 */
	public Analyzer getAnalyzerForField(FieldNames fieldName, TokenStream stream) {
		FieldNameAnalyser fieldNameAnalyser = FieldNameAnalyser.valueOf(fieldName.name());
		
		if(fieldNameAnalyser != null) {
			try {
				Analyzer analyzer = (Analyzer) Class.forName(fieldNameAnalyser.getClassName()).newInstance();
				analyzer.setStream(stream);
				return analyzer;
			} catch (Exception e) {
				System.err.println(e);
			}			
		}
		return null;
	}
}
