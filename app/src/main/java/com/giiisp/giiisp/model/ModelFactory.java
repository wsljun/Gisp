package com.giiisp.giiisp.model;



/**
 *
 * Created by Administrator on 2017/5/3.
 */
public class ModelFactory {

    private static BaseModel baseModel;

    public static BaseModel getBaseModel() {
        BaseModel model;
        if (baseModel == null) {
            baseModel = new BaseModel();
        }
        model = baseModel;
        return model;
    }


}
