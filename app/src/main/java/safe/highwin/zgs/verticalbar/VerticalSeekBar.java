package safe.highwin.zgs.verticalbar;

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
     * 第二进度条开始的颜色
     */
    private int mSecondaryProgressBgStartColor;

    /**
     * 第二进度条结束的颜色
     */
    private int mSecondaryProgressBgEndColor;
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
    private int thumbWidth;
    private int mWidth;
    private int mHeight;
    private int mMax;
    private RectF mBackgroundRect;
    private RectF mProgressRect;
    private RectF mSecondProgressRect;

    public int getSeekBarBorderColor() {
        return mSeekBarBorderColor;
    }

    public VerticalSeekBar setSeekBarBorderColor(int mSeekBarBorderColor) {
        this.mSeekBarBorderColor = mSeekBarBorderColor;
        backgroundBorderPaint.setColor(mSeekBarBorderColor);
        return this;
    }

    public int getSeekBarBorderWidth() {
        return mSeekBarBorderWidth;
    }

    public VerticalSeekBar setSeekBarBorderWidth(int mSeekBarBorderWidth) {
        this.mSeekBarBorderWidth = mSeekBarBorderWidth;
        backgroundBorderPaint.setStrokeWidth(mSeekBarBorderWidth);
        return this;
    }

    private Paint backgroundBorderPaint;

    /**
     * 设置seekBar的圆角
     *
     * @param mSeekBarRadius
     */
    public VerticalSeekBar setSeekBarRadius(int mSeekBarRadius) {
        this.mSeekBarRadius = mSeekBarRadius;
        return this;
    }

    /**
     * 设置seekBar的宽度
     *
     * @param mSeekBarWidth
     */
    public VerticalSeekBar setSeekBarWidth(int mSeekBarWidth) {
        this.mSeekBarWidth = mSeekBarWidth;
        backgroundPaint.setStrokeWidth(mSeekBarWidth);
        return this;
    }

    /**
     * 设置滑块的图片
     *
     * @param mThumbBg
     */
    public VerticalSeekBar setThumbBg(int mThumbBg) {
        this.mThumbBg = mThumbBg;
        return this;
    }

    /**
     * 设置拖动条的开始的颜色
     *
     * @param mProgressBgStartColor
     */
    public VerticalSeekBar setProgressBgStartColor(int mProgressBgStartColor) {
        this.mProgressBgStartColor = mProgressBgStartColor;
        return this;
    }

    /**
     * 设置拖动条的结束的颜色
     *
     * @param mProgressBgEndColor
     */
    public VerticalSeekBar setProgressBgEndColor(int mProgressBgEndColor) {
        this.mProgressBgEndColor = mProgressBgEndColor;
        return this;
    }

    public VerticalSeekBar setSecondaryProgressBgStartColor(final int secondaryProgressBgStartColor) {
        this.mSecondaryProgressBgStartColor = secondaryProgressBgStartColor;
        return this;
    }

    public VerticalSeekBar setSecondaryProgressBgEndColor(final int secondaryProgressBgEndColor) {
        this.mSecondaryProgressBgEndColor = secondaryProgressBgEndColor;
        return this;
    }

    /**
     * 设置开始背景的颜色
     *
     * @param mBgStartColor
     */
    public VerticalSeekBar setBgStartColor(int mBgStartColor) {
        this.mBgStartColor = mBgStartColor;
        return this;
    }

    /**
     * 设置背景结束的颜色
     *
     * @param mBgEndColor
     */
    public VerticalSeekBar setBgEndColor(int mBgEndColor) {
        this.mBgEndColor = mBgEndColor;
        return this;
    }

    public VerticalSeekBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomVerticalSeekBar);
        mThumbBg = typedArray.getResourceId(R.styleable.CustomVerticalSeekBar_thumbBg, R.mipmap.thumb);
        Log.d("zgs==>mThumbBg", String.valueOf(mThumbBg));
        mProgressBgStartColor = typedArray.getColor(R.styleable.CustomVerticalSeekBar_progressBgStartColor, Color.WHITE);
        Log.d("zgs==>progressStart", String.valueOf(mProgressBgStartColor));
        mProgressBgEndColor = typedArray.getColor(R.styleable.CustomVerticalSeekBar_progressBgEndColor, Color.GREEN);
        Log.d("zgs==>progressEnd", String.valueOf(mProgressBgEndColor));
        mSecondaryProgressBgStartColor = typedArray.getColor(R.styleable.CustomVerticalSeekBar_secondaryProgressBgStartColor, Color.WHITE);
        Log.d("zgs==>secProgressStart", String.valueOf(mSecondaryProgressBgStartColor));
        mSecondaryProgressBgEndColor = typedArray.getColor(R.styleable.CustomVerticalSeekBar_secondaryProgressBgEndColor, Color.GREEN);
        Log.d("zgs==>secProgressEnd", String.valueOf(mSecondaryProgressBgEndColor));
        mBgStartColor = typedArray.getColor(R.styleable.CustomVerticalSeekBar_bgStartColor, Color.BLACK);
        Log.d("zgs==>mBgStartColor", String.valueOf(mBgStartColor));
        mBgEndColor = typedArray.getColor(R.styleable.CustomVerticalSeekBar_bgEndColor, Color.BLACK);
        Log.d("zgs==>mBgEndColor", String.valueOf(mBgEndColor));
        mSeekBarBorderColor = typedArray.getColor(R.styleable.CustomVerticalSeekBar_borderColor, Color.BLACK);

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
        thumb = BitmapFactory.decodeResource(context.getResources(), mThumbBg);//获取图片
        thumbWidth = thumb.getWidth();

        mBackgroundRect = new RectF();
        mProgressRect = new RectF();
        mSecondProgressRect = new RectF();
    }

    @Override
    protected void onSizeChanged(final int w, final int h, final int oldw, final int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //以下三个为渐变色
        LinearGradient progressLG = new LinearGradient(0, 0, getWidth() + mSeekBarWidth, mHeight >> 1, mProgressBgStartColor, mProgressBgEndColor, Shader.TileMode.MIRROR);
        progressPaint.setShader(progressLG);
        LinearGradient secondaryProgressLG = new LinearGradient(0, 0, getWidth() + mWidth, mHeight >> 1, mSecondaryProgressBgStartColor, mSecondaryProgressBgEndColor, Shader.TileMode.MIRROR);
        secondProgressPaint.setShader(secondaryProgressLG);
        LinearGradient backgroundLG = new LinearGradient(0, 0, getWidth() + mWidth, mHeight >> 1, mBgStartColor, mBgEndColor, Shader.TileMode.MIRROR);
        backgroundPaint.setShader(backgroundLG);
        mWidth = getWidth();
        mHeight = getHeight();
        mMax = getMax();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.rotate(-90);
        canvas.translate(-getHeight(), 0);
        mBackgroundRect.set((float) thumbWidth / 2, (float) mWidth / 2 - mSeekBarWidth, mHeight - (float) thumbWidth / 2, (float) mWidth / 2 + mSeekBarWidth);
        Log.d("zgs==>getHeight", String.valueOf(getHeight()));
        canvas.drawRoundRect(mBackgroundRect, mSeekBarRadius, mSeekBarRadius, backgroundPaint);  //绘制背景
        canvas.drawRoundRect(mBackgroundRect, mSeekBarRadius, mSeekBarRadius, backgroundBorderPaint);
        if (getMax() != 0) {

            //绘制拖动条
            mProgressRect.set((float) thumbWidth / 2, (float) mWidth / 2 - mSeekBarWidth + (float) mSeekBarBorderWidth / 2,
                    getProgress() * (mHeight - thumbWidth) / mMax + (float) thumbWidth / 2, (float) mWidth / 2 + mSeekBarWidth - (float) mSeekBarBorderWidth / 2);

            canvas.drawRoundRect(mProgressRect, mSeekBarRadius, mSeekBarRadius, progressPaint);
            //绘制第二条拖动条
            int secondaryProgress = getSecondaryProgress();
            if(secondaryProgress!=0){
                mSecondProgressRect.set((float) thumbWidth / 2, (float) mWidth / 2 - mSeekBarWidth + (float) mSeekBarBorderWidth / 2,
                        secondaryProgress * mHeight / mMax, (mWidth >> 1) + mSeekBarWidth - (float) mSeekBarBorderWidth / 2);
                canvas.drawRoundRect(mSecondProgressRect, mSeekBarRadius, mSeekBarRadius, secondProgressPaint);
            }

            int floatx = getProgress() * (getHeight() - thumb.getWidth()) / getMax();
            canvas.drawBitmap(thumb, floatx, 0, thumbPaint);   //绘制滑块
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.d("zgs==>onMeasure", "onMeasure");
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
        } else {
            height = thumb.getHeight() + 50;    //50给示默认的高度
        }
        Log.d("zgs==>height", String.valueOf(height));


        setMeasuredDimension(width, height);
    }

    /**
     * 获取当前的进度
     *
     * @return
     */
    public int getSeekBarProgress() {
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
    public void setProgress(int progress) {
        super.setProgress(progress);
    }

}
