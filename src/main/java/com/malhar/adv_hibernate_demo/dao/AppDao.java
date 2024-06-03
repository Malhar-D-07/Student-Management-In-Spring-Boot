package com.malhar.adv_hibernate_demo.dao;

import com.malhar.adv_hibernate_demo.entity.*;

import java.util.List;

public interface AppDao {
    public void save(Instructor instructor);

    public Instructor findInstructorById(Integer id);

    public InstructorDetail findInstructorDetailById(Integer id);

    public void deleteInstructorDetailById(Integer id);

    public List<Course> getCourseByInstructorId(Integer id);

    public Instructor getInstructorByJoinFetch(Integer id);

    public void updateInstructor(Instructor i);

    public void deleteInstructor(Integer iId);

    public void addReview(Review review, Integer courseId);

    public Course findCourseAndReviews(Integer courseId);

    public void addStudentToCourse(Student student, Integer courseId);

    public List<Course> getAllCoursesOfAStudent(Integer studentId);
}
