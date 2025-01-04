package com.askthem.posts.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Posts {
	@Id
	private Integer postNo;
	private String question;
	private String answer;
	private Boolean isAnswered;
}
