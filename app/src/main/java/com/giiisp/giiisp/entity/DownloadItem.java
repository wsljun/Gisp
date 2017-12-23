package com.giiisp.giiisp.entity;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import io.reactivex.disposables.Disposable;
import zlc.season.rxdownload2.entity.DownloadRecord;

/**
 * Author: Season(ssseasonnn@gmail.com)
 * Date: 2016/10/28
 * Time: 09:30
 * FIXME
 */
public class DownloadItem implements MultiItemEntity {
    public Disposable disposable;
    public DownloadRecord record;
    private int itemType;


    @Override
    public int getItemType() {
        return itemType;
    }
}
