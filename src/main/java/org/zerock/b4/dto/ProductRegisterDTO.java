package org.zerock.b4.dto;

import java.util.List;

import lombok.Data;

@Data
public class ProductRegisterDTO {
  
  private String pname;
  private String price;
  private boolean status;

  private List<String> fileNames;

}
