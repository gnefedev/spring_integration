package com.gnefedev.integration.persistence;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.*;

/**
 * Created by gerakln on 31.01.16.
 */
@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class LoggedMessage {
    @GeneratedValue
    @Id
    @XmlAttribute
    private int id;

    @XmlElement
    private String message;

    public int getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
