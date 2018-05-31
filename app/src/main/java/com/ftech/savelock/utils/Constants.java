package com.ftech.savelock.utils;

import com.ftech.savelock.animation.AccordionTransformer;
import com.ftech.savelock.animation.BackgroundToForegroundTransformer;
import com.ftech.savelock.animation.CubeInTransformer;
import com.ftech.savelock.animation.CubeOutTransformer;
import com.ftech.savelock.animation.DefaultTransformer;
import com.ftech.savelock.animation.DepthPageTransformer;
import com.ftech.savelock.animation.FlipHorizontalTransformer;
import com.ftech.savelock.animation.FlipVerticalTransformer;
import com.ftech.savelock.animation.ForegroundToBackgroundTransformer;
import com.ftech.savelock.animation.RotateDownTransformer;
import com.ftech.savelock.animation.RotateUpTransformer;
import com.ftech.savelock.animation.ScaleInOutTransformer;
import com.ftech.savelock.animation.StackTransformer;
import com.ftech.savelock.animation.TabletTransformer;
import com.ftech.savelock.animation.ZoomInTransformer;
import com.ftech.savelock.animation.ZoomOutSlideTransformer;
import com.ftech.savelock.animation.ZoomOutTranformer;

import java.util.ArrayList;

/**
 * Created by nilax on 26/10/16.
 */

public class Constants {

    public static final String NAV_HEADER_COLOR_TAG = "nav_header_color";
    public static final ArrayList<TransformerItem> TRANSFORM_CLASSES;

    static {
        TRANSFORM_CLASSES = new ArrayList<>();
        TRANSFORM_CLASSES.add(new TransformerItem(DefaultTransformer.class));
        TRANSFORM_CLASSES.add(new TransformerItem(AccordionTransformer.class));
        TRANSFORM_CLASSES.add(new TransformerItem(BackgroundToForegroundTransformer.class));
        TRANSFORM_CLASSES.add(new TransformerItem(CubeInTransformer.class));
        TRANSFORM_CLASSES.add(new TransformerItem(CubeOutTransformer.class));
        TRANSFORM_CLASSES.add(new TransformerItem(DepthPageTransformer.class));
        TRANSFORM_CLASSES.add(new TransformerItem(FlipHorizontalTransformer.class));
        TRANSFORM_CLASSES.add(new TransformerItem(FlipVerticalTransformer.class));
        TRANSFORM_CLASSES.add(new TransformerItem(ForegroundToBackgroundTransformer.class));
        TRANSFORM_CLASSES.add(new TransformerItem(RotateDownTransformer.class));
        TRANSFORM_CLASSES.add(new TransformerItem(RotateUpTransformer.class));
        TRANSFORM_CLASSES.add(new TransformerItem(ScaleInOutTransformer.class));
        TRANSFORM_CLASSES.add(new TransformerItem(StackTransformer.class));
        TRANSFORM_CLASSES.add(new TransformerItem(TabletTransformer.class));
        TRANSFORM_CLASSES.add(new TransformerItem(ZoomInTransformer.class));
        TRANSFORM_CLASSES.add(new TransformerItem(ZoomOutSlideTransformer.class));
        TRANSFORM_CLASSES.add(new TransformerItem(ZoomOutTranformer.class));
    }
}
