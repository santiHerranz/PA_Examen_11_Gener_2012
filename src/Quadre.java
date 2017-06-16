public class Quadre {

    private int identificador; //identificació quadre
    private float preu; //preu del quadre
    private float llargaria; //dimensió més gran
    private float alçada; //dimensió més petita
    private String pintor; //autor del quadre
    private int prestigi; // [0,5] el 5 indica màxim prestigi


    private boolean usat=false;
    public boolean getUsat(){return usat;}
    public void setUsat(){ usat=!usat;}

    public Quadre(int identificador,String nom, float llargaria, float alçada, float preu, int prestigi){
        this.pintor=nom;
        this.identificador=identificador;
        this.llargaria=llargaria;
        this.alçada=alçada;
        this.preu=preu;
        this.prestigi=prestigi;
    }

    public int getIdentificador(){ return identificador;}
    public int getPrestigi(){ return prestigi;}
    public float getAlçada(){ return alçada;}
    public float getLlargaria(){ return llargaria;}
    public float getPreu(){ return preu;}
    public String getPintor(){return pintor;}

    public float getAmplada() {
        return llargaria;
    }



    public String toString() {
        return "Cuadre "+ identificador +" ("+ llargaria +"x"+ alçada +")" ;
    }


}
