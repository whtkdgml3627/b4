package org.zerock.b4.mappers;

import java.util.List;

import org.zerock.b4.dto.PageRequestDTO;
import org.zerock.b4.dto.ProductListDTO;

public interface ProductMapper {
  
  List<ProductListDTO> getList(PageRequestDTO pageRequestDTO);

}
