/*
package com.giiisp.giiisp.utils;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool;
import com.bumptech.glide.load.engine.cache.DiskCache;
import com.bumptech.glide.load.engine.cache.DiskLruCacheFactory;
import com.bumptech.glide.load.engine.cache.ExternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.load.engine.cache.MemorySizeCalculator;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.module.GlideModule;

import java.io.InputStream;

*/
/**
 * AnswerEntity {@link GlideModule} implementation to replace Glide'MyScholarEntity default
 * {@link java.net.HttpURLConnection} based {@link com.bumptech.glide.load.model.ModelLoader} with an OkHttp based
 * {@link com.bumptech.glide.load.model.ModelLoader}.
 * <p>
 * <p>
 * If you're using gradle, you can include this module simply by depending on the aar, the module will be merged
 * in by manifest merger. For other build systems or for more more information, see
 * {@link GlideModule}.
 * </p>
 *//*

public class OkHttpGlideModule implements GlideModule {
    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        // Do nothing.
        MemorySizeCalculator calculator = new MemorySizeCalculator(context);
        int memoryCacheSize = calculator.getMemoryCacheSize();

        DiskLruCacheFactory factory = new ExternalCacheDiskCacheFactory(context, "image_cache", DiskCache.Factory.DEFAULT_DISK_CACHE_SIZE);
        builder.setDiskCache(factory);

        LruResourceCache cache = new LruResourceCache((int) (1.2 * memoryCacheSize));
        builder.setMemoryCache(cache);

        int customBitmaPoolSize = (int) (1.2 * calculator.getBitmapPoolSize());
        builder.setBitmapPool(new LruBitmapPool(customBitmaPoolSize));
    }

    @Override
    public void registerComponents(Context context, Glide glide) {
        glide.register(GlideUrl.class, InputStream.class, new OkHttpUrlLoader.Factory());
    }
}
*/
