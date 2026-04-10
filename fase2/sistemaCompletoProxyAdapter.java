class ConfigurationManager {
    private static ConfigurationManager instance;
    private int maxRetries = 2;

    private ConfigurationManager() {}

    public static ConfigurationManager getInstance() {
        if (instance == null) instance = new ConfigurationManager();
        return instance;
    }

    public int getMaxRetries() { return maxRetries; }
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

class ExternalSmsService {
    public void enviarSMS(String texto) {
        System.out.print("[Servico Externo] SMS enviado: " + texto);
    }
}

class SmsAdapter extends Notification {
    private ExternalSmsService legacyService = new ExternalSmsService();

    @Override
    protected boolean deliver(String msg) {
        legacyService.enviarSMS(msg);
        return true;
    }
}

class NotificationProxy extends Notification {
    private Notification realNotification;

    public NotificationProxy(Notification realNotification) {
        this.realNotification = realNotification;
    }

    @Override
    protected boolean deliver(String msg) {
        System.out.println("[LOG] Interceptando chamada...");
        return realNotification.deliver(msg);
    }
}

class NotificationFactory {
    public static Notification create(String tipo) {
        Notification n = null;

        if (tipo.equalsIgnoreCase("email")) {
            n = new EmailNotification();
        } else if (tipo.equalsIgnoreCase("sms")) {
            n = new SmsAdapter();
        }

        if (n != null) return new NotificationProxy(n);
        return null;
    }
}

public class Main {
    public static void main(String[] args) {
        Notification n = NotificationFactory.create("sms");
        if (n != null) n.send("Ola com Adapter e Proxy!");
    }
}
