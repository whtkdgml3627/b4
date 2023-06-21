package org.zerock.b4.service;

import org.springframework.transaction.annotation.Transactional;
import org.zerock.b4.dto.PageRequestDTO;
import org.zerock.b4.dto.PageResponseDTO;
import org.zerock.b4.dto.ProductListDTO;
import org.zerock.b4.dto.ProductRegisterDTO;

@Transactional
public interface ProductService {
  
  //등록
  Integer register(ProductRegisterDTO registerDTO);

  //리스트
  PageResponseDTO<ProductListDTO> list(PageRequestDTO pageRequestDTO);

}
