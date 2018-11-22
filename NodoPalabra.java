
package proyecto2;


public class NodoPalabra {
    private NodoPalabra raiz;
    String palabra;
    int fe=0,datos=0;
    NodoPalabra nodoDer;
    NodoPalabra nodoIzq;
    
    public NodoPalabra(String palabra){
        raiz=null;
        this.datos = 0;
        this.palabra = palabra;
        this.nodoDer=null;
        this.nodoIzq=null;
        this.fe=0;
    }
    
    //Buscar el factor de equilibrio
    public int obtenerFe(NodoPalabra nodo){
        if(nodo==null){
            return -1;
        }else{
            return nodo.fe;
        }
    }
    
    //Rotacion simple izquierda
    public NodoPalabra rotacionIzquierda(NodoPalabra nodo){
        NodoPalabra auxiliar = nodo.nodoIzq;
        nodo.nodoIzq = auxiliar.nodoDer;
        auxiliar.nodoDer=nodo;
        nodo.fe=Math.max(obtenerFe(nodo.nodoIzq), obtenerFe(nodo.nodoDer))+1;
        auxiliar.fe = Math.max(obtenerFe(auxiliar.nodoIzq),obtenerFe(auxiliar.nodoDer))+1;
        
        return auxiliar;
    }
    
    //Rotacion simple derecha
    public NodoPalabra rotacionDerecha(NodoPalabra nodo){
        NodoPalabra auxiliar = nodo.nodoDer;
        nodo.nodoDer = auxiliar.nodoIzq;
        auxiliar.nodoIzq=nodo;
        nodo.fe=Math.max(obtenerFe(nodo.nodoIzq), obtenerFe(nodo.nodoDer))+1;
        auxiliar.fe = Math.max(obtenerFe(auxiliar.nodoIzq),obtenerFe(auxiliar.nodoDer))+1;
        
        return auxiliar;
    }
    
       //Rotacion Doble a la derecha
    public NodoPalabra rotacionDobleIzquierda(NodoPalabra nodo){
        NodoPalabra temporal;
        nodo.nodoIzq=rotacionDerecha(nodo.nodoIzq);
        temporal=rotacionIzquierda(nodo);
        
        return temporal;
    }
    
    //Rotacion Doble a la Izquierda
    public NodoPalabra rotacionDobleDerecha(NodoPalabra nodo){
        NodoPalabra temporal;
        nodo.nodoDer=rotacionIzquierda(nodo.nodoDer);
        temporal=rotacionDerecha(nodo);
        
        return temporal;
    }
    // Retorna el char de una posicion en especifico.
    public int retornarCharDeUnString(String pPalabra, int pPosicion){
        int assciiValuewordToInsert= pPalabra.charAt(pPosicion);
        return assciiValuewordToInsert;
    }
   //Metodo para Insertar AVL
    public NodoPalabra insertarAVL(NodoPalabra nuevo, NodoPalabra subAr){
        //int assciiValuewordToInsert= valorInsertar.charAt(charPositionToEvaluate);
        int charPositionToEvaluate =0;
        NodoPalabra nuevoPadre = subAr;
        if(nuevo.datos<subAr.datos){
            if(subAr.nodoIzq==null){
                subAr.nodoIzq=nuevo;
            }else{
                subAr.nodoIzq=insertarAVL(nuevo,subAr.nodoIzq);
                if((obtenerFe(subAr.nodoIzq)-obtenerFe(subAr.nodoDer)==2)){
                    //if(nuevo.datos<subAr.nodoIzq.datos){
                    if(retornarCharDeUnString(nuevo.palabra,charPositionToEvaluate)<retornarCharDeUnString(subAr.palabra,charPositionToEvaluate)){
                        nuevoPadre=rotacionIzquierda(subAr);
                    }else{
                        nuevoPadre = rotacionDobleIzquierda(subAr);
                    }
                }
            }
        }else if(retornarCharDeUnString(nuevo.palabra,charPositionToEvaluate)>retornarCharDeUnString(subAr.palabra,charPositionToEvaluate)){
            if(subAr.nodoDer==null){
                subAr.nodoDer=nuevo;
            }else{
                subAr.nodoDer=insertarAVL(nuevo,subAr.nodoDer);
                if((obtenerFe(subAr.nodoDer)-obtenerFe(subAr.nodoIzq)==2)){
                    if(retornarCharDeUnString(nuevo.palabra,charPositionToEvaluate)>retornarCharDeUnString(subAr.palabra,charPositionToEvaluate)){
                        nuevoPadre = rotacionDerecha(subAr);
                    }else{
                        nuevoPadre = rotacionDobleDerecha(subAr);
                    }
                }
            }
        }else{
            System.out.println("Palabra Duplicada");
        }
        //ACtualizando la altura
        if((subAr.nodoIzq==null) && (subAr.nodoDer!=null)){
            subAr.fe=subAr.nodoDer.fe+1;
        }else if((subAr.nodoDer==null)&& (subAr.nodoIzq!=null)){
            subAr.fe=subAr.nodoIzq.fe+1;
        }else{
            subAr.fe=Math.max(obtenerFe(subAr.nodoIzq), obtenerFe(subAr.nodoDer))+1;
        }
        return nuevoPadre;
    }
    public NodoPalabra obtenerRaiz(){
        return raiz;
    }
    //Metodo para insertar
    public void insertar(String value){
        NodoPalabra nuevo = new NodoPalabra(value);
        if(raiz==null){
            raiz=nuevo;
        }else{
            raiz=insertarAVL(nuevo, raiz);
        }
    }
    
    //Arbol in order
    public void inOrden(NodoPalabra nodo){
        if(nodo!=null){
            inOrden(nodo.nodoIzq);
            System.out.print(nodo.datos+", ");
            inOrden(nodo.nodoIzq);
        }
    }
    // Arbol preorden
    public void preOrden(NodoPalabra nodo){
        if(nodo!=null){
            System.out.print(nodo.datos+", ");
            preOrden(nodo.nodoIzq);
            preOrden(nodo.nodoDer);
        }
    }
    //Arbol postOrden
    public void postOrden(NodoPalabra nodo){
        if(nodo!=null){
            
            postOrden(nodo.nodoIzq);
            postOrden(nodo.nodoDer);
            System.out.print(nodo.datos+", ");
            
        }
    }
}
