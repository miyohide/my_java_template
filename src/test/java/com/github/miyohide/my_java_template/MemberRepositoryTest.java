package com.github.miyohide.my_java_template;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJdbcTest
class MemberRepositoryTest {
    @Autowired
    private MemberRepository memberRepository;

    @Test
    void test() {
        String name = "みよひで";
        Member save = memberRepository.save(new Member(null, name));
        assertNotNull(save.getId());
        Optional<Member> maybeMember = memberRepository.findById(save.getId());
        assertTrue(maybeMember.isPresent());
        maybeMember.ifPresent(member -> assertEquals(save.getName(), member.getName()));
    }
}
