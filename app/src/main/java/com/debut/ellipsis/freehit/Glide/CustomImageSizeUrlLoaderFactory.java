package com.debut.ellipsis.freehit.Glide;

import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.ModelCache;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.model.ModelLoaderFactory;
import com.bumptech.glide.load.model.MultiModelLoaderFactory;

import java.io.InputStream;

class CustomImageSizeUrlLoaderFactory implements ModelLoaderFactory<CustomImageSizeModel, InputStream> {
    private final ModelCache<CustomImageSizeModel, GlideUrl> modelCache = new ModelCache<>(500);

    @Override
    public ModelLoader<CustomImageSizeModel, InputStream> build(MultiModelLoaderFactory multiFactory) {
        ModelLoader<GlideUrl, InputStream> modelLoader = multiFactory.build(GlideUrl.class, InputStream.class);
        return new CustomImageSizeUrlLoader(modelLoader, modelCache);
    }

    @Override
    public void teardown() {

    }
}