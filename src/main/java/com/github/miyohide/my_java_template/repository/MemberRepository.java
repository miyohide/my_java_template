package com.github.miyohide.my_java_template.repository;

import com.github.miyohide.my_java_template.model.Member;
import org.springframework.data.repository.CrudRepository;

public interface MemberRepository extends CrudRepository<Member, Integer> {}
