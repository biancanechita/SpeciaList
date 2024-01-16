package ro.uaic.info.userauthenticationservice.entities;

import lombok.Data;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Audited
@Data
@MappedSuperclass
public class BaseEntity {

    @Column(name = "created_date_time", nullable = false, updatable = false)
    @CreatedDate
    private LocalDateTime createdDateTime;

    @Column(name = "modified_date_time")
    @LastModifiedDate
    private LocalDateTime modifiedDateTime;
}