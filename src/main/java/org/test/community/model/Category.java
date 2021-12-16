package org.test.community.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "category")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Category {

    @Id
    @Column(name = "c_no", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cNo;

    @Column(name = "c_name")
    private String cName;

    @OneToMany(mappedBy="category")
    private List<TotalBoard> boards  = new ArrayList<>();

}
