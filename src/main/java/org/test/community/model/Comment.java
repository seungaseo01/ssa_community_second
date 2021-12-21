package org.test.community.model;



import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "comment")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Comment{

	 @Id
	 @Column(name = "cm_no")
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private int cmNo;
	 
	 
	 @Column(name = "cm_seq")
	 private int cmSeq;
	 
	 @Column(name = "cm_lvl")
	 private int cmLvl;
	 
	 @Column(name = "cm_content")
	 private String cmContent;
	 
	 @CreationTimestamp
	 @Column(name = "cm_regdate", updatable = false)
	 private Timestamp cmRegdate;
	 	 
	 @ManyToOne
	 @JoinColumn(name = "cm_writer")
	 private User user;

	 @ManyToOne
	 @JoinColumn(name = "b_no")
	 private TotalBoard board;
	 
	 
	 
	 

}
