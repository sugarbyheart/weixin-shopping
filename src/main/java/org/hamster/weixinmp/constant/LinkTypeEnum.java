package org.hamster.weixinmp.constant;


/**
 * @author grossopaforever@gmail.com
 * @version Jan 4, 2014
 *
 */
public enum LinkTypeEnum {
    Letian(LinkType.Letian),
    Xinluo(LinkType.Xinluo);

    LinkTypeEnum(final String text) {
        this._text = text;
    }

    private final String _text;

    @Override
    public String toString() {
        return _text;
    }

    public static LinkTypeEnum inst(String strVal) {
        for (LinkTypeEnum type : LinkTypeEnum.values()) {
            if (type.toString().equalsIgnoreCase(strVal)) {
                return type;
            }
        }
        return null;
    }
}
