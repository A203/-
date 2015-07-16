package zs.zsmediaplayer;

import android.app.Service;
import android.content.Intent;
import android.database.Cursor;
import android.os.Binder;
import android.os.IBinder;
import android.provider.MediaStore;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by X84 on 2015/7/13.
 */
public class MusicScanner extends Service{
    MyBinder myBinder = new MyBinder();
    private void foo(){
        Cursor cursor = getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null, MediaStore.Audio.Media.DEFAULT_SORT_ORDER);
        if(cursor.moveToFirst()){
            while(!cursor.isAfterLast()){
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media._ID));
                //歌曲标题
                String tilte = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE));
                //歌曲的专辑名：MediaStore.Audio.Media.ALBUM
                String album = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM));
                //歌曲的歌手名： MediaStore.Audio.Media.ARTIST
                String artist = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST));
                //歌曲文件的路径 ：MediaStore.Audio.Media.DATA
                String url = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA));
                //歌曲的总播放时长 ：MediaStore.Audio.Media.DURATION
                int duration = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION));
                //歌曲文件的大小 ：MediaStore.Audio.Media.SIZE
                long size = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.SIZE));
                cursor.moveToNext();
                if(duration<45000)
                    continue;
                SongInfo songInfo = new SongInfo();
                songInfo.setId(id);
                songInfo.setTitle(tilte);
                songInfo.setAlbum(album);
                songInfo.setArtist(artist);
                songInfo.setUrl(url);
                songInfo.setDuration(duration);
                songInfo.setSize(size);
                myBinder.arrayList.add(songInfo);
               /* Log.e("id",String.valueOf(id));
                Log.i("title", tilte);
                Log.d("album", album);
                Log.v("url", url);
                Log.w("artist", artist);
                Log.wtf("duration", String.valueOf(duration));
                Log.wtf("size",String.valueOf(size));*/

            }
        }
        cursor.close();
    }

    @Override
    public void onCreate() {
        super.onCreate();
       // Log.e("MusicScanner","onCreate1");
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.e("MusicScanner","onBind");
        foo();
        return (IBinder)myBinder;
    }
    public class MyBinder extends Binder{
        public ArrayList<SongInfo> arrayList = new ArrayList<SongInfo>();
    }
}
