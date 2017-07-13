package com.springbatch.demo.item;

import org.springframework.batch.item.ItemProcessor;

import com.springbatch.demo.entity.Student;

public class StudentProcessor implements ItemProcessor<Student, Student> {

	@Override
	public Student process(Student arg0) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
//	public InventoryAdjustmentIn process(final Student student)
//			throws Exception {
//		student.setAge(student.getAge() + 1);
//		return student;
//	}
}