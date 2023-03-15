package com.springrest.springrest.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.springrest.springrest.dto.LogInDto;
import com.springrest.springrest.dto.UserDto;
import com.springrest.springrest.interfaces.IUserServiceInterface;
import com.springrest.springrest.model.Songs;
import com.springrest.springrest.model.Users;
import com.springrest.springrest.repository.IRoleRepository;
import com.springrest.springrest.repository.ISongRepository;
import com.springrest.springrest.repository.IStatusRepository;
import com.springrest.springrest.repository.IUsersRepository;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersService implements IUserServiceInterface {
	@Autowired
	private IUsersRepository iUsersRepository;

	@Autowired
	IStatusRepository iStatusRepository;

	@Autowired
	private IRoleRepository iRoleRepository;
	@Autowired
	private ISongRepository iSongRepository;

	@Override
	public JSONObject addUser(UserDto userDto, String currentUser) {
		JSONObject jsonObject = new JSONObject();
		Users user = new Users();

		List<Users> alreadyPresantUser = iUsersRepository.FindByUserName(userDto.getUserName());
		if (!alreadyPresantUser.isEmpty()) {
			jsonObject.put("errorMessage", "User is already present with userName - " + userDto.getUserName());
			return jsonObject;
		}

		if (!iRoleRepository.findById(userDto.getRoleId()).isPresent()) {
			jsonObject.put("errorMessage", "No role is present with roleId " + userDto.getRoleId());
		}

		if (userDto.getRoleId() == 1 && currentUser.equals("admin")) {
			user.setRole(iRoleRepository.findById(userDto.getRoleId()).get());
		} else if (userDto.getRoleId() == 1 && currentUser.equals("user")) {
			jsonObject.put("errorMessage", "User cannot have admin role, please select another role (recommended 2) ");
			return jsonObject;
		} else {
			user.setRole(iRoleRepository.findById(2).get());
			user.setRole(null);
		}

		user.setFirstName(userDto.getFirstName());
		user.setLastName(userDto.getLastName());
		user.setUserName(userDto.getUserName());
		user.setPassword(userDto.getPassword());
		user.setEmail(userDto.getEmail());
		user.setPhoneNumber(userDto.getPhoneNumber());
		user.setGender(userDto.getGender());
		user.setDateOfBirth(userDto.getDateOfBirth());
		user.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		user.setPlayList(new ArrayList<>());
		user.setStatus(iStatusRepository.findById(1).get());
		iUsersRepository.save(user);
		jsonObject.put("Response", currentUser + " created with userName - " + userDto.getUserName());
		return jsonObject;
	}

	@Override
	public JSONObject logIn(LogInDto logInDto) {
		JSONObject loginStatus = new JSONObject();
		String userName = logInDto.getUserName();
		String password = logInDto.getPassword();

		List<Users> getUser = iUsersRepository.findByUP(userName, password);
		if (getUser.isEmpty()) {
			loginStatus.put("errormessage", "Wrong userName/password:" + userName + "," + password);
		} else {
			Integer roleId = getUser.get(0).getRole().getRoleId();
			if (roleId == 2) {
				loginStatus.put("Userstatus", ":- User Log in successful");
			} else {
				loginStatus.put("Adminstatus", ":- Admin Log in successful");
			}
		}
		return loginStatus;
	}

	@Override
	public JSONObject updateUser(UserDto userDto) {
		JSONObject jsonObject = new JSONObject();
		String userName = userDto.getUserName();
		String password = userDto.getPassword();
		List<Users> getUser = iUsersRepository.findByUP(userName, password);
		if (getUser.isEmpty()) {
			jsonObject.put("errormessage", "Wrong userName or password   - " + userName + "," + password);
			return jsonObject;
		} else {
			Users user = getUser.get(0);
			user.setFirstName(userDto.getFirstName());
			user.setLastName(userDto.getLastName());
			user.setEmail(userDto.getEmail());
			user.setPhoneNumber(userDto.getPhoneNumber());
			user.setGender(userDto.getGender());
			user.setDateOfBirth(userDto.getDateOfBirth());
			user.setUpdatedDate(new Timestamp(System.currentTimeMillis()));
			iUsersRepository.save(user);
			jsonObject.put("status", "User updated having userName :-" + userName);
			return jsonObject;
		}
	}

	@Override
	public JSONObject deleteUser(String userName, String password) {
		JSONObject jsonObject = new JSONObject();
		List<Users> getUser = iUsersRepository.findByUP(userName, password);
		if (!getUser.isEmpty()) {
			iUsersRepository.deleteUserByUP(userName, password);
			jsonObject.put("status", "User deleted having userName :- " + userName);
			return jsonObject;
		} else if (getUser.isEmpty()) {
			jsonObject.put("errormessage", "Wrong userName or password   - " + userName + "," + password);
			return jsonObject;
		}
		return jsonObject;
	}

	@Override
	public JSONObject getUser(Integer userId) {
		JSONObject jsonObject = new JSONObject();
		if (iUsersRepository.findById(userId).isPresent()) {
			Users users = iUsersRepository.findById(userId).get();
			jsonObject.put("id", users.getId());
			jsonObject.put("firstName", users.getFirstName());
			jsonObject.put("lastName", users.getLastName());
			jsonObject.put("userName", users.getUserName());
			jsonObject.put("email", users.getEmail());
			jsonObject.put("phoneNumber", users.getPhoneNumber());
			jsonObject.put("gender", users.getGender());
			jsonObject.put(("dateOfBirth"), users.getDateOfBirth());
			jsonObject.put("createdDate", users.getCreatedDate());
			jsonObject.put("updatedDate", users.getUpdatedDate());
		} else {
			jsonObject.put("errorMessage", "User not found having userId :- " + userId);
		}
		return jsonObject;
	}

	@Override
	public JSONObject addSongInMyPlayList(String userName, String password, Integer songId, String command) {
		JSONObject jsonObject = new JSONObject();
		List<Users> getUser = iUsersRepository.findByUP(userName, password);
		if (!getUser.isEmpty()) {
			Users users = getUser.get(0);
			ArrayList<Integer> playList123 = users.getPlayList();
			if (iSongRepository.findById(songId).isPresent()) {
				Songs songs = iSongRepository.findById(songId).get();
				if (playList123.contains(songId)) {
					jsonObject.put("errorMessage", "The song is already present having id - " + songId);
					return jsonObject;
				}
				playList123.add(songId);
				users.setPlayList(playList123);
				iUsersRepository.save(users);
				jsonObject.put("status", "Song added to user's playlist :- " + songs.getSongName());
			} else {
				jsonObject.put("errorMessage", "No song is present with id - " + songId);
			}
			return jsonObject;

		} else if (getUser.isEmpty()) {
			jsonObject.put("errormessage", "Wrong userName or password   - " + userName + ", " + password);
			return jsonObject;
		}
		return jsonObject;
	}

	@Override
	public JSONArray getMyPlayList(Integer user_id) {
		JSONArray jsonArray = new JSONArray();
		if (iUsersRepository.findById(user_id).isPresent()) {
			ArrayList<Integer> playList = iUsersRepository.findById(user_id).get().getPlayList();
			for (Integer play : playList) {
				Songs ele = iSongRepository.findById(play).get();
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
		} else {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("errorMessage", "No user is present with userId :- " + user_id);
			jsonArray.put(jsonObject);
		}
		return jsonArray;
	}

	@Override
	public JSONObject deleteFromMyPlayList(String userName, String password, Integer songId, String command) {
		List<Users> getUser = iUsersRepository.findByUP(userName, password);
		JSONObject jsonObject = new JSONObject();
		Users users = getUser.get(0);

		ArrayList<Integer> id = new ArrayList<>();
		for(Integer play:users.getPlayList()) {
			if (play != 1) {
				id.add(play);
			}
		}
		id.add(0);
		users.setPlayList(id);
		iUsersRepository.save(users);

		jsonObject.put("status", "Song deleted having songId :- " + songId);
		return jsonObject;
	}
}
