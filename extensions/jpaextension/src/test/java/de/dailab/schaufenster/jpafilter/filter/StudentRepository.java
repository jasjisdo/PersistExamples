package de.dailab.schaufenster.jpafilter.filter;

import de.dailab.schaufenster.jpafilter.annotation.EntityFilter;
import de.dailab.schaufenster.jpafilter.annotation.FilterQuery;
import de.dailab.schaufenster.jpafilter.repository.HibernateJpaRepository;

@EntityFilter(
        filterQueries = {
                @FilterQuery(name = "query1",
                        jpql = "SELECT s FROM Student LEFT JOIN FETCH s.Subject where s.subject = :subject"),
                @FilterQuery(name = "query2",
                        jpql = "SELECT s FROM Student LEFT JOIN s.TeacherSubject where s.teacher =  :teacher")
        }
)
public interface StudentRepository extends HibernateJpaRepository<Student, Long> {
}