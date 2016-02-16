/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package podracergame;

import audio.Playlist;
import audio.SoundManager;
import audio.Source;
import audio.Track;
import java.util.ArrayList;

/**
 *
 * @author Liam
 */
public class AudioManager extends SoundManager{

    public static AudioManager getSoundManager(){
        ArrayList<Track> tracks = new ArrayList<>();
        tracks.add(new Track(AudioManager.NOISE, Source.RESOURCE, "/podracergame/Coin_pickup.wav"));
        
        Playlist playlist = new Playlist(tracks);
        return new AudioManager(playlist);
    }
    
    public AudioManager(Playlist playlist) {
        super(playlist);
    }
    
    public static final String NOISE = "SONG";
    public static final String NOISE2 = "SONG2";
    
}
