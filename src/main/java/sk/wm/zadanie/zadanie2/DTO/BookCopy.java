package sk.wm.zadanie.zadanie2.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//@AllArgsConstructor
@NoArgsConstructor
public class BookCopy {
    private Long id;
    private Boolean available;

     public BookCopy(Long id, Boolean available) {
         this.id = id;
         this.available = available;
     }
}
