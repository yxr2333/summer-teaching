package com.ssssheep.summer.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created By Intellij IDEA
 *
 * @author ssssheep
 * @package com.ssssheep.summer.pojo
 * @datetime 2022/7/28 星期四
 */
@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_movie_tickets")
public class MovieTicket {

    @Column(name = "order_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "movie_title",length = 40)
    private String name;

    private Double price;

    @Column(length = 40)
    private String cinema;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;

    @ManyToOne
    @JoinColumn(name = "f_uid")
    private User buyer;

    @ManyToOne
    @JoinColumn(name = "f_movie_id")
    private Movie movie;

}
