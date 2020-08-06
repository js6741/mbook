package playground;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;

@Entity
@Table(name="Book_table")
public class Book {
 
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String userName;
    private String date;
    private Long groundId;
/*    private String groundType;
    private Integer maxPlayer;*/
    private String status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    public Long getGroundId() {
        return groundId;
    }

    public void setGroundId(Long groundId) {
        this.groundId = groundId;
    }
/*    public String getGroundType() {
        return groundType;
    }

    public void setGroundType(String groundType) {
        this.groundType = groundType;
    }
    public Integer getMaxPlayer() {
        return maxPlayer;
    }

    public void setMaxPlayer(Integer maxPlayer) {
        this.maxPlayer = maxPlayer;
    }*/
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @PostPersist
    public void onPostPersist(){
        if(this.getStatus().equals("Booked")) {
            Booked booked = new Booked();
            BeanUtils.copyProperties(this, booked);
            booked.publishAfterCommit();
        }

        else if(this.getStatus().equals("Available")) {

            BookCanceled bookCanceled = new BookCanceled();
            BeanUtils.copyProperties(this, bookCanceled);
            bookCanceled.publishAfterCommit();
        }

        else if(this.getStatus().equals("Cancelled")) {


        }
    }
    @PostUpdate
    public void onPostUpdate() {
        BookCanceled bookCanceled = new BookCanceled();
        BeanUtils.copyProperties(this, bookCanceled);
        bookCanceled.publishAfterCommit();
    }
}
