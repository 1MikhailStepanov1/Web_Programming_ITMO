package entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="shots")
@Data
public class Shots {

    @Id
    @Column(name="shots_id")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "next_shots_id")
    private int id;

    @Column(name="x")
    private Float x;

    @Column(name="y")
    private Float y;

    @Column(name="r")
    private Integer r;

    @Column(name="currentTime")
    private String currentTime;

    @Column(name="duration")
    private long duration;

    @Column(name="result")
    private boolean result;
}
