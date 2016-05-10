package test;

import javax.sound.sampled.DataLine;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.TargetDataLine;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;

public class SysAudio {

	public static void main(String[] args) {

		Mixer.Info[] mxInfo = AudioSystem.getMixerInfo();
		int mixIndex = 4;
		Mixer mixer = AudioSystem.getMixer(mxInfo[mixIndex]);
		System.out.println(mxInfo[mixIndex]);
		
		
		Line.Info[] info = mixer.getTargetLineInfo();
		System.out.println(info[0]);
		
		TargetDataLine line = null;
		try {
			line = (TargetDataLine) mixer.getLine(info[0]);
			line.open(line.getFormat());
			System.out.println(line.getFormat().toString() + ", " + line.getBufferSize());
		} catch (LineUnavailableException e){
			e.printStackTrace();
		}

		byte[] data = new byte[line.getBufferSize() / 5];
		int read; //number of bytes read at the time
		
		line.start();
		
		while(true) {
			read = line.read(data, 0, data.length);
			for(int i = 0; i<read; i++){
				System.out.println(data[i] + " ");
			}
		}
		
	}

}
