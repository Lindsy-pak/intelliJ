package com.lindsy.spring.board;

import com.lindsy.spring.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class BoardService {

    @Autowired
    private BoardMapper mapper;

    @Autowired
    private BoardCmtMapper cmtMapper;

    @Autowired
    private HttpSession session;

    public List<BoardDomain> selBoardList() {
        return mapper.selBoardList();
    }
    public BoardDomain selBoard(BoardDTO param) {
        return mapper.selBoard(param);
    }

    public int insBoardCmt(BoardCmtEntity param) {
        UserEntity loginUser = (UserEntity)  session.getAttribute("loginUser");
        param.setIuser(loginUser.getIuser());
        return cmtMapper.insBoardCmt(param);
    }

    public List<BoardCmtDomain> selBoardCmtList(BoardCmtEntity param) {
        return cmtMapper.selBoardCmtList(param);
    }

    public int updBoardCmt(BoardCmtEntity param) {
        UserEntity loginUser = (UserEntity) session.getAttribute("loginUser");
        param.setIuser(loginUser.getIuser());
        return cmtMapper.updBoardCmt(param);
    }

    public int delBoardCmt(BoardCmtEntity param) {
        UserEntity loginUser = (UserEntity) session.getAttribute("loginUser");
        param.setIuser(loginUser.getIuser());
        return cmtMapper.delBoardCmt(param);
    }

    public int writeMod(BoardEntity param) {
        UserEntity loginUser = (UserEntity) session.getAttribute("loginUser");
        param.setIuser(loginUser.getIuser());

        if (param.getIboard() == 0) {
            //등록
            return 0;
        }
        //수정
        
        return 0;
    }
}
