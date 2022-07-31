package com.ssssheep.summer.pojo.entity;

import lombok.*;

import javax.persistence.*;

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
@Table(name = "t_users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer uid;

    @Column(nullable = false,name = "pwd",length = 40)
    private String password;

    @Column(nullable = false, name = "mailbox",length = 40)
    private String mail;

    @Column(length = 11)
    private String phone;

    @Column(length = 40,unique = true,nullable = false)
    private String name;

    @Column(length = 4)
    private String gender;

    private Integer age;

}
