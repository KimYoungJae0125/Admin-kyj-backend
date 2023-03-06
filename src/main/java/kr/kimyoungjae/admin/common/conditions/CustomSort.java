package kr.kimyoungjae.admin.common.conditions;

import org.springframework.data.domain.Sort;

public class CustomSort {

    public static Sort layoutSort() {
        return Sort.by(Sort.Direction.ASC, "layoutOrder");
    }
    public static Sort layoutSort(String... properties) {
        String[] newProperties = new String[properties.length+1];
        int i = 0;
        for(; i < properties.length; i++) {
            newProperties[i] = properties[i];
        }
        newProperties[i] = "layoutOrder";
        return Sort.by(Sort.Direction.ASC, newProperties);

    }

}
