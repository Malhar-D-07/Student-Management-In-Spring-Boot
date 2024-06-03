package com.malhar.adv_hibernate_demo.dao;

import com.malhar.adv_hibernate_demo.entity.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Type;
import java.util.List;

@Repository
public class AppDaoImpl implements AppDao {

    private EntityManager entityManager;

    public AppDaoImpl(){}

    @Autowired
    public AppDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    @Override
    public void save(Instructor instructor) {
        entityManager.merge(instructor);
    }

    @Transactional
    @Override
    public Instructor findInstructorById(Integer id) {
        return entityManager.find(Instructor.class, id);
    }

    @Override
    public InstructorDetail findInstructorDetailById(Integer id) {
        return entityManager.find(InstructorDetail.class, id);
    }

    @Transactional
    @Override
    public void deleteInstructorDetailById(Integer id) {
        InstructorDetail temp = entityManager.find(InstructorDetail.class, id);
        entityManager.remove(temp);
    }

    @Override
    @Transactional
    public List<Course> getCourseByInstructorId(Integer id) {
        TypedQuery<Course> coursesQuery = entityManager.createQuery("select c from Course c where instructor.id = :instructor_id", Course.class);
        coursesQuery.setParameter("instructor_id", id);
        return coursesQuery.getResultList();
    }

    @Override
    @Transactional
    public Instructor getInstructorByJoinFetch(Integer id) {
        TypedQuery<Instructor> instructorQuery = entityManager.createQuery("select i from Instructor i LEFT JOIN FETCH i.courses where i.id = :instructor_id", Instructor.class);
        instructorQuery.setParameter("instructor_id", id);
        return instructorQuery.getSingleResult();
    }

    @Override
    @Transactional
    public void updateInstructor(Instructor i) {
        entityManager.merge(i);
    }

    @Override
    @Transactional
    public void deleteInstructor(Integer iId) {
        Instructor i = entityManager.find(Instructor.class, iId);
        List<Course> courses = i.getCourses();
        for(Course temp: courses) {
            temp.setInstructor(null);
        }
        entityManager.remove(i);
    }

    @Override
    @Transactional
    public void addReview(Review review, Integer courseId) {
        Course course = entityManager.find(Course.class, courseId);
        course.getReviews().add(review);
        entityManager.merge(course);
    }

    @Override
    @Transactional
    public Course findCourseAndReviews(Integer courseId) {
        TypedQuery<Course> q = entityManager.createQuery("select c from Course c LEFT JOIN FETCH c.reviews where c.id = :courseId", Course.class);
        q.setParameter("courseId", courseId);
        return q.getSingleResult();
    }

    @Override
    @Transactional
    public void addStudentToCourse(Student student, Integer courseId) {
        Course course = entityManager.find(Course.class, courseId);
        course.getStudents().add(student);
    }

    @Override
    @Transactional
    public List<Course> getAllCoursesOfAStudent(Integer studentId) {
        TypedQuery<Student> studentTypedQuery = entityManager.createQuery("select s from Student s LEFT JOIN FETCH s.courses where s.id = :studentId", Student.class);
        studentTypedQuery.setParameter("studentId",studentId);
        Student s = studentTypedQuery.getSingleResult();
        List<Course> courses = s.getCourses();
        return courses;
    }
}

