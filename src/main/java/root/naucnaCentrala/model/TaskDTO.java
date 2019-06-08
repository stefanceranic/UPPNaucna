package root.naucnaCentrala.model;

public class TaskDTO {

    private String taskId;
    private String name;
    private String processId;
    private String rad;

    public TaskDTO() {
    }

    public TaskDTO(String taskId, String name, String processId, String rad) {
        this.taskId = taskId;
        this.name = name;
        this.processId = processId;
        this.rad = rad;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProcessId() {
        return processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }

    public String getRad() {
        return rad;
    }

    public void setRad(String article) {
        this.rad = article;
    }
}
