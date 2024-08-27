package com.example.testdemo.controller.einvoice;

import com.example.testdemo.model.common.RestResult;
import com.example.testdemo.model.dto.EinvoiceMessage;
import com.example.testdemo.service.einvoice.Impl.EinvoiceMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/einvoice")
public class EinvoiceController {
    @Autowired
   EinvoiceMessageService einvoiceMessageService;
  @GetMapping("/uploadOutpatienEinvoice")
   public RestResult uploadOutpatienEinvoice(@RequestParam("cashId") String cashId){
      EinvoiceMessage einvoiceMessage = einvoiceMessageService.getEinvoiceMessage(1);
      System.out.println(einvoiceMessage.toString());
      return null;
   }
}
