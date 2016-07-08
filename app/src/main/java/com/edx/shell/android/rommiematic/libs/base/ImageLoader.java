package com.edx.shell.android.rommiematic.libs.base;

import android.widget.ImageView;

public interface ImageLoader {
    void load(ImageView imageView, String url);
    void setOnFinishedImageLoadingListener(Object listener);
}
