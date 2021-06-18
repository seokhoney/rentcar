package carrent;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;
import java.util.Date;

@Entity
@Table(name="Booking_table")
public class Booking {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Integer qty;
    private String status;
    private String startDate;
    private String endDate;
    private Long productId;

    @PostPersist
    public void onPostPersist(){

            boolean rslt = BookingApplication.applicationContext.getBean(carrent.external.ProductService.class)
            .modifyStock(this.getProductId(), this.getQty());

            if (rslt) {
                //this.setStatus("Booked");
                this.setStatus(System.getenv("STATUS"));

                Booked booked = new Booked();
                BeanUtils.copyProperties(this, booked);
                booked.publishAfterCommit();                
            } else {throw new BookingException("No Available stock!");}

    }

    @PreRemove
    public void onPreRemove(){
        carrent.external.Cancellation cancellation = new carrent.external.Cancellation();
        // mappings goes here
        cancellation.setBookingId(this.getId());

        this.setStatus("Cancelled");
        BookingCancelled bookingCancelled = new BookingCancelled();
        BeanUtils.copyProperties(this, bookingCancelled);
        bookingCancelled.publishAfterCommit();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    
    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }




}
