package org.zerock.b4.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.b4.dto.PageRequestDTO;
import org.zerock.b4.dto.ProductRegisterDTO;
import org.zerock.b4.service.ProductService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/product/")
public class ProductController {

  private final ProductService productService;

  @GetMapping("list")
  public void list(PageRequestDTO pageRequestDTO, Model model) {
    model.addAttribute("res", productService.list(pageRequestDTO));
  }

  @GetMapping("register")
  public void regsiter() {
    log.info("get product register");
  }

  @PostMapping("register")
  public String registerPost(ProductRegisterDTO registerDTO, RedirectAttributes rttr) {

    log.info("==========================================");
    log.info(registerDTO);

    Integer pno = productService.register(registerDTO);
    log.info("NEW PNO: " + pno);

    //flash가 없으면 쿼리스트링으로 따라 다닌다.
    rttr.addFlashAttribute("result", pno);

    return "redirect:/product/list";
  }
  
}
