
package producerconsumer;

public class ProducerConsumer {

    public static void main(String[] args) {
        
        
        
       
        
       

        
    }

    public ProducerConsumer(int numberProducers, int numberConsumers, int bufferSize) {
        
        Buffer buffer = new Buffer(bufferSize);
        
        for (int i = 0; i < numberProducers; i++) {
            Producer producer = new Producer(buffer,i);
            producer.start();
        }
        
        for (int i = 0; i < numberProducers; i++) {
            Consumer consumer = new Consumer(buffer,i);
            consumer.start();
        }
    }
    
}
