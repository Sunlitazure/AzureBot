package test;

import java.io.IOException;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;
import edu.cmu.sphinx.result.WordResult;

public class TranscriberMicTest {
	
	private static LiveSpeechRecognizer recog = null;
	private static String result = "";
	

	public static void main(String[] args) {
		System.out.println("loading models...");
		
		Configuration config = new Configuration();
		
        config.setAcousticModelPath("file:models/acoustic/cmusphinx5.2");
        config.setDictionaryPath("file:models/acoustic/wsj/dict/cmudict.0.6d");
        config.setLanguageModelPath("file:models/language/cmusphinx-5.0-en-us.lm.dmp");
        config.setGrammarPath("models/grammar");
        config.setGrammarName("digits");
        config.setUseGrammar(false);
        
        
        System.out.println("Ready for audio input");
        
        try {
			recog = new LiveSpeechRecognizer(config);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        recog.startRecognition(true);
        
        while(!result.equals("exit")){
        	
        	SpeechResult result = recog.getResult();
        	System.out.print(result.getHypothesis() + "\n");

        }
        
        recog.stopRecognition();

	}

}
