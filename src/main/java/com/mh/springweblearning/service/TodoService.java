package com.mh.springweblearning.service;

import java.util.*;

import com.mh.springweblearning.model.Todo;
import org.springframework.stereotype.Service;


@Service
public class TodoService {
	private static List<Todo> todos = new ArrayList<Todo>();
	private static int todoCount = 3;

	static {
		todos.add(new Todo(1, "manu", "Learn Spring MVC", new Date(),
				false));
		todos.add(new Todo(2, "manu", "Learn Struts", new Date(), false));
		todos.add(new Todo(3, "manu", "Learn Hibernate", new Date(),
				false));
	}

	public Todo retrieveTodo(String user, int id) {
		for (Todo todo : todos) {
			if (todo.getUser().equals(user) && todo.getId()==id)
				return todo;
		}
		return null;
	}

	public void update(Todo todo) {
		this.deleteTodo(todo.getId());
		this.todos.add(todo);
	}

	public List<Todo> retrieveTodos(String user) {
		List<Todo> filteredTodos = new ArrayList<Todo>();
		for (Todo todo : todos) {
			if (todo.getUser().equals(user))
				filteredTodos.add(todo);
		}
		Collections.sort(filteredTodos , (o1, o2) -> o1.getId() < o2.getId() ? 0:1);
		return filteredTodos;
	}

	public void addTodo(String name, String desc, Date targetDate, boolean isDone) {
		todos.add(new Todo(++todoCount, name, desc, targetDate, isDone));
	}

	public void deleteTodo(int id) {
		Iterator<Todo> iterator = todos.iterator();
		while (iterator.hasNext()) {
			Todo todo = iterator.next();
			if (todo.getId() == id) {
				iterator.remove();
			}
		}
	}
}