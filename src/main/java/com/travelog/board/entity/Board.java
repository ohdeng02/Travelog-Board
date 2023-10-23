package com.travelog.board.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import java.util.List;
import java.util.ArrayList;

import java.time.LocalDateTime;

@Entity
@Table
@Getter
@NoArgsConstructor
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long boardId;
    private String nickname;
    private String local;
    private String title;
    private String contents;
    private String summary;

    @OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE)
    private List<Schedule> schedules = new ArrayList<>();

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    private boolean status;
    private int views;

//    public void addSchedule(Schedule schedule) {
//        schedules.add(schedule);
//        schedule.setBoard(this);
//    }

    @Builder
    public Board(String nickname, String local, String title, String contents, String summary,
                 List<Schedule> schedules, LocalDateTime createdAt, LocalDateTime updatedAt, boolean status, int views) {
        this.nickname = nickname;
        this.local = local;
        this.title = title;
        this.contents = contents;
        this.summary = summary;
        this.schedules = schedules;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.status = status;
        this.views = views;
    }
}
