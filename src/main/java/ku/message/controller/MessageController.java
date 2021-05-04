package ku.message.controller;

import ku.message.dto.MessageDto;
import ku.message.model.Message;
import ku.message.repository.MessageRepository;
import ku.message.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class MessageController {

    @Autowired
    private MessageService messageService;
    @GetMapping("/message")
    public String getMessagePage(Model model) {
        model.addAttribute("messages", messageService.getMessages());
        return "message";
    }

    @GetMapping("/post")
    public String getPostPage() {
        return "post";
    }

    @PostMapping("/message")
    public String postMessage(@ModelAttribute MessageDto messageDto,
                              Model model, Principal principal) {
        String username = principal.getName();
        messageService.createMessage(messageDto, username);
        model.addAttribute("messages", messageService.getMessages());
        return "redirect:message";
    }

}