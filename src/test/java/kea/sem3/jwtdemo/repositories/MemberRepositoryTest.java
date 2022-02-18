package kea.sem3.jwtdemo.repositories;

import kea.sem3.jwtdemo.entity.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @BeforeEach
    void setUp() {
        memberRepository.deleteAll();
        memberRepository.save(new Member("xxx", "xxx@a.dk", "pass1234", "Frederik", "Wandall", "vej1", "by1", "1010", true ));
        memberRepository.save(new Member("yyy", "yyy@a.dk", "pass5678", "Fred", "Benzon", "vej2", "by2", "2020", true ));
    }

    @Test
    public void testCount(){
        assertEquals(2, memberRepository.count());
    }
}