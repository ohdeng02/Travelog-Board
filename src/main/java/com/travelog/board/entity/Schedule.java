package com.travelog.board.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_id")
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Board board;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private LocalDate date;

    @Column(nullable = false)
    private String location;
    //숫자형태로 계산이 필요하지 않다면 string으로 해도됨.
    @Column(nullable = false)
    private Double latitude;//위도
    @Column(nullable = false)
    private Double longitude;//경도
    private String transport;

    public void setBoard(Board board){
        this.board = board;
    }

    @Builder
    public Schedule (Board board, LocalDate date, String location, Double latitude, Double longitude, String transport) {
        this.board = board;
        this.date = date;
        this.location = location;
        this.latitude = latitude;
        this.longitude = longitude;
        this.transport = transport;
    }

}
