package com.springrest.springrest.service;

import java.util.List;

import com.springrest.springrest.dto.SongDto;
import com.springrest.springrest.interfaces.ISongServiceInterface;
import com.springrest.springrest.model.Songs;
import com.springrest.springrest.model.Users;
import com.springrest.springrest.repository.ISongRepository;
import com.springrest.springrest.repository.IUsersRepository;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SongService implements ISongServiceInterface {
	@Autowired
	private ISongRepository iSongRepository;

	@Autowired
	private IUsersRepository iUsersRepository;

	@Override
	public JSONObject addSong(SongDto songs, String userName, String password) {

		JSONObject jsonObject = new JSONObject();

		List<Users> getUser = iUsersRepository.findByUP(userName, password);
		if (!getUser.isEmpty() && getUser.get(0).getRole().getRoleId() == 1) {
			Songs newSong = new Songs();
			newSong.setSongName(songs.getSongName());
			newSong.setSinger(songs.getSinger());
			newSong.setMovie(songs.getMovie());
			newSong.setAlbum(songs.getAlbum());
			newSong.setDuration(songs.getDuration());
			iSongRepository.save(newSong);
			jsonObject.put("status", "Song saved");
			return jsonObject;
		} else if (getUser.isEmpty()) {
			jsonObject.put("errormessage", "Wrong userName or password   - " + userName + "," + password);
			return jsonObject;
		} else {
			jsonObject.put("errormessage", "Users are not allowed to add song");
			return jsonObject;
		}
	}

	@Override
	public JSONObject updateSong(SongDto songs, String userName, String password, Integer songId) {
		JSONObject jsonObject = new JSONObject();
		List<Users> getUser = iUsersRepository.findByUP(userName, password);
		Songs newSong = iSongRepository.findById(songId).get();
		if (!getUser.isEmpty() && getUser.get(0).getRole().getRoleId() == 1) {
			if (newSong != null) {
				newSong.setSongName(songs.getSongName());
				newSong.setSinger(songs.getSinger());
				newSong.setMovie(songs.getMovie());
				newSong.setAlbum(songs.getAlbum());
				newSong.setDuration(songs.getDuration());
				iSongRepository.save(newSong);
				jsonObject.put("status", "Song saved");
				return jsonObject;
			} else {
				jsonObject.put("errormessage", "Song not found with id - " + songId);
			}
		} else if (getUser.isEmpty()) {
			jsonObject.put("errormessage", "Wrong userName or password   - " + userName + "," + password);
			return jsonObject;
		} else {
			jsonObject.put("errormessage", "Users are not allowed to update song");
			return jsonObject;
		}
		return jsonObject;
	}

	@Override
	public JSONArray getSongs() {
		List<Songs> all = iSongRepository.findAll();
		JSONArray jsonArray = new JSONArray();
		for (Songs ele : all) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("songId", ele.getSongId());
			jsonObject.put("songName", ele.getSongName());
			jsonObject.put("singer", ele.getSinger());
			jsonObject.put("movie", ele.getMovie());
			jsonObject.put("album", ele.getAlbum());
			jsonObject.put("duration", ele.getDuration());
			jsonArray.put(ele.getSongId(), jsonObject);
		}
		return jsonArray;
	}

	@Override
	public JSONObject deleteSong(String userName, String password, Integer songId) {
		JSONObject jsonObject = new JSONObject();
		List<Users> getUser = iUsersRepository.findByUP(userName, password);
		Songs newSong = iSongRepository.findById(songId).get();
		if (!getUser.isEmpty() && getUser.get(0).getRole().getRoleId() == 1) {
			if (newSong != null) {
				iSongRepository.deleteSong(songId);
				jsonObject.put("status", "Song deleted");
				return jsonObject;
			} else {
				jsonObject.put("errormessage", "Song not found with id - " + songId);
			}
		} else if (getUser.isEmpty()) {
			jsonObject.put("errormessage", "Wrong userName or password   - " + userName + "," + password);
			return jsonObject;
		} else {
			jsonObject.put("errormessage", "User not allowed to delete song");
			return jsonObject;
		}
		return jsonObject;
	}

}
