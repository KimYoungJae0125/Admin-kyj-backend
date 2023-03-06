package kr.kimyoungjae.admin.common.conditions;

import org.springframework.data.domain.Sort;

public class CustomSort {

    public static Sort layoutSort() {
        return Sort.by(Sort.Direction.ASC, "layoutOrder");
    }
}
