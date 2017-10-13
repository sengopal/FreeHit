package com.debut.ellipsis.freehit.Glide;


import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;

import java.io.InputStream;

@GlideModule
public class CustomImageSizeGlideModule extends AppGlideModule {
    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        // nothing to do here
    }

    @Override
    public void registerComponents(Context context, Glide glide, Registry registry) {
        registry.append(CustomImageSizeModel.class, InputStream.class, new CustomImageSizeUrlLoaderFactory());
    }
}
