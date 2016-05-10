package voice_synth;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import javazoom.jl.player.Player;


public class Voice_Capture_Utility {
	public static void main(String[]args){
		
		Voice_Capture_Utility  util = new Voice_Capture_Utility();
		
		VoiceCap voice = util.new VoiceCap();
		
		// this is where the action happens--------------------------------------------------------------------voice.speak(words to say, filename);
		voice.speak("", "Test");
	}



	private class VoiceCap {
		private boolean connection;
		private String accent;
		
		/**
		 * default constructor
		 */
		public VoiceCap(){
			connection = connection();
			accent = "en-us";
		}

		
		
		/**
		 * Speaks what ever text is input.
		 * Uses Voce if there's internet and freetts if there's none.
		 * @param speech a <code>String</code> of what the voice should say
		 */
		public void speak(String speech, String mp3Name){
			
//			if(connection == true){
//				try{
//					Synthesiser synth = new Synthesiser(accent);
//					InputStream is = synth.getMP3Data(speech);
//					
//					File target = new File("data/" + mp3Name + ".mp3");
//					OutputStream out = new FileOutputStream(target);
//					byte[] buffer = new byte[8*1024];
//					int totalRead;
//					while((totalRead = is.read(buffer)) != -1){
//						out.write(buffer, 0, totalRead);
//					}
//					out.close();
//					is.close();
//					
//					is = new FileInputStream(target);
//					Player player = new Player(is); 
//					player.play();
//					player.close();
//					is.close();
//					
//				}catch(Exception e){
//					System.out.println("Error in InputStream or Player");
//				}
//			}else{
				SpeechInterface.init("", "", "", "");
				SpeechInterface.synthesize(speech);
//			}
		}
		
		
		/**
		 * Indicates true if there's an internet connection, false in not.
		 * @return a <code>boolean</code> indicating true if the connection's good.
		 */
		private boolean connection(){
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
		
		/**
		 * Set the voice accent using uk, au, or us.
		 * @param accent indicate uk, au, or us for different accents
		 */
		public void setAccent(String accent){
			if(accent == "uk")
				this.accent = "en-uk";
			else if(accent == "au")
				this.accent = "en-au";
			else if(accent == "us")
				this.accent = "en-us";
			else if(accent == "japan")
				this.accent = "ja";
			else
				System.out.println("please enter a valid region");
		}
		
		

	}

}
