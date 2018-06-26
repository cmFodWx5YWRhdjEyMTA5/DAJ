package com.tinnovat.app.daj.Circlelist;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.LinearLayout;

public class MatrixView extends LinearLayout {
    private int h = 0;

    public MatrixView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setParentHeight(int height) {
        h = height;
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        canvas.save();
        int top = getTop();

        //float rotate =0;
        float rotate = calculateAngel(top, h);
        float scale = calcuylateScale(top, h);

        Matrix m = canvas.getMatrix();
        m.preTranslate(-2 / getWidth(), -2 / getHeight());
        Log.e("preTrans",""+-2 / getWidth());
        m.postScale(scale, scale);
        m.postTranslate(2 / getWidth(), 2 / getHeight());
        m.postRotate(rotate);
        canvas.concat(m);
        super.dispatchDraw(canvas);
        canvas.restore();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private float calculateAngel(int top, int h) {
        //Angel
        float result = 0f;
        float test = 360f;
        float fullAngelFactor = 190f;
        if (top < h / 2f) {
            result = (top - (h / 2f)) / (h / 2f) * fullAngelFactor;
        } else if (top > h / 2f) {
            result = (top - (h / 2f)) / (h / 2f) * fullAngelFactor;
        }
        Log.e("result_1",""+result);
        return result;
    }

    private float calcuylateScale(int top, int h) {
        //Size
        float result = 0f;
        float test = 1f;

        float fullScaleFactor = 1;
        result = (1f - 1f/2f*Math.abs((top - h / 2f)) / (h / 2f)) * fullScaleFactor;
        return result;

    }
}
