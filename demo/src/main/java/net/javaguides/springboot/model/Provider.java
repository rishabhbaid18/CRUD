package net.javaguides.springboot.model;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "providers")
public class Provider {

    @Id
    @Column(name = "provider_name")
    private String providerName;

    @Column(name = "flow_name")
    private String flowName;

    @Column(name = "downtime_from")
    private LocalDateTime downtimeFrom;

    @Column(name = "downtime_to")
    private LocalDateTime downtimeTo;

}
