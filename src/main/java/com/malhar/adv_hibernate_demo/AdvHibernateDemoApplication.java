package com.malhar.adv_hibernate_demo;

import com.malhar.adv_hibernate_demo.dao.AppDao;
import com.malhar.adv_hibernate_demo.entity.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class AdvHibernateDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdvHibernateDemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AppDao appDao) {
		return runner -> {
			createInstructor(appDao);
		};
	}

	private void createInstructor(AppDao appDao) {
		Scanner sc = new Scanner(System.in);
		String command = "";

		while (!sc.nextLine().equals("exit")) {
			System.out.println("Enter 1 for adding Instructor");
			System.out.println("Enter 2 for fetching Instructor by Id");
			System.out.println("Enter 3 for fetching InstructorDetail by Id");
			System.out.println("Enter 4 for Deleting InstructorDetail by Id");
			System.out.println("Enter 5 for Adding new Courses to Instructor");
			System.out.println("Enter 6 for Updating Instructor");
			System.out.println("Enter 7 for Deleting Instructor");
			System.out.println("Enter 8 for Adding Review for a course");
			System.out.println("Enter 9 for fetching a course and its reviews");
			System.out.println("Enter 10 for Enrolling a Student into a Course");
			System.out.println("Enter 11 for Fetching all Courses enrolled by a Student");
			System.out.println("Enter exit for exiting");
			String instructionId = sc.nextLine();

			if(Integer.parseInt(instructionId) == 1) {
				System.out.println("Enter First Name");
				String fName = sc.nextLine();

				System.out.println("Enter Last Name");
				String lName = sc.nextLine();

				String email = STR."\{fName}.\{lName}@Gmail.com";

				String hobby = "Coding";
				String channel = STR."https://\{fName}_\{lName}.com/youtube";

				Instructor instructor = new Instructor(fName, lName, email);
				InstructorDetail instructorDetail = new InstructorDetail(hobby, channel);
				instructor.setInstructorDetail(instructorDetail);
				appDao.save(instructor);
			}

			if(Integer.parseInt(instructionId) == 2) {
				System.out.println("Enter Id:");
				String id = sc.nextLine();
				System.out.println(appDao.findInstructorById(Integer.parseInt(id)));
			}

			if(Integer.parseInt(instructionId) == 3) {
				System.out.println("Enter Details Id:");
				String id = sc.nextLine();
				System.out.println(appDao.findInstructorById(Integer.parseInt(id)));
			}

			if(Integer.parseInt(instructionId) == 4) {
				System.out.println("Enter Details Id:");
				String id = sc.nextLine();
				appDao.deleteInstructorDetailById(Integer.parseInt(id));
			}

			if(Integer.parseInt(instructionId) == 5) {
				System.out.println("Enter Instructor Id to fetch:");
				String id = sc.nextLine();
				Instructor instructor = appDao.getInstructorByJoinFetch(Integer.parseInt(id));

				System.out.println("Enter the number of courses to add");
				int noOfCourses = Integer.parseInt(sc.nextLine());

				for (int i = 0; i < noOfCourses; i++) {
					System.out.println("Enter Course title");
					String courseTitle = sc.nextLine();
					Course c = new Course();
					c.setTitle(courseTitle);
					instructor.add(c);
				}
				appDao.save(instructor);
			}

			if(Integer.parseInt(instructionId) == 6) {
				System.out.println("Enter Instructor Id to update:");
				int id = Integer.parseInt(sc.nextLine());
				Instructor instructor = appDao.findInstructorById(id);
				System.out.println("Enter updated First Name");
				String updatedFName = sc.nextLine();

				System.out.println("Enter updated Last Name");
				String updatedLName = sc.nextLine();

				instructor.setFirstName(updatedFName);
				instructor.setLastName(updatedLName);
				appDao.updateInstructor(instructor);
			}

			if(Integer.parseInt(instructionId) == 7) {
				System.out.println("Enter Instructor Id to Delete:");
				int id = Integer.parseInt(sc.nextLine());
				appDao.deleteInstructor(id);
			}

			if(Integer.parseInt(instructionId) == 8) {
				System.out.println("Enter Course Id for adding a Review:");
				int courseId = Integer.parseInt(sc.nextLine());
				System.out.println("Enter Review Comment: ");
				String comment = sc.nextLine();
				Review review = new Review();
				review.setComment(comment);
				appDao.addReview(review, courseId);
			}

			if(Integer.parseInt(instructionId) == 9) {
				System.out.println("Enter Course Id for fetching");
				int courseId = Integer.parseInt(sc.nextLine());
				Course course = appDao.findCourseAndReviews(courseId);

				System.out.println("Fetching Result: ");

				System.out.println(course.toString());
			}

			if(Integer.parseInt(instructionId) == 10) {
				System.out.println("Enter Course Id for adding Student into it");
				int courseId = Integer.parseInt(sc.nextLine());

				System.out.println("Enter First Name: ");
				String fName = sc.nextLine();

				System.out.println("Enter Last Name: ");
				String lName = sc.nextLine();

				String email = fName + "." + lName + "@gmail.com";
				Student student = new Student(fName, lName, email);
				appDao.addStudentToCourse(student, courseId);
			}

			//TODO: This function is giving LazyInitException. Please look into it and gain learning
			if(Integer.parseInt(instructionId) == 11) {
				System.out.println("Enter Student Id for fetching courses");
				int studentId = Integer.parseInt(sc.nextLine());

				List<Course> courses = appDao.getAllCoursesOfAStudent(studentId);
				System.out.println(courses);
			}

			if(instructionId.equals("exit")) {
				command = "exit";
			}
		}
		sc.close();
	}
}
