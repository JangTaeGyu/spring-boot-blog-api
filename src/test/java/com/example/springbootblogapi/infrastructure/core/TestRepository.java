package com.example.springbootblogapi.infrastructure.core;

import com.example.springbootblogapi.infrastructure.config.QuerydslConfig;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest
@Transactional(propagation = Propagation.NOT_SUPPORTED )
@Import(QuerydslConfig.class)
@TestPropertySource("classpath:application-test.properties")
public class TestRepository {
}
