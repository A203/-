package zs.zsmediaplayer;

import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.audiofx.Visualizer;
import android.os.Binder;
import android.os.IBinder;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static zs.zsmediaplayer.MusicScanner.*;

class BaseSongInfo {
    private String song;
    private String singer;

    public String getSong() {
        return song;
    }

    public void setSong(String song) {
        this.song = song;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }
}

class MyAdapter extends BaseAdapter {
    ArrayList<BaseSongInfo> arrayList = new ArrayList<BaseSongInfo>();
    //ArrayList<String> arrayList = new ArrayList<String>();
    Context context;

    public MyAdapter(Context context) {
        super();
        this.context = context;
    }

    @Override
    public int getCount() {
        //Log.e("getCount", "1");
        //Log.e("Count", String.valueOf(arrayList.size()));
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        //Log.e("getItem", "1");
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        BaseSongInfo baseSongInfo = arrayList.get(position);

        TextView button = new TextView(this.context);
        button.setBackgroundResource(R.drawable.item);
        button.setTextColor(R.color.bright_foreground_material_dark);
        button.setGravity(Gravity.CENTER);
        button.setTextSize(18);
        button.setHeight(100);
        button.setText(baseSongInfo.getSong() + " / " + baseSongInfo.getSinger());
        //button.setText(arrayList.get(position));
        return button;
    }
}

public class HomePage extends Activity {
    //HomePage homePage = this;
    MyAdapter adapter = new MyAdapter(this);
    TextView title,album;
    SeekBar seekBar;
    MusicScanner.MyBinder binder = null;
    ZSPlayerService.MusicBinder musicBinder = null;
    Button play, pre, next, stop;
    LinearLayout linearLayout;
    boolean flag = true;
    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            binder = (MusicScanner.MyBinder) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
    ServiceConnection musicConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            musicBinder = (ZSPlayerService.MusicBinder) service;
            state = musicBinder.getState();
            musicBinder.setHomepage(HomePage.this);
            int num = musicBinder.getSongnum();
            if (num!=-1)
                updateTitle(musicBinder.getSongnum());
            if (state == HomePage.playing) {
                play.setBackgroundResource(R.drawable.pasue);
            }
            if (musicBinder.getVisualizer()==null){
                mVisualizer = new Visualizer(musicBinder.getMediaPlayer().getAudioSessionId());
                mVisualizer.setCaptureSize(Visualizer.getCaptureSizeRange()[1]);
                musicBinder.setVisualizer(mVisualizer);
            }
            else{
                mVisualizer = musicBinder.getVisualizer();
            }
            mVisualizer.setDataCaptureListener(
                    new Visualizer.OnDataCaptureListener()
                    {
                        @Override
                        public void onFftDataCapture(Visualizer visualizer,
                                                     byte[] fft, int samplingRate)
                        {
                        }
                        @Override
                        public void onWaveFormDataCapture(Visualizer visualizer,byte[] waveform, int samplingRate)
                        {
                            // 用waveform波形数据更新mVisualizerView组件
                            myVisualizerView.updateVisualizer(waveform);
                        }
                    }, Visualizer.getMaxCaptureRate(), true, false);
            mVisualizer.setEnabled(true);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
    final static int playing = 0;
    final static int pausing = 1;
    final static int stoping = 2;
    int state = HomePage.stoping;
    public void updateTitle(int index){
        SongInfo songInfo = binder.arrayList.get(index);
        title.setText(songInfo.getTitle()+" / "+songInfo.getArtist());
        album.setText("专辑： "+songInfo.getAlbum());
    }
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
        setContentView(R.layout.activity_home_page);

        seekBar = (SeekBar)findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(musicBinder!=null&&fromUser==true)
                    musicBinder.setPosition(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        new Thread(){
            @Override
            public void run() {
                while (musicBinder==null);
                while(true){
                    seekBar.setProgress(musicBinder.getProgress());
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();

        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        linearLayout = (LinearLayout)findViewById(R.id.show);
        myVisualizerView = new MyVisualizerView(this);
        myVisualizerView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));
        linearLayout.addView(myVisualizerView);

        //bindService(intent, serviceConnection, Service.BIND_AUTO_CREATE);
        WindowManager wm = this.getWindowManager();
        int width = (int) (wm.getDefaultDisplay().getWidth() / 4.5);
        int height = (int) (wm.getDefaultDisplay().getHeight() *0.20);
        if(height<width)
            width = height;
        play = (Button) findViewById(R.id.play);
        pre = (Button) findViewById(R.id.pre);
        next = (Button) findViewById(R.id.next);
        stop = (Button) findViewById(R.id.stop);
        title = (TextView) findViewById(R.id.title);
        album = (TextView) findViewById(R.id.album);
        play.getLayoutParams().height = play.getLayoutParams().width = width;
        pre.getLayoutParams().height = pre.getLayoutParams().width = width;
        next.getLayoutParams().height = next.getLayoutParams().width = width;
        stop.getLayoutParams().height = stop.getLayoutParams().width = width;

        //ArrayList<SongInfo> arrayList = new ArrayList<SongInfo>();
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (state == HomePage.stoping || state == HomePage.pausing) {
                    play.setBackgroundResource(R.drawable.pasue);
                    state = HomePage.playing;
                } else {
                    play.setBackgroundResource(R.drawable.play);
                    state = HomePage.pausing;
                }
                musicBinder.playCommand();
            }
        });
        pre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (state == HomePage.stoping || state == HomePage.pausing) {
                    play.setBackgroundResource(R.drawable.pasue);
                    state = HomePage.playing;
                }
                musicBinder.preCommand();
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (state == HomePage.stoping || state == HomePage.pausing) {
                    play.setBackgroundResource(R.drawable.pasue);
                    state = HomePage.playing;
                }
                musicBinder.nextCommand();
            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (state == HomePage.playing) {
                    play.setBackgroundResource(R.drawable.play);
                }
                state = HomePage.stoping;
                musicBinder.stopCommand();

            }
        });

        ListView listView = (ListView) findViewById(R.id.SongList);
        adapter.arrayList.clear();
        BaseSongInfo baseSongInfo = new BaseSongInfo();
        baseSongInfo.setSong("歌曲");
        baseSongInfo.setSinger("歌手");
        adapter.arrayList.add(baseSongInfo);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Log.e("Click",position+"");
                if (position != 0) {
                    musicBinder.playIndexCommand(position - 1);
                    if (state == HomePage.stoping || state == HomePage.pausing) {
                        play.setBackgroundResource(R.drawable.pasue);
                        state = HomePage.playing;
                    }
                }
            }
        });

        new Thread() {
            public void run() {
                while (flag);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent();
                intent.setAction("zs.zsmediaplayer.MusicScanner");
                bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);

                while (binder == null) {
                }
                intent.setAction("zs.zsmediaplayer.ZSPlayerService");
                Bundle bundle = new Bundle();
                ArrayList<String> arrayList = new ArrayList<String>();
                for (SongInfo songInfo : binder.arrayList) {
                    arrayList.add(songInfo.getUrl());
                }
                startService(intent);
                bundle.putStringArrayList("SongURLList", arrayList);
                intent.putExtras(bundle);
                bindService(intent, musicConnection, Context.BIND_AUTO_CREATE);
                //adapter.arrayList.clear();
                for(SongInfo s:binder.arrayList){
                    BaseSongInfo baseSongInfo = new BaseSongInfo();
                    baseSongInfo.setSinger(s.getArtist());
                    baseSongInfo.setSong(s.getTitle());
                    adapter.arrayList.add(baseSongInfo);
                }

               // adapter.notifyDataSetChanged();
            }
        }.start();
        flag = false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        musicBinder.homepage=null;
        unbindService(musicConnection);
        super.onDestroy();
    }
    private Visualizer mVisualizer;
    public MyVisualizerView myVisualizerView;
    private  class MyVisualizerView extends View{
        private byte[] bytes;
        private float[] points;
        private Paint paint = new Paint();
        private Rect rect = new Rect();
        private byte type = 0;
        public MyVisualizerView(Context context)
        {
            super(context);
            bytes = null;
            // 设置画笔的属性
            paint.setStrokeWidth(2f);
            paint.setAntiAlias(true);
            paint.setColor(Color.BLACK);
            paint.setStyle(Paint.Style.FILL);
        }

        public void updateVisualizer(byte[] ftt)
        {
            bytes = ftt;
            // 通知该组件重绘自己。
            invalidate();
        }

        @Override
        public boolean onTouchEvent(MotionEvent me)
        {
            // 当用户触碰该组件时，切换波形类型
            if(me.getAction() != MotionEvent.ACTION_DOWN)
            {
                return false;
            }
            type ++;
            if(type >= 3)
            {
                type = 0;
            }
            return true;
        }

        @Override
        protected void onDraw(Canvas canvas)
        {
            super.onDraw(canvas);
            if (bytes == null)
            {
                return;
            }
            // 绘制白色背景（主要为了印刷时好看）
            //canvas.drawColor(Color.WHITE);
            // 使用rect对象记录该组件的宽度和高度
            rect.set(0,0,getWidth(),getHeight());
            switch(type)
            {
                // -------绘制块状的波形图-------
                case 0:
                    for (int i = 0; i < bytes.length - 1; i++)
                    {
                        float left = getWidth() * i / (bytes.length - 1);
                        // 根据波形值计算该矩形的高度
                        float top = rect.height()-(byte)(bytes[i+1]+128)
                                * rect.height() / 128;
                        float right = left + 1;
                        float bottom = rect.height();
                        canvas.drawRect(left, top, right, bottom, paint);
                    }
                    break;
                // -------绘制柱状的波形图（每隔18个抽样点绘制一个矩形）-------
                case 1:
                    for (int i = 0; i < bytes.length - 1; i += 18)
                    {
                        float left = rect.width()*i/(bytes.length - 1);
                        // 根据波形值计算该矩形的高度
                        float top = rect.height()-(byte)(bytes[i+1]+128)
                                * rect.height() / 128;
                        float right = left + 6;
                        float bottom = rect.height();
                        canvas.drawRect(left, top, right, bottom, paint);
                    }
                    break;
                // -------绘制曲线波形图-------
                case 2:
                    // 如果point数组还未初始化
                    if (points == null || points.length < bytes.length * 4)
                    {
                        points = new float[bytes.length * 4];
                    }
                    for (int i = 0; i < bytes.length - 1; i++)
                    {
                        // 计算第i个点的x坐标
                        points[i * 4] = rect.width()*i/(bytes.length - 1);
                        // 根据bytes[i]的值（波形点的值）计算第i个点的y坐标
                        points[i * 4 + 1] = (rect.height() / 2)
                                + ((byte) (bytes[i] + 128)) * 128
                                / (rect.height() / 2);
                        // 计算第i+1个点的x坐标
                        points[i * 4 + 2] = rect.width() * (i + 1)
                                / (bytes.length - 1);
                        // 根据bytes[i+1]的值（波形点的值）计算第i+1个点的y坐标
                        points[i * 4 + 3] = (rect.height() / 2)
                                + ((byte) (bytes[i + 1] + 128)) * 128
                                / (rect.height() / 2);
                    }
                    // 绘制波形曲线
                    canvas.drawLines(points, paint);
                    break;
            }
        }
    }
}
