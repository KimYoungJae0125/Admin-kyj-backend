package kr.kimyoungjae.admin.common.querydsl;

import java.util.List;

public interface QueryDSLRepository<T> {

    List<T> findAll();

}
