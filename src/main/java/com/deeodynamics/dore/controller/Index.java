package com.deeodynamics.dore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Index {
	@RequestMapping("index")
  public String defaultPage() {
    return "retroIndex";
  }
}
