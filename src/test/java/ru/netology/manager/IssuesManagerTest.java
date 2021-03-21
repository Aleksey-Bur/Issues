package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Issues;
import ru.netology.repository.IssuesRepository;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.*;

public class IssuesManagerTest {
    private IssuesRepository repository = new IssuesRepository();
    private IssuesManager manager = new IssuesManager(repository);

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
        manager.add(issues1);
        manager.add(issues2);
        manager.add(issues3);
        manager.add(issues4);
    }

    @Test
    void shouldFilterByAuthor() {
        String author = "Fedr";
        Predicate<Issues> predicate = issues -> issues.getAuthor().equals(author);
        List<Issues> actual = manager.filterByAuthor(predicate);
        List<Issues> expected = Arrays.asList(issues4,issues3);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotFilterByAuthorIfNotExist() {
        String author = "Alisa";
        Predicate<Issues> predicate = issues -> issues.getAuthor().equals(author);
        List<Issues> actual = manager.filterByAuthor(predicate);
        List<Issues> expected = Arrays.asList();
        assertEquals(expected, actual);
    }

    @Test
    void shouldFilterByLabel() {
        Predicate<HashSet> equalLabel = t -> t.equals(label4);
        List<Issues> actual = manager.filterByLabel(equalLabel);
        List<Issues> expected = Arrays.asList(issues4);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotFilterByLabelIfNotExist() {
        HashSet<String> label5 = new HashSet<>((Arrays.asList("promotion")));
        Predicate<HashSet> equalLabel = t -> t.equals(label5);
        List<Issues> actual = manager.filterByLabel(equalLabel);
        List<Issues> expected = Arrays.asList();
        assertEquals(expected, actual);
    }
    @Test
    void shouldFilterByAssignee() {
        Predicate<HashSet> equalAssignee = t -> t.equals(assignee1);
        List<Issues> actual = manager.filterByAssignee(equalAssignee);
        List<Issues> expected = Arrays.asList(issues1,issues4);
        assertEquals(expected, actual);
    }
    @Test
    void shouldNotFilterByAssigneeIfNotExist() {
        Predicate<HashSet> equalAssignee = t -> t.equals(assignee4);
        List<Issues> actual = manager.filterByAssignee(equalAssignee);
        List<Issues> expected = Arrays.asList();
        assertEquals(expected, actual);
    }
    @Test
    void shouldFindAllOpen() {
        List<Issues> actual = manager.findAllOpen();
        List<Issues> expected = Arrays.asList(issues1, issues3, issues4);
        assertEquals(expected, actual);
    }
    @Test
    void shouldFindAllClosed() {
        List<Issues> actual = manager.findAllClosed();
        List<Issues> expected = Arrays.asList(issues2);
        assertEquals(expected, actual);
    }

    @Test
    void shouldOpenById() {
        manager.openById(2);
        assertTrue(issues2.isOpen());
    }

    @Test
    void shouldCloseById() {
        manager.closeById(2);
        assertFalse(issues2.isOpen());
    }
}