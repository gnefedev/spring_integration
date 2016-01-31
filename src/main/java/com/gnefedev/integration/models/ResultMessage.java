package com.gnefedev.integration.models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by gerakln on 31.01.16.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ResultMessage {
    @XmlElement
    private String message;
    @XmlElement
    private String name;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
