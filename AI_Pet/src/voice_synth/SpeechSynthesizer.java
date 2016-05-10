package voice_synth;

import java.io.File;
import java.util.Locale;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyVetoException;

import javax.speech.Central;
import javax.speech.Engine;
import javax.speech.EngineList;
import javax.speech.EngineCreate;
import javax.speech.EngineException;
import javax.speech.EngineModeDesc;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;
import javax.speech.synthesis.SynthesizerProperties;
import javax.speech.synthesis.Voice;
import javax.speech.synthesis.Speakable;
import javax.speech.synthesis.SpeakableAdapter;
import javax.speech.synthesis.SpeakableEvent;

import com.sun.speech.freetts.jsapi.FreeTTSEngineCentral; 

/// Handles all speech synthesis (i.e. text-to-speech) 
/// functions.
public class SpeechSynthesizer
{
	/// The speech Synthesizer instance.
	private Synthesizer mSynthesizer = null;

	/// Constructs and initializes the speech synthesizer.
	public SpeechSynthesizer(String name)
	{
		// Create a default voice.
		Voice theVoice = new Voice(name, 
			Voice.GENDER_MALE, Voice.AGE_DONT_CARE, null);

		
		try
		{		
			// Create the synthesizer with the general domain voice.
			SynthesizerModeDesc generalDesc = new SynthesizerModeDesc(
				null,			// engine name
				"general",		// mode name
				Locale.US,		// locale
				null,			// running
				null);			// voice
			
			// Avoid using the JSAPI Central class (and the use of the speech.properties 
			// file) by using FreeTTSEngineCentral directly.
			FreeTTSEngineCentral central = new FreeTTSEngineCentral();
			EngineList list = central.createEngineList(generalDesc); 

			if (list.size() > 0)
			{ 
				EngineCreate creator = (EngineCreate)list.get(0);
				mSynthesizer = (Synthesizer)creator.createEngine();
			}

			if (null  == mSynthesizer)
			{
				Utils.log("ERROR", "Cannot create speech synthesizer");
				System.exit(1);
			}

			mSynthesizer.allocate();

			// Setup the general domain synthesizer.
			mSynthesizer.getSynthesizerProperties().setVoice(theVoice);

			mSynthesizer.resume();

			// Force the synthesizer to create its thread now by making 
			// it synthesize something.  Otherwise, the first synthesize 
			// request in a user's app could be delayed.
			synthesize(" ");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/// Deallocates voice synthesizer.
	public void destroy()
	{
		mSynthesizer.cancelAll();
		
		try
		{		
			mSynthesizer.deallocate();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/// Adds a message to the synthesizer's queue and synthesize it as 
	/// soon as it reaches the front of the queue.
	public void synthesize(String message)
	{
		//Utils.log("debug", "SpeechSynthesizer.speak: Adding message to speech queue: " + message);
	
		// Note that the Synthesize class maintains its own internal queue.
		mSynthesizer.speakPlainText(message, null);
	}

	/// Stops synthesizing the current message and removes all pending 
	/// messages from the queue.
	public void stopSynthesizing()
	{
		mSynthesizer.cancelAll();
	}
}
