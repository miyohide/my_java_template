package com.github.miyohide.my_java_template;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MemberController {
  @Autowired private MemberRepository memberRepository;

  @GetMapping("/members")
  public String index(Model model) {
    model.addAttribute("members", memberRepository.findAll());
    return "index";
  }

  @GetMapping("/members/{id}")
  public String show(@PathVariable("id") Integer id, Model model) {
    // TODO memberが存在しない時の処理を実装する
    model.addAttribute("member", memberRepository.findById(id).get());
    return "show";
  }
}
