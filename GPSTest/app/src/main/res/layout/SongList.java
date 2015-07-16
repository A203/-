package zs.zsmediaplayer;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by X84 on 2015/7/13.
 */
class MyAdapter extends BaseAdapter{
    ArrayList<String> arrayList = new ArrayList<String>();
    Context context;
    public MyAdapter(Context context){
        super();
        this.context = context;
    }
    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Button button = new Button(this.context);
        button.setText(String.valueOf(this.arrayList.get(position)));
        return button;
    }
}
public class SongList extends Activity{
    //TextView[] textViews = new TextView[12];
    Adapter adapter = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.song_list);
        Log.e("SongList","1!");
        ListView listView= (ListView)findViewById(R.id.SongList);
        Log.e("SongList","2!");
       // String[] a = {"123","456","789"};
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.item,a);
        MyAdapter adapter = new MyAdapter(this);
        adapter.arrayList.add("ABC");
        adapter.arrayList.add("DEF");
        adapter.arrayList.add("GHL");
        adapter.arrayList.add("GHL");
        adapter.arrayList.add("GHL");
        adapter.arrayList.add("GHL");
        adapter.arrayList.add("GHL");
        adapter.arrayList.add("GHL");
        adapter.arrayList.add("GHL");
        adapter.arrayList.add("GHL");
        adapter.arrayList.add("GHL");
        adapter.arrayList.add("GHL");
        adapter.arrayList.add("GHL");
        adapter.arrayList.add("GHL");
        adapter.arrayList.add("GHL");
        adapter.arrayList.add("GHL");
        adapter.arrayList.add("GHL");
        adapter.arrayList.add("GHL");
        adapter.arrayList.add("GHL");
        adapter.arrayList.add("GHL");
        listView.setAdapter(adapter);
        /*ListAdapter listAdapter = (ListAdapter)adapter;
        int totalHeight = 0;
        for (int i = 0, len = listAdapter.getCount(); i < len; i++) {
            Button listViewItem =(Button) listAdapter.getView(i , null, listView);
            // 计算子项View 的宽高
            listViewItem.measure(0, 0);
            //Log.e("HHHHHHHHHHHHHHHHHeight",);
            // 计算所有子项的高度和
            Log.e("1234",String.valueOf(listViewItem.getMeasuredHeight()));
            totalHeight += listViewItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        // listView.getDividerHeight()获取子项间分隔符的高度
        // params.height设置ListView完全显示需要的高度
        params.height = totalHeight+ (listView.getDividerHeight() * ( listAdapter.getCount() - 1));
        Log.e("HHHHHHeight",String.valueOf(params.height));
        listView.setLayoutParams(params);*/
    }
}
