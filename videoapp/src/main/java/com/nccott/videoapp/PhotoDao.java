package com.nccott.videoapp;

/**
 * Created by Lyndon.Li on 2018/1/9.
 *
 "path":"http://122.70.151.186:8787/jsp/static/upload/paper/pic/88f8820e-3f71-477b-a224-9f7721bf16cb/lunwen/915043d8-2ccd-40b4-9d91-e1049a87a427.gif",
 "version":"0",
 "type":"3",
 "order":"0",

 "path":"http://122.70.151.186:8787/jsp/static/upload/paper/pic/88f8820e-3f71-477b-a224-9f7721bf16cb/lunwen/57498887-210e-4ad7-9a8c-fa9165e7eb4e.png",
 "version":"0",
 "type":"1",
 "order":"1",

 "id":"927e7c48-6458-40dd-aaaa-d1280dc3783a",
 "path":"http://122.70.151.186:8787/jsp/static/upload/paper/pic/88f8820e-3f71-477b-a224-9f7721bf16cb/lunwen/3fa5ab14-5dd9-4e76-ba6c-dc5d06a4377c.mp4",
 "version":"0",
 "type":"2",
 "order":"3",




 */

public class PhotoDao {

    private String type;
    private String path;

    public static final String GifPath = "http://122.70.151.186:8787/jsp/static/upload/paper/pic/88f8820e-3f71-477b-a224-9f7721bf16cb/lunwen/915043d8-2ccd-40b4-9d91-e1049a87a427.gif";
    public static final String PngPath = "http://122.70.151.186:8787/jsp/static/upload/paper/pic/88f8820e-3f71-477b-a224-9f7721bf16cb/lunwen/57498887-210e-4ad7-9a8c-fa9165e7eb4e.png";
//    public static  final String Mp4Path = "http://122.70.151.186:8787/jsp/static/upload/paper/pic/88f8820e-3f71-477b-a224-9f7721bf16cb/lunwen/3fa5ab14-5dd9-4e76-ba6c-dc5d06a4377c.mp4"
    public static  final String Mp4Path = "http://mirror.aarnet.edu.au/pub/TED-talks/911Mothers_2010W-480p.mp4"
            ;

    public PhotoDao(){

    }

    /**
     * @param type
     * @param path
     */
    public PhotoDao(String type, String path) {
        this.type = type;
        this.path = path;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
