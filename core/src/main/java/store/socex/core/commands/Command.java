package store.socex.core.commands;

public interface Command extends Runnable {
    default String getType() {
        return getClass().getSimpleName();
    }

    default CommandKind getKind() {
        return CommandKind.Unknown;
    };

    default int getUnixSignal() {
        return -1;
    }

    default int getAcknowledgeDelay() {
        return -1;
    };

    default String getAcknowledgeChannel() {
        return  null;
    };
    int getStartWaitingTimeout();
    int getExecutionTimeout();
}
