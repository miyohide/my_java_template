package com.github.miyohide.my_java_template;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import com.github.miyohide.my_java_template.model.Member;
import com.github.miyohide.my_java_template.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;

@DataJdbcTest
class MemberRepositoryTest {
  @Autowired private MemberRepository memberRepository;

  @Test
  void test() {
    String name = "foobar";
    Member save = memberRepository.save(new Member(null, name));
    // 保存することでidが採番されてnullではなくなること
    assertNotNull(save.getId());
    Optional<Member> maybeMember = memberRepository.findById(save.getId());
    // 保存したデータを検索したときに正常に検索できること
    assertTrue(maybeMember.isPresent());
    // 検索で得られたデータが保存したデータと一致していること
    maybeMember.ifPresent(member -> assertEquals(save.getName(), member.getName()));
  }
}
