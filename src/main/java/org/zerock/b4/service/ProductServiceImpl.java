package org.zerock.b4.service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.zerock.b4.dto.PageRequestDTO;
import org.zerock.b4.dto.PageResponseDTO;
import org.zerock.b4.dto.ProductListDTO;
import org.zerock.b4.dto.ProductRegisterDTO;
import org.zerock.b4.mappers.ProductMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

  private final ProductMapper productMapper;

  @Override
  public Integer register(ProductRegisterDTO dto) {

    List<String> fileNames = dto.getFileNames();

    int count = productMapper.insertProduct(dto);

    log.info("insert product count: " + count);

    int pno = dto.getPno();

    log.info("-----------------------------" + pno);

    AtomicInteger index = new AtomicInteger();

    List<Map<String,String>> list = fileNames.stream().map(str -> {
      String uuid = str.substring(0, 36);
      String fileName = str.substring(37);

      return Map.of("uuid", uuid, "fileName", fileName,"pno", ""+pno, "ord", "" + index.getAndIncrement());

    }).collect(Collectors.toList());

    log.info(list);

    int countImages = productMapper.insertImages(list);

    log.info("=====================" + countImages);

    return pno;
  }

  @Override
  public PageResponseDTO<ProductListDTO> list(PageRequestDTO pageRequestDTO) {

    List<ProductListDTO> result = productMapper.getList(pageRequestDTO);
    long total = 123L;

    //빌더를 만들어준 이유
    //빌더는 생성자와 다르게 원하는 값만 넣어서 만들어줄 수 있음
    //값을 넣어도 되고 안넣어도 되는 단점이 생김
    //생성자로 사용하지 않기 위해 빌더로 만들어줌
    //생성자의 목적은 이 클래스에서 인스턴스만 찍어내는게 목적이므로 return 타입이 없음
    //자바에서 유일한 함수는 생성자밖에없다
    return PageResponseDTO.<ProductListDTO>withAll()
      .list(result)
      .total(total)
      .build();

  }
  
}
