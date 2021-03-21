package ru.netology.manager;

import ru.netology.domain.Issues;
import ru.netology.repository.IssuesRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.function.Predicate;

public class IssuesManager {
    private IssuesRepository repository;

    public IssuesManager(IssuesRepository repository){
        this.repository = repository;
    }

    public void add(Issues issues) {
        repository.save(issues);
    }

    public List<Issues> filterByAuthor(Predicate<Issues> predicate) {
        List<Issues> result = new ArrayList<>();
        for (Issues issues : repository.getAll()) {
            if (predicate.test(issues)) {
                result.add(issues);
            }
        }
        return sortByNewest(result);
    }

    public List<Issues> filterByLabel(Predicate<HashSet> equalLabel) {
        List<Issues> result = new ArrayList<>();
        for (Issues issues : repository.getAll()) {
            if (equalLabel.test(issues.getLabel())) {
                result.add(issues);
            }
        }
        return result;
    }

    public List<Issues> filterByAssignee(Predicate<HashSet> equalAssignee) {
        List<Issues> result = new ArrayList<>();
        for (Issues issues : repository.getAll()) {
            if (equalAssignee.test(issues.getAssignee())) {
                result.add(issues);
            }
        }
        return result;
    }

    public List<Issues> sortByNewest(List<Issues> list) {
        list.sort((o1, o2) -> o2.getDate().compareTo(o1.getDate()));
        return list;
    }

    public List<Issues> findAllOpen() {
        List<Issues> temp = new ArrayList<>();
        for (Issues issues : repository.getAll()) {
            if (issues.isOpen()) {
                temp.add(issues);
            }
        }
        return temp;
    }

    public List<Issues> findAllClosed() {
        List<Issues> temp = new ArrayList<>();
        for (Issues issues : repository.getAll()) {
            if (!issues.isOpen()) {
                temp.add(issues);
            }
        }
        return temp;
    }

    public void openById(int id) {
        repository.openById(id);
    }

    public void closeById(int id){
        repository.closeById(id);
    }
}