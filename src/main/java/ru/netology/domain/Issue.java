package ru.netology.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class Issue {
    private int id;
    private boolean open;
    private String author;
    private Set<String> label;
    private String date;
    private Set<String> assignee;
    private int commentsCount;
}
