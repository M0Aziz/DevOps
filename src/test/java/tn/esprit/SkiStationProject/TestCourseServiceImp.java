package tn.esprit.SkiStationProject;

import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.SkiStationProject.entities.Course;
import tn.esprit.SkiStationProject.repositories.CourseRepository;
import tn.esprit.SkiStationProject.services.CourseServicesImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class TestCourseServiceImp {

    @InjectMocks
    private CourseServicesImpl courseServices;

    @Mock
    private CourseRepository courseRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testRetrieveAllCourses() {
        // Créez un ensemble de données fictives pour simuler les données de la base de données
        Course course1 = new Course(1L, "Course 1", "Description 1");
        Course course2 = new Course(2L, "Course 2", "Description 2");

        List<Course> courseList = new ArrayList<>();
        courseList.add(course1);
        courseList.add(course2);

        when(courseRepository.findAll()).thenReturn(courseList);

        List<Course> courses = courseServices.retrieveAllCourses();

        assertEquals(2, courses.size());
        assertEquals(course1, courses.get(0));
        assertEquals(course2, courses.get(1));
    }

    @Test
    public void testAddCourse() {
        Course newCourse = new Course(3L, "New Course", "New Description");

        when(courseRepository.save(newCourse)).thenReturn(newCourse);

        Course addedCourse = courseServices.addCourse(newCourse);

        assertEquals(newCourse, addedCourse);
    }

    @Test
    public void testUpdateCourse() {
        Course existingCourse = new Course(1L, "Course 1", "Description 1");
        Course updatedCourse = new Course(1L, "Updated Course", "Updated Description");

        when(courseRepository.save(updatedCourse)).thenReturn(updatedCourse);
        when(courseRepository.findById(1L)).thenReturn(Optional.of(existingCourse));

        Course result = courseServices.updateCourse(updatedCourse);

        assertEquals(updatedCourse, result);
    }

    @Test
    public void testRetrieveCourse() {
        Long courseId = 1L;
        Course existingCourse = new Course(courseId, "Course 1", "Description 1");

        when(courseRepository.findById(courseId)).thenReturn(Optional.of(existingCourse));

        Course result = courseServices.retrieveCourse(courseId);

        assertEquals(existingCourse, result);
    }
}
