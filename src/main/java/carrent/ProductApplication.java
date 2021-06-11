package carrent;
import carrent.config.kafka.KafkaProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@EnableBinding(KafkaProcessor.class)
@EnableFeignClients
public class ProductApplication {
    protected static ApplicationContext applicationContext;
    public static void main(String[] args) {
        applicationContext = SpringApplication.run(ProductApplication.class, args);

        /*
        ProductRepository productRepository = applicationContext.getBean(ProductRepository.class);
        // 초기 상품 셋팅
        String[] products = {"Tesla", "IONIQ"};
        int i = 1;
        for(String p : products){
            Product product = new Product();

            product.setName(p);
            product.setStock(i*2);
            product.setProductId(product.getProductId());
            //product.setProductId(product.getId());

            i++;
            productRepository.save(product);
        }
        */
        
    }
}
