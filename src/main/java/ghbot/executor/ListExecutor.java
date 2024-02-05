package ghbot.executor;

/**
 * ListExecutor Class.
 * Executes "list" command.
 */
public class ListExecutor extends Executor {
    /**
     * ListExecutor Constructor.
     */
    public ListExecutor() {
    }

    /**
     * Prints all the saved tasks.
     */
    @Override
    public void execute() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < this.taskList.taskSize(); i++) {
            System.out.println(i + 1 + "." + this.taskList.getTask(i));
        }
    }
}
