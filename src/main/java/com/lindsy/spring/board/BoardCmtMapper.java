package com.lindsy.spring.board;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardCmtMapper {
//    리턴type은 int or void(insert, update, delete -> int:excuteUpdate) , select는 Domain
    int insBoardCmt(BoardCmtEntity param);
    abstract List<BoardCmtDomain> selBoardCmtList(BoardCmtDomain param); /*댓글의 여러줄만 정보를 가져오기 때문에 List*/
//    abstract는 interface라면 무조건


}
