package com.springrest.springrest.model;

import org.json.JSONObject;

public interface IStatusInterface {
    public Status setStatus(JSONObject jsonObject);
    public int saveSatus(Status newStatus);
}
