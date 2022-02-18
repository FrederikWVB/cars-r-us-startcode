package kea.sem3.jwtdemo.api;

import kea.sem3.jwtdemo.dto.MemberRequest;
import kea.sem3.jwtdemo.dto.MemberResponse;
import kea.sem3.jwtdemo.service.MemberService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/members")
public class MemberController {
    MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping
    public List<MemberResponse> getMembers(){
        return memberService.getMembers();
    }

    @GetMapping("/{username}")
    public MemberResponse getMember(@PathVariable String username) throws Exception {
        return memberService.getMemberByUserName(username);
    }

    @PostMapping
    public MemberResponse AddMember(@RequestBody MemberRequest body) {
        return memberService.addMember(body);
    }

    @DeleteMapping ("/{username}")
    public void deleteMember(@PathVariable String username) throws Exception {
        memberService.deleteMember(username);
    }

}
