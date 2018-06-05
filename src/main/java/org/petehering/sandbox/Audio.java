package org.petehering.sandbox;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;

public class Audio
{
    private final Map<String, Clip> clips;
    private final Map<String, Sequence> sequences;
    
    public Audio ()
    {
        this.clips = new HashMap<> ();
        this.sequences = new HashMap<> ();
    }
    
    public void loadClip (String path)
    {
        if (!clips.containsKey(path))
        {
            try (InputStream stream = getClass ().getResourceAsStream(path))
            {
                AudioInputStream input = AudioSystem.getAudioInputStream(stream);
                AudioFormat format = input.getFormat();
                DataLine.Info info = new DataLine.Info (Clip.class, format);
                Clip clip = (Clip) AudioSystem.getLine(info);
                clip.open(input);
                clips.put(path, clip);
            }
            catch (Exception ex)
            {
                throw new RuntimeException (ex);
            }
        }
    }
    
    public void playClip (String path)
    {
        if (clips.containsKey(path))
        {
            Clip clip = clips.get(path);
            clip.start();
        }
    }
    
    public void stopClip (String path)
    {
        if (clips.containsKey(path))
        {
            Clip clip = clips.get(path);
            clip.stop();
            clip.setFramePosition(0);
        }
    }
    
    public void unloadClip (String path)
    {
        if (clips.containsKey(path))
        {
            Clip clip = clips.get(path);
            clip.stop ();
            clip.flush();
            clip.close();
            clips.remove(path);
        }
    }

    public void loadStream (String path)
    {
    }
    
    public void playStream (String path)
    {
    }
    
    public void stopStream (String path)
    {
    }
    
    public void unloadStream (String path)
    {
    }

    public void loadSequence (String path)
    {
        if (!sequences.containsKey(path))
        {
            try (InputStream stream = getClass ().getResourceAsStream(path))
            {
                Sequence seq = MidiSystem.getSequence(stream);
                sequences.put(path, seq);
            }
            catch (Exception ex)
            {
                throw new RuntimeException (ex);
            }
        }
    }
    
    public void playSequence (String path)
    {
        if (sequences.containsKey(path))
        {
            try
            {
                Sequence seq = sequences.get(path);
                Sequencer sequencer = MidiSystem.getSequencer();
                sequencer.setSequence(seq);
                sequencer.start();
            }
            catch (Exception ex)
            {
                throw new RuntimeException (ex);
            }
        }
    }
    
    public void stopSequence ()
    {
        try
        {
            Sequencer sequencer = MidiSystem.getSequencer();
            sequencer.stop();
        }
        catch (Exception ex)
        {
            throw new RuntimeException (ex);
        }
    }
    
    public void unloadSequence (String path)
    {
        sequences.remove(path);
    }
    
    public void dispose ()
    {
        Set<String> clipKeys = clips.keySet();
        clipKeys.forEach((key) -> unloadClip (key));;
        
        stopSequence ();
        sequences.clear();
    }
}
