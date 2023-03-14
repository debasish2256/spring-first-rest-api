package com.springrest.springrest.controller;

import java.util.List;

import com.springrest.springrest.service.ChatService;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/chat")
public class ChatController {
	@Autowired
	private ChatService chatService;

	@PostMapping("/sendMessage")
	public ResponseEntity<String> sendMessage(@RequestBody String message) {
		JSONObject jsonObject = new JSONObject(message);
		JSONObject errorList = chatService.validateMessage(jsonObject);
		if (errorList.has("errorMessage")) {
			return new ResponseEntity<>(errorList.toString(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(errorList.toString(), HttpStatus.CREATED);
	}

	@GetMapping("/get_received_messages")
	public ResponseEntity<List<String>> getMessages(@RequestParam("user_id") Integer user_id) {
		List<String> receivedMessages = chatService.getReceivedMessages(user_id);

		return new ResponseEntity<>(receivedMessages, HttpStatus.OK);
	}

	@GetMapping("/get_sent_messages")
	public ResponseEntity<List<String>> getsentMessages(@RequestParam("user_id") Integer user_id) {
		List<String> sentMessages = chatService.getsentMessages(user_id);
		return new ResponseEntity<>(sentMessages, HttpStatus.OK);
	}

	@GetMapping("/getConversation")
	public ResponseEntity<String> getConversation(@RequestParam("user_id1") Integer user_id1,
			@RequestParam("user_id2") Integer user_id2) {
		JSONObject conversation = chatService.getConversation(user_id1, user_id2);
		return new ResponseEntity<>(conversation.toString(), HttpStatus.OK);
	}

	@DeleteMapping("/deleteMessage")
	public Integer deleteMessage(@RequestParam("user_id1") Integer user_id1,
			@RequestParam("user_id2") Integer user_id2) {
		return chatService.deleteConversation(user_id1, user_id2);
	}

	@GetMapping("/getConversation1")
	public ResponseEntity<List<String>> getConversation1(@RequestParam("user_id1") Integer user_id1,
			@RequestParam("user_id2") Integer user_id2) {
		List<String> conversation1 = chatService.getConversation1(user_id1, user_id2);
		return new ResponseEntity<>(conversation1, HttpStatus.OK);
	}
}
