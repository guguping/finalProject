package com.example.finalproject.entitiy;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "member_search_table")
public class MemberSearchEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
//  검색 하는 사람의 번호
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "search_member_id")
    private MemberEntity searchMember;

//  검색 받는 사람의 번호
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_search_id")
    private MemberEntity memberSearch;

    public static MemberSearchEntity toSaveEntity(MemberEntity loginEntity, MemberEntity searchEntity) {
        MemberSearchEntity memberSearchEntity = new MemberSearchEntity();
        memberSearchEntity.setSearchMember(loginEntity);
        memberSearchEntity.setMemberSearch(searchEntity);
        return memberSearchEntity;
    }
}
