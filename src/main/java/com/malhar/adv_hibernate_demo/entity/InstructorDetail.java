package com.malhar.adv_hibernate_demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "instructor_detail")
public class InstructorDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "hobby")
    private String hobby;

    @Column(name = "youtube_channel")
    private String youtubeChannel;

    @OneToOne(mappedBy = "instructorDetail", cascade = CascadeType.ALL)
    private Instructor instructor;

    public InstructorDetail() {}

    public InstructorDetail(String hobby, String youtubeChannel) {
        this.hobby = hobby;
        this.youtubeChannel = youtubeChannel;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getYoutubeChannel() {
        return youtubeChannel;
    }

    public void setYoutubeChannel(String youtubeChannel) {
        this.youtubeChannel = youtubeChannel;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    @Override
    public String toString() {
        return STR."InstructorDetail{id=\{id}, hobby='\{hobby}\{'\''}, youtubeChannel='\{youtubeChannel}\{'\''}\{'}'}";
    }
}
