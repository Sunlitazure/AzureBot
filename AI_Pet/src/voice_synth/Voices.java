package voice_synth;

import java.io.IOException;
import java.io.InputStream;

import java.net.URL;
import java.net.URLConnection;

import javazoom.jl.player.Player;


/**
 * 
 */
public class Voices {
	private boolean connection;
	private String accent;
	
	/**
	 * default constructor
	 */
	public Voices(){
		//connection = connection();
		connection = false;
		accent = "en-us";
	}

	
	
//	/**
//	 * Speaks what ever text is input.
//	 * Uses Voce if there's internet and freetts if there's none.
//	 * @param speech a <code>String</code> of what the voice should say
//	 */
//	public void speak(String speech){
//		
//		if(connection == true){
//			try{
//				Synthesiser synth = new Synthesiser(accent);
//				InputStream is = synth.getMP3Data(speech);
//				Player player = new Player(is); 
//				player.play();
//				is.close();
//			}catch(Exception e){
//				System.out.println("Error in InputStream or Player");
//			}
//		}else{
//			voce.SpeechInterface.init(true, false, "", "", "", "");
//			voce.SpeechInterface.synthesize(speech);
//		}
//	}
	
	public void speak(String speech){
		SpeechInterface.init( "", "", "", "");
		SpeechInterface.synthesize(speech);
	}
	
	
	/**
	 * Indicates true if there's an internet connection, false in not.
	 * @return a <code>boolean</code> indicating true if the connection's good.
	 */
	public static boolean connection(){
		boolean test;
		
		try{
			System.out.println("testing connection...");
			final URL url = new URL("http://www.google.com");
			final URLConnection con = url.openConnection();
			con.getInputStream();
			
			System.out.println("connected!");
			test = true;
		}catch(IOException e){
			System.out.println("error");
			test = false;
		}
		return test;
	}
	
//	/**
//	 * Set the voice accent using uk, au, or us.
//	 * @param accent indicate uk, au, or us for different accents
//	 */
//	public void setAccent(String accent){
//		if(accent == "uk")
//			this.accent = "en-uk";
//		else if(accent == "au")
//			this.accent = "en-au";
//		else if(accent == "us")
//			this.accent = "en-us";
//		else if(accent == "japan")
//			this.accent = "ja";
//		else
//			System.out.println("please enter a valid region");
//	}
	
	

}
