package org.zerock.b4.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.b4.dto.ProductRegisterDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/product/")
public class ProductController {

  @GetMapping("register")
  public void regsiter() {
    log.info("get product register");
  }

  @PostMapping("register")
  public String registerPost(ProductRegisterDTO registerDTO) {

    log.info("==========================================");
    log.info(registerDTO);

    return "redirect:/product/list";
  }
  
}
