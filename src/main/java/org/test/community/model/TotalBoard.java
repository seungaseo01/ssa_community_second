package org.test.community.model;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.stereotype.Controller;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "total_board")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TotalBoard {

    @Id
    @Column(name = "b_no")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bNo;


    @Column(name = "b_divide")
    private String bDivide;

    @Column(name = "b_title")
    private String bTitle;

    @Column(name = "b_secret")
    private int bSecret;

    @Column(name = "b_content")
    private String bContent;

    @Column(name = "b_writer")
    private String bWriter;

	/*
	 * @Column(name = "b_like",nullable = true) private int bLike;
	 */

    @CreationTimestamp
    @Column(name = "b_regdate", updatable = false)
    private Timestamp bRegdate;

    @UpdateTimestamp
    @Column(name = "b_moddate")
    private Timestamp bModdate;

    @Column(name = "b_viewcnt")
    private int bViewcnt;

    @ManyToOne
    @JoinColumn(name = "c_no")
    private Category category;


    @OneToMany(mappedBy="board")
    private List<LikeTable> likes  = new ArrayList<>();
    
    @OneToMany(mappedBy="board")
    private List<Comment> comments  = new ArrayList<>();

}
