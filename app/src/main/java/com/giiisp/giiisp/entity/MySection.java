package com.giiisp.giiisp.entity;

import com.chad.library.adapter.base.entity.SectionEntity;
import com.giiisp.giiisp.view.adapter.ClickEntity;

/**
 * https://github.com/CymChad/BaseRecyclerViewAdapterHelper
 */
public class MySection extends SectionEntity<ClickEntity> {
    private boolean isMore;
    private boolean isHeader;
    public MySection(boolean isHeader, String header, boolean isMroe) {
        super(isHeader, header);
        this.isMore = isMroe;
        this.isHeader = isHeader;
    }

    public MySection(ClickEntity t) {
        super(t);
    }

    public boolean isMore() {
        return isMore;
    }

    public boolean isHeader() {
        return isHeader;
    }

    public void setHeader(boolean header) {
        isHeader = header;
    }

    public void setMore(boolean mroe) {
        isMore = mroe;
    }
}
