package com.blogcode.base;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import java.time.LocalDateTime;

/**
 * <pre>
 * com.blogcode.base
 *
 * Description :
 * </pre>
 *
 * @author leejinho
 * @version 1.0
 * @see <pre>
 * == 개정이력(Modification Information) ==
 *
 * 수정일     수정자   수정내용
 * ---------- -------- -------------------
 * 2021.07.13 leejinho 최초 생성
 * </pre>
 * @since 2021.07.13
 */
//@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@Setter @Getter
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {
    @Column(name="CREATE_ID", updatable = false)
    protected Long createId;

    @CreatedDate
    @Column(name="CREATE_DATE_TIME", updatable = false)
    private LocalDateTime createDateTime;

    @Column(name="MODIFY_ID")
    protected Long modifyId;

    @LastModifiedDate
    @Column(name="MODIFY_DATE_TIME")
    private LocalDateTime modifyDateTime;

    @Column
    private Integer useYn;
}
