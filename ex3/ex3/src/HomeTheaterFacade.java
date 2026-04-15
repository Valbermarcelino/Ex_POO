public class HomeTheaterFacade {
    private TV tv;
    private Som som;
    private Luzes luzes;

    public HomeTheaterFacade() {
        this.tv = new TV();
        this.som = new Som();
        this.luzes = new Luzes();
    }

    public void assistirFilme() {
        System.out.println("\nIniciando Configuração de filme");
        luzes.atenuar();
        tv.ligar();
        som.ligar();
    }

    public void ouvirMusica() {
        System.out.println("\nPreparando Sistema sonoro");
        som.ligar();
    }
}