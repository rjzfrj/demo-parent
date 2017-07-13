package com.springbatch.demo.item;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.batch.item.ItemWriter;
import com.springbatch.demo.entity.Student;

public class StudentWriter implements ItemWriter<Student> {


	@Override
	public void write(final List<? extends Student> students) throws Exception {

		for (Student item : students) {
			System.out.println(item);
		}
	}
}