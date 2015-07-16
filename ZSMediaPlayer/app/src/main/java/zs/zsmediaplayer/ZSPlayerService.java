package zs.zsmediaplayer;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.media.audiofx.Visualizer;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
//计算机\OPPO X9070\内存设备\Unit 3
//4 To Being a Bridge Builder.mp3

/**
 * Created by X84 on 2015/7/13.
 */
public class ZSPlayerService extends Service {
    final static int playing = 0;
    final static int pausing = 1;
    final static int stoping = 2;
    int state = stoping;
    int songDuration = 0;
    int songnum = -1;
    MusicBinder binder = new MusicBinder();
    // boolean isPrepare = false;
    private MediaPlayer mediaPlayer = new MediaPlayer();
    ArrayList<String> songList = new ArrayList<String>();
    int listLength;
    Visualizer visualizer = null;
    public class MusicBinder extends Binder {
        HomePage homepage = null;
        public void setPosition(int p){
            if(state==ZSPlayerService.stoping)
                return;
            mediaPlayer.seekTo(p*songDuration/100);
        }
        public int getProgress(){
            if(songDuration==0)
                return 0;
            return mediaPlayer.getCurrentPosition()*100/songDuration;
        }
        public void setVisualizer(Visualizer v){
            visualizer = v;
        }
        public Visualizer getVisualizer(){
            return visualizer;
        }
        public MediaPlayer getMediaPlayer(){
            return mediaPlayer;
        }
        public int getSongnum(){
            return songnum;
        }
        public int getState() {
            return state;
        }

        public void setHomepage(HomePage homepage) {
            this.homepage = homepage;
        }

        public void nextCommand() {
            next();
        }

        public void preCommand() {
            pre();
        }

        public void playCommand() {
            //Log.e("PlayService",String.valueOf(listLength));
            play();
            //Log.e("PlayService", "2");
        }

        public void stopCommand() {
            stop();
        }

        public void playIndexCommand(int index) {
            playIndex(index);
        }
    }

    public void next() {
        if (songnum == -1) {
            if (listLength == 0)
                return;
            songnum++;
        }
        songnum = (songnum + 1) % listLength;
        mediaPlayer.stop();
        mediaPlayer.reset();
        try {
            mediaPlayer.setDataSource(songList.get(songnum));
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        state = ZSPlayerService.playing;
        try {
            if (binder.homepage != null)
                binder.homepage.updateTitle(songnum);
        }catch (Exception e){

        }
        songDuration = mediaPlayer.getDuration();
    }

    public void play() {
        switch (state) {
            case ZSPlayerService.playing:
                mediaPlayer.pause();
                state = ZSPlayerService.pausing;
                break;
            case ZSPlayerService.stoping:
                if (songnum == -1) {
                    if (listLength == 0) {
                        return;
                    }
                    songnum++;
                }
                try {
                    mediaPlayer.setDataSource(songList.get(songnum));
                    mediaPlayer.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                songDuration = mediaPlayer.getDuration();
            case ZSPlayerService.pausing:
                mediaPlayer.start();
                state = ZSPlayerService.playing;
        }
        binder.homepage.updateTitle(songnum);
    }

    public void stop() {
        mediaPlayer.stop();
        mediaPlayer.reset();
        songDuration = 0;
        state = ZSPlayerService.stoping;
    }

    public void pre() {
        if (songnum == -1) {
            if (listLength == 0)
                return;
            songnum++;
        }
        songnum = (songnum - 1 + listLength) % listLength;
        mediaPlayer.stop();
        mediaPlayer.reset();
        try {
            mediaPlayer.setDataSource(songList.get(songnum));
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        state = ZSPlayerService.playing;
        binder.homepage.updateTitle(songnum);
        songDuration = mediaPlayer.getDuration();
    }

    public void playIndex(int index) {
        songnum = index;
        mediaPlayer.stop();
        mediaPlayer.reset();
        try {
            mediaPlayer.setDataSource(songList.get(songnum));
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        state = ZSPlayerService.playing;
        binder.homepage.updateTitle(songnum);
        songDuration = mediaPlayer.getDuration();
    }

    @Override
    public void onDestroy() {
        mediaPlayer.stop();
        super.onDestroy();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                next();
            }
        });
        mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                next();
                return false;
            }
        });
    }

    @Override
    public IBinder onBind(Intent intent) {
        Bundle bundle = intent.getExtras();
        ArrayList<String> arrayList = bundle.getStringArrayList("SongURLList");
        songList.clear();
        for (String song : arrayList) {
            songList.add(song);
        }
        this.listLength = songList.size();
        return binder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }
}
