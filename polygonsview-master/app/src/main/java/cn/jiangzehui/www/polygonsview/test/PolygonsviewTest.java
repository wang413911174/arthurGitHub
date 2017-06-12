package cn.jiangzehui.www.polygonsview.test;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import cn.jiangzehui.www.polygonsview.R;

/**
 * Created by arthur on 2017/6/11.
 */

public class PolygonsviewTest extends View {
    private int center;//中心点
    private float one_radius;//外层多边形圆半径
    private float distance;//多边形之间的间距
    private int defalutSize = 300;//默认大小
    private String[] str = {"击杀", "生存", "助攻", "物理", "魔法", "防御", "金钱"};
    private Rect str_rect;//字体矩形
    private Paint rank_Paint;//各等级进度画笔
    private Paint str_paint;//字体画笔
    private Paint center_paint; //中心线画笔
    private Paint one_paint;//最外层多边形画笔
    private Paint two_paint;//第二层多边形画笔
    private Paint three_paint;//第三层多边形画笔
    private Paint four_paint;//第四层多边形画笔
    private float f1, f2, f3, f4, f5, f6, f7;


    public PolygonsviewTest(Context context) {
        this(context, null);
    }

    public PolygonsviewTest(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PolygonsviewTest(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        defalutSize = dp_px(defalutSize);
        //初始化字体画笔
        str_paint = new Paint();
        str_paint.setAntiAlias(true);
        str_paint.setColor(Color.BLACK);
        str_paint.setTextSize(dp_px(16));
        str_rect = new Rect();
        str_paint.getTextBounds(str[0], 0, str[0].length(), str_rect);

        //初始化各等级进度画笔
        rank_Paint = new Paint();
        rank_Paint.setAntiAlias(true);
        rank_Paint.setColor(Color.RED);
        rank_Paint.setStrokeWidth(8);
        rank_Paint.setStyle(Paint.Style.STROKE);//设置空心

        //初始化最外层多边形画笔
        one_paint = new Paint();
        one_paint.setAntiAlias(true);
        one_paint.setColor(getResources().getColor(R.color.one));
        one_paint.setStyle(Paint.Style.FILL);//设置实心

        //初始化第二层多边形画笔
        two_paint = new Paint();
        two_paint.setAntiAlias(true);
        two_paint.setColor(getResources().getColor(R.color.two));
        two_paint.setStyle(Paint.Style.FILL);

        //初始化第三层多边形画笔
        three_paint = new Paint();
        three_paint.setAntiAlias(true);
        three_paint.setColor(getResources().getColor(R.color.three));
        three_paint.setStyle(Paint.Style.FILL);

        //初始化最内层多边形
        four_paint = new Paint();
        four_paint.setAntiAlias(true);
        four_paint.setColor(getResources().getColor(R.color.four));
        four_paint.setStyle(Paint.Style.FILL);

        //初始化中心线画笔
        center_paint = new Paint();
        center_paint.setAntiAlias(true);
        center_paint.setColor(Color.WHITE);

    }

    /**
     * 测量
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int windtSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int width, height;
        if (widthMode == MeasureSpec.EXACTLY) {
            width = windtSize;
        } else {
            width = Math.min(windtSize, defalutSize);
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else {
            height = Math.min(heightSize, defalutSize);
        }
        center = width / 2;//中心点
        one_radius = center - getPaddingTop() - 2 * str_rect.height();
        f1 = one_radius - one_radius / 4 * 1;
        f2 = one_radius - one_radius / 4 * 1;
        f3 = one_radius - one_radius / 4 * 1;
        f4 = one_radius - one_radius / 4 * 1;
        f5 = one_radius - one_radius / 4 * 1;
        f6 = one_radius - one_radius / 4 * 1;
        f7 = one_radius - one_radius / 4 * 1;
        setMeasuredDimension(width, height);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        PaintFout(canvas);
        onePolygons(canvas);
        twoPolygons(canvas);
        threePolygons(canvas);
        fourPolygons(canvas);
        center(canvas);
        PaintRank(canvas);
    }

    /**
     * 绘制字体
     *
     * @param canvas
     */
    private void PaintFout(Canvas canvas) {
        canvas.drawText(str[0], center - str_rect.width() / 2, (float) (getPaddingTop() + 1.5 * str_rect.height()), str_paint);
        canvas.drawText(str[1], (float) (center + Math.sin(Math.toRadians(360 / 7)) * one_radius + str_rect.height() / 2),
                (float) (getPaddingTop() + 2 * str_rect.height() + one_radius - Math.abs(Math.cos(Math.toRadians(360 / 7)) * one_radius)), str_paint);
        canvas.drawText(str[2], (float) (center + Math.sin(Math.toRadians(360 / 7 + 180 / 7)) * one_radius + str_rect.height() / 2),
                (float) (center + Math.cos(Math.toRadians(360 / 7 + 180 / 7)) * one_radius + str_rect.height() / 2), str_paint);
        canvas.drawText(str[3], (float) (center + Math.sin(Math.toRadians(180 / 7)) * one_radius - str_rect.height() / 2 + str_rect.width() / 2),
                (float) (center + Math.cos(Math.toRadians(180 / 7)) * one_radius + str_rect.height()), str_paint);
        canvas.drawText(str[4], (float) (center - Math.sin(Math.toRadians(180 / 7)) * one_radius - str_rect.height() / 2 - str_rect.width()),
                (float) (center + Math.cos(Math.toRadians(180 / 7)) * one_radius + str_rect.height()), str_paint);
        canvas.drawText(str[5], (float) (center - Math.sin(Math.toRadians(360 / 7 + 180 / 7)) * one_radius - str_rect.height() / 2 - str_rect.width()),
                (float) (center + Math.cos(Math.toRadians(360 / 7 + 180 / 7)) * one_radius + str_rect.height() / 2), str_paint);
        canvas.drawText(str[6], (float) (center - Math.sin(Math.toRadians(360 / 7)) * one_radius - str_rect.height() / 2 - str_rect.width()),
                (float) (center - Math.cos(Math.toRadians(360 / 7)) * one_radius), str_paint);
    }

    /**
     * 绘制最外层多边形
     *
     * @param canvas
     */
    private void onePolygons(Canvas canvas) {
        Path path = new Path();
        path.moveTo(center, getPaddingTop() + 2 * str_rect.height());
        path.lineTo((float) (center + Math.sin(Math.toRadians(360 / 7)) * one_radius), (float) (center - Math.cos(Math.toRadians(360 / 7)) * one_radius));
        path.lineTo((float) (center + Math.sin(Math.toRadians(360 / 7 + 360 / 7)) * one_radius), (float) (center - Math.cos(Math.toRadians(360 / 7 + 360 / 7)) * one_radius));
        path.lineTo((float) (center + Math.sin(Math.toRadians(360 / 7 + 360 / 7 + 360 / 7)) * one_radius), (float) (center - (Math.cos(Math.toRadians(360 / 7 + 360 / 7 + 360 / 7)) * one_radius)));
        path.lineTo((float) (center - Math.sin(Math.toRadians(360 / 7 + 360 / 7 + 360 / 7)) * one_radius), (float) (center - (Math.cos(Math.toRadians(360 / 7 + 360 / 7 + 360 / 7)) * one_radius)));
        path.lineTo((float) (center - Math.sin(Math.toRadians(360 / 7 + 360 / 7)) * one_radius), (float) (center - Math.cos(Math.toRadians(360 / 7 + 360 / 7)) * one_radius));
        path.lineTo((float) (center - Math.sin(Math.toRadians(360 / 7)) * one_radius), (float) (center - Math.cos(Math.toRadians(360 / 7)) * one_radius));
        path.close();
        canvas.drawPath(path, one_paint);
    }

    /**
     * 绘制第二层多边形
     *
     * @param canvas
     */
    private void twoPolygons(Canvas canvas) {
        distance = one_radius / 4;
        Path path = new Path();
        path.moveTo(center, getPaddingTop() + 2 * str_rect.height() + distance);
        path.lineTo((float) (center + Math.sin(Math.toRadians(360 / 7)) * (one_radius - distance)), (float) (center - Math.cos(Math.toRadians(360 / 7)) * (one_radius - distance)));
        path.lineTo((float) (center + Math.sin(Math.toRadians(360 / 7 + 360 / 7)) * (one_radius - distance)), (float) (center - Math.cos(Math.toRadians(360 / 7 + 360 / 7)) * (one_radius - distance)));
        path.lineTo((float) (center + Math.sin(Math.toRadians(360 / 7 + 360 / 7 + 360 / 7)) * (one_radius - distance)), (float) (center - Math.cos(Math.toRadians(360 / 7 + 360 / 7 + 360 / 7)) * (one_radius - distance)));
        path.lineTo((float) (center - Math.sin(Math.toRadians(360 / 7 + 360 / 7 + 360 / 7)) * (one_radius - distance)), (float) (center - Math.cos(Math.toRadians(360 / 7 + 360 / 7 + 360 / 7)) * (one_radius - distance)));
        path.lineTo((float) (center - Math.sin(Math.toRadians(360 / 7 + 360 / 7)) * (one_radius - distance)), (float) (center - Math.cos(Math.toRadians(360 / 7 + 360 / 7)) * (one_radius - distance)));
        path.lineTo((float) (center - Math.sin(Math.toRadians(360 / 7)) * (one_radius - distance)), (float) (center - Math.cos(Math.toRadians(360 / 7)) * (one_radius - distance)));
        path.close();
        canvas.drawPath(path, two_paint);
    }

    /**
     * 绘制第三层多边形
     *
     * @param canvas
     */
    private void threePolygons(Canvas canvas) {
        distance = one_radius / 2;
        Path path = new Path();
        path.moveTo(center, center - distance);
        for (int i = 0; i < 7; i++) {
            path.lineTo((float) (center + Math.sin(Math.toRadians(360 / 7 * i)) * (one_radius - distance)), (float) (center - Math.cos(Math.toRadians(360 / 7 * i)) * (one_radius - distance)));
        }
        path.close();
        canvas.drawPath(path, three_paint);
    }

    /**
     * 绘制最内层多边形
     *
     * @param canvas
     */
    private void fourPolygons(Canvas canvas) {
        distance = one_radius / 2 + one_radius / 4;
        Path path = new Path();
        path.moveTo(center, getPaddingTop() + 2 * str_rect.height() + distance);
        for (int i = 0; i < 7; i++) {
            path.lineTo((float) (center + Math.sin(Math.toRadians(360 / 7 * i)) * (one_radius - distance)), (float) (center - Math.cos(Math.toRadians(360 / 7 * i)) * (one_radius - distance)));
        }
        path.close();
        canvas.drawPath(path, three_paint);
    }

    /**
     * 绘制中心线
     *
     * @param canvas
     */
    private void center(Canvas canvas) {
//绘制七边形中心线
        canvas.save();//保存当前状态
        canvas.rotate(0, center, center);
        float startY = getPaddingTop() + 2 * str_rect.height();
        float endY = center;
        float du = (float) (360 / 7 + 0.5);
        for (int i = 0; i < 7; i++) {
            canvas.drawLine(center, startY, center, endY, center_paint);
            canvas.rotate(du, center, center);

        }
        canvas.restore();//恢复之前状态
    }

    /**
     * 绘制等级进度
     *
     * @param canvas
     */
    private void PaintRank(Canvas canvas) {

        Path path = new Path();
        path.moveTo(center, getPaddingTop() + 2 * str_rect.height() + f1);                           //起始点
        path.lineTo((float) (center + Math.sin(Math.toRadians(360 / 7)) * (one_radius - f2)), (float) (getPaddingTop() + 2 * str_rect.height() + (one_radius) - Math.abs(Math.cos(Math.toRadians(360 / 7)) * (one_radius - f2))));
        path.lineTo((float) (center + Math.sin(Math.toRadians(360 / 7 + 360 / 7 / 2)) * (one_radius - f3)), (float) (Math.cos(Math.toRadians(360 / 7 + 360 / 7 / 2)) * (one_radius - f3)) + center);
        path.lineTo((float) (center + Math.sin(Math.toRadians(360 / 7 / 2)) * (one_radius - f4)), (float) (Math.cos(Math.toRadians(360 / 7 / 2)) * (one_radius - f4)) + center);
        path.lineTo((float) (center - Math.sin(Math.toRadians(360 / 7 / 2)) * (one_radius - f5)), (float) (Math.cos(Math.toRadians(360 / 7 / 2)) * (one_radius - f5)) + center);
        path.lineTo((float) (center - Math.sin(Math.toRadians(360 / 7 + 360 / 7 / 2)) * (one_radius - f6)), (float) (Math.cos(Math.toRadians(360 / 7 + 360 / 7 / 2)) * (one_radius - f6)) + center);
        path.lineTo((float) (center - Math.sin(Math.toRadians(360 / 7)) * (one_radius - f7)), (float) (getPaddingTop() + 2 * str_rect.height() + (one_radius) - Math.abs(Math.cos(Math.toRadians(360 / 7)) * (one_radius - f7))));
        path.close();
        canvas.drawPath(path, rank_Paint);
    }


    public void setValue1(float value) {
        f1 = one_radius - one_radius / 4 * value;
        invalidate();
    }

    public void setValue2(float value) {
        f2 = one_radius - one_radius / 4 * value;
        invalidate();
    }

    public void setValue3(float value) {
        f3 = one_radius - one_radius / 4 * value;
        invalidate();
    }

    public void setValue4(float value) {
        f4 = one_radius - one_radius / 4 * value;
        invalidate();
    }

    public void setValue5(float value) {
        f5 = one_radius - one_radius / 4 * value;
        invalidate();
    }

    public void setValue6(float value) {
        f6 = one_radius - one_radius / 4 * value;
        invalidate();
    }

    public void setValue7(float value) {
        f7 = one_radius - one_radius / 4 * value;
        invalidate();
    }


    public int dp_px(int values) {
        float density = getResources().getDisplayMetrics().density;
        return (int) (values * density + 0.5f);
    }
}
