package test;


import java.io.IOException;
import java.util.List;

import natural_lang_processing.Tokenizer;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;




public class ai_test {
	
	public static void main(String[] args) throws IOException{
		System.setProperty("mbrola.base",  "C:/Program Files (x86)/Mbrola Tools");
		Voice voice;
		VoiceManager vocman = VoiceManager.getInstance();
		voice = vocman.getVoice("mbrola_us3");
		voice.allocate();
		
		
//		String text = "Run, run away, fast as you can.";
//		List<String> tokens = Tokenizer.wordsToList(text);
//		System.out.println(text);
		
		
		voice.speak("This is a test");
		
		String define = "search";
		String url = ("http://www.google.com/search?q=define%20" + define);
		Document document = Jsoup.connect(url).userAgent("Mozilla").get();
		Elements titles = document.select(".r");
		
		Element first = titles.first();
		
		for( Element t: titles){
		System.out.println(t.html());
		
		voice.speak(t.text());
		
	}
		
		
	}

}
