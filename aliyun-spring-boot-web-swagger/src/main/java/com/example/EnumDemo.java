package com.example;

public class EnumDemo {
    /**
     * 应用内共享操作类型
     */
    enum ShareOperateType {
        SHARE("share", "应用内分享"),
        UNSHARED("unshared", "取消应用内分享"),
        DOWNLOAD("download", "应用内分享下载");
        /**
         * 编码
         */
        private final String code;

        /**
         * 名称
         */
        private final String name;

        /**
         * 全参构造函数
         * @param code 编码
         * @param name 名称
         */
        ShareOperateType(String code, String name) {
            this.code = code;
            this.name = name;
        }

        /**
         * 根据code获取name
         * @param code 编码
         * @return 名称
         */
        public static String getNameByCode(String code){
            for(ShareOperateType shareOperateType:ShareOperateType.values()){
                if(code.equals(shareOperateType.getCode())){
                    return shareOperateType.getName();
                }
            }
            return  null;
        }

        public String getCode() {
            return code;
        }

        public String getName() {
            return name;
        }
    }
}
