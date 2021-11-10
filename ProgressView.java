package zgs.com.androidtest;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class ProgressView extends View {

    private static final String TAG = "ProgressView";

    private Paint mPaint; //底色
    private Paint mPaintBg; //底色
    private RectF mBgRectF;
    private int mHeight;
    private int mWidth;
    private RectF mProRectF;

    private int mNum = 1;
    private float mProgress;
    private Resources resources;

    public ProgressView(final Context context) {
        super(context);
        init();
    }

    public ProgressView(final Context context, @Nullable final AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public void setProgress(float progress, int num) {
        mProgress = progress;
        mNum = num;
        Log.d(TAG, "progress:" + progress);
        refresh();
    }

    private void refresh() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            postInvalidateOnAnimation();
        } else {
            invalidate();
        }
    }

    private void init() {
        resources = getResources();
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.BLACK);
//        mPaint.setColor(resources.getColor(R.color.color_tv_class_item_light_text_color));
        mPaint.setDither(true);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);

        mPaintBg = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintBg.setColor(Color.RED);
//        mPaintBg.setColor(resources.getColor(R.color.color_class_item_light));
        mPaintBg.setDither(true);
        mPaintBg.setStyle(Paint.Style.FILL_AND_STROKE);


        mBgRectF = new RectF();
        mProRectF = new RectF();
    }

    @Override
    protected void onDraw(final Canvas canvas) {
        super.onDraw(canvas);

        mBgRectF.left = 0;
        mBgRectF.top = 0;
        mBgRectF.right = mWidth;
        mBgRectF.bottom = mHeight;

        float value2 = mProgress * 1.0f / (mNum * 100) * mWidth;
        Log.d(TAG, "value2:" + value2);
        float value = value2;
        Log.d(TAG, "value:" + value);
        mProRectF.left = value;
        mProRectF.top = 0;
        mProRectF.right = mWidth;
        mProRectF.bottom = mHeight;

        canvas.drawRoundRect(mBgRectF, 30, 30, mPaintBg);
//        canvas.drawRect(mProRectF, mPaintPro);
        int layerId = canvas.saveLayer(mBgRectF, null, Canvas.ALL_SAVE_FLAG);
        canvas.drawRoundRect(mBgRectF, 30, 30, mPaint);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
//        mPaint.setColor(resources.getColor(R.color.color_tv_class_item_light_text_color));
        canvas.drawRect(mProRectF, mPaint);
        mPaint.setXfermode(null);
        canvas.restoreToCount(layerId);

    }

    @Override
    protected void onSizeChanged(final int w, final int h, final int oldw, final int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mHeight = getMeasuredHeight();
        mWidth = getMeasuredWidth();
    }
}
