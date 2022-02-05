package store.socex.lib.commands;

public class NestedCommand implements Command {

    @Override
    public int getStartWaitingTimeout() {
        return 200;
    }

    @Override
    public int getExecutionTimeout() {
        return 5000;
    }

    @Override
    public void run() {

    }
}
