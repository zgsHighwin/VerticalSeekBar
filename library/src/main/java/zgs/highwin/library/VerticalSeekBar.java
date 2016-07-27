package zgs.highwin.library;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.SeekBar;

/**
 * Created by zgsHighwin on 2016/4/1.
 */
public class VerticalSeekBar extends SeekBar {

    private Context context;
    /**
     * 背景画笔
     */
    private Paint backgroundPaint;
    /**
     * 拖动条画笔
     */
    private Paint progressPaint;
    /**
     * 第二进度条画笔
     */
    private Paint secondProgressPaint;
    /**
     * 滑块画笔
     */
    private Paint thumbPaint;

    /**
     * 滑块图片
     */
    private Bitmap thumb;

    /**
     * seekBar的宽度
     */
    private int mSeekBarWidth;
    /**
     * 滑块的背景图片
     */
    private int mThumbBg = R.mipmap.thumb;
    /**
     * 拖动条开始的颜色
     */
    private int mProgressBgStartColor;
    /**
     * 拖动条结束的颜色
     */
    private int mProgressBgEndColor;
    /**
     * 背景开始的颜色
     */
    private int mBgStartColor;
    /**
     * 背景结束的颜色
     */
    private int mBgEndColor;
    /**
     * seekBar的圆角
     */
    private int mSeekBarRadius;

    private int mSeekBarBorderWidth;
    private int mSeekBarBorderColor;

    public int getSeekBarBorderColor() {
        return mSeekBarBorderColor;
    }

    public VerticalSeekBar setSeekBarBorderColor(int mSeekBarBorderColor) {
        this.mSeekBarBorderColor = mSeekBarBorderColor;
        return this;
    }

    public int getSeekBarBorderWidth() {
        return mSeekBarBorderWidth;
    }

    public VerticalSeekBar setSeekBarBorderWidth(int mSeekBarBorderWidth) {
        this.mSeekBarBorderWidth = mSeekBarBorderWidth;
        return this;
    }

    private Paint backgroundBorderPaint;

    /**
     * 设置seekBar的圆角
     *
     * @param mSeekBarRadius
     */
    public VerticalSeekBar setmSeekBarRadius(int mSeekBarRadius) {
        this.mSeekBarRadius = mSeekBarRadius;
        return this;
    }

    /**
     * 设置seekBar的宽度
     *
     * @param mSeekBarWidth
     */
    public VerticalSeekBar setmSeekBarWidth(int mSeekBarWidth) {
        this.mSeekBarWidth = mSeekBarWidth;
        return this;
    }

    /**
     * 设置滑块的图片
     *
     * @param mThumbBg
     */
    public VerticalSeekBar setmThumbBg(int mThumbBg) {
        this.mThumbBg = mThumbBg;
        return this;
    }

    /**
     * 设置拖动条的开始的颜色
     *
     * @param mProgressBgStartColor
     */
    public VerticalSeekBar setmProgressBgStartColor(int mProgressBgStartColor) {
        this.mProgressBgStartColor = mProgressBgStartColor;
        return this;
    }

    /**
     * 设置拖动条的结束的颜色
     *
     * @param mProgressBgEndColor
     */
    public VerticalSeekBar setmProgressBgEndColor(int mProgressBgEndColor) {
        this.mProgressBgEndColor = mProgressBgEndColor;
        return this;
    }

    /**
     * 设置开始背景的颜色
     *
     * @param mBgStartColor
     */
    public VerticalSeekBar setmBgStartColor(int mBgStartColor) {
        this.mBgStartColor = mBgStartColor;
        return this;
    }

    /**
     * 设置背景结束的颜色
     *
     * @param mBgEndColor
     */
    public VerticalSeekBar setmBgEndColor(int mBgEndColor) {
        this.mBgEndColor = mBgEndColor;
        return this;
    }

    public VerticalSeekBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomVerticalSeekBar);
        mSeekBarWidth = typedArray.getInteger(R.styleable.CustomVerticalSeekBar_seekBarWidth, 10);
        Log.d("zgs==>mSeekBarWidth", String.valueOf(mSeekBarWidth));
        mThumbBg = typedArray.getResourceId(R.styleable.CustomVerticalSeekBar_thumbBg, R.mipmap.thumb);
        Log.d("zgs==>mThumbBg", String.valueOf(mThumbBg));
        mProgressBgStartColor = typedArray.getColor(R.styleable.CustomVerticalSeekBar_progressBgStartColor, Color.WHITE);
        Log.d("zgs==>progressStart", String.valueOf(mProgressBgStartColor));
        mProgressBgEndColor = typedArray.getColor(R.styleable.CustomVerticalSeekBar_progressBgEndColor, Color.GREEN);
        Log.d("zgs==>progressEnd", String.valueOf(mProgressBgEndColor));
        mBgStartColor = typedArray.getColor(R.styleable.CustomVerticalSeekBar_bgStartColor, Color.BLACK);
        Log.d("zgs==>mBgStartColor", String.valueOf(mBgStartColor));
        mBgEndColor = typedArray.getColor(R.styleable.CustomVerticalSeekBar_bgEndColor, Color.BLACK);
        Log.d("zgs==>mBgEndColor", String.valueOf(mBgEndColor));
        mSeekBarRadius = typedArray.getInteger(R.styleable.CustomVerticalSeekBar_seekBarRadius, 10);
        Log.d("zgs==>mSeekBarRadius", String.valueOf(mSeekBarRadius));
        mSeekBarBorderWidth = typedArray.getInteger(R.styleable.CustomVerticalSeekBar_borderWidth,0);
        Log.d("zgs==>BorderWidth", String.valueOf(mSeekBarBorderWidth));
        mSeekBarBorderColor = typedArray.getInteger(R.styleable.CustomVerticalSeekBar_borderColor, 0);

        typedArray.recycle();
        init(context);
    }

    public VerticalSeekBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public VerticalSeekBar(Context context) {
        this(context, null);
    }

    /**
     * 初始化
     */
    private void init(Context context) {
        this.context = context;
        initPaint();
    }


    /**
     * 初始化画笔
     */
    private void initPaint() {
        backgroundPaint = new Paint();
        backgroundPaint.setDither(true);  //防抖动
        backgroundPaint.setAntiAlias(true); //抗锯齿
        backgroundPaint.setStyle(Paint.Style.FILL); //不画边框，只画实体内容
        backgroundPaint.setStrokeCap(Paint.Cap.ROUND); // 定义线段断电形状为圆头

        backgroundBorderPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        backgroundBorderPaint.setDither(true);
        backgroundBorderPaint.setStyle(Paint.Style.STROKE);
        backgroundBorderPaint.setStrokeCap(Paint.Cap.ROUND);
        backgroundBorderPaint.setStrokeJoin(Paint.Join.ROUND);
        backgroundBorderPaint.setStrokeWidth(mSeekBarBorderWidth);
        backgroundBorderPaint.setColor(mSeekBarBorderColor);


        progressPaint = new Paint();
        progressPaint.setDither(true);
        progressPaint.setAntiAlias(true);
        progressPaint.setStyle(Paint.Style.FILL);
        progressPaint.setStrokeCap(Paint.Cap.ROUND); // 定义线段断电形状为圆头

        secondProgressPaint = new Paint();
        secondProgressPaint.setDither(true);
        secondProgressPaint.setAntiAlias(true);
        secondProgressPaint.setStyle(Paint.Style.FILL);
        secondProgressPaint.setStrokeCap(Paint.Cap.ROUND); // 定义线段断电形状为圆头

        thumbPaint = new Paint();
        thumbPaint.setDither(true);
        thumbPaint.setAntiAlias(true);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        Log.d("zgs==>onSizeChanged", "onSizeChanged");
        super.onSizeChanged(h, w, oldh, oldw);
    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {
        canvas.rotate(-90);
        canvas.translate(-getHeight(), 0);

        RectF backgroundRect = new RectF(thumb.getWidth() / 2, getWidth() / 2 - mSeekBarWidth, getHeight() - thumb.getWidth() / 2, getWidth() / 2 + mSeekBarWidth);
        Log.d("zgs==>getHeight", String.valueOf(getHeight()));
        canvas.drawRoundRect(backgroundRect, mSeekBarRadius, mSeekBarRadius, backgroundPaint);  //绘制背景
        canvas.drawRoundRect(backgroundRect, mSeekBarRadius, mSeekBarRadius, backgroundBorderPaint);
        if (getMax() != 0) {
            //绘制第二条拖动条
            RectF secondProgressRect = new RectF(thumb.getWidth() / 2, getWidth() / 2 - mSeekBarWidth + mSeekBarBorderWidth/2,
                    getSecondaryProgress() * getHeight() / getMax(), getWidth()
                    / 2 + mSeekBarWidth - mSeekBarBorderWidth/2);
            canvas.drawRoundRect(secondProgressRect, mSeekBarRadius, mSeekBarRadius, secondProgressPaint);
            //绘制拖动条
            RectF progressRect = new RectF(thumb.getWidth() / 2, getWidth() / 2 - mSeekBarWidth + mSeekBarBorderWidth/2,
                    getProgress() * (getHeight() - thumb.getWidth()) / getMax() + thumb.getWidth() / 2, getWidth() / 2
                    + mSeekBarWidth - mSeekBarBorderWidth/2);

            canvas.drawRoundRect(progressRect, mSeekBarRadius, mSeekBarRadius, progressPaint);
            int floatx = getProgress() * (getHeight() - thumb.getWidth()) / getMax();
            canvas.drawBitmap(thumb, floatx, 0, thumbPaint);   //绘制滑块
        }
    }

    @Override
    protected synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.d("zgs==>onMeasure", "onMeasure");
        thumb = BitmapFactory.decodeResource(context.getResources(), mThumbBg);//获取图片
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width = 0;
        int height = 0;
        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize + thumb.getWidth();
        } else {
            width = thumb.getWidth();
            Log.d("zgs==>width", String.valueOf(width));
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize + thumb.getHeight();
            Log.d("zgs==>height", String.valueOf(height));
        } else {
            height = thumb.getHeight() + 50;    //50给示默认的高度
            Log.d("zgs==>height", String.valueOf(height));
        }

        //以下三个为渐变色
        LinearGradient progressLG = new LinearGradient(0, 0, getWidth() + mSeekBarWidth, getHeight() / 2, mProgressBgStartColor, mProgressBgEndColor, Shader.TileMode.MIRROR);
        progressPaint.setShader(progressLG);
        LinearGradient backgroundLG = new LinearGradient(0, 0, getWidth() + width, getHeight() / 2, mBgStartColor, mBgEndColor, Shader.TileMode.MIRROR);
        backgroundPaint.setShader(backgroundLG);

        setMeasuredDimension(width, height);
    }

    /**
     * 获取当前的进度
     *
     * @return
     */
    public int getSeekBarPorgress() {
        int realProgress = 0;
        realProgress = getProgress();
        if (this.getProgress() > this.getMax()) {
            realProgress = this.getMax();
        }
        if (this.getProgress() < 0) {
            realProgress = 0;
        }
        return realProgress;
    }


    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (!isEnabled()) {
            return false;
        }
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
            case MotionEvent.ACTION_UP:
                setProgress(getMax() - (int) ((getMax() * event.getY()) / (getHeight())));
                //onSizeChanged(getWidth(), getHeight(), 0, 0);
                break;

            case MotionEvent.ACTION_CANCEL:
                break;
        }

        return true;
    }

    // 解决调用setProgress（）方法时滑块不跟随的bug
    @Override
    public synchronized void setProgress(int progress) {
        super.setProgress(progress);
        onSizeChanged(getWidth(), getHeight(), 0, 0);
    }

}
