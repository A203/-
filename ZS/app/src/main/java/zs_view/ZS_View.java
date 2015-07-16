package zs_view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by X84 on 2015/7/10.
 */
public class ZS_View extends View {
    public float cX = 40;
    public float cY = 50;
    Paint p = new Paint();
    public ZS_View(Context context){
        super(context);
    }
    public ZS_View(Context context,AttributeSet set){
        super(context,set);
    }
    @Override
    public void onDraw(Canvas ca){
        super.onDraw(ca);
        p.setColor(Color.BLUE);
        ca.drawCircle(cX, cY, 15, p);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event){
        cX = event.getX();
        cY = event.getY();
        invalidate();
        return true;
    }
}
