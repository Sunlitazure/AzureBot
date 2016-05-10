package test;

import java.io.IOException;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;
import edu.cmu.sphinx.models.*;
import edu.cmu.sphinx.result.WordResult;

public class LiveSpeechRecognize {
	
	public static void main(String[] args) throws IOException{

	Configuration configuration = new Configuration();
	 
	// Set path to acoustic model.
	// Set path to acoustic model.
	configuration.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
	// Set path to dictionary.
	configuration.setDictionaryPath("resource:/edu/cmu/sphinx/models/en-us/cmudict-en-us.dict");
	// Set language model.
	configuration.setLanguageModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us.lm.bin");
	
	
	LiveSpeechRecognizer recognizer = new LiveSpeechRecognizer(configuration);
	// Start recognition process pruning previously cached data.
	recognizer.startRecognition(true);
	SpeechResult result = recognizer.getResult();
	System.out.println(result.getHypothesis());
	System.out.println(result.getNbest(8));
	 
	// Get individual words and their times.
	for (WordResult r : result.getWords()) {
	    System.out.println(r);
	}
	// Pause recognition process. It can be resumed then with startRecognition(false).
	recognizer.stopRecognition();
	}

}
