package com.example.Proj1_2019202085.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Getter
@Setter
@EntityListeners({AuditingEntityListener.class})
@NoArgsConstructor
public class Gallery {
    @Id
    @Column(name = "id", nullable = false)      //primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false)      //이미지 title
    private String title;

    @Column(length = 100, nullable = false)     //이미지 comment
    private String comment;

    @Column(nullable = false)
    private String imageName;

    @Column(nullable = false)
    private String imagePath;

//    @Lob
//    @Column(nullable = false)
//    private byte[] imageData;

}
