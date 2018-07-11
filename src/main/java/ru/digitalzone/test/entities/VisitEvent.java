package ru.digitalzone.test.entities;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name = "visitEvents")
@Table(name = "visitEvents")
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class VisitEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    @Column
    private String userName;
    @Column
    @Getter
    @Setter
    private String site;
    @Column
    @Getter
    @Setter
    private LocalDate visitDate;

    public VisitEvent(String userName, String site, LocalDate visitDate) {
        this.userName = userName;
        this.site = site;
        this.visitDate = visitDate;
    }

}
