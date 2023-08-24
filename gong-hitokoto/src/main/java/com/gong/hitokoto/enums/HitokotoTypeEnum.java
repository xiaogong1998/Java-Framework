package com.gong.hitokoto.enums;

import com.gong.core.annotation.BaseEnum;

public enum HitokotoTypeEnum implements BaseEnum<String> {

    Animation("a", "动画"),
    Caricature("b", "漫画"),
    Game("c", "游戏"),
    Literature("d", "文学"),
    Original("e", "原创"),
    FromTheWeb("f", "来自网络"),
    Other("g", "其他"),
    FilmAndTelevision("h", "影视"),
    Poetry("i", "诗词"),
    NetEaseCloud("j", "网易云"),
    Philosophy("k", "哲学"),
    ShakeSmart("l", "抖机灵");

    private final String value;

    private final String desc;

    HitokotoTypeEnum(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public BaseEnum<String> findByValue(String value) {
        for (HitokotoTypeEnum hitokotoTypeEnum : values()) {
            if(hitokotoTypeEnum.value.equals(value)){
                return hitokotoTypeEnum;
            }
        }
        return null;
    }

    public String getDesc() {
        return desc;
    }

}
