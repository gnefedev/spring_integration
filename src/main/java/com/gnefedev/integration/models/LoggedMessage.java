package com.gnefedev.integration.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.*;

/**
 * Created by gerakln on 31.01.16.
 */
@Entity
public class LoggedMessage {
    @GeneratedValue
    @Id
    private int id;

    private String message;

    private String error;

    private int tryCount = 0;

    public LoggedMessage incrementTry() {
        setTryCount(getTryCount() + 1);
        return this;
    }

    public int getTryCount() {
        return tryCount;
    }

    private void setTryCount(int tryCount) {
        this.tryCount = tryCount;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    private boolean success = false;

    public boolean isSuccess() {
        return success;
    }

    public LoggedMessage setSuccess(boolean success) {
        this.success = success;
        return this;
    }

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
