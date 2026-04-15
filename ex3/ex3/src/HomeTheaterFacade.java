public class HomeTheaterFacade {
    private TV tv;
    private Projetor projetor;
    private Receiver receiver;
    private PlayerMidia player;
    private Som som;
    private Luzes luzes;

    public HomeTheaterFacade() {
        this.tv = new TV();
        this.projetor = new Projetor();
        this.receiver = new Receiver();
        this.player = new PlayerMidia();
        this.som = new Som();
        this.luzes = new Luzes();
    }

    public void assistirFilme() {
        System.out.println("\n--- Iniciando Modo Cinema ---");
        projetor.ligar();
        projetor.modoCinema();
        receiver.ligar();
        receiver.configurarEntrada();
        som.ligar();
        player.ligar();
        luzes.desligar();
    }

    public void ouvirMusica() {
        System.out.println("\n--- Iniciando Modo Música ---");
        receiver.ligar();
        som.ligar();
        player.ligar();
        luzes.ligar();
    }

    public void encerrarSistema() {
        System.out.println("\n--- Desligando Home Theater ---");
        player.desligar();
        som.desligar();
        receiver.desligar();
        projetor.desligar();
        tv.desligar();
        luzes.desligar();
    }
}