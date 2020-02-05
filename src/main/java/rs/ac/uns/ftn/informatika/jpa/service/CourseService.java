package rs.ac.uns.ftn.informatika.jpa.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.informatika.jpa.model.Course;
import rs.ac.uns.ftn.informatika.jpa.model.Klinika;
import rs.ac.uns.ftn.informatika.jpa.model.LekarOdsustvo;
import rs.ac.uns.ftn.informatika.jpa.model.Pregled;
import rs.ac.uns.ftn.informatika.jpa.model.ZauzetostLekara;
import rs.ac.uns.ftn.informatika.jpa.model.ZauzetostSala;
import rs.ac.uns.ftn.informatika.jpa.repository.CourseRepository;

@Service
public class CourseService {

	@Autowired
	private CourseRepository courseRepository;

	public Course findOne(Long id) {
		return courseRepository.findById(id).orElseGet(null);
	}

	public List<Course> findAll() {
		return courseRepository.findAll();
	}

	public Page<Course> findAll(Pageable page) {
		return courseRepository.findAll(page);
	}

	public Course save(Course course) {
		return courseRepository.save(course);
	}

	public void remove(Long id) {
		courseRepository.deleteById(id);
	}
}