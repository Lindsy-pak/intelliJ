package com.lindsy.spring.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping("/detail")
    public String detail(BoardDTO param, Model model) {
        System.out.println("iboard = " + param.getIboard());
        BoardDomain data = service.selBoard(param);
        model.addAttribute(data);
        return "board/detail";
    }

    @GetMapping("/writeMod")
    public void writeMod() {}

    @PostMapping("/writeMod")
    public void writeMod(BoardEntity param) {
        int iboard = service.writeMod(param);
        return "redirect:detail?iboard=" + iboard;
    }

    @ResponseBody
    @RequestMapping(value = "/cmt", method = RequestMethod.POST)
    public Map<String, Integer> cmtIns(@RequestBody BoardCmtEntity param) {
        System.out.println("param" + param );
        int result = service.insBoardCmt(param);

        Map<String, Integer> data = new HashMap<>();
        /*리스트처럼 방이 만들어지지만 리스트처럼 순서가 없고 result와 age라는 키값에 값이 저장*/
        /* Map은 forEach문을 돌릴수 없다 list배열은 가능함 */
        // List<String> list = new ArrayList(); 와 비슷한 개념
        data.put("result", result);
        return data;
    }

    @ResponseBody
    @RequestMapping("/cmt/${iboard}")
    public List<BoardCmtDomain> cmtSel(BoardCmtEntity param, @PathVariable int iboard) {
        param.setIboard(iboard);
        return service.selBoardCmtList(param);
    }

    @ResponseBody
    @RequestMapping(value = "/cmt", method = RequestMethod.PUT) {
    public Map<String, Integer> cmtUpd(@RequestBody BoardCmtEntity param) {
        int result = service.updBoardCmt(param);
        Map<String, Integer> data  = new HashMap();
        data.put("result", result);
        return data;
        }
    }

    @ResponseBody
    @RequestMapping(value = "/cmt/${icmt}", method = RequestMethod.DELETE)
    public Map<String, Integer> cmtDel(BoardCmtEntity param, @PathVariable int icmt) {
        param.setIcmt(icmt);
        int result = service.delBoardCmt(param);
        Map<String, Integer> data  = new HashMap();
        data.put("result", result);
        return data;
    }

}





