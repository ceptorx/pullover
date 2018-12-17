package io.nosphere.controller;

import io.nosphere.domain.Message;
import io.nosphere.domain.User;
import io.nosphere.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class MainController {

    @Autowired
    private MessageRepository repository;

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "index";
    }

    @GetMapping("main")
    public String main(@RequestParam(required = false, defaultValue = "") String filter, Model model) {
        Iterable<Message> messages;

        if(filter != null && !filter.isEmpty()){
            messages = repository.findByTag(filter);
        } else {
            messages = repository.findAll();
        }

        model.addAttribute("messages", messages);
        model.addAttribute("filter", filter);
        return "main";
    }

    @PostMapping("main")
    public String add(@RequestParam String text,
                      @RequestParam String tag,
                      @AuthenticationPrincipal User user,
                      Map<String, Object> model) {

        Message message = new Message(text,tag, user);
        repository.save(message);
        model.put("messages", repository.findAll());
        return "main";
    }
}
