package com.lindsy.spring.board;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    List<BoardDomain> selBoardList(); //id에 있는게 메소드 명임 # $에 아무것도 안넣으면 파라미터 값에 아무것도 넣지 않는다.
}
