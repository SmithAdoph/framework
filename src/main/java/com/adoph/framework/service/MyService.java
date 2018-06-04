package com.adoph.framework.service;

import org.springframework.transaction.annotation.Transactional;

/**
 * TODO
 *
 * @author Tangqiandong
 * @version v1.0
 * @since 2018/6/4
 */
public interface MyService {
    @Transactional
    void test();
}
