package com.example.springbootjdbc.controller;


import com.example.springbootjdbc.data.Message;
import com.example.springbootjdbc.service.ContactService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Slf4j
@Controller
public class MessageController {
    @Autowired
    private ContactService service;
    @Autowired
    private ContactService contactService;


    @RequestMapping("/message")
    public String displayMessage(){
        return "message.html";
    }
    @PostMapping("/saveMessage")
    public String displayHome(@Valid Message message, Errors error){
        if(error.hasErrors()){
            log.error("contact form failed due to : "+error.toString());
            return "message.html";
        }
        service.saveMessage(message);
        return "redirect:/message";
    }

    @RequestMapping("/displayMessages")
    public ModelAndView displayMessages(){
        List<Message> contactList = service.findMessageWithOpenStatus();
        ModelAndView view = new ModelAndView("displayMessages.html");// when the user is invoking the /displayMessages view the displayMessages.html page.
        view.addObject("contactList", contactList);
        return view;
    }
//    use when the link is static
    @RequestMapping("/closeMessage/{id}")
    public String closeMessageAction(@PathVariable(name = "id") int id, Authentication authentication){
        System.out.println(id);
        boolean res = contactService.changeTicketStatus(id,authentication.getName());
        System.out.println(res ? "success" : "fail");
        return  "redirect:/displayMessages";
    }

//    use when the link is dynamic
//    @RequestMapping("/closeMessage")
//    public String closeMessageAction(@RequestParam(name = "id") int id, Authentication authentication){
//        System.out.println(id);
//        boolean res = contactService.changeTicketStatus(id,authentication.getName());
//        System.out.println(res ? "success" : "fail");
//        return  "redirect:/displayMessages";
//    }
}
