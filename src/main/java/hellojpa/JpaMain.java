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
            //팀 저장
            Team team = new Team();
            team.setName("teamA");
            em.persist(team);

            //회원 등록
            Member member = new Member();
            member.setName("HelloA");
            member.setTeam(team); //단방향 연관관계 설정, 참조 저장

            em.persist(member);

//            Member member2 = new Member();
//            member2.setName("HelloB");
//
//            em.persist(member2);

            //회원 조회
            final Member findMember = em.find(Member.class, member.getId());
//            final List<Member> findMembers = em.createQuery("select m from Member as m", Member.class).getResultList();

            //팀 조회
            Team findTeam = findMember.getTeam();

            //회원 삭제
//            em.remove(findMember);

            //회원 수정
            findMember.setName("Hello"); //persist() 안 해줘도 됨

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        enf.close();
    }
}
