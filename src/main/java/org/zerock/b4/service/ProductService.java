package org.zerock.b4.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.zerock.b4.dto.PageRequestDTO;
import org.zerock.b4.dto.PageResponseDTO;
import org.zerock.b4.dto.ProductDTO;
import org.zerock.b4.dto.ProductListDTO;
import org.zerock.b4.dto.ProductRegisterDTO;

@Transactional
public interface ProductService {
  
  //등록
  Integer register(ProductRegisterDTO registerDTO);

  //리스트
  PageResponseDTO<ProductListDTO> list(PageRequestDTO pageRequestDTO);

  //수정
  ProductDTO get(Integer pno);

  List<String> getImage(Integer pno);
  //void modify();
  //상품 데이터 수정
  //기존 첨부파일 DB에서 삭제
  //DTO에 있는 첨부파일 DB에 추가

}
