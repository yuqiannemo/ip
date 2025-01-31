abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws NemoException;

    public boolean isExit() {
        return false;
    }
}
