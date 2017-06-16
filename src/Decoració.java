import java.util.Arrays;

public class Decoració {
    private Quadre totsQuadres[]; //magatzem quadres
    private int qQuadres; //dimensió real taula anterior
    private Parell millor[];
    private Parell solucio[];

    // afegim atributs
    private float paret; //dada entrada per l’usuari
    private int prestigiSolucio; //acumulador prestigi solució en construcció
    private float espaiOcupat; //acc paret usada
    private int prestigiMillor; //acumulador prestigi millor solució
    private int quantsMillor; //quants quadres té la millor solució
    private float espaiOcupatMillor; //paret ocupada ren la millor solució

    private class Parell {
        private int identificador;
        private boolean llargaria; //veure més endavant aclariment *

        public Parell(int identificador, boolean llargaria) {
            this.identificador = identificador;
            this.llargaria = llargaria;
        }

        public void canviarOrientacio() {
            llargaria = !llargaria;
        }

        public float getPreu() {
            return totsQuadres[identificador].getPreu();
        }

        @Override
        public String toString() {
            return "C"+ identificador;
        }
    }

    public static void TotesDades(Quadre quadres[], int quantsQ) {
        /*es determinen i creen tots els Objectes Quadre que la Maria ha seleccionat en primera opció.
        Aquesta informació queda emmagatzemada en el paràmetre quadres.
        El segon paràmetre indica el nombre de quadres d’on estriar*/

        quadres[0] = new Quadre(1, "Miro", 5,3,4000, 500);
        if (quantsQ==1) return;
        quadres[1] = new Quadre(2, "", 3,4,1000, 20);
        if (quantsQ==2) return;
        quadres[2] = new Quadre(3, "", 5,3,1000, 50);
        if (quantsQ==3) return;
        quadres[3] = new Quadre(4, "", 5,3,1000, 50);
        if (quantsQ==4) return;
        quadres[4] = new Quadre(5, "", 5,3,1000, 50);
        if (quantsQ==5) return;
        quadres[5] = new Quadre(6, "", 5,3,1000, 50);
        if (quantsQ==6) return;
        quadres[6] = new Quadre(7, "", 5,3,1000, 50);
        if (quantsQ==7) return;
        quadres[7] = new Quadre(8, "", 5,3,1000, 50);
        if (quantsQ==8) return;

    }

    public Decoració(int quantsQuadres, float paret) {
        /* Exercici 2*/
        //creació i inicialització de magatzems

        this.paret=paret;           //dada entrada per l’usuari

        this.qQuadres = quantsQuadres;
        totsQuadres=new Quadre[qQuadres];
        TotesDades(totsQuadres, qQuadres); //omplenem dades

        //dupliquem els quadres
/**/
        Quadre aux[]=new Quadre[qQuadres*2];
        for (int i=0; i<qQuadres; i++){
            aux[i]=totsQuadres[i];
            aux[qQuadres+i]= totsQuadres[i];
        }
        totsQuadres=aux;
        ///////////////////////////////////////////////////
        solucio = new Parell[qQuadres];
        millor  = new Parell[qQuadres];

        prestigiSolucio = 0;    // acumulador prestigi solució en construcció
        prestigiMillor = -1;    // acumulador prestigi millor solució
        espaiOcupat =0;         // acc paret usada
        espaiOcupatMillor =0;   // paret ocupada ren la millor solució
        quantsMillor=0;         // quants quadres té la millor solució

    } // fi constructor

    public String toString() {
        /* Exercici 3*/
        /*sentències que generen una cadena amb la millor solució trobada */
        String r="";
        if (prestigiMillor == -1)
            r = "No hi ha cap solució. Cap quadre cap a la paret";
        else {
            float total = 0.0f;
            for (int i=0; i<quantsMillor; i++){
                r+= "Cuadre "+ millor[i].identificador;
                total += millor[i].getPreu();
                if (millor[i].llargaria)
                    r+= " en llargaria\n";
                else
                    r+= " en alçada\n";
            }
            r += "\nEl cost és de "+ total;
            r += "\nEl prestigi total és de: "+ prestigiMillor;
        }
        return r;
    }

    public void backtracking( int k) {
        /*Exercici 5*/
        // Esquema Millor
        int i=0;
        while (i < qQuadres*2){

            if (pucAfegir(i)){

                boolean girat = (i > qQuadres);

                //sempre és solució

                totsQuadres[i].setUsat(); //marcat
                prestigiSolucio +=totsQuadres[i].getPrestigi();
                if (!girat)
                    espaiOcupat +=totsQuadres[i].getLlargaria();
                else
                    espaiOcupat +=totsQuadres[i].getAlçada();

                solucio[k] = new Parell(i,girat);

                //Millor solució
                if (esMillorSolucio(k)){

                    System.out.println(
                            " k="+ k
                                    +" "+ " i="+ i
                                    +" "+ totsQuadres[i].toString()
                                    +" Girat:"+ girat
                                    +" Ocupat:"+ espaiOcupat
                                    +" Prestigi:"+ prestigiSolucio
                                    +" "+ Arrays.toString(solucio));


                    // copia de la solucio
                    for (int n=0; n<=k; n++)
                        millor[n]=solucio[n];

                    quantsMillor=k;
                    prestigiMillor = prestigiSolucio;
                    espaiOcupatMillor = espaiOcupat;


                } // fi millor

                if (esCompletable(k))
                    backtracking(k+1);

                //desfer
                if (!girat)
                    espaiOcupat -=totsQuadres[i].getLlargaria();
                else
                    espaiOcupat -=totsQuadres[i].getAlçada();

                prestigiSolucio -=totsQuadres[i].getPrestigi();
                totsQuadres[i].setUsat(); //desmarcat
                solucio[k] = null;

            } // fi acceptable

            i++; //següent
        } //fi while
    } // fi procediment

    private boolean pucAfegir(int i) {

        return !totsQuadres[i].getUsat() &&
                ( i < qQuadres && espaiOcupat + totsQuadres[i].getLlargaria()< paret
                        ||
                        i >= qQuadres&& espaiOcupat +totsQuadres[i-qQuadres].getAmplada()< paret);
    }

    private boolean esCompletable(int k) {
        // Un conjunt de decisions serà completable si queden quadres i hi ha espai en la paret
        return  (k < qQuadres && espaiOcupat < paret);
    }


    private boolean esMillorSolucio(int k) {
        //Una solució serà millor que una altre si acumula més prestigi.
        // A igual prestigi la que empleni més la paret i a igualtat, la que tingui més quadres.
        if(prestigiSolucio > prestigiMillor) return true;                                                           // quant més prestigi millor
        if(prestigiSolucio == prestigiMillor && espaiOcupat > espaiOcupatMillor) return true;                       // quant més espai ocupat millor
        if(prestigiSolucio == prestigiMillor && espaiOcupat == espaiOcupatMillor && k>quantsMillor) return true;    // quant més quadres millor
        return false;
    }
}
