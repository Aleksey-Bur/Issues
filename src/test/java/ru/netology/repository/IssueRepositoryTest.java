package ru.netology.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Issue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class IssueRepositoryTest {

    private IssueRepository repository = new IssueRepository();

    private Set<String> assignee1 = new HashSet<>((Arrays.asList("Ivan")));
    private Set<String> assignee2 = new HashSet<>((Arrays.asList("Petr")));
    private Set<String> assignee3 = new HashSet<>((Arrays.asList("Aleksey")));
    private Set<String> assignee4 = new HashSet<>((Arrays.asList("Denis")));

    private Set<String> label1 = new HashSet<>((Arrays.asList("Bag")));
    private Set<String> label2 = new HashSet<>((Arrays.asList("New feature")));
    private Set<String> label3 = new HashSet<>((Arrays.asList("Task")));
    private Set<String> label4 = new HashSet<>((Arrays.asList("Question")));

    private Issue issue1 = new Issue(1, true, "Cookie", label1, "10.03.2020", assignee1, 10);
    private Issue issue2 = new Issue(2, false, "Smith", label2, "06.06.2020", assignee2, 0);
    private Issue issue3 = new Issue(3, true, "Fedr", label3, "16.05.2020", assignee3, 3);
    private Issue issue4 = new Issue(4, true, "Fedr", label4, "20.08.2020", assignee1, 0);

    @BeforeEach
    void setUp() {
        repository.save(issue1);
        repository.save(issue2);
        repository.save(issue3);
        repository.save(issue4);
    }

    @Test
    void shouldFindAll() {
        List<Issue> actual = repository.getAll();
        List<Issue> expected = Arrays.asList(issue1, issue2, issue3, issue4);
        assertEquals(expected,actual);
    }

    @Test
    void shouldFindById() {
        Issue actual = repository.findById(3);
        Issue expected = issue3;
        assertEquals(expected,actual);
    }

    @Test
    void shouldOpenById() {
        repository.openById(2);
        assertTrue(issue2.isOpen());
    }

    @Test
    void shouldCloseById() {
        repository.closeById(2);
        assertFalse(issue2.isOpen());
    }
}