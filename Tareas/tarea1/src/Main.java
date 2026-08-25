public class Main{
    static class Nodo{
        int numero;
        Nodo[] siguiente;
        boolean visitado;

        Nodo(int numero){
            this.numero = numero;
            this.siguiente = new Nodo[3];
            this.visitado = false;
        }

        @Override
        public String toString(){
            return "Nodo(" + numero + ")";
        }
    }

    static void reiniciarVisitado(Nodo nodo) {
        if (nodo == null || !nodo.visitado) return;
        Nodo[] apilar = new Nodo[50];
        int arriba = 0;
        apilar[arriba] = nodo;
        while (arriba >= 0) {
            Nodo actual = apilar[arriba--];
            if (actual == null) continue;
            actual.visitado = false;
            for (int i = 0; i < actual.siguiente.length; i++) {
                Nodo hijo = actual.siguiente[i];
                if (hijo != null && hijo.visitado) {
                    arriba++;
                    apilar[arriba] = hijo;
                }
            }
        }
    }

    static Nodo encontrarNodoDFS(Nodo nodo, int objetivo){
        if(nodo == null || nodo.visitado) return null;
        nodo.visitado = true;
        if(nodo.numero == objetivo) return nodo;
        for(int i=0; i<nodo.siguiente.length; i++){
            Nodo sig = nodo.siguiente[i];
            Nodo encontrado = encontrarNodoDFS(sig, objetivo);
            if(encontrado != null) return encontrado;
        }
        return null;
    }

    public static void main(String[] args){
        Nodo n20 = new Nodo(20);
        Nodo n23 = new Nodo(23);
        Nodo n19 = new Nodo(19);
        Nodo n67 = new Nodo(67);
        Nodo n57 = new Nodo(57);
        Nodo n99 = new Nodo(99);
        Nodo head = n20;

        n20.siguiente[0] = n23;
        n20.siguiente[1] = n19;
        n20.siguiente[2] = null;
        n19.siguiente[0] = null;
        n19.siguiente[1] = null;
        n19.siguiente[2] = n67;
        n23.siguiente[0] = null;
        n23.siguiente[1] = n57;
        n23.siguiente[2] = null;
        n67.siguiente[1] = n99;

        reiniciarVisitado(head);
        Nodo encontrar99 = encontrarNodoDFS(head, 99);
        if(encontrar99 != null) {
            System.out.println("Nodo encontrado con valor 99: " + encontrar99 + " (valor = " + encontrar99.numero + ")");
        }

        reiniciarVisitado(head);
        Nodo encontrar57 = encontrarNodoDFS(head, 57);
        if(encontrar57 != null) {
            System.out.println("Nodo encontrado con valor 57: " + encontrar57 + " (valor = " + encontrar57.numero + ")");
        }
    }
}

