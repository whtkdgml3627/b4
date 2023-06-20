package org.zerock.b4.mappers;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.b4.dto.PageRequestDTO;
import org.zerock.b4.dto.ProductListDTO;
import org.zerock.b4.dto.ProductRegisterDTO;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class ProductMapperTests {
  
  @Autowired(required = false)
  private ProductMapper productMapper;

  @Test
  public void testGetList() {

    PageRequestDTO pageRequestDTO = PageRequestDTO.builder().build();
    List<ProductListDTO> result = productMapper.getList(pageRequestDTO);
    log.info("======================");
    log.info(result);
  }
  
  @Transactional
  @Test
  @Commit
  public void testRegister(){

    ProductRegisterDTO dto = new ProductRegisterDTO();
    dto.setPname("테스트상품");
    dto.setPrice(5000);
    dto.setStatus(true);
    dto.setFileNames(List.of("aaaaa_f1.jpg","bbbbb_f2.jpg"));

    List<String> fileNames = dto.getFileNames();



    int count = productMapper.insertProduct(dto);

    log.info("insert product count: " + count);

    Integer pno = dto.getPno();

    log.info("-----------------------------" + pno);

    AtomicInteger index = new AtomicInteger();

    List<Map<String,String>> list = fileNames.stream().map(str -> {
      String uuid = str.substring(0, 5);
      String fileName = str.substring(6);

      return Map.of("uuid", uuid, "fileName", fileName,"pno", ""+pno, "ord", "" + index.getAndIncrement());

    }).collect(Collectors.toList());

    log.info(list);

    int countImages = productMapper.insertImages(list);

    log.info("=====================" + countImages);
    

  }

}
