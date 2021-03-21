package ru.netology.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class Issues {
    private int id;
    private boolean open;
    private String author;
    private HashSet<String> label;
    private String date;
    private HashSet<String> assignee;
    private int commentsCount;
}
