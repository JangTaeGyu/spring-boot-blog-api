# Spring Boot Blog API

> 블로그 관리를 하는 API 프로젝트 입니다.

## Development Stack

- Language : Java 17
- Framework : Spring Boot 2.7.*
- Database : PostgreSQL

## Installation and Getting Started

```shell
# 소스 다운 받기
git clone https://github.com/JangTaeGyu/spring-boot-blog-api.git

# 프로젝트 설정 파일 생성
# 상세한 파일 설정은 Settings 참고
cd {project}/src/main/resources
touch application-local.properties

## 환경 변수 설정
## IntelliJ IDEA > 구성 편집 > 환경 변수 아래 항목 추가 하시면 됩니다.
## - PROFILE_ACTIVE=local

## Spring Boot 실행
mvn spring-boot:run
```

## Settings

```dotenv
spring.application.name=spring-boot-blog-api

# datasource
spring.datasource.url=
spring.datasource.username=
spring.datasource.password=
spring.datasource.driver-class-name=org.postgresql.Driver

# hibernate
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgresPlusDialect
#spring.jpa.properties.hibernate.show_sql=true
#spring.jpa.properties.hibernate.format_sql=false

# jpa
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.open-in-view=false
```