package task.tracker.controllers;

import task.tracker.entities.Task;

public class TaskDTO {

    public TaskDTO(Task task) {
        id = task.getId();
        text = task.getText();
    }

    public Long id;
    
    public String text;
    
    public boolean state;
    
}
