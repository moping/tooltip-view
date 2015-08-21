package com.venmo.view;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.view.View;

import static android.graphics.Path.Direction;

class BottomArrowLocation implements ArrowLocation {

    @Override
    public void configureDraw(TooltipView view, Canvas canvas) {
        view.setTooltipPath(new Path());
        RectF rectF = new RectF(canvas.getClipBounds());
        rectF.bottom -= view.getArrowHeight();
        view.getTooltipPath().addRoundRect(rectF, view.getCornerRadius(), view.getCornerRadius(), Direction.CW);

        float middle = rectF.width() / 2;
        if (view.getAnchoredViewId() != View.NO_ID) {
            View anchoredView = ((View) view.getParent()).findViewById(view.getAnchoredViewId());
            middle += anchoredView.getX() + anchoredView.getWidth() / 2 - view.getX() - view.getWidth() / 2;
        }
        view.getTooltipPath().moveTo(middle, view.getHeight());
        int arrowDx = view.getArrowWidth() / 2;
        view.getTooltipPath().lineTo(middle - arrowDx, rectF.bottom);
        view.getTooltipPath().lineTo(middle + arrowDx, rectF.bottom);
        view.getTooltipPath().close();

        view.setPaint(new Paint(Paint.ANTI_ALIAS_FLAG));
        view.getTooltipPaint().setColor(view.getTooltipColor());
    }
}