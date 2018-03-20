package com.suxiunet.data.entity.user;

import com.suxiunet.data.entity.base.BasicDataEntity;

import java.io.Serializable;
import java.util.List;

/**
 * author : chenzhi
 * time   : 2018/03/06
 * desc   :
 */
public class HomeEntity extends BasicDataEntity {

    private String notice;
    private List<BannerDtosBean> bannerDtos;

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public List<BannerDtosBean> getBannerDtos() {
        return bannerDtos;
    }

    public void setBannerDtos(List<BannerDtosBean> bannerDtos) {
        this.bannerDtos = bannerDtos;
    }

    public static class BannerDtosBean implements Serializable {
        /**
         * id : 1
         * isShow : Y
         * category : 首页
         * title : 
         * content : 
         * img : banner1.png
         * createTime : 1510461576000
         */

        private String id;
        private String isShow;
        private String category;
        private String title;
        private String content;
        private String img;
        private long createTime;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getIsShow() {
            return isShow;
        }

        public void setIsShow(String isShow) {
            this.isShow = isShow;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }
    }
}
