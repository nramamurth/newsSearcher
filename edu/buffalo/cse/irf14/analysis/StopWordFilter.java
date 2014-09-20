package edu.buffalo.cse.irf14.analysis;

import java.util.Arrays;

public class StopWordFilter extends TokenFilter{
	
	String[] stopWordsList = {"a","able","about","across","after","all","almost","also","am","among","an","and","any","are","as","at","be","because","been","but","by","can","cannot","could","dear","did","do","does","either","else","ever","every","for","from","get","got","had","has","have","he","her","hers","him","his","how","however","i","if","in","into","is","it","its","just","least","let","like","likely","may","me","might","most","must","my","neither","no","nor","not","of","off","often","on","only","or","other","our","own","rather","said","say","says","she","should","since","so","some","than","that","the","their","them","then","there","these","they","this","tis","to","too","twas","us","wants","was","we","were","what","when","where","which","while","who","whom","why","will","with","would","yet","you","your"};
	
	public StopWordFilter() { super(); }
	
	public StopWordFilter(TokenStream stream) {
		super(stream);
	}

	public int isStopWord(String word){
		return (Arrays.binarySearch(stopWordsList, word));
	}
	
	@Override
	public boolean increment() throws TokenizerException {
		while (tokenStream.hasNext()){
			if (isStopWord(tokenStream.next().toString().toLowerCase()) >= 0){
				tokenStream.remove();
			}	
		}
		return false;
	}

	@Override
	public TokenStream getStream() {
		return tokenStream;
	}

	
}
