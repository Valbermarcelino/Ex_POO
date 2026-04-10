class ConfigurationManager {
    private static ConfigurationManager instance;
    private int maxRetries = 3;

    private ConfigurationManager() {}

    public static ConfigurationManager getInstance() {
        if (instance == null) {
            instance = new ConfigurationManager();
        }
        return instance;
    }

    public int getMaxRetries() {
        return maxRetries;
    }
}

abstract class Notification {
    public final void send(String msg) {
        int limit = ConfigurationManager.getInstance().getMaxRetries();
        
        for (int i = 1; i <= limit; i++) {
            System.out.print("Tentativa " + i + ": ");
            if (deliver(msg)) {
                System.out.println("SUCESSO");
                return; 
            }
            System.out.println("FALHA");
        }
    }

    protected abstract boolean deliver(String msg);
}

class EmailNotification extends Notification {
    @Override
    protected boolean deliver(String msg) { 
        System.out.print("[Email] ");
        return true; 
    }
}

class SMSNotification extends Notification {
    @Override
    protected boolean deliver(String msg) { 
        System.out.print("[SMS] ");
        return true; 
    }
}

class NotificationFactory {
    public static Notification create(String tipo) {
        if (tipo.equalsIgnoreCase("email")) {
            return new EmailNotification();
        } 
        
        if (tipo.equalsIgnoreCase("sms")) {
            return new SMSNotification();
        }
        
        return null;
    }
}

public class Main {
    public static void main(String[] args) {
        Notification n = NotificationFactory.create("email");

        if (n != null) {
            n.send("Ola! Teste");
        }
    }
}