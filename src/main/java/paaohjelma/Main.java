package paaohjelma;

import ohtu.ohtuvarasto.Varasto;

public class Main {

    public static void main(String[] args) {
        
        Varasto mehua = new Varasto(100.0);
        Varasto olutta = new Varasto(100.0, 20.2);
        mehua.lisaaVarastoon(50);
        olutta.otaVarastosta(10);
        for (int i = 0; i<10; i++) {
            for (int j = 0; j<10; j++) {
                mehua.otaVarastosta(1);
            }
        }
    }
    
    
}
