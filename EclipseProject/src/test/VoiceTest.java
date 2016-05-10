package test;

import javax.sound.sampled.AudioFileFormat.Type;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import com.sun.speech.freetts.audio.AudioPlayer;
import com.sun.speech.freetts.audio.SingleFileAudioPlayer;



public class VoiceTest {
	
public static void main(String[] args){
	System.setProperty("mbrola.base",  "C:/Program Files (x86)/Mbrola Tools");
	
	Voice voice;
	VoiceManager voicemanager = VoiceManager.getInstance();
	voice = voicemanager.getVoice("mbrola_us1");
	voice.setRate(140);
	//voice.setPitchShift(1.2f);
	voice.setPitch(290);
	voice.setPitchRange(20);
	voice.allocate();
	//voice.speak("A robot is a mechanical or virtual artificial agent, usually an electro-mechanical"
	//		+ " machine that is guided by a computer program or electronic circuitry.");
	voice.setPitchRange(40);
	voice.speak("Saallutations friends!");
	//AudioPlayer player = new SingleFileAudioPlayer("data/voice", Type.WAVE);
	//voice.setAudioPlayer(player);
	
	//voice.speak("The cake is a lie.");
	voice.setPitch(210);
	voice.setRate(150);
	voice.setPitchRange(30);
	//voice.speak("Mental state re avaluated, enforcements mode activated");
	
	Voice male2;
	male2 = voicemanager.getVoice("mbrola_us2");
	male2.allocate();
	male2.setPitch(210);
	male2.setRate(136);
	//male2.speak("The Jedeye have escaped from the fourth quadrant!");

	Voice male3;
	male3 = voicemanager.getVoice("mbrola_us3");
	male3.allocate();
	male3.setPitch(0);
	male3.setRate(130);
	male3.setPitchRange(50);
	//male3.speak("Rudimentary creatures of blood and flesh, you touch my mind fumbling in ignorance, incapable of understanding.");
	
	//SingleFileAudioPlayer audioplayer = new SingleFileAudioPlayer("Voice",Type.WAVE);
	//male3.setAudioPlayer(audioplayer);
	//male3.speak("Children, you represent chaos, we represent order. You cannot comprehend the magnitude of our presence.");
	//male3.speak("Without our interaction organics are Doomed. We are your salvation.");
	
	
	//System.out.println(voicemanager.getVoiceInfo());
	
	//audioplayer.close();
	

}
}
