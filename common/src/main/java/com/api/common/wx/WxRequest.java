package com.api.common.wx;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
 * Created by Administrator on 2019/6/13.
 */
//
@JacksonXmlRootElement(localName = "xml")
public class WxRequest {
    @JacksonXmlProperty(localName = "ToUserName")
    private String toUserName;    //	是接收方（公众号）的user name
    @JacksonXmlProperty(localName = "FromUserName")
    private String fromUserName; //	是 	发送方（微信用户）的user name
    @JacksonXmlProperty(localName = "CreateTime")
    private String createTime;    //	是消息创建时间，消息后台生成
    @JacksonXmlProperty(localName = "MsgType")
    private String msgType;        //是 	消息类型：device_event
    @JacksonXmlProperty(localName = "Event")
    private String event;        //	是 	事件类型，取值为bind/unbind bind：绑定设备 unbind：解除绑定
    @JacksonXmlProperty(localName = "Content")
    private String content;        //是 	当Event为bind时，Content字段存放二维码中

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
