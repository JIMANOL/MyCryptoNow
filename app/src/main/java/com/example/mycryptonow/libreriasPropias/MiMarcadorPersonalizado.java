package com.example.mycryptonow.libreriasPropias;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Typeface;

import com.github.mikephil.charting.components.IMarker;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Utils;

import java.text.NumberFormat;

public class MiMarcadorPersonalizado implements IMarker {
    private Entry mEntry;
    private NumberFormat numberFormat = NumberFormat.getCurrencyInstance();

    @Override
    public MPPointF getOffset() {
        return null;
    }

    @Override
    public MPPointF getOffsetForDrawingAtPoint(float posX, float posY) {
        return null;
    }

    @Override
    public void refreshContent(Entry e, Highlight highlight) {
        this.mEntry = e;
    }

    @Override
    public void draw(Canvas canvas, float posX, float posY) {
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        // dibuja un triángulo isósceles inverso
        Path path = new Path();
        path.moveTo(posX, posY - Utils.convertDpToPixel(5));
        path.lineTo(posX - Utils.convertDpToPixel(8), posY - Utils.convertDpToPixel(18));
        path.lineTo(posX + Utils.convertDpToPixel(8), posY - Utils.convertDpToPixel(18));
        path.close();
        canvas.drawPath(path, paint);
        // dibujar rectángulo
        RectF rect = new RectF(
                posX - Utils.convertDpToPixel(38),
                posY - Utils.convertDpToPixel(41),
                posX + Utils.convertDpToPixel(38),
                posY - Utils.convertDpToPixel(17));
        canvas.drawRect(rect, paint);
        // Dibuja texto, centrado en el marco rectangular
        if (mEntry != null) {
            String str = numberFormat.format(mEntry.getY());
            Paint textPaint = new Paint();
            textPaint.setColor(Color.WHITE);
            textPaint.setTextSize(Utils.convertDpToPixel(10));
            textPaint.setTypeface(Typeface.DEFAULT_BOLD);
            canvas.drawText(str,
                    // x = rectángulo izquierda coordenada + (rectángulo ancho-ancho de texto) / 2
                    rect.left + (Utils.convertDpToPixel(70) - Utils.calcTextWidth(textPaint, str)) / 2,
                    // y = coordenada del rectángulo + (altura del rectángulo-altura del texto) / 2
                    rect.bottom - (Utils.convertDpToPixel(24) - Utils.calcTextHeight(textPaint, str)) / 2,
                    textPaint);
        }
    }

}
