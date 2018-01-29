package com.giiisp.giiisp.api;

/**
 */

public class UrlConstants {
    public static class RequestUrl {
        //                public static final String BASE_URL = "http://app.gogobamboo.com/bamboo/";
        //        public static final String BASE_URL = "http://101.201.49.42/bamboo/";
//                        public static final String BASE_URL = "https://192.168.1.155/giiisp/";

        //        public static final String BASE_URL = "https://47.93.23.62/giiisp/";
//        public static final String BASE_URL = "http://121.21.34.153:7777/jsp/";//z
        public static final String BASE_URL = "http://www.giiisp.cn/";//fhttp://www.wwfgzs.com/jsp/ ,http://www.giiisp.cn/ =101.201.57.201
        //http://47.104.97.60
        //        public static final String BASE_URL = "app.giiisp.com/";getWaitRecordPaperList.do
        //        public static final String MP3_URL = "http://o9kg05vzs.bkt.clouddn.com/";
        public static final String MP3_URL = "http://oq2xwecq0.bkt.clouddn.com/";

        //保存客户端设备类型
        public static final String SAVE_CLIENT_TYPE = "api/common/saveClientType.do";

        //发送手机验证码
        public static final String SEND_CODE = "api/common/sendCode.do";

        //用户注册
        public static final String USER_ENROLL = "api/common/registe.do";

        //检查指定手机号是否注册
        public static final String EXAMINE_PHONE = "api/common/isMobileExist.do";

        //修改重新绑定手机号码""
        public static final String USER_UPDATE_MOBILE = "api/user/updateMobile.do";

        //用户登录
        public static final String USER_LOGIN = "api/common/login.do";

        //用户注销
        public static final String USER_LOGOUT = "api/common/logout.do";

        //忘记密码
        public static final String UPDATE_PWD = "api/common/updatePWD.do";

        //重置密码
        public static final String USER_UPDATE_PWD = "api/user/updatePWD.do";

        //第三方登录
        public static final String LOGIN_WITH3RD = "api/common/loginWith3rd.do";

        //获取用户点赞、订阅、关注的相关内容
        public static final String USER_CATER = "api/cater/listOfUserCater.do";

        //获取我的订阅，赞和新消息数量
        public static final String USER_NUMS = "api/user/getUserNums.do";

        //更新个人信息接口
        public static final String UPDATE_USER_INFO = "api/user/updateUserInfo.do";

        //别名模糊查询
        public static final String USER_NICKNAME_INFO = "api/user/getListByNicknameLike.do";

        //获取个人信息接口
        public static final String USER_INFO = "api/user/info.do";

        // 获取用户头像接口
        public static final String USER_PORTRAIT = "api/user/getPortrait.do";

        //更新用户头像接口
        public static final String USER_UPDATE_PORTRAIT = "api/user/updatePortrait.do";

        //获取评论列表
        public static final String LIST_COMMENT = "api/post/listComment.do";

        //关注学者
        public static final String SAVE_FOLLOW_USER = "api/cater/saveFollowUser.do";

        //取消关注
        public static final String CANCEL_FOLLOW_USER = "api/cater/cancelFollowUser.do";

        //获取用户的粉丝列表
        public static final String LISTOF_USER_FOLLOWED = "api/cater/listOfUserFollowed.do";

        //获取用户关注的人的信息列表
        public static final String LISTOF_USER_FOLLOW = "api/cater/listOfUserFollow.do";

        //分享
        public static final String SAVE_SHARE = "api/cater/saveShare.do";

        //获取新消息数量
        public static final String NEW_NUM = "api/msg/getNewNum.do";

        //获取新消息列表
        public static final String MSG_LIST = "api/msg/list.do";

        //删除一条消息
        public static final String MSG_DEL = "api/msg/del.do";

        //获取消息详细信息
        public static final String MSG_DETAIL = "api/msg/detail.do";

        //发送公共消息
        public static final String SEND_SYS_MSG = "api/msg/sendSysMsg.do";

        //发送私有消息
        public static final String SEND_PROVIDE_MSG = "api/msg/sendProvideMsg.do";

        //获取七牛图片上传token
        public static final String QN_UPLOAD_TOKEN = "api/qn/getQNUploadToken.do";

        //保存用户反馈信息
        public static final String FEEDBACK_SAVE = "api/feedback/save.do";

        //提交并保存举报信息
        public static final String INFORM_SAVE = "api/inform/save.do";

        //用户协议页面
        public static final String AGREEMENT = "http://giiisp.com/terms_of_use.html";

        //分享链接
        public static final String WEB_PAGE = "http://gogobamboo.com/topic_detail.php?id=";

        //七牛服务器地址
        public static final String QN_ADDRESS = "http://oq2xwecq0.bkt.clouddn.com/";

        //        public static final String GET_SUBSCRIBE_FIELD= "api/user/myFollowedScholar.do";

        //获取我订阅的领域/关键字和推荐的领域/关键字
        public static final String GET_SUBSCRIBE_FIELD = "api/user/getMyArea.do";

        //取消订阅领域/关键字
        public static final String CANCEL_FOLLOW_TYPE = "api/cater/cancelFollowType.do";

        //获取我关注的学者
        public static final String GET_SCHOLARS_FOLLOWED = "api/common/MyFollowedScholar.do";

        //获取推荐的学者
        public static final String GET_SCHOLARS_RECOMMEND = "api/common/recommendScholar.do";

        //我的回答列表
        public static final String LIST_ANSWER = "api/post/listAnswer.do";

        //我的提问列表
        public static final String LIST_QUIZ = "api/post/listQuiz.do";

        //收藏点赞 论文或者图片
        public static final String SAVE_FOLLOW_PAPER_PICTURE = "api/cater/saveFollowPaperOrPicture.do";

        //取消收藏点赞 论文或者图片
        public static final String CANCEL_FOLLOW_PAPER_PICTURE = "api/cater/cancelFollowPaperOrPicture.do";

        //订阅领域/关键字
        public static final String SAVE_FOLLOW_TYPE = "api/cater/saveFollowType.do";

        //提问
        public static final String SAVE_QUIZ = "api/post/saveQuiz.do";

        //回答
        public static final String SAVE_ANSWER = "api/post/saveAnswer.do";

        //发表论文
        public static final String UPDATE_PAPER_BASE = "api/post/updatePaperBase.do";

        //保存论文
        public static final String SAVE_PAPER_BASE = "api/post/savePaperBase.do";

        //获取论文详细信息
        public static final String GET_PAPER_BASE = "api/post/getPaperBaseById.do";

        //获取论文对应的图片的问答列表
        public static final String GET_LIST_QA = "api/post/getListOfQuizAndAnswer.do";

        //获取论文文献列表
        public static final String GET_LIST_LITERATURE = "api/post/getListOfLiterature.do";

        //获取论文关键字列表
        public static final String GET_LIST_ANTISTOP = "api/post/getListOfAntistop.do";

        //获取待配音论文列表
        public static final String WAIT_RECORD_LIST = "api/post/getWaitRecordPaperList.do";

        //获取用户已发表论文列表
        public static final String PUBLISH_PAPER_LIST = "api/post/getPublishPaperList.do";

        //我收藏的论文/综述列表
        public static final String LIST_FOLLOWED_PAPER = "api/cater/listOfMyFollowedPaper.do";

        //用户订阅的论文列表
        public static final String LIST_FOLLOW_PAPER = "api/cater/listOfMyFollowPaper.do";

        //获取用户的履历
        public static final String GET_USER_RECORD = "api/user/getUserRecord.do";

        //综述列表
        public static final String LIST_SUMMARIZE = "api/post/listOfSummarize.do";

        //论文列表
        public static final String LIST_NEW_PAPER = "api/post/listOfNewPaper.do";

        //学者列表
        public static final String LIST_SCHOLAR = "api/user/listScholar.do";

        //首页
        public static final String HOME_PAGE = "api/post/homePage.do";

        //首页搜索
        public static final String HOME_SEARCH = "api/common/homeSearch.do";

        //我的搜索和其他人的搜索关键词
        public static final String SEARCH_HISTORY = "api/common/searchHistory.do";

        //清空我的搜索历史
        public static final String CLEAN_SEARCH_HISTORY = "api/common/cleanMySearchHistory.do";

        //提问提示列表
        public static final String QUIZ_HINT_LIST = "api/common/quizHintList.do";

        //保存录音
        public static final String SAVE_RECORD = "api/common/saveRecord.do";

        //更新APP
        public static final String APP_INFO = "api/post/getAppInfo.do";

        //邮箱认证
        public static final String AUTHEN_USER = "api/user/sendEmail.do";
        //添加教育经历
        public static final String ADD_EXP = "api/user/addExp.do";
        public static final String UPDATE_EXP = "api/user/updateExp.do";
        public static final String DELETE_EXP = "api/user/delExp.do";

        //选择证明人列表
        public static final String SELECT_USER = "api/user/selectUser.do";
        //用户认证
        public static final String USER_AUTHEN = "api/user/userAuthen.do";

        /**
         * 验证论文密码 根据homePage 返回的isEncrypt 判断 0:需要验证，1：不需要验证密码
         * {
         "result": 1,
         "code": "",
         "info": "操作成功！"
         }
         *
         * */
        public static final String CHECK_PAPERPWD = "/jsp/api/post/checkPaperPwd.do";


    }

    public static class RequestPrams {
    }


}
