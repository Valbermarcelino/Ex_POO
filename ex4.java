interface Observer {
    void update(double temp, double ph, double pa, double ura);
}

class SensoresAmazonia {
    private Observer[] instituicoes = new Observer[4];
    private int contador = 0;

    public void registrar(Observer o) {
        if (contador < instituicoes.length) {
            instituicoes[contador] = o;
            contador++;
        }
    }

    public void atualizarSensores(double temp, double ph, double pa, double ura) {
        System.out.println("\n[Amazônia] Novos dados captados! Enviando para a rede...");
        for (int i = 0; i < contador; i++) {
            instituicoes[i].update(temp, ph, pa, ura);
        }
    }
}

class Instituicao implements Observer {
    private String nome;

    public Instituicao(String nome) { 
        this.nome = nome; 
    }

    public void update(double temp, double ph, double pa, double ura) {
        System.out.println(nome + " -> Temp: " + temp + " | pH: " + ph + " | PA: " + pa + " | URA: " + ura + "%");
    }
}

class Teste {
    public static void main(String[] args) {
        SensoresAmazonia sensores = new SensoresAmazonia();

        sensores.registrar(new Instituicao("PoA"));
        sensores.registrar(new Instituicao("SP"));
        sensores.registrar(new Instituicao("RJ"));
        sensores.registrar(new Instituicao("Vale do Paraíba"));

        sensores.atualizarSensores(30.2, 6.5, 1010.5, 88.0);
    }
}
