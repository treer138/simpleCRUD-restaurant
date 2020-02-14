SIMPLE CURD STUDY ver.Restaurant
=================================

### 주제

-Spring MVC에 TDD를 이용한 간단한 CRUD 공부(BackEnd)

### 목적

-Optional, lamda 등 java 기술 활용 및 습득
-각종 어노테이션에 대한 습득
-테스트 코드 작성법 공부
-멀티모듈 방식 경험

### 사용 기술

-JAVA, SPRING, SPRING BOOT, H2DB 
-Lombok, Node, junit5

### 형상관리

-NPM, chocolatey, github, git bash

### 툴

-Intellij

### 대표적 트러블 슈팅
-Test 코드 작성시 Json 데이터 타입을 지정안함을 확인 -> mvc.perform.contentType(MediaType.APPLICATION_JSON) 추가

-MVC 테스트 사용시 필요 어노테이션 미작성 -> test class에 @WebMvcTest(CategoryController.class) 추가

-Java lamda사용 코드작성 -> for, while 문의 대체가 아님 적절한 상황에 맞는 선택이 필요

-객체가 null일 경우 []값이 아닌 없는 값으로 보내주는 역활 -> @JsonInclude(JsonInclude.Include.NON_DEFAULT)