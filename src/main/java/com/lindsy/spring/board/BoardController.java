package com.lindsy.spring.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardService service;

    @RequestMapping("/list")
    public String list(Model model) {
        List<BoardDomain> list = service.selBoardList();
        model.addAttribute("list", list);
        return "board/list";
    }

    @RequestMapping("detail")
    public String detail(BoardDTO param, Model model) {
        System.out.println("iboard = " + param.getIboard());
        BoardDomain data = service.selBoard(param);
        model.addAttribute("data", data);
        return "board/detail";
    }

    @ResponseBody
    @RequestMapping(value = "/cmtInsSel", method = RequestMethod.POST)
    public Map<String, Integer> cmtInsSel(@RequestBody BoardCmtEntity param) {
        System.out.println("param" + param );

        int result = service.insBoardCmt(param);

        Map<String, Integer> data = new HashMap<>();
        /*리스트처럼 방이 만들어지지만 리스트처럼 순서가 없고 result와 age라는 키값에 값이 저장*/
        /* Map은 forEach문을 돌릴수 없다 list배열은 가능함 */
        // List<String> list = new ArrayList(); 와 비슷한 개념
        data.put("result", 1);
        data.put("age",11);

        return data;
    }
    @ResponseBody
    @RequestMapping(value = "/cmtIns", method = RequestMethod.POST)
    public List<BoardDomain> cmtIns(BoardCmtEntity param) {
        System.out.println("param" + param ); /*iboard값이 넘어오는지만 체크*/
        BoardDomain bd = new BoardDomain();
        bd.setIboard(1);

        BoardDomain bd2 = new BoardDomain();
        bd2.setIboard(3);

        list.add(bd);
        list.add(bd2);

        return list;
    }

    @ResponseBody
    @RequestMapping(value = "/cmtInsSel", method = RequestMethod.POST)
    public List<BoardCmtDomain> cmtSel(BoardCmtEntity param) {
        return service.selBoardCmtList(param);
    }


}





