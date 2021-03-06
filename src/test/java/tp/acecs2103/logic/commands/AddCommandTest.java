package tp.acecs2103.logic.commands;

import org.junit.jupiter.api.Test;
import tp.acecs2103.logic.commands.exceptions.CommandException;
import tp.acecs2103.model.Model;
import tp.acecs2103.model.ModelManager;
import tp.acecs2103.model.UserPrefs;
import tp.acecs2103.model.task.*;
import tp.acecs2103.testutil.TypicalTasks;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class AddCommandTest {

    @Test
    public void execute_NewIPTask_success() {
        Model model = new ModelManager(TypicalTasks.getTypicalTaskList(), new UserPrefs());
        ModelManager expectedModel = new ModelManager(TypicalTasks.getTypicalTaskList(), new UserPrefs());

        Task task = new IP(new Index("0210"), new WeekNumber("2"), new Description("Test Task One"),
                new OfficialDeadline("2020-09-10", LocalDate.of(2020, 9, 10)),
                null, new Remark("no remark"), false, false);

        AddCommand addCommand = null;
        CommandResult commandResult = null;
        try {
            addCommand = new AddCommand(task);
            commandResult = addCommand.execute(model);
        } catch (CommandException e) {
            e.printStackTrace();
        }

        expectedModel.addTask(task);

        String expectedMessage = String.format(AddCommand.MESSAGE_SUCCESS, task);
        assertEquals(expectedMessage, commandResult.getFeedbackToUser());
        assertEquals(expectedModel, model);
    }

    @Test
    public void execute_NewTPTask_success() {
        Model model = new ModelManager(TypicalTasks.getTypicalTaskList(), new UserPrefs());
        ModelManager expectedModel = new ModelManager(TypicalTasks.getTypicalTaskList(), new UserPrefs());

        Task task = new TP(new Index("0203"), new WeekNumber("2"), new Description("Test Task One"),
                new OfficialDeadline("2020-09-10", LocalDate.of(2020, 9, 10)),
                null, new Remark("no remark"), false, false);

        AddCommand addCommand = null;
        CommandResult commandResult = null;
        try {
            addCommand = new AddCommand(task);
            commandResult = addCommand.execute(model);
        } catch (CommandException e) {
            e.printStackTrace();
        }

        expectedModel.addTask(task);

        String expectedMessage = String.format(AddCommand.MESSAGE_SUCCESS, task);
        assertEquals(expectedMessage, commandResult.getFeedbackToUser());
        assertEquals(expectedModel, model);
    }

    @Test
    public void execute_NewAdminTask_success() {
        Model model = new ModelManager(TypicalTasks.getTypicalTaskList(), new UserPrefs());
        ModelManager expectedModel = new ModelManager(TypicalTasks.getTypicalTaskList(), new UserPrefs());

        Task task = new Admin(new Index("0210"), new WeekNumber("2"), new Description("Test Task One"),
                new OfficialDeadline("2020-09-10", LocalDate.of(2020, 9, 10)),
                null, new Remark("no remark"), false, false);

        AddCommand addCommand = null;
        CommandResult commandResult = null;
        try {
            addCommand = new AddCommand(task);
            commandResult = addCommand.execute(model);
        } catch (CommandException e) {
            e.printStackTrace();
        }

        expectedModel.addTask(task);

        String expectedMessage = String.format(AddCommand.MESSAGE_SUCCESS, task);
        assertEquals(expectedMessage, commandResult.getFeedbackToUser());
        assertEquals(expectedModel, model);
    }

    @Test
    public void execute_NewTopicTask_success() {
        Model model = new ModelManager(TypicalTasks.getTypicalTaskList(), new UserPrefs());
        ModelManager expectedModel = new ModelManager(TypicalTasks.getTypicalTaskList(), new UserPrefs());

        Task task = new Topic(new Index("0210"), new WeekNumber("2"), new Description("Test Task One"),
                new OfficialDeadline("2020-09-10", LocalDate.of(2020, 9, 10)),
                null, new Remark("no remark"), false, false);

        AddCommand addCommand = null;
        CommandResult commandResult = null;
        try {
            addCommand = new AddCommand(task);
            commandResult = addCommand.execute(model);
        } catch (CommandException e) {
            e.printStackTrace();
        }

        expectedModel.addTask(task);

        String expectedMessage = String.format(AddCommand.MESSAGE_SUCCESS, task);
        assertEquals(expectedMessage, commandResult.getFeedbackToUser());
        assertEquals(expectedModel, model);
    }

    @Test
    public void execute_inconsistentIndex_fail() {
        Model model = new ModelManager(TypicalTasks.getTypicalTaskList(), new UserPrefs());
        ModelManager expectedModel = new ModelManager(TypicalTasks.getTypicalTaskList(), new UserPrefs());

        Task task = new IP(new Index("0203"), new WeekNumber("1"), new Description("Test Task One"),
                new OfficialDeadline("2020-09-10", LocalDate.of(2020, 9, 10)),
                null, new Remark("no remark"), false, false);

        assertThrows(CommandException.class, () -> new AddCommand(task).execute(model));
    }

    @Test
    public void execute_existingTask_fail() {
        Model model = new ModelManager(TypicalTasks.getTypicalTaskList(), new UserPrefs());
        ModelManager expectedModel = new ModelManager(TypicalTasks.getTypicalTaskList(), new UserPrefs());

        Task task = new IP(new Index("0101"), new WeekNumber("1"), new Description("Test Task One"),
                new OfficialDeadline("2020-09-10", LocalDate.of(2020, 9, 10)),
                null, new Remark("no remark"), false, false);

        assertThrows(CommandException.class, () -> new AddCommand(task).execute(model));
    }

    @Test
    public void execute_missingDDL_fail() {
        Model model = new ModelManager(TypicalTasks.getTypicalTaskList(), new UserPrefs());
        ModelManager expectedModel = new ModelManager(TypicalTasks.getTypicalTaskList(), new UserPrefs());

        Task task = new IP(new Index("0203"), new WeekNumber("2"), new Description("Test Task One"),
                new OfficialDeadline("2020-09-10", LocalDate.of(2020, 9, 10)),
                null, new Remark("no remark"), true, false);
        Task task2 = new IP(new Index("0203"), new WeekNumber("2"), new Description("Test Task One"),
                null, new CustomizedDeadline("2020-09-10",
                LocalDate.of(2020, 9, 10)), new Remark("no remark"),
                false, false);

        assertThrows(CommandException.class, () -> new AddCommand(task).execute(model));
        assertThrows(CommandException.class, () -> new AddCommand(task2).execute(model));
    }

    @Test
    public void execute_extraDDL_fail() {
        Model model = new ModelManager(TypicalTasks.getTypicalTaskList(), new UserPrefs());
        ModelManager expectedModel = new ModelManager(TypicalTasks.getTypicalTaskList(), new UserPrefs());

        Task task = new IP(new Index("0203"), new WeekNumber("2"), new Description("Test Task One"),
                new OfficialDeadline("2020-09-10", LocalDate.of(2020, 9, 10)),
                new CustomizedDeadline("2020-09-10",
                        LocalDate.of(2020, 9, 10)), new Remark("no remark"),
                true, false);

        assertThrows(CommandException.class, () -> new AddCommand(task).execute(model));
    }

    @Test
   public void equals_null_returnTrue() {
        Task task = new Admin(new Index("0203"), new WeekNumber("2"), new Description("Test Task One"),
                new OfficialDeadline("2020-09-10", LocalDate.of(2020, 9, 10)),
                null, new Remark("no remark"), false, false);
        AddCommand addCommand = null;
        AddCommand addCommand1 = null;
        try {
            addCommand = new AddCommand(task);
            addCommand1 = new AddCommand(task);
        } catch (CommandException e) {
            e.printStackTrace();
        }

        assertTrue(addCommand.equals(addCommand1));
    }

    @Test
    public void equals_null_returnFalse() {
        Task task = new IP(new Index("0203"), new WeekNumber("2"), new Description("Test Task One"),
                new OfficialDeadline("2020-09-10", LocalDate.of(2020, 9, 10)),
                null, new Remark("no remark"), false, false);
        Task task1 = new TP(new Index("0204"), new WeekNumber("2"), new Description("Test Task One"),
                new OfficialDeadline("2020-09-10", LocalDate.of(2020, 9, 10)),
                null, new Remark("no remark"), false, false);
        Task task2 = new Admin(new Index("0205"), new WeekNumber("2"), new Description("Test Task"),
                new OfficialDeadline("2020-09-10", LocalDate.of(2020, 9, 10)),
                null, new Remark("no remark"), false, false);
        AddCommand addCommand = null;
        AddCommand addCommand1 = null;
        AddCommand addCommand2 = null;
        try {
            addCommand = new AddCommand(task);
            addCommand1 = new AddCommand(task1);
            addCommand2 = new AddCommand(task2);
        } catch (CommandException e) {
            e.printStackTrace();
        }
        assertFalse(addCommand.equals(addCommand1));
        assertFalse(addCommand.equals(addCommand2));
    }

    @Test
    public void get_null_Task() {
        Task task = new IP(new Index("0203"), new WeekNumber("2"), new Description("Test Task One"),
                new OfficialDeadline("2020-09-10", LocalDate.of(2020, 9, 10)),
                null, new Remark("no remark"), false, false);
        AddCommand addCommand = null;
        try {
            addCommand = new AddCommand(task);
        } catch (CommandException e) {
            e.printStackTrace();

            assertTrue(task.equals(addCommand.getTaskToAdd()));
        }
    }
}