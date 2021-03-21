package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Issue;
import ru.netology.repository.IssueRepository;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.*;

public class IssueManagerTest {
    private IssueRepository repository = new IssueRepository();
    private IssueManager manager = new IssueManager(repository);

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
        manager.add(issue1);
        manager.add(issue2);
        manager.add(issue3);
        manager.add(issue4);
    }

    @Test
    void shouldFilterBy() {
        String author = "Fedr";
        Predicate<Issue> predicate = issue -> issue.getAuthor().equals(author);
        List<Issue> actual = manager.filterBy(predicate);
        List<Issue> expected = Arrays.asList(issue4, issue3);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotFilterByIfNotExist() {
        String author = "Alisa";
        Predicate<Issue> predicate = issue -> issue.getAuthor().equals(author);
        List<Issue> actual = manager.filterBy(predicate);
        List<Issue> expected = Arrays.asList();
        assertEquals(expected, actual);
    }

    @Test
    void shouldFilterByLabel() {
        Predicate<Set<String>> equalLabel = t -> t.equals(label4);
        List<Issue> actual = manager.filterByLabel(equalLabel);
        List<Issue> expected = Arrays.asList(issue4);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotFilterByLabelIfNotExist() {
        Set<String> label5 = new HashSet<>((Arrays.asList("promotion")));
        Predicate<Set<String>> equalLabel = t -> t.equals(label5);
        List<Issue> actual = manager.filterByLabel(equalLabel);
        List<Issue> expected = Arrays.asList();
        assertEquals(expected, actual);
    }
    @Test
    void shouldFilterByAssignee() {
        Predicate<Set<String>> equalAssignee = t -> t.equals(assignee1);
        List<Issue> actual = manager.filterByAssignee(equalAssignee);
        List<Issue> expected = Arrays.asList(issue1, issue4);
        assertEquals(expected, actual);
    }
    @Test
    void shouldNotFilterByAssigneeIfNotExist() {
        Predicate<Set<String>> equalAssignee = t -> t.equals(assignee4);
        List<Issue> actual = manager.filterByAssignee(equalAssignee);
        List<Issue> expected = Arrays.asList();
        assertEquals(expected, actual);
    }
    @Test
    void shouldFindAllOpen() {
        List<Issue> actual = manager.findAllOpen();
        List<Issue> expected = Arrays.asList(issue1, issue3, issue4);
        assertEquals(expected, actual);
    }
    @Test
    void shouldFindAllClosed() {
        List<Issue> actual = manager.findAllClosed();
        List<Issue> expected = Arrays.asList(issue2);
        assertEquals(expected, actual);
    }

    @Test
    void shouldOpenById() {
        manager.openById(2);
        assertTrue(issue2.isOpen());
    }

    @Test
    void shouldCloseById() {
        manager.closeById(2);
        assertFalse(issue2.isOpen());
    }
}