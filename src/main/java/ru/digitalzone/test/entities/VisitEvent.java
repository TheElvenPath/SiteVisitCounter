package ru.digitalzone.test.entities;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity(name = "visitEvents")
@Table(name = "visitEvents")
public class VisitEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String userName;
    @Column
    private String site;
    @Column
    private LocalDate visitDate;

    public VisitEvent() {
    }

    public VisitEvent(String userName, String site, LocalDate visitDate) {
        this.userName = userName;
        this.site = site;
        this.visitDate = visitDate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public LocalDate getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(LocalDate visitDate) {
        this.visitDate = visitDate;
    }

    @Override
    public String toString() {

        return "VisitEvent{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", site='" + site + '\'' +
                ", visitDate=" + visitDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VisitEvent that = (VisitEvent) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(userName, that.userName) &&
                Objects.equals(site, that.site) &&
                Objects.equals(visitDate, that.visitDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, userName, site, visitDate);
    }

}
