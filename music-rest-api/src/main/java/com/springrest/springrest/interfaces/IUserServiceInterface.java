package com.springrest.springrest.interfaces;

import com.springrest.springrest.dto.LogInDto;
import com.springrest.springrest.dto.UserDto;

import org.json.JSONArray;
import org.json.JSONObject;

public interface IUserServiceInterface {
    public JSONObject addUser(UserDto userDto, String me);
    public JSONObject logIn(LogInDto logInDto);
    public JSONObject updateUser(UserDto userDto);
    public JSONObject deleteUser(String userName, String password);
    public JSONObject getUser(Integer userId);
    public JSONObject addSongInMyPlayList(String userName, String password, Integer songId , String command);
    public JSONArray getMyPlayList(Integer user_id);
    public JSONObject deleteFromMyPlayList(String userName, String password, Integer songId, String command);
}
