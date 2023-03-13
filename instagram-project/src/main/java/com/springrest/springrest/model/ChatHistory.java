package com.springrest.springrest.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_chat_history")
public class ChatHistory {

	@Id
	@Column(name = "chat_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int chatId;
	
	@JoinColumn(name = "to_user_id")
	@ManyToOne
	private Users to;

	@JoinColumn(name = "from_user_id")
	@ManyToOne
	private Users from;
	
	@Column(name = "message")
	private String message;
	
	@CreationTimestamp
	@Column(nullable = false, name = "created_date")
	private Timestamp createdDate;
	
	@UpdateTimestamp
	@Column(nullable = false, name = "updated_date")
	private Timestamp UpdatedDate;
}
