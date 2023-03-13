package com.springrest.springrest.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_status")
public class Status {

	@Id
	@Column(name = "status_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int statusId;

	@NotBlank(message = "post data is mandatory")
	@Column(name = "status_data")
	private String postData;

	@Column(name = "status_description")
	private String statusDescription;
	private Timestamp createdDate;
	private Timestamp updatedDate;
	@JsonIgnore
	@JoinColumn(name = "user_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private Users user;
}
