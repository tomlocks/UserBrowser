package com.tomlockapps.userbrowser.picasso;/*
 * Copyright 2014 Julian Shen
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.squareup.picasso.Transformation;
import com.tomlockapps.userbrowser.R;

/**
 * Class responsible for Circle Transformation in Picasso.
 *
 * Created by julian on 13/6/21.
 * Modified by tomlo on 26.10.2016.
 */
public class CircleTransform implements Transformation {

    public static final int STORKE_WIDTH = 4;
    private Paint paintStroke;

    public CircleTransform(int color) {
        paintStroke = new Paint();

        paintStroke.setColor(color);
        paintStroke.setStyle(Paint.Style.STROKE);
        paintStroke.setAntiAlias(true);
        paintStroke.setStrokeWidth(STORKE_WIDTH);
    }

    @Override
    public Bitmap transform(Bitmap source) {
        int size = Math.min(source.getWidth(), source.getHeight());

        int x = (source.getWidth() - size) / 2;
        int y = (source.getHeight() - size) / 2;

        Bitmap squaredBitmap = Bitmap.createBitmap(source, x, y, size, size);
        if (squaredBitmap != source) {
            source.recycle();
        }

        Bitmap bitmap = Bitmap.createBitmap(size, size, source.getConfig());

        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        BitmapShader shader = new BitmapShader(squaredBitmap, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP);
        paint.setShader(shader);
        paint.setAntiAlias(true);



        float r = size/2f;
        canvas.drawCircle(r, r, r, paint);

        canvas.drawCircle(r,r,r-STORKE_WIDTH/2, paintStroke);

        squaredBitmap.recycle();
        return bitmap;
    }

    @Override
    public String key() {
        return "circle";
    }
}