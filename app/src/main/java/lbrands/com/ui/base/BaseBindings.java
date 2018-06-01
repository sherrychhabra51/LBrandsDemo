package lbrands.com.ui.base;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;

import lbrands.com.GlideApp;

public class BaseBindings {
    @BindingAdapter({"imageUrl", "error"})
    public static void loadImage(ImageView view, String url, Drawable error) {
        GlideApp.with(view.getContext()).load(url).diskCacheStrategy(DiskCacheStrategy.RESOURCE).error(error).into(view);
    }
}
