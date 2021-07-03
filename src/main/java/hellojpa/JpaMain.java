package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        final EntityManagerFactory enf = Persistence.createEntityManagerFactory("hello");
        final EntityManager em = enf.createEntityManager();

        final EntityTransaction tx = em.getTransaction();
        tx.begin();

        //code
        try {
            Member member = new Member();
            member.setId(1L);
            member.setName("HelloA");

            //회원 등록
            em.persist(member);

            Member member2 = new Member();
            member2.setId(2L);
            member2.setName("HelloB");

            em.persist(member2);

            //회원 조회
            final Member findMember = em.find(Member.class, 2L);
            final List<Member> findMembers = em.createQuery("select m from Member as m", Member.class).getResultList();

            //회원 삭제
            em.remove(findMember);

            //회원 수정
            findMember.setName("HelloJPA"); //persist() 안 해줘도 됨

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        enf.close();
    }
}
