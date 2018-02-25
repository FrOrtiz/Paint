package com.example.dam.paintdef;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class PaintView extends View {

    static int color = Color.BLACK;
    static Paint.Style style = Paint.Style.STROKE;
    static float strokeWidth = 5;
    private final Paint paint = new Paint();
    private final Path path = new Path();
    private static Bitmap bitmap;
    private static Canvas backgroundCanvas;
    private float xIni, yIni, xFinal, yFinal, radius;

    public PaintView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                xIni = x;
                yIni = y;
                switch (MainActivity.modo) {
                    case LIBRE:
                        xFinal = xIni;
                        yFinal = yIni;
                        path.moveTo(xIni, yIni);
                        break;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                switch (MainActivity.modo) {
                    case LIBRE:
                        xIni = xFinal;
                        yIni = yFinal;
                        path.quadTo(xIni, yIni, (x + xIni) / 2, (y + yIni) / 2);
                        break;
                }
                xFinal = x;
                yFinal = y;
                switch (MainActivity.modo) {
                    case CIRCULO:
                        radius = (float) Math.sqrt(Math.pow(xFinal - xIni, 2) + Math.pow(yFinal - yIni, 2));
                        break;
                }
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                switch (MainActivity.modo) {
                    case LIBRE:
                        xIni = xFinal;
                        yIni = yFinal;
                        break;
                }
                xFinal = x;
                yFinal = y;
                switch (MainActivity.modo) {
                    case LINEA:
                        backgroundCanvas.drawLine(xIni, yIni, xFinal, yFinal, paint);
                        break;
                    case RECTANGULO:
                        backgroundCanvas.drawRect(xIni, yIni, xFinal, yFinal, paint);
                        break;
                    case CIRCULO:
                        backgroundCanvas.drawCircle(xIni, yIni, radius, paint);
                        break;
                    case LIBRE:
                        backgroundCanvas.drawPath(path, paint);
                        path.reset();
                        break;
                }
                invalidate();
                break;
        }
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        paint.setAntiAlias(true);
        paint.setColor(color);
        paint.setStyle(style);
        paint.setStrokeWidth(strokeWidth);
        canvas.drawBitmap(bitmap, 0, 0, null);
        switch (MainActivity.modo) {
            case LINEA:
                canvas.drawLine(xIni, yIni, xFinal, yFinal, paint);
                break;
            case RECTANGULO:
                canvas.drawRect(xIni, yIni, xFinal, yFinal, paint);
                break;
            case CIRCULO:
                canvas.drawCircle(xIni, yIni, radius, paint);
                break;
            case LIBRE:
                paint.setStyle(Paint.Style.STROKE);
                canvas.drawPath(path, paint);
                break;
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        bitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        backgroundCanvas = new Canvas(bitmap);
    }

    public static int getColor(){
        return color;
    }

    public static Paint.Style getStyle() {
        return style;
    }
}
