package org.test.community.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class User {


	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

	@Size(min = 4, max = 16, message = "아이디는 4~12자리로 입력해야 합니다.")
    private String username;
	
    private String nickname;
    
    @Size(min = 4, message = "비밀번호를 4자 이상 입력해주세요.")
    private String password;
    
    private String email;
    
    
	@CreationTimestamp
	private Timestamp regdate;
	
    private Boolean enabled;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles = new ArrayList<>();

    @OneToMany(mappedBy="user")
    private List<LikeTable> likes  = new ArrayList<>();
    
    @OneToMany(mappedBy="user")
    private List<Comment> comments  = new ArrayList<>();

    @OneToMany(mappedBy="user")
    private List<TotalBoard> boards  = new ArrayList<>();
    
    
    
    


}
