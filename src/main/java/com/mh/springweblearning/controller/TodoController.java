package com.mh.springweblearning.controller;

import com.mh.springweblearning.model.Todo;
import com.mh.springweblearning.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


@Controller
@SessionAttributes("name")
public class TodoController {

	@InitBinder
	private void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
			dateFormat, false
		));
	}


	@Autowired
	private TodoService service;

	private void listAttributes(ModelMap model) {
		if(null!=model && null!=model.entrySet())
		model.keySet().stream().forEach((s)-> System.out.println("Key:"+s+" value:"+model.get(s)));

	}

	@RequestMapping(value = "/list-todos", method = RequestMethod.GET)
	public String listTodos(ModelMap model) {
		listAttributes(model);
		model.addAttribute("todos", service.retrieveTodos(model.get("name").toString()));
		return "list-todos";
	}

	@RequestMapping(value = "/add-todo", method = RequestMethod.GET)
	public String showAddTodoPage(ModelMap model) {
		listAttributes(model);
		model.addAttribute("todo", new Todo());
		return "todo";
	}

	@RequestMapping(value = "/add-todo", method = RequestMethod.POST)
	public String submitAddTodoPage(ModelMap model, @Valid Todo todo, BindingResult result) {

		if (result.hasErrors())
			return "todo";

		service.addTodo((String) model.get("name"), todo.getDesc(), todo.getTargetDate(),
				false);
		model.clear();// to prevent request parameter "name" to be passed
		return "redirect:/list-todos";

	}
	@RequestMapping(value = "/delete-todo", method = RequestMethod.GET)
	public String deleteTodo(ModelMap model, @RequestParam(value="id") Integer id) {
		service.deleteTodo(id.intValue());
		model.clear();
		return "redirect:/list-todos";
	}


	@RequestMapping(value = "/edit-todo", method = RequestMethod.GET)
	public String showEditTodoPage(ModelMap model, @RequestParam(value = "id") Integer id) {
		System.out.println("Showing Edit Todo");
		listTodos(model);
		Todo todo = service.retrieveTodo(model.get("name").toString(), id.intValue());
		model.addAttribute("todo", todo);
		return "todo";
	}

	@RequestMapping(value = "/edit-todo", method = RequestMethod.POST)
	public String submitEditTodoPage(ModelMap model, @Valid Todo todo, BindingResult result) {
		System.out.println("Showing Edit Todo Before submit");
		listAttributes(model);
		if (result.hasErrors())
			return "todo";

		Todo todotoupdate = null;
		if(todo.getId() > 0) {
			todotoupdate = service.retrieveTodo(model.get("name").toString(), todo.getId());
			todotoupdate.setDesc(todo.getDesc());
			todotoupdate.setTargetDate(todo.getTargetDate());
			service.update(todotoupdate);
			return "redirect:/list-todos";
		} else {
			result.reject("Todo Not Found");
			model.addAttribute("todo" , todo);
			return "todo";
		}	}


}