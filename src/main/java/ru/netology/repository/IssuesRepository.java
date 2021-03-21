package ru.netology.repository;

import ru.netology.domain.Issues;

import java.util.ArrayList;
import java.util.List;

public class IssuesRepository {
    private List<Issues> issuess = new ArrayList<>();

    public void save(Issues issues) {
        issuess.add(issues);
    }

    public List<Issues> getAll() {
        return issuess;
    }

    public Issues findById(int id) {
        for (Issues issues : issuess) {
            if (issues.getId() == id) {
                return issues;
            }
        }
        return null;
    }

    public void openById(int id) {
        for (Issues issues : issuess) {
            if (issues.getId() == id) {
                issues.setOpen(true);
                break;
            }
        }
    }
    public void closeById(int id){
        for (Issues issues : issuess) {
            if (issues.getId() == id) {
                issues.setOpen(false);
                break;
            }
        }
    }
}