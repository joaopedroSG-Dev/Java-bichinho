import java.util.Scanner;

public class Main {

    public static class PetVirtual {
        private String nome;
        private int fome;
        private int felicidade;
        private int cansaco;
        private int idade;
        private int vontadeBanheiro;
        private int sujeira;

        public PetVirtual(String nome) {
            this.nome = nome;
            this.fome = 20;
            this.felicidade = 80;
            this.cansaco = 0;
            this.idade = 0;
            this.vontadeBanheiro = 0;
            this.sujeira = 0;
        }

        public void alimentar() {
            this.fome = Math.max(0, this.fome - 25);
            this.felicidade = Math.min(100, this.felicidade + 5);
            this.vontadeBanheiro = Math.min(100, this.vontadeBanheiro + 20);
            System.out.println("\n🍖 Você alimentou " + nome + "! Fome diminuiu, mas a vontade de ir ao banheiro aumentou.");
        }

        public void brincar() {
            this.felicidade = Math.min(100, this.felicidade + 20);
            this.cansaco = Math.min(100, this.cansaco + 20);
            this.fome = Math.min(100, this.fome + 10);
            this.sujeira = Math.min(100, this.sujeira + 20);
            System.out.println("\n🎾 Você brincou com " + nome + "! Felicidade subiu, mas cansaço e sujeira aumentaram.");
        }

        public void descansar(int horas) {
            if (horas < 0) {
                horas = 0;
            }
            if (horas >= 8) {
                this.cansaco = 0;
                System.out.println("\n😴 " + nome + " dormiu por " + horas + "h e ficou totalmente descansado!");
            } else {
                int reducao = (horas * 100) / 8;
                this.cansaco = Math.max(0, this.cansaco - reducao);
                System.out.println("\n💤 " + nome + " descansou por " + horas + "h. Cansaço atual: " + this.cansaco + "/100.");
            }
        }

        public void irAoBanheiro() {
            this.vontadeBanheiro = 0;
            System.out.println("\n🚽 " + nome + " usou o banheiro e está aliviado!");
        }

        public void tomarBanho() {
            this.sujeira = 0;
            System.out.println("\n🛁 " + nome + " tomou banho e está limpinho!");
        }

        public void verificarStatus() {
            System.out.println("\n================ STATUS DE " + nome.toUpperCase() + " ================");
            System.out.println("Idade:                 " + idade + " / 50");
            System.out.println("Fome:                  " + fome + " / 100");
            System.out.println("Felicidade:            " + felicidade + " / 100");
            System.out.println("Cansaço:               " + cansaco + " / 100");
            System.out.println("Vontade de ir ao banho:" + vontadeBanheiro + " / 100");
            System.out.println("Sujeira:               " + sujeira + " / 100");
            System.out.println("====================================================");
        }

        public void passarTempo() {
            this.fome = Math.min(100, this.fome + 3);
            this.felicidade = Math.max(0, this.felicidade - 3);
            this.cansaco = Math.min(100, this.cansaco + 10);
            this.idade += 1;
            this.vontadeBanheiro = Math.min(100, this.vontadeBanheiro + 5);
            this.sujeira = Math.min(100, this.sujeira + 4);
        }

        public boolean venceu() {
            return this.idade >= 50;
        }

        public String verificarDerrota() {
            if (this.fome >= 100) {
                return "A fome de " + nome + " chegou a 100!";
            }
            if (this.cansaco >= 100) {
                return nome + " ficou exausto (cansaço chegou a 100)!";
            }
            if (this.felicidade <= 0) {
                return "A felicidade de " + nome + " chegou a 0!";
            }
            if (this.vontadeBanheiro >= 100) {
                return nome + " não aguentou segurar a vontade de ir ao banheiro (100)!";
            }
            if (this.sujeira >= 100) {
                return nome + " ficou sujo demais (sujeira chegou a 100)!";
            }
            return null;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("🐾 Bem-vindo ao Simulador de Animal de Estimação Virtual!");
        System.out.print("Digite o nome do seu pet: ");
        String nomePet = scanner.nextLine().trim();
        if (nomePet.isEmpty()) {
            nomePet = "Bichinho";
        }

        PetVirtual pet = new PetVirtual(nomePet);
        System.out.println("Objetivo: leve " + nomePet + " até a idade 50 sem estourar os limites!");

        while (true) {
            pet.verificarStatus();

            System.out.println("\nEscolha uma ação:");
            System.out.println("1 - Alimentar");
            System.out.println("2 - Brincar");
            System.out.println("3 - Descansar");
            System.out.println("4 - Ir ao banheiro");
            System.out.println("5 - Tomar banho");
            System.out.println("6 - Apenas aguardar (passar 1 ciclo)");
            System.out.println("0 - Sair do jogo");
            System.out.print("Opção: ");

            int opcao;
            if (scanner.hasNextInt()) {
                opcao = scanner.nextInt();
            } else {
                System.out.println("⚠️ Digite apenas números.");
                scanner.next();
                continue;
            }

            if (opcao == 0) {
                System.out.println("👋 Até a próxima!");
                break;
            }

            switch (opcao) {
                case 1:
                    pet.alimentar();
                    break;
                case 2:
                    pet.brincar();
                    break;
                case 3:
                    System.out.print("Por quantas horas " + nomePet + " vai descansar? ");
                    if (scanner.hasNextInt()) {
                        int horas = scanner.nextInt();
                        pet.descansar(horas);
                    } else {
                        System.out.println("⚠️ Valor inválido para horas. Descansando 1 hora por padrão.");
                        scanner.next();
                        pet.descansar(1);
                    }
                    break;
                case 4:
                    pet.irAoBanheiro();
                    break;
                case 5:
                    pet.tomarBanho();
                    break;
                case 6:
                    System.out.println("\n⏳ O tempo passou...");
                    break;
                default:
                    System.out.println("⚠️ Opção inválida! O tempo ainda assim avançou.");
                    break;
            }

            // Cada turno avança 1 ciclo de tempo
            pet.passarTempo();

            String motivoDerrota = pet.verificarDerrota();
            if (motivoDerrota != null) {
                pet.verificarStatus();
                System.out.println("\n💀 FIM DE JOGO! " + motivoDerrota);
                break;
            }

            if (pet.venceu()) {
                pet.verificarStatus();
                System.out.println("\n🏆 PARABÉNS! " + nomePet + " alcançou a idade 50 com saúde e alegria!");
                break;
            }
        }

        scanner.close();
    }
}