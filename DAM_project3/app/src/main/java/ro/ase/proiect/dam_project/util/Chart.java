package ro.ase.proiect.dam_project.util;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Chart  extends View {
    private Map<String, Long> source;
    private Paint paint;

    public Chart(Context context, Map<String, Long> source) {
        super(context);
        this.source = source;
        this.paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        this.paint.setColor(Color.BLACK);
    }

    public Chart(Context context) {
        super(context);
        this.paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        this.paint.setColor(Color.BLACK);
    }



    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (source != null && source.size() != 0) {
            //distante fata de margine
            float paddW = (float) (canvas.getWidth() * 0.1);
            float paddH = (float) (canvas.getHeight() * 0.1);
            //calculam lungimea si inaltimea disponibila
            float availableWidth = canvas.getWidth() - 2 * paddW;
            float availableHeight = canvas.getHeight() - 2 * paddH;
            //calculam latimea unei bare
            float widthOfElement = availableWidth / source.size();
            //calculam valoarea maxima
            double max = getMaxim();

            //trasare axe
            paint.setStrokeWidth((float) (availableWidth * 0.01));
            canvas.drawLine(paddW,
                    paddH,
                    paddW,
                    paddH + availableHeight,
                    paint);
            canvas.drawLine(paddW,
                    paddH + availableHeight,
                    paddW + availableWidth,
                    paddH + availableHeight,
                    paint);

            List<String> labels = new ArrayList<>(source.keySet());
            Random r = new Random();
            for (int i = 0; i < labels.size(); i++) {
                int color = Color.argb(100,
                        1 + r.nextInt(254),
                        1 + r.nextInt(254),
                        1 + r.nextInt(254));
                paint.setColor(color);

                float x1 = paddW + i * widthOfElement;
                float y1 = (float) (paddH + (1 - source
                        .get(labels.get(i)) / max)
                        * availableHeight);
                float x2 = x1 + widthOfElement;
                float y2 = paddH + availableHeight;

                canvas.drawRect(x1, y1, x2, y2, paint);

                float x = x1 + widthOfElement / 2;
                float y = paddH / 2 + availableHeight;
                paint.setColor(Color.BLACK);
                paint.setTextSize((float) (availableHeight * 0.05));
                canvas.rotate(270, x, y);
                canvas.drawText(labels.get(i) + " - "
                                + source.get(labels.get(i)),
                        x, y, paint);
                canvas.rotate(-270, x, y);
            }
        }
    }

    private double getMaxim() {
        double max = 0;

        for (double d : source.values()) {
            if (d > max) {
                max = d;
            }
        }

        return max;
    }


}
