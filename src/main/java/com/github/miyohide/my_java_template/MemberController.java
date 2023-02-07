package com.github.miyohide.my_java_template;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberController {
  @Autowired private MemberRepository memberRepository;

  @GetMapping("/members")
  public String index(Model model) {
    model.addAttribute("members", memberRepository.findAll());
    return "members/index";
  }

  @GetMapping("/members/{id}")
  public String show(@PathVariable("id") Integer id, Model model) {
    // TODO memberが存在しない時の処理を実装する
    model.addAttribute("member", memberRepository.findById(id).get());
    return "members/show";
  }

  @GetMapping("/members/new")
  public String newMember(Model model) {
    model.addAttribute("member", new Member());
    return "members/new";
  }

  @PostMapping("/members")
  public String create(@ModelAttribute Member member, Model model) {
    Member savedMember = memberRepository.save(member);
    model.addAttribute("member", savedMember);
    return "members/show";
  }
}
