package com.springrest.springrest.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.validator.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "tbl_songs")
public class Songs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer songId;
    @NotBlank(message = "songName is mandatory")
    private String songName;
    @NotNull(message = "singer is mandatory")
    private String singer;
    @NotNull(message = "movie name is mandatory/NA")
    private String movie;

    @NotNull(message = "album name is mandatory/NA")
    private String album;

    @NotNull(message = "duartion is mandatory")
    private String duration;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Status status;

}
