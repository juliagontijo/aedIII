//package TRABALHOPRATICO;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Scanner;

 
public class Aplication{
    public static void main(String[] args){

        public static int pGlobal;
        public static int nEntradasBucket;

        //System.out.print("--MENU DE OPÇÕES--\n1 - Criar arquivo\n2 - Inserir registro\n3 - Editar registro\n4 - Remover registro\n5 - Imprimir arquivos\n6 - Simulação\n\nOPÇÃO: ");
        //int opção = in.nextInt();
        Scanner ler = new Scanner(System.in);

       
        try {
            Diretorio diretorio = new Diretorio();
            ObjectInputStream in = new ObjectInputStream (new FileInputStream("diretorio.dat"));
            
            in.readObject(diretorio);
            
            //System.out.print("Profundidade inicial: ");
            //pGlobal = ler.nextInt();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class NoBucket{ 
    private long cpfKey;
    private long numRegistroArqMestre;

    public NoBucket(){
        this.cpfKey = -1;
        this.numRegistroArqMestre = -1;
    }

    public NoBucket(long CPF, long Num){
        this.cpfKey = CPF;
        this.numRegistroArqMestre = Num;
    }

    public boolean isFull(){
        return (this.cpfKey != -1);
    }

    public void setValue(long CPF, long Num){
        this.cpfKey = CPF;
        this.numRegistroArqMestre = Num;
    }

    public long getCPFValue(){
        return this.cpfKey;
    }

    public long getNumRegistroValue(){
        return this.numRegistroArqMestre;
    }
}

class Bucket implements Serializable{
    private int pLocal;
    private NoBucket[] no;
    private int n;

    public Bucket(){
        this.pLocal = Aplication.pGlobal;
        this.n = Aplication.nEntradasBucket;
        this.no = new NoBucket[n];
        for(int i=0; i<this.n; i++){
            this.no[i] = new NoBucket();
        }
    }
    public Bucket(int profundidade){
        this.pLocal = profundidade;
        this.n = Aplication.nEntradasBucket;
        this.no = new NoBucket[this.n];
        for(int i=0; i<this.n; i++){
            this.no[i] = new NoBucket();
        }
    }
    public Bucket(int profundidade, int nEnt){
        this.pLocal = profundidade;
        this.n = nEnt;
        this.no = new NoBucket[this.n];
        for(int i=0; i<this.n; i++){
            this.no[i] = new NoBucket();
        }
    }

    public boolean isFull(){
        for(int i=0; i<this.n; i++){
            if(!this.no[i].isFull())
                return false;
        }
        return true;
    }

    public boolean addElement(long CPF, long numArqMestre){
        if(this.isFull()){
            return false;
            //chamar função de dividir bucket;
            //chamar função addElement da classe Bucket recursivamente;
        }else{
            for(int i=0; i<this.n; i++){
                if(!this.no[i].isFull()){
                    this.no[i].setValue(CPF, numArqMestre);
                    i=this.n;
                }
            }
            return true;
        }
    }

    public Bucket divideBucket(){
        if(Aplication.pGlobal == Aplication.pLocal){
            //se profundidade for igual a do diretorio, atualizar o diretorio (aumentar ele e mudar a profundidade do mesmo)
        }
        Aplication.pLocal += 1;
        Bucket buck = new Bucket(Aplication.pLocal, this.n); //cria bucket vazio com profundidade correta
        this.reedestribuiCPF(buck);
        return buck;
    }

    public void reedestribuiCPF(Bucket b1){

    }

    public void removeElement(long CPF){ 
        for(int i=0; i<this.n; i++){
            if(CPF == this.no[i].getCPFValue()){
                this.no[i].setValue(-1, -1);
            }
        }
    }


}

class Indice implements Serializable{
    private Bucket[] bucks;

}

class NoDiretorio{
    public int id;
    public Bucket apontaBucket;
    public NoDiretorio proxDirect;

    public NoDiretorio(){
        this.id = -1;
        this.apontaBucket = null;
        this.proxDirect = null;
    }

    public NoDiretorio(int i, Bucket buck){
        this.id = i;
        this.apontaBucket = buck;
        this.proxDirect = null;
    }

}

class Diretorio implements Serializable{
    private int p; //profundidade
    private List <NoDiretorio> listaDeNos;

    public Diretorio(){
        this.p = Aplication.pGlobal;
        this.listaDeNos = new List();
    }

    public Diretorio(int prof){
        this.p = prof;
        this.listaDeNos = new List();
    }

    public Diretorio(int prof, Bucket buck){
        this.p = prof;
        this.listaDeNos = new List();
        this.listaDeNos.apontaBucket = buck;
    }

    public Diretorio(int prof, Bucket buck, NoDiretorio prox){
        this.p = prof;
        this.listaDeNos = new List();
        this.listaDeNos.apontaBucket = buck;
        this.listaDeNos.proxDirect = prox;
    }

    public void setDiretorioProfundidade(int profundidade){
        this.p = profundidade;
    }

    public int getDiretorioProfundidade(){
        return this.p;
    }

    public int hashingKey(int keyCPF){
        return (keyCPF % this.p);
    }

    public void extendDiretorio(){
        this.setDiretorioProfundidade(this.p+1);
        int qtdAumenta = (pow(2, this.p))/2;
        for(int i=0; i<qtdAumenta; i++){

        }

    }

}

class Prontuario implements Serializable{
    private String nome;
    private Data dataNascimento;
    private String sexo;
    private String observacoes;

    Prontuario(){}
    
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public Data getDataNascimento() {
        return dataNascimento;
    }
    public void setDataNascimento(Data dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
    public String getSexo() {
        return sexo;
    }
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
    public String getObservacoes() {
        return observacoes;
    }
    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

}

class Data{
    int dia;
    int mes;
    int ano;

    Data(){}

    public int getDia() {
        return dia;
    }
    public void setDia(int dia) {
        this.dia = dia;
    }
    public int getMes() {
        return mes;
    }
    public void setMes(int mes) {
        this.mes = mes;
    }
    public int getAno() {
        return ano;
    }
    public void setAno(int ano) {
        this.ano = ano;
    }
}
