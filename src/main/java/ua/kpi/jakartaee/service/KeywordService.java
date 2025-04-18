package ua.kpi.jakartaee.service;

import jakarta.ejb.Stateless;
import ua.kpi.jakartaee.entity.Keyword;

import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class KeywordService {

    private List<String> filterKeywords(List<String> keywords) {
        return keywords.stream()
                .filter(keyword -> keyword != null && !keyword.isBlank())
                .distinct()
                .collect(Collectors.toList());
    }

    public List<Keyword> convertStringsToKeywords(List<String> keywords) {
        return filterKeywords(keywords).stream()
                .map(keyword -> {
                    Keyword keywordEntity = new Keyword();
                    keywordEntity.setKeyword(keyword);
                    return keywordEntity;
                })
                .toList();
    }

    public List<String> convertKeywordsToStrings(List<Keyword> keywords) {
        return keywords.stream()
                .map(Keyword::getKeyword)
                .sorted()
                .toList();
    }
}
