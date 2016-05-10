/**
 * 
 */
package ai_app;

import java.util.concurrent.BlockingQueue;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

/**
 * @author SunlitAzure
 *
 */
public class VoiceSynth extends Thread {
	private BlockingQueue<String> queue;
	private boolean end = false;
	
	private Voice voice;
	private VoiceManager voicemanager;
	
	
	VoiceSynth(BlockingQueue<String> queue){
		this.queue = queue;
	}
	
	
	public void run(){
		System.setProperty("mbrola.base",  "C:/Program Files (x86)/Mbrola Tools");
		
		voicemanager = VoiceManager.getInstance();
		
		
		voice = voicemanager.getVoice("mbrola_us3");
		voice.allocate();
		voice.setPitch(0);
		voice.setRate(130);
		voice.setPitchRange(50);
		
		
//		voice = voicemanager.getVoice("mbrola_us1");
//		voice.allocate();
//		voice.setRate(140);
//		voice.setPitch(255);
//		voice.setPitchShift(1.1f);
//		voice.setPitchRange(20);
//		voice.allocate();
		
		while(end != true){
			if(queue.size() > 0)
				try {
					voice.speak(queue.take());
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
	}
	
	public void setEnd(){
		end = true;
	}
}
