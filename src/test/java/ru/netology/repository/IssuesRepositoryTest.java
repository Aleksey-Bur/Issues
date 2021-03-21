package ru.netology.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Issues;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class IssuesRepositoryTest {

    private IssuesRepository repository = new IssuesRepository();

    private HashSet<String> assignee1 = new HashSet<>((Arrays.asList("Ivan")));
    private HashSet<String> assignee2 = new HashSet<>((Arrays.asList("Petr")));
    private HashSet<String> assignee3 = new HashSet<>((Arrays.asList("Aleksey")));
    private HashSet<String> assignee4 = new HashSet<>((Arrays.asList("Denis")));

    private HashSet<String> label1 = new HashSet<>((Arrays.asList("Bag")));
    private HashSet<String> label2 = new HashSet<>((Arrays.asList("New feature")));
    private HashSet<String> label3 = new HashSet<>((Arrays.asList("Task")));
    private HashSet<String> label4 = new HashSet<>((Arrays.asList("Question")));

    private Issues issues1 = new Issues(1, true, "Cookie", label1, "10.03.2020", assignee1, 10);
    private Issues issues2 = new Issues(2, false, "Smith", label2, "06.06.2020", assignee2, 0);
    private Issues issues3 = new Issues(3, true, "Fedr", label3, "16.05.2020", assignee3, 3);
    private Issues issues4 = new Issues(4, true, "Fedr", label4, "20.08.2020", assignee1, 0);

    @BeforeEach
    void setUp() {
        repository.save(issues1);
        repository.save(issues2);
        repository.save(issues3);
        repository.save(issues4);
    }

    @Test
    void shouldFindAll() {
        List<Issues> actual = repository.getAll();
        List<Issues> expected = Arrays.asList(issues1,issues2,issues3,issues4);
        assertEquals(expected,actual);
    }

    @Test
    void shouldFindById() {
        Issues actual = repository.findById(3);
        Issues expected = issues3;
        assertEquals(expected,actual);
    }

    @Test
    void shouldOpenById() {
        repository.openById(2);
        assertTrue(issues2.isOpen());
    }

    @Test
    void shouldCloseById() {
        repository.closeById(2);
        assertFalse(issues2.isOpen());
    }
}