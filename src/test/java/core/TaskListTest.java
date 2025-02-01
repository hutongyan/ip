package core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import task.Task;
import task.Todo;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

class TaskListTest {

    private TaskList taskList;

    @BeforeEach
    void setUp() {
        taskList = new TaskList();
    }

    @Test
    void addTask_addsTaskSuccessfully() {
        Task task = new Todo("Read book");

        taskList.addTask(task);

        // 检查列表大小是否增加
        assertEquals(1, taskList.getTasks().size());

        // 确保列表中的任务是刚刚添加的任务
        assertSame(task, taskList.getTasks().get(0));
    }
}
