package jpabook.jpashop.repository;

import jpabook.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    public void save(Item item) {
        if (item.getId() == null) {
            em.persist(item);
        } else {
            //병합시 동작 방식
            //1. 준영속 엔티티의 식별자 값으로 영속 엔티티를 조회
            //2. 영속 엔티티의 값을 준영속 엔티티의 값으로 모두 교체
            //3. 트랜잭션 커밋 시점에 변경 감지 기능이 동작해서 데이터베이스에 UPDATE SQL 실행
            //주의: 병합을 사용하면 모든 속성이 변경된다. 병합 시 값이 없으면 null로 업데이트 할 위험이 있다(병합은 모든 필드를 교체)
            em.merge(item);
        }
    }

    public Item findOne(Long id) {
       return em.find(Item.class, id);
    }

    public List<Item> findAll() {
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }
}
