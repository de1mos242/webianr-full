package task.tracker.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import task.tracker.entities.Task;


@Transactional
@RestController
@RequestMapping(path="/rest/tasks/")
public class TaskRestController {

    @Autowired
    SessionFactory sessionFactory;
    
    @SuppressWarnings("unchecked")
    @RequestMapping(method = RequestMethod.GET)
    public List<TaskDTO> getTasks() {
        Session session = sessionFactory.getCurrentSession();
        List<TaskDTO> dtos = null;
        Criteria criteria = session.createCriteria(Task.class);
        List<Task> tasks = (List<Task>) criteria.list();
        dtos = tasks.stream().map(TaskDTO::new).collect(Collectors.toList());
        return dtos;
    }

    @RequestMapping(path = "{id:\\d+}", method = RequestMethod.GET)
    public TaskDTO getTask(@PathVariable long id) {
        Session session = sessionFactory.getCurrentSession();
        Task task = session.byId(Task.class).load(id);
        return new TaskDTO(task);
    }

}
