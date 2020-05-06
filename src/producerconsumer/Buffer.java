
package producerconsumer;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Buffer {
    
     private ArrayList<Character> buffer;
     private int numberProducers;
     private int leftToConsume;
     private int bufferSize;
    
    Buffer(int bufferSize) {
        this.buffer = new ArrayList<>(bufferSize);
        this.bufferSize = bufferSize;
        this.numberProducers = numberProducers;
        this.leftToConsume = 0;
    }
    
    synchronized char consume() {
        char product = 0;
        
        // no hay nada que se pueda consumir, asi que se espera un segundo.
        while(this.leftToConsume == 0) {
            try {
                wait(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //Se manda el producto de la posición actual
        product = this.buffer.get(0);
        this.buffer.remove(0);
        //Se reduce el número de elementos que s epueden consumir ya que fue enviado a consumir
        this.leftToConsume -= 1;
        notify();
        return product;
    }
    
    synchronized void produce(char product) {
        
        //ya no hay espacio disponible para agregar elementos al buffer, esperar a que se consuma algun espacio.
        while(this.leftToConsume == this.bufferSize) {
            try {
                wait(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        //Hay un elemento más el cual se puede consumir
        this.leftToConsume += 1;
        //Agrega un elemento en el buffer en la posición en siguiente.
        this.buffer.add(product);
        notify();
    }
    
    static int count = 1;
    synchronized static void print(String string) {
        System.out.print(count++ + " ");
        System.out.println(string);
    }
    
}
