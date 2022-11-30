package com.github.joeri5.jobboard.job;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.github.joeri5.jobboard.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Job {

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    @JsonIgnoreProperties({
            "password",
            "locked",
            "enabled",
            "accountNonExpired",
            "accountNonLocked",
            "credentialsNonExpired",
            "username",
            "authorities",
            "role"
    })
    @ManyToOne
    @JoinColumn(
            name = "author_id"
    )
    private User author;

    public Job(String title, String content) {
        this.title = title;
        this.content = content;
    }

}
